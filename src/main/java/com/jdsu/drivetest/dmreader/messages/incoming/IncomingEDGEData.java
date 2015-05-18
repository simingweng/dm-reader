package com.jdsu.drivetest.dmreader.messages.incoming;

import com.jdsu.drivetest.dmreader.messages.incoming.edge.TimeSlotInfo;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;

/**
 * Rx EDGE data from MT to TE
 * Created by wen55527 on 18/5/15.
 */
public class IncomingEDGEData {
    @BoundNumber(size = "8")
    private short type;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "type == 0x00", type = TimeSlotInfo.class)
            }
    ))
    private Object message;

    public short getType() {
        return type;
    }

    public Object getMessage() {
        return message;
    }
}
