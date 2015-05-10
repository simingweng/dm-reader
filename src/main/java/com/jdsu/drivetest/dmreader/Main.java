package com.jdsu.drivetest.dmreader;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

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
            LOG.info("send DM Start request");
            serialPort.writeBytes(DM_START_REQ);//Write data to port
            LOG.info("read DM Start response " + serialPort.readHexString());
            LOG.info("send DM Stop request");
            serialPort.writeBytes(DM_STOP_REQ);
            String hex;
            while ((hex = serialPort.readHexString()) != null) {
                LOG.info("read DM Stop response " + hex);
            }
            serialPort.closePort();//Close serial port
        } catch (SerialPortException ex) {
            LOG.log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
