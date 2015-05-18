package com.jdsu.drivetest.dmreader.messages.outgoing;

import org.codehaus.preon.annotation.BoundNumber;

/**
 * Tx DM message
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingDMMessage extends OutgoingIPCMessage {

    private static final int DM_HEADER_LENGTH = 1;

    @BoundNumber(size = "8")
    private short mainCommandType = 0xA0;

    public OutgoingDMMessage(int payloadLength) {
        super(payloadLength + DM_HEADER_LENGTH);
    }

    public short getMainCommandType() {
        return mainCommandType;
    }
}
