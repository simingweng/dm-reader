package com.jdsu.drivetest.dmreader.messages.outgoing;

import com.jdsu.drivetest.dmreader.messages.SubCommandType;
import org.codehaus.preon.annotation.BoundNumber;

/**
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingControlMessage extends OutgoingDMMessage {

    protected static final short CONTROL_HEADER_LENGTH = 1;

    @BoundNumber(size = "8")
    private SubCommandType subCommandType = SubCommandType.DM_CONTROL_MSG;

    public SubCommandType getSubCommandType() {
        return subCommandType;
    }
}
