package com.jdsu.drivetest.dmreader.messages.outgoing.control;

import com.jdsu.drivetest.dmreader.messages.ControlMessageType;
import com.jdsu.drivetest.dmreader.messages.outgoing.OutgoingControlMessage;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Created by wen55527 on 11/5/15.
 */
public class StopRequest extends OutgoingControlMessage {
    @BoundNumber(size = "8")
    private ControlMessageType controlMessageType = ControlMessageType.START_REQ;
    @BoundNumber(size = "32", byteOrder = ByteOrder.LittleEndian)
    private long timestamp;

    public StopRequest(long timestamp) {
        this.timestamp = timestamp;
        setIpcLength((short) (IPC_HEADER_LENGTH + DM_HEADER_LENGTH + CONTROL_HEADER_LENGTH + 5));
        setHdlcLength((short) (HDLC_HEADER_LENGTH + getIpcLength()));
        sequenceNo++;
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
