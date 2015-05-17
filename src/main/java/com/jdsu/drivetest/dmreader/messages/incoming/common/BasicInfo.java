package com.jdsu.drivetest.dmreader.messages.incoming.common;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Created by simingweng on 17/5/15.
 */
public class BasicInfo {
    @BoundNumber(size = "32", byteOrder = ByteOrder.LittleEndian)
    private long timestamp;
    @BoundNumber(size = "8")
    private Rat rat;

    public long getTimestamp() {
        return timestamp;
    }

    public Rat getRat() {
        return rat;
    }

    public enum Rat {
        NOT_SELECTED,
        GSM,
        GPRS,
        UMTS,
        EDGE,
        HSPA,
        LTE,
        GSM_G,
        GSM_COMPACT_G,
        UTRAN,
        GSM_WEGPRS,
        UTRAN_WHSDPA,
        UTRAN_WHSUPA,
        UTRAN_WHSPA,
        EUTRAN,
        NONE
    }

    public enum ModemStatus {

    }
}
