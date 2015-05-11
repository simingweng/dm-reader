package com.jdsu.drivetest.dmreader.messages.outgoing;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingIPCMessage extends OutgoingHDLCPPacket {

    protected static final short IPC_HEADER_LENGTH = 4;
    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    protected static short sequenceNo;
    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short ipcLength;

    public short getIpcLength() {
        return ipcLength;
    }

    protected void setIpcLength(short ipcLength) {
        this.ipcLength = ipcLength;
    }

    public short getSequenceNo() {
        return sequenceNo;
    }
}
