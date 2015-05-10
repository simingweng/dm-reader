package com.jdsu.drivetest.dmreader.messages;

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
public class IPCMessage {

    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short length;
    @BoundNumber(byteOrder = ByteOrder.LittleEndian)
    private short sequenceNo;
    @BoundNumber(size = "8")
    private MainCommandType mainCommandType = MainCommandType.IPC_DM_CMD;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "mainCommandType == MainCommandType.IPC_DM_CMD", type = DMMessage.class)
            }
    ))
    private Object dmMessage;

    public IPCMessage(short sequenceNo, Object dmMessage) {
        this.sequenceNo = sequenceNo;
        setDmMessage(dmMessage);
    }

    public short getLength() {
        return length;
    }

    public short getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(short sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public MainCommandType getMainCommandType() {
        return mainCommandType;
    }

    public void setMainCommandType(MainCommandType mainCommandType) {
        this.mainCommandType = mainCommandType;
    }

    public Object getDmMessage() {
        return dmMessage;
    }

    public void setDmMessage(Object dmMessage) {
        this.dmMessage = dmMessage;
        if (dmMessage instanceof Sizable) {
            length = (short) (((Sizable) dmMessage).getCalculatedLength() + 5);
        }
    }
}
