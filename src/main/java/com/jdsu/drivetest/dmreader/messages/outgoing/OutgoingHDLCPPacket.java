package com.jdsu.drivetest.dmreader.messages.outgoing;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingHDLCPPacket {

    protected static final short HDLC_HEADER_LENGTH = 3;

    @Bound
    private byte startFlag = 0x7F;
    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short hdlcLength;
    @Bound
    private byte control = 0x00;

    public OutgoingHDLCPPacket(short payloadLength) {
        hdlcLength = (short) (payloadLength + HDLC_HEADER_LENGTH);
    }
    public short getHdlcLength() {
        return hdlcLength;
    }
}
