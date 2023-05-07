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

/*
 *      FTP stream opener
 */

package java.base.share.classes.sun.net.www.protocol.ftp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.Proxy;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.base.share.classes.sun.net.ftp.FtpClient;
import java.base.share.classes.sun.net.www.protocol.http.HttpURLConnection;

/** open an ftp connection given a URL */
public class Handler extends java.net.URLStreamHandler {

    protected int getDefaultPort() {
        return 21;
    }

    protected boolean equals(URL u1, URL u2) {
        String userInfo1 = u1.getUserInfo();
        String userInfo2 = u2.getUserInfo();
        return super.equals(u1, u2) && Objects.equals(userInfo1, userInfo2);
    }

    protected java.net.URLConnection openConnection(URL u)
        throws IOException {
        return openConnection(u, null);
    }

    protected java.net.URLConnection openConnection(URL u, Proxy p)
        throws IOException {
        FtpURLConnection connection = null;
        try {
            connection = new FtpURLConnection(u, p);
        } catch (IllegalArgumentException e) {
            var mfue = new MalformedURLException(e.getMessage());
            mfue.initCause(e);
            throw mfue;
        }
        return connection;
    }
}
