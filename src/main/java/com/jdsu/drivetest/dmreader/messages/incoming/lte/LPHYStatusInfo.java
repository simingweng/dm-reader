package com.jdsu.drivetest.dmreader.messages.incoming.lte;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Created by simingweng on 17/5/15.
 */
public class LPHYStatusInfo {
    @BoundNumber(size = "32", byteOrder = ByteOrder.LittleEndian)
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }
}
