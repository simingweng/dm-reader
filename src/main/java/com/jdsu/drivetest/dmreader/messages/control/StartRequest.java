package com.jdsu.drivetest.dmreader.messages.control;

import com.jdsu.drivetest.dmreader.messages.Sizable;
import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Sub command byte = 0x00, command type byte = 0x00
 * Created by wen55527 on 10/5/15.
 */
public class StartRequest implements Sizable {
    @BoundNumber(size = "32", byteOrder = ByteOrder.LittleEndian)
    private long timestamp;
    @BoundList(size = "4")
    private byte[] vendor;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getVendor() {
        return vendor;
    }

    public void setVendor(byte[] vendor) {
        this.vendor = vendor;
    }

    public short getCalculatedLength() {
        return 8;
    }
}
