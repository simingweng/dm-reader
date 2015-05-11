package com.jdsu.drivetest.dmreader.messages.incoming;

import com.jdsu.drivetest.dmreader.messages.SubCommandType;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.el.ImportStatic;


/**
 * DM Message, main command byte = 0xA0
 * Created by wen55527 on 10/5/15.
 */
@ImportStatic(SubCommandType.class)
public class IncomingDMMessage {
    @BoundNumber(size = "8")
    private SubCommandType subCommandType;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "subCommandType == SubCommandType.DM_CONTROL_MSG", type = IncomingControlMessage.class)
            }
    ))
    private Object subCommand;

    public SubCommandType getSubCommandType() {
        return subCommandType;
    }

    public Object getSubCommand() {
        return subCommand;
    }
}
