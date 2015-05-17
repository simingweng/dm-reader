package com.jdsu.drivetest.dmreader.messages.outgoing;

import org.codehaus.preon.annotation.Bound;

/**
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingDMMessage extends OutgoingIPCMessage {

    private static final int DM_HEADER_LENGTH = 1;

    @Bound
    private byte mainCommandType = (byte) 0xA0;

    public OutgoingDMMessage(int payloadLength) {
        super(payloadLength + DM_HEADER_LENGTH);
    }

    public byte getMainCommandType() {
        return mainCommandType;
    }
}
