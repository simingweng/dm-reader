package com.jdsu.drivetest.dmreader.messages.incoming;

import com.jdsu.drivetest.dmreader.messages.ControlMessageType;
import com.jdsu.drivetest.dmreader.messages.incoming.control.StartResponse;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.el.ImportStatic;

/**
 * DM Control message, sub command byte = 0x00
 * Created by wen55527 on 10/5/15.
 */
@ImportStatic(ControlMessageType.class)
public class IncomingControlMessage {
    @BoundNumber(size = "8")
    private ControlMessageType type;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "type == ControlMessageType.START_RSP", type = StartResponse.class)
            }
    ))
    private Object message;

    public ControlMessageType getType() {
        return type;
    }

    public Object getMessage() {
        return message;
    }
}
