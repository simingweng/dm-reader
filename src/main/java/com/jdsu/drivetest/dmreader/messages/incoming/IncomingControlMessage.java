package com.jdsu.drivetest.dmreader.messages.incoming;

import com.jdsu.drivetest.dmreader.messages.incoming.control.StartResponse;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;

/**
 * DM Control message, sub command byte = 0x00
 * Created by wen55527 on 10/5/15.
 */
public class IncomingControlMessage {
    @BoundNumber(size = "8")
    private short type;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "type == 0x01", type = StartResponse.class)
            }
    ))
    private Object message;

    public short getType() {
        return type;
    }

    public Object getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "IncomingControlMessage{" +
                "type=" + type +
                ", message=" + message +
                '}';
    }
}
