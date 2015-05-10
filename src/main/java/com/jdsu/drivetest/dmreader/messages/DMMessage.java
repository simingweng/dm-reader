package com.jdsu.drivetest.dmreader.messages;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.el.ImportStatic;


/**
 * DM Message, main command byte = 0xA0
 * Created by wen55527 on 10/5/15.
 */
@ImportStatic(SubCommandType.class)
public class DMMessage implements Sizable {
    @BoundNumber(size = "8")
    private SubCommandType subCommandType;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "subCommandType == SubCommandType.DM_CONTROL_MSG", type = ControlMessage.class)
            }
    ))
    private Object subCommand;

    public DMMessage(SubCommandType subCommandType, Object subCommand) {
        this.subCommandType = subCommandType;
        this.subCommand = subCommand;
    }

    public SubCommandType getSubCommandType() {
        return subCommandType;
    }

    public void setSubCommandType(SubCommandType subCommandType) {
        this.subCommandType = subCommandType;
    }

    public Object getSubCommand() {
        return subCommand;
    }

    public void setSubCommand(Object subCommand) {
        this.subCommand = subCommand;
    }

    public short getCalculatedLength() {
        return (short) (((Sizable) subCommand).getCalculatedLength() + 1);
    }
}
