package com.jdsu.drivetest.dmreader.messages;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * HDLC packet
 * Created by wen55527 on 10/5/15.
 */
public class HDLCPacket {
    @Bound
    private byte START_FLAG = 0x7F;
    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short length;
    @Bound
    private byte CONTROL = 0x00;
    @Bound
    private IPCMessage ipcMessage;
    @Bound
    private byte END_FLAG = 0x7E;

    public HDLCPacket(IPCMessage ipcMessage) {
        setIpcMessage(ipcMessage);
    }

    public short getLength() {
        return length;
    }

    public IPCMessage getIpcMessage() {
        return ipcMessage;
    }

    public void setIpcMessage(IPCMessage ipcMessage) {
        this.ipcMessage = ipcMessage;
        length = (short) (ipcMessage.getLength() + 3);
    }
}
