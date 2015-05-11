package com.jdsu.drivetest.dmreader.messages.incoming.control;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Created by wen55527 on 11/5/15.
 */
public class StartResponse {
    @BoundNumber(size = "32", byteOrder = ByteOrder.LittleEndian)
    private long timestamp;
}
