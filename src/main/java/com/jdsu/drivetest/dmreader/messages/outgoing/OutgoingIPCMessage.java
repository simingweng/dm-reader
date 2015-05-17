package com.jdsu.drivetest.dmreader.messages.outgoing;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingIPCMessage extends OutgoingHDLCPPacket {

    private static final int IPC_HEADER_LENGTH = 4;
    @BoundNumber(size = "16", byteOrder = ByteOrder.LittleEndian)
    private int ipcLength;
    @BoundNumber(size = "16", byteOrder = ByteOrder.LittleEndian)
    private int sequenceNo;

    public OutgoingIPCMessage(int payloadLength) {
        super(payloadLength + IPC_HEADER_LENGTH);
        ipcLength = payloadLength + IPC_HEADER_LENGTH;
    }

    public int getIpcLength() {
        return ipcLength;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
}
