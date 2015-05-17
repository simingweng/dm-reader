package com.jdsu.drivetest.dmreader.messages.incoming;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * HDLC packet
 * Created by wen55527 on 10/5/15.
 */
public class IncomingHDLCPacket {
    @Bound
    private byte startFlag;
    @BoundNumber(size = "16", byteOrder = ByteOrder.LittleEndian)
    private int length;
    @Bound
    private byte control;
    @Bound
    private IncomingIPCMessage incomingIPCMessage;
    @Bound
    private byte endFlag;

    public byte getStartFlag() {
        return startFlag;
    }

    public int getLength() {
        return length;
    }

    public byte getControl() {
        return control;
    }

    public IncomingIPCMessage getIncomingIPCMessage() {
        return incomingIPCMessage;
    }

    public byte getEndFlag() {
        return endFlag;
    }

    @Override
    public String toString() {
        return "IncomingHDLCPacket{" +
                "startFlag=" + startFlag +
                ", length=" + length +
                ", control=" + control +
                ", incomingIPCMessage=" + incomingIPCMessage +
                ", endFlag=" + endFlag +
                '}';
    }
}
