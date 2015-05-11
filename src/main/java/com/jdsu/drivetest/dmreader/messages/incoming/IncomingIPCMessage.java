package com.jdsu.drivetest.dmreader.messages.incoming;

import com.jdsu.drivetest.dmreader.messages.MainCommandType;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.el.ImportStatic;

/**
 * the base class for all the DM messages
 * Created by wen55527 on 10/5/15.
 */
@ImportStatic(MainCommandType.class)
public class IncomingIPCMessage {

    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short length;
    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short sequenceNo;
    @BoundNumber(size = "8")
    private MainCommandType mainCommandType = MainCommandType.IPC_DM_CMD;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "mainCommandType == MainCommandType.IPC_DM_CMD", type = IncomingDMMessage.class)
            }
    ))
    private Object dmMessage;

    public short getLength() {
        return length;
    }

    public short getSequenceNo() {
        return sequenceNo;
    }

    public MainCommandType getMainCommandType() {
        return mainCommandType;
    }

    public Object getDmMessage() {
        return dmMessage;
    }
}
