package com.jdsu.drivetest.dmreader.messages.incoming;

import org.codehaus.preon.annotation.BoundList;

/**
 * Created by simingweng on 17/5/15.
 */
public class UnknowMessage {
    @BoundList(size = "1")
    private byte[] bytes;
}
