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

                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
                    if (serialPortEvent.isRXCHAR()) {
                        try {
                            //TODO extract packets in bytes by the deliminators
                            byte[] bytes = serialPort.readBytes(serialPortEvent.getEventValue());
                            ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
                            LOG.info("received packet: " + Hex.encodeHexString(bytes));
                            //IncomingHDLCPacket packet = Codecs.decode(codec, buffer);
                        } catch (SerialPortException e) {
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
