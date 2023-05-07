/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

/*-
 *      HTTP stream opener
 */

package java.base.share.classes.sun.net.www.protocol.http;

import java.io.IOException;
import java.net.URL;
import java.net.Proxy;

/** open an http input stream given a URL */
public class Handler extends java.net.URLStreamHandler {
    protected String proxy;
    protected int proxyPort;

    protected int getDefaultPort() {
        return 80;
    }

    public Handler () {
        proxy = null;
        proxyPort = -1;
    }

    public Handler (String proxy, int port) {
        this.proxy = proxy;
        this.proxyPort = port;
    }

    protected java.net.URLConnection openConnection(URL u)
    throws IOException {
        return openConnection(u, (Proxy)null);
    }

    protected java.net.URLConnection openConnection(URL u, Proxy p)
        throws IOException {
        return new HttpURLConnection(u, p, this);
    }
}
