package com.jdsu.drivetest.dmreader.messages;

import com.jdsu.drivetest.dmreader.messages.control.StartRequest;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.el.ImportStatic;

/**
 * DM Control message, sub command byte = 0x00
 * Created by wen55527 on 10/5/15.
 */
@ImportStatic(ControlMessageType.class)
public class ControlMessage implements Sizable {
    @BoundNumber(size = "8")
    private ControlMessageType type;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "type == ControlMessageType.START_REQ", type = StartRequest.class)
            }
    ))
    private Object message;

    public ControlMessage(ControlMessageType type, Object message) {
        this.type = type;
        this.message = message;
    }

    public ControlMessageType getType() {
        return type;
    }

    public void setType(ControlMessageType type) {
        this.type = type;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public short getCalculatedLength() {
        return (short) (((Sizable) message).getCalculatedLength() + 1);
    }
}
