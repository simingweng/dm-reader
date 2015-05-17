package com.jdsu.drivetest.dmreader;

import com.jdsu.drivetest.dmreader.messages.incoming.IncomingHDLCPacket;
import com.jdsu.drivetest.dmreader.messages.outgoing.control.StartRequest;
import com.jdsu.drivetest.dmreader.messages.outgoing.control.StopRequest;
import jssc.*;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SystemUtils;
import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Test program
 * Created by simon on 5/9/2015.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String[] serialPortNames = null;
        if (SystemUtils.IS_OS_MAC_OSX) {
            serialPortNames = SerialPortList.getPortNames("/dev/", Pattern.compile("tty\\.SAMSUNG"));
        } else if (SystemUtils.IS_OS_WINDOWS) {
            serialPortNames = SerialPortList.getPortNames();
        }
        if (serialPortNames == null) {
            LOG.warning("unsupported OS");
            return;
        }
        LOG.info("List visible serial ports:");
        if (serialPortNames.length == 0) {
            LOG.info("No serial port found, quit.");
            return;
        }

        for (String name : serialPortNames) {
            LOG.info(name);
        }

        Scanner scanner = new Scanner(System.in);
        String comPort;
        while (true) {
            LOG.info("specify the name of the COM port to connect, or type quit to terminate the program: ");
            comPort = scanner.next();
            if (ArrayUtils.contains(serialPortNames, comPort)) {
                break;
            } else if (comPort != null && comPort.equals("quit")) {
                return;
            }
        }

        SerialPort serialPort = new SerialPort(comPort);
        try {
            LOG.info("trying to open " + comPort);
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            serialPort.addEventListener(new SerialPortEventListener() {

                private Codec<IncomingHDLCPacket> codec = Codecs.create(IncomingHDLCPacket.class);
                //the length field of the incoming HDLC packet is a short value, so maximum length is 0xFFFF
                private ByteBuffer buffer = ByteBuffer.allocate(0xFFFF).order(ByteOrder.LITTLE_ENDIAN);

                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
                    if (serialPortEvent.isRXCHAR()) {
                        try {
                            ByteBuffer tempBuffer = ByteBuffer.wrap(serialPort.readBytes()).order(ByteOrder.LITTLE_ENDIAN);
                            while (tempBuffer.hasRemaining()) {
                                if (buffer.position() == 0) {
                                    //we're expecting a new packet from start, looking for the 0x7F delimit
                                    while (tempBuffer.get() != 0x7F) {
                                        //LOG.info("discard a single garbage byte");
                                    }
                                    buffer.put((byte) 0x7F);
                                } else if (buffer.position() < 3) {
                                    //the length field is not complete yet, we copy byte by byte to make things simpler
                                    buffer.put(tempBuffer.get());
                                } else {
                                    //we know the length of the HDLC packet, let's try to complete the packet if possible
                                    int hdlcLength = buffer.getShort(1) & 0xFFFF;
                                    int numOfBytesToComplete = hdlcLength + 2 - buffer.position();
                                    if (tempBuffer.remaining() < numOfBytesToComplete) {
                                        //still not enough even we consume everything left in the temp buffer
                                        byte[] bytes = new byte[tempBuffer.remaining()];
                                        tempBuffer.get(bytes);
                                        buffer.put(bytes);
                                    } else {
                                        //sufficient bytes to complete this packet
                                        byte[] bytes = new byte[numOfBytesToComplete];
                                        tempBuffer.get(bytes);
                                        buffer.put(bytes);
                                        buffer.flip();
                                        byte[] wholePacket = new byte[buffer.limit()];
                                        buffer.get(wholePacket);
                                        LOG.info("received raw packet: " + Hex.encodeHexString(wholePacket));
                                        IncomingHDLCPacket packet = Codecs.decode(codec, wholePacket);
                                        LOG.info("decoded packet: " + packet.toString());
                                        buffer.clear();
                                    }
                                }
                            }
                        } catch (SerialPortException | DecodingException e) {
                            LOG.log(Level.SEVERE, e.toString(), e);
                        }
                    }
                }

            });

            //send DM Start Request
            StartRequest startRequest = new StartRequest((short) 0, System.currentTimeMillis(), new byte[]{'J' & 0xFF, 'D' & 0xFF, 'S' & 0xFF, 'U' & 0xFF});
            Codec<StartRequest> startReqCodec = Codecs.create(StartRequest.class);
            byte[] startReqInBytes = Codecs.encode(startRequest, startReqCodec);
            LOG.info("send DM Start Request: " + Hex.encodeHexString(startReqInBytes));
            serialPort.writeBytes(startReqInBytes);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    //send DM Stop Request
                    StopRequest stopRequest = new StopRequest((short) 1, System.currentTimeMillis());
                    Codec<StopRequest> stopReqCodec = Codecs.create(StopRequest.class);
                    byte[] stopReqInBytes = Codecs.encode(stopRequest, stopReqCodec);
                    LOG.info("send DM Stop request: " + Hex.encodeHexString(stopReqInBytes));
                    serialPort.writeBytes(stopReqInBytes);
                    serialPort.removeEventListener();
                    serialPort.closePort();
                } catch (SerialPortException | IOException e) {
                    LOG.log(Level.SEVERE, e.toString(), e);
                }
            }));
        } catch (SerialPortException | IOException e) {
            LOG.log(Level.SEVERE, e.toString(), e);
        }
    }
}
