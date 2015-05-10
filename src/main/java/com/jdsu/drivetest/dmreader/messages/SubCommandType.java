package com.jdsu.drivetest.dmreader.messages;

import org.codehaus.preon.annotation.BoundEnumOption;

/**
 * Created by wen55527 on 10/5/15.
 */
public enum SubCommandType {
    @BoundEnumOption(0x00)
    DM_CONTROL_MSG,
    @BoundEnumOption(0x01)
    DM_COM_DATA_OUT,
    @BoundEnumOption(0x02)
    DM_LTE_DATA_OUT,
    @BoundEnumOption(0x03)
    DM_EDGE_DATA_OUT,
    @BoundEnumOption(0x04)
    DM_HSPA_DATA_OUT,
    @BoundEnumOption(0x07)
    DM_HEX_DUMP_DATA_OUT,
    @BoundEnumOption(0x21)
    DM_COM_DATA_OUT_STACK1,
    @BoundEnumOption(0x22)
    DM_LTE_DATA_OUT_STACK1,
    @BoundEnumOption(0x23)
    DM_EDGE_DATA_OUT_STACK1,
    @BoundEnumOption(0x24)
    DM_HSPA_DATA_OUT_STACK1,
    @BoundEnumOption(0x41)
    DM_COM_DATA_OUT_STACK2,
    @BoundEnumOption(0x42)
    DM_LTE_DATA_OUT_STACK2,
    @BoundEnumOption(0x43)
    DM_EDGE_DATA_OUT_STACK2,
    @BoundEnumOption(0x44)
    DM_HSPA_DATA_OUT_STACK2
}
