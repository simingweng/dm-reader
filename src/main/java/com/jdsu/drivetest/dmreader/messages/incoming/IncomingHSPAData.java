package com.jdsu.drivetest.dmreader.messages.incoming;

import com.jdsu.drivetest.dmreader.messages.incoming.hspa.UPHYPowerControlInfo;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;

/**
 * Rx HSPA data from MT to TE
 * Created by simingweng on 17/5/15.
 */
public class IncomingHSPAData {
    @BoundNumber(size = "8")
    private short type;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "type == 0x00", type = UPHYPowerControlInfo.class)
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
        return "IncomingHSPAData{" +
                "type=" + type +
                ", message=" + message +
                '}';
    }
}
