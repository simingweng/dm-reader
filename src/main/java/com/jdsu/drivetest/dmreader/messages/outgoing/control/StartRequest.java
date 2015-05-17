package com.jdsu.drivetest.dmreader.messages.outgoing.control;

import com.jdsu.drivetest.dmreader.messages.outgoing.OutgoingControlMessage;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * {0x7F, 0x12, 0x00, 0x00, 0x0F, 0x00, 0x00, 0x00, 0xA0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x4A, 0x44, 0x53, 0x55, 0x7E}
 * Created by wen55527 on 10/5/15.
 */
public class StartRequest extends OutgoingControlMessage {
    @Bound
    private byte controlMessageType = 0x00;
    @BoundNumber(size = "32", byteOrder = ByteOrder.LittleEndian)
    private long timestamp;
    @BoundList(size = "4")
    private byte[] vendor;
    @Bound
    private byte endFlag = 0x7E;

    public StartRequest(int sequenceNo, long timestamp, byte[] vendor) {
        super(9);
        this.timestamp = timestamp;
        this.vendor = vendor;
        setSequenceNo(sequenceNo);
    }

    public byte getControlMessageType() {
        return controlMessageType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getVendor() {
        return vendor;
    }

    public void setVendor(byte[] vendor) {
        this.vendor = vendor;
    }

}
