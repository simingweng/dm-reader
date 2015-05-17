package com.jdsu.drivetest.dmreader.messages.outgoing;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingHDLCPPacket {

    private static final int HDLC_HEADER_LENGTH = 3;

    @Bound
    private byte startFlag = 0x7F;
    @BoundNumber(size = "16", byteOrder = ByteOrder.LittleEndian)
    private int hdlcLength;
    @Bound
    private byte control = 0x00;

    public OutgoingHDLCPPacket(int payloadLength) {
        hdlcLength = payloadLength + HDLC_HEADER_LENGTH;
    }

    public int getHdlcLength() {
        return hdlcLength;
    }
}
