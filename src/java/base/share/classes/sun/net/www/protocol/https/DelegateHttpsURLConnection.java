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

package java.base.share.classes.sun.net.www.protocol.https;

import java.net.URL;
import java.net.Proxy;
import java.io.IOException;

/**
 * This class was introduced to provide an additional level of
 * abstraction between javax.net.ssl.HttpURLConnection and
 * com.sun.net.ssl.HttpURLConnection objects. <p>
 *
 * javax.net.ssl.HttpURLConnection is used in the new sun.net version
 * of protocol implementation (this one)
 * com.sun.net.ssl.HttpURLConnection is used in the com.sun version.
 *
 */
public class DelegateHttpsURLConnection extends AbstractDelegateHttpsURLConnection {

    // we need a reference to the HttpsURLConnection to get
    // the properties set there
    // we also need it to be public so that it can be referenced
    // from sun.net.www.protocol.http.HttpURLConnection
    // this is for ResponseCache.put(URI, URLConnection)
    // second parameter needs to be cast to javax.net.ssl.HttpsURLConnection
    // instead of AbstractDelegateHttpsURLConnection
    public javax.net.ssl.HttpsURLConnection httpsURLConnection;

    DelegateHttpsURLConnection(URL url,
            sun.net.www.protocol.http.Handler handler,
            javax.net.ssl.HttpsURLConnection httpsURLConnection)
            throws IOException {
        this(url, null, handler, httpsURLConnection);
    }

    DelegateHttpsURLConnection(URL url, Proxy p,
            sun.net.www.protocol.http.Handler handler,
            javax.net.ssl.HttpsURLConnection httpsURLConnection)
            throws IOException {
        super(url, p, handler);
        this.httpsURLConnection = httpsURLConnection;
    }

    protected javax.net.ssl.SSLSocketFactory getSSLSocketFactory() {
        return httpsURLConnection.getSSLSocketFactory();
    }

    protected javax.net.ssl.HostnameVerifier getHostnameVerifier() {
        return httpsURLConnection.getHostnameVerifier();
    }
}
