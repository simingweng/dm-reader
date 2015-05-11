package com.jdsu.drivetest.dmreader.messages.outgoing.control;

import com.jdsu.drivetest.dmreader.messages.ControlMessageType;
import com.jdsu.drivetest.dmreader.messages.outgoing.OutgoingControlMessage;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Sub command byte = 0x00, command type byte = 0x00
 * Created by wen55527 on 10/5/15.
 */
public class StartRequest extends OutgoingControlMessage {
    @BoundNumber(size = "8")
    private ControlMessageType controlMessageType = ControlMessageType.START_REQ;
    @BoundNumber(size = "32", byteOrder = ByteOrder.LittleEndian)
    private long timestamp;
    @BoundList(size = "4")
    private byte[] vendor;
    @Bound
    private byte endFlag = 0x7E;

    public StartRequest(long timestamp, byte[] vendor) {
        this.timestamp = timestamp;
        this.vendor = vendor;
        setIpcLength((short) (IPC_HEADER_LENGTH + DM_HEADER_LENGTH + CONTROL_HEADER_LENGTH + 9));
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

    public byte[] getVendor() {
        return vendor;
    }

    public void setVendor(byte[] vendor) {
        this.vendor = vendor;
    }

}
