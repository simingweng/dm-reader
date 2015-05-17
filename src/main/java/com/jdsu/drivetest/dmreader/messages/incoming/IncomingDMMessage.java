package com.jdsu.drivetest.dmreader.messages.incoming;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;


/**
 * DM Message, main command byte = 0xA0
 * Created by wen55527 on 10/5/15.
 */
public class IncomingDMMessage {
    @Bound
    private byte subCommandType;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "subCommandType == 0x00", type = IncomingControlMessage.class),
                    @Choices.Choice(condition = "subCommandType == 0x01 || subCommandType == 0x21 || subCommandType == 0x41", type = IncomingCommonData.class),
                    @Choices.Choice(condition = "subCommandType == 0x02 || subCommandType == 0x22 || subCommandType == 0x42", type = IncomingLTEData.class),
                    @Choices.Choice(condition = "subCommandType == 0x03 || subCommandType == 0x23 || subCommandType == 0x43", type = IncomingEDGEData.class),
                    @Choices.Choice(condition = "subCommandType == 0x04 || subCommandType == 0x24 || subCommandType == 0x44", type = IncomingHSPAData.class),
                    @Choices.Choice(condition = "subCommandType == 0x07", type = HexDumpData.class)
            }
    ))
    private Object subCommand;

    public byte getSubCommandType() {
        return subCommandType;
    }

    public Object getSubCommand() {
        return subCommand;
    }

    @Override
    public String toString() {
        return "IncomingDMMessage{" +
                "subCommandType=" + subCommandType +
                ", subCommand=" + subCommand +
                '}';
    }
}
