package com.jdsu.drivetest.dmreader;

import com.jdsu.drivetest.dmreader.messages.incoming.IncomingHDLCPacket;
import com.jdsu.drivetest.dmreader.messages.outgoing.control.StartRequest;
import com.jdsu.drivetest.dmreader.messages.outgoing.control.StopRequest;
import jssc.*;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test program
 * Created by simon on 5/9/2015.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    private static final byte[] DM_START_REQ = {0x7F, 0x12, 0x00, 0x00, 0x0F, 0x00, 0x01, 0x00, (byte) 0xA0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x4A, 0x44, 0x53, 0x55, 0x7E};
    private static final byte[] DM_STOP_REQ = {0x7F, 0x0E, 0x00, 0x00, 0x0B, 0x00, 0x02, 0x00, (byte) 0xA0, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x7E};

    public static void main(String[] args) {

        String[] portNames = SerialPortList.getPortNames();
        for (String portName : portNames) {
            LOG.info(portName);
        }

        SerialPort serialPort = new SerialPort("COM3");
        try {
            LOG.info("open COM3 port");
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);

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
                            IncomingHDLCPacket packet = Codecs.decode(codec, buffer);
                        } catch (SerialPortException | DecodingException e) {
                            LOG.log(Level.SEVERE, e.toString(), e);
                        }
                    }
                }

            });

            //send DM Start Request
            StartRequest startRequest = new StartRequest((short) 0, System.currentTimeMillis(), new byte[]{'J' & 0xFF, 'D' & 0xFF, 'S' & 0xFF, 'U' & 0xFF});
            Codec<StartRequest> startReqCodec = Codecs.create(StartRequest.class);
            byte[] packet = Codecs.encode(startRequest, startReqCodec);
            LOG.info("send DM Start Request: " + Hex.encodeHexString(packet));
            serialPort.writeBytes(packet);//Write data to port

            Thread.sleep(5 * 1000);

            //send DM Stop Request
            StopRequest stopRequest = new StopRequest((short) 1, System.currentTimeMillis());
            Codec<StopRequest> stopReqCodec = Codecs.create(StopRequest.class);
            packet = Codecs.encode(stopRequest, stopReqCodec);
            LOG.info("send DM Stop request: " + Hex.encodeHexString(packet));
            serialPort.writeBytes(packet);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    serialPort.closePort();
                } catch (SerialPortException e) {
                    LOG.log(Level.SEVERE, e.toString(), e);
                }
            }));
        } catch (SerialPortException | IOException | InterruptedException e) {
            LOG.log(Level.SEVERE, e.toString(), e);
        }
    }
}
