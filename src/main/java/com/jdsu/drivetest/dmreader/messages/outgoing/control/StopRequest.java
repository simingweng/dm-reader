package com.jdsu.drivetest.dmreader.messages.outgoing.control;

import com.jdsu.drivetest.dmreader.messages.ControlMessageType;
import com.jdsu.drivetest.dmreader.messages.outgoing.OutgoingControlMessage;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * {0x7F, 0x0E, 0x00, 0x00, 0x0B, 0x00, 0x02, 0x00, 0xA0, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x7E}
 * Created by wen55527 on 11/5/15.
 */
public class StopRequest extends OutgoingControlMessage {
    @BoundNumber(size = "8")
    private ControlMessageType controlMessageType = ControlMessageType.STOP_REQ;
    @BoundNumber(size = "32", byteOrder = ByteOrder.LittleEndian)
    private long timestamp;
    @Bound
    private byte endFlag = 0x7E;

    public StopRequest(short sequenceNo, long timestamp) {
        super((short) 5);
        this.timestamp = timestamp;
        setSequenceNo(sequenceNo);
    }

    public ControlMessageType getControlMessageType() {
        return controlMessageType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
