package com.jdsu.drivetest.dmreader.messages.incoming;

import com.jdsu.drivetest.dmreader.messages.incoming.common.BasicInfo;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;

/**
 * Created by simingweng on 17/5/15.
 */
public class IncomingCommonData {

    @Bound
    private byte type;
    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "type == 0x00", type = BasicInfo.class)
            }
    ))
    private Object message;

    public byte getType() {
        return type;
    }

    public Object getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "IncomingCommonData{" +
                "type=" + type +
                ", message=" + message +
                '}';
    }
}
