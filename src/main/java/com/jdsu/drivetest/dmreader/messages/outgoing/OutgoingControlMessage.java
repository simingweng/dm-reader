package com.jdsu.drivetest.dmreader.messages.outgoing;

import org.codehaus.preon.annotation.BoundNumber;

/**
 * Tx DM Control messages from TE to MT
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingControlMessage extends OutgoingDMMessage {

    private static final int CONTROL_HEADER_LENGTH = 1;

    @BoundNumber(size = "8")
    private short subCommandType = 0x00;

    public OutgoingControlMessage(int payloadLength) {
        super(payloadLength + CONTROL_HEADER_LENGTH);
    }

    public short getSubCommandType() {
        return subCommandType;
    }
}
