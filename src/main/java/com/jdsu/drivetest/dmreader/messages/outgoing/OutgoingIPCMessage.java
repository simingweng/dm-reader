package com.jdsu.drivetest.dmreader.messages.outgoing;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingIPCMessage extends OutgoingHDLCPPacket {

    protected static final short IPC_HEADER_LENGTH = 4;
    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short ipcLength;
    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short sequenceNo;

    public OutgoingIPCMessage(short payloadLength) {
        super((short) (payloadLength + IPC_HEADER_LENGTH));
        ipcLength = (short) (payloadLength + IPC_HEADER_LENGTH);
    }

    public short getIpcLength() {
        return ipcLength;
    }

    public short getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(short sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
}
