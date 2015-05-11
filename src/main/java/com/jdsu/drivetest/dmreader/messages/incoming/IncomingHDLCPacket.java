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
    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short length;
    @Bound
    private byte control;
    @Bound
    private IncomingIPCMessage incomingIPCMessage;
    @Bound
    private byte endFlag;

    public byte getStartFlag() {
        return startFlag;
    }

    public short getLength() {
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
}
