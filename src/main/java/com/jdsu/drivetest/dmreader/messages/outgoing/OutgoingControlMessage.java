package com.jdsu.drivetest.dmreader.messages.outgoing;

import org.codehaus.preon.annotation.Bound;

/**
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingControlMessage extends OutgoingDMMessage {

    private static final int CONTROL_HEADER_LENGTH = 1;

    @Bound
    private byte subCommandType = 0x00;

    public OutgoingControlMessage(int payloadLength) {
        super(payloadLength + CONTROL_HEADER_LENGTH);
    }

    public byte getSubCommandType() {
        return subCommandType;
    }
}
