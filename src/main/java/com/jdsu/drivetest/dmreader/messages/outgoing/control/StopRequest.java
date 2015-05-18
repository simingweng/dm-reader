package com.jdsu.drivetest.dmreader.messages.outgoing.control;

import com.jdsu.drivetest.dmreader.messages.outgoing.OutgoingControlMessage;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * {0x7F, 0x0E, 0x00, 0x00, 0x0B, 0x00, 0x00, 0x00, 0xA0, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x7E}
 * Created by wen55527 on 11/5/15.
 */
public class StopRequest extends OutgoingControlMessage {
    @BoundNumber(size = "8")
    private short controlMessageType = 0x02;
    @BoundNumber(size = "32", byteOrder = ByteOrder.LittleEndian)
    private long timestamp;
    @Bound
    private byte endFlag = 0x7E;

    public StopRequest(int sequenceNo, long timestamp) {
        super(5);
        this.timestamp = timestamp;
        setSequenceNo(sequenceNo);
    }

    public short getControlMessageType() {
        return controlMessageType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
