package com.jdsu.drivetest.dmreader.messages.incoming;

import com.jdsu.drivetest.dmreader.messages.incoming.lte.LPHYStatusInfo;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;

/**
 * Rx LTE Data from MT to TE
 * Created by simingweng on 17/5/15.
 */
public class IncomingLTEData {
    @BoundNumber(size = "8")
    private short type;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "type == 0x00", type = LPHYStatusInfo.class)
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
        return "IncomingLTEData{" +
                "type=" + type +
                ", message=" + message +
                '}';
    }
}
