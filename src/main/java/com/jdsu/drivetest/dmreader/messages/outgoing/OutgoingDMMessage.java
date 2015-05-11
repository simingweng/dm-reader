package com.jdsu.drivetest.dmreader.messages.outgoing;

import com.jdsu.drivetest.dmreader.messages.MainCommandType;
import org.codehaus.preon.annotation.BoundNumber;

/**
 * Created by wen55527 on 11/5/15.
 */
public class OutgoingDMMessage extends OutgoingIPCMessage {

    protected static final short DM_HEADER_LENGTH = 1;

    @BoundNumber(size = "8")
    private MainCommandType mainCommandType = MainCommandType.IPC_DM_CMD;

    public MainCommandType getMainCommandType() {
        return mainCommandType;
    }
}
