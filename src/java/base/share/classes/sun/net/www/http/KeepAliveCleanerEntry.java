/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package sun.net.www.http;

import java.io.*;

class KeepAliveCleanerEntry
{
    KeepAliveStream kas;
    HttpClient hc;

    public KeepAliveCleanerEntry(KeepAliveStream kas, HttpClient hc) {
        this.kas = kas;
        this.hc = hc;
    }

    protected KeepAliveStream getKeepAliveStream() {
        return kas;
    }

    protected HttpClient getHttpClient() {
        return hc;
    }

    protected void setQueuedForCleanup() {
        kas.queuedForCleanup = true;
    }

    protected boolean getQueuedForCleanup() {
        return kas.queuedForCleanup;
    }

}
