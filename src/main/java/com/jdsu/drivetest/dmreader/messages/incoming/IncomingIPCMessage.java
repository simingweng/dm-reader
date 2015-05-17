package com.jdsu.drivetest.dmreader.messages.incoming;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * the base class for all the DM messages
 * Created by wen55527 on 10/5/15.
 */
public class IncomingIPCMessage {

    @BoundNumber(size = "16", byteOrder = ByteOrder.LittleEndian)
    private int length;
    @BoundNumber(size = "16", byteOrder = ByteOrder.LittleEndian)
    private int sequenceNo;
    @Bound
    private byte mainCommandType;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "mainCommandType == 0xA0", type = IncomingDMMessage.class)
            }
    ))
    private Object dmMessage;

    public int getLength() {
        return length;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public byte getMainCommandType() {
        return mainCommandType;
    }

    public Object getDmMessage() {
        return dmMessage;
    }

    @Override
    public String toString() {
        return "IncomingIPCMessage{" +
                "length=" + length +
                ", sequenceNo=" + sequenceNo +
                ", mainCommandType=" + mainCommandType +
                ", dmMessage=" + dmMessage +
                '}';
    }
}
