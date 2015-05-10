package com.jdsu.drivetest.dmreader.messages;

import org.codehaus.preon.annotation.BoundEnumOption;

/**
 * Created by wen55527 on 10/5/15.
 */
public enum ControlMessageType {
    @BoundEnumOption(0x00)
    START_REQ,
    @BoundEnumOption(0x01)
    START_RSP,
    @BoundEnumOption(0x02)
    STOP_REQ,
    @BoundEnumOption(0x03)
    STOP_RSP,
    @BoundEnumOption(0x04)
    RESET_REQ,
    @BoundEnumOption(0x05)
    RESET_RSP,
    @BoundEnumOption(0x06)
    CHANGE_UPDATE_PERIOD_REQ,
    @BoundEnumOption(0x07)
    CHANGE_UPDATE_PERIOD_RSP,
    @BoundEnumOption(0x08)
    SLEEP_NOTIFICATION,
    @BoundEnumOption(0x09)
    WAKEUP_NOTIFICATION,
    @BoundEnumOption(0x10)
    COMMON_ITEM_SELECT_REQ,
    @BoundEnumOption(0x11)
    COMMON_ITEM_SELECT_RSP,
    @BoundEnumOption(0x12)
    COMMON_ITEM_REFRESH_REQ,
    @BoundEnumOption(0x13)
    COMMON_ITEM_REFRESH_RSP,
    @BoundEnumOption(0x20)
    LTE_ITEM_SELECT_REQ,
    @BoundEnumOption(0x21)
    LTE_ITEM_SELECT_RSP,
    @BoundEnumOption(0x22)
    LTE_ITEM_REFRESH_REQ,
    @BoundEnumOption(0x23)
    LTE_ITEM_REFRESH_RSP,
    @BoundEnumOption(0x30)
    EDGE_ITEM_SELECT_REQ,
    @BoundEnumOption(0x31)
    EDGE_ITEM_SELECT_RSP,
    @BoundEnumOption(0x32)
    EDGE_ITEM_REFRESH_REQ,
    @BoundEnumOption(0x33)
    EDGE_ITEM_REFRESH_RSP,
    @BoundEnumOption(0x40)
    HSPA_ITEM_SELECT_REQ,
    @BoundEnumOption(0x41)
    HSPA_ITEM_SELECT_RSP,
    @BoundEnumOption(0x42)
    HSPA_ITEM_REFRESH_REQ,
    @BoundEnumOption(0x43)
    HSPA_ITEM_REFRESH_RSP,
    @BoundEnumOption(0x90)
    TCP_IP_DUMP_REQ,
    @BoundEnumOption(0x91)
    TCP_IP_DUMP_RSP
}
