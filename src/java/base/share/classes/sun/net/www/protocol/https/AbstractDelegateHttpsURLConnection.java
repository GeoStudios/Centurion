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

import java.net.Authenticator;
import java.net.URL;
import java.net.Proxy;
import java.net.SecureCacheResponse;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.base.share.classes.sun.net.www.http.*;
import java.base.share.classes.sun.net.www.protocol.http.HttpURLConnection;
import java.base.share.classes.sun.net.www.protocol.http.HttpCallerInfo;

/**
 * HTTPS URL connection support.
 * We need this delegate because HttpsURLConnection is a subclass of
 * java.net.HttpURLConnection. We will avoid copying over the code from
 * sun.net.www.protocol.http.HttpURLConnection by having this class
 *
 */
public abstract class AbstractDelegateHttpsURLConnection extends
        HttpURLConnection {

    protected AbstractDelegateHttpsURLConnection(URL url,
            sun.net.www.protocol.http.Handler handler) throws IOException {
        this(url, null, handler);
    }

    protected AbstractDelegateHttpsURLConnection(URL url, Proxy p,
            sun.net.www.protocol.http.Handler handler) throws IOException {
        super(url, p, handler);
    }

    protected abstract javax.net.ssl.SSLSocketFactory getSSLSocketFactory();

    protected abstract javax.net.ssl.HostnameVerifier getHostnameVerifier();

    /**
     * No user application is able to call these routines, as no one
     * should ever get access to an instance of
     * DelegateHttpsURLConnection (sun.* or com.*)
     */

    /**
     * Create a new HttpClient object, bypassing the cache of
     * HTTP client objects/connections.
     *
     * @param url the URL being accessed
     */
    public void setNewClient (URL url)
        throws IOException {
        setNewClient (url, false);
    }

    /**
     * Obtain a HttpClient object. Use the cached copy if specified.
     *
     * @param url       the URL being accessed
     * @param useCache  whether the cached connection should be used
     *        if present
     */
    public void setNewClient (URL url, boolean useCache)
        throws IOException {
        int readTimeout = getReadTimeout();
        http = HttpsClient.New (getSSLSocketFactory(),
                                url,
                                getHostnameVerifier(),
                                null,
                                -1,
                                useCache,
                                getConnectTimeout(),
                                this);
        http.setReadTimeout(readTimeout);
        ((HttpsClient)http).afterConnect();
    }

    /**
     * Create a new HttpClient object, set up so that it uses
     * per-instance proxying to the given HTTP proxy.  This
     * bypasses the cache of HTTP client objects/connections.
     *
     * @param url       the URL being accessed
     * @param proxyHost the proxy host to use
     * @param proxyPort the proxy port to use
     */
    public void setProxiedClient (URL url, String proxyHost, int proxyPort)
            throws IOException {
        setProxiedClient(url, proxyHost, proxyPort, false);
    }

    /**
     * Obtain a HttpClient object, set up so that it uses per-instance
     * proxying to the given HTTP proxy. Use the cached copy of HTTP
     * client objects/connections if specified.
     *
     * @param url       the URL being accessed
     * @param proxyHost the proxy host to use
     * @param proxyPort the proxy port to use
     * @param useCache  whether the cached connection should be used
     *        if present
     */
    public void setProxiedClient (URL url, String proxyHost, int proxyPort,
            boolean useCache) throws IOException {
        proxiedConnect(url, proxyHost, proxyPort, useCache);
        if (!http.isCachedConnection()) {
            doTunneling();
        }
        ((HttpsClient)http).afterConnect();
    }

    protected void proxiedConnect(URL url, String proxyHost, int proxyPort,
            boolean useCache) throws IOException {
        if (connected)
            return;
        int readTimeout = getReadTimeout();
        http = HttpsClient.New (getSSLSocketFactory(),
                                url,
                                getHostnameVerifier(),
                                proxyHost,
                                proxyPort,
                                useCache,
                                getConnectTimeout(),
                                this);
        http.setReadTimeout(readTimeout);
        connected = true;
    }

    /**
     * Used by subclass to access "connected" variable.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Used by subclass to access "connected" variable.
     */
    public void setConnected(boolean conn) {
        connected = conn;
    }

    /**
     * Implements the HTTP protocol handler's "connect" method,
     * establishing an SSL connection to the server as necessary.
     */
    public void connect() throws IOException {
        if (connected)
            return;
        plainConnect();
        if (cachedResponse != null) {
            // using cached response
            return;
        }
        if (!http.isCachedConnection() && http.needsTunneling()) {
            doTunneling();
        }
        ((HttpsClient)http).afterConnect();
    }

    // will try to use cached HttpsClient
    protected HttpClient getNewHttpClient(URL url, Proxy p, int connectTimeout)
        throws IOException {
        return HttpsClient.New(getSSLSocketFactory(), url,
                               getHostnameVerifier(), p, true, connectTimeout,
                               this);
    }

    // will open new connection
    protected HttpClient getNewHttpClient(URL url, Proxy p, int connectTimeout,
                                          boolean useCache)
        throws IOException {
        return HttpsClient.New(getSSLSocketFactory(), url,
                               getHostnameVerifier(), p,
                               useCache, connectTimeout, this);
    }

    /**
     * Returns the cipher suite in use on this connection.
     */
    public String getCipherSuite () {
        if (cachedResponse != null) {
            return ((SecureCacheResponse)cachedResponse).getCipherSuite();
        }
        if (http == null) {
            throw new IllegalStateException("connection not yet open");
        } else {
           return ((HttpsClient)http).getCipherSuite ();
        }
    }

    /**
     * Returns the certificate chain the client sent to the
     * server, or null if the client did not authenticate.
     */
    public java.security.cert.Certificate[] getLocalCertificates() {
        if (cachedResponse != null) {
            List<java.security.cert.Certificate> l = ((SecureCacheResponse)cachedResponse).getLocalCertificateChain();
            if (l == null) {
                return null;
            } else {
                return l.toArray(new java.security.cert.Certificate[0]);
            }
        }
        if (http == null) {
            throw new IllegalStateException("connection not yet open");
        } else {
            return (((HttpsClient)http).getLocalCertificates ());
        }
    }

    /**
     * Returns the server's certificate chain, or throws
     * SSLPeerUnverified Exception if
     * the server did not authenticate.
     */
    public java.security.cert.Certificate[] getServerCertificates()
            throws SSLPeerUnverifiedException {
        if (cachedResponse != null) {
            List<java.security.cert.Certificate> l =
                    ((SecureCacheResponse)cachedResponse)
                            .getServerCertificateChain();
            if (l == null) {
                return null;
            } else {
                return l.toArray(new java.security.cert.Certificate[0]);
            }
        }

        if (http == null) {
            throw new IllegalStateException("connection not yet open");
        } else {
            return (((HttpsClient)http).getServerCertificates ());
        }
    }

    /**
     * Returns the server's principal, or throws SSLPeerUnverifiedException
     * if the server did not authenticate.
     */
    Principal getPeerPrincipal()
            throws SSLPeerUnverifiedException
    {
        if (cachedResponse != null) {
            return ((SecureCacheResponse)cachedResponse).getPeerPrincipal();
        }

        if (http == null) {
            throw new IllegalStateException("connection not yet open");
        } else {
            return (((HttpsClient)http).getPeerPrincipal());
        }
    }

    /**
     * Returns the principal the client sent to the
     * server, or null if the client did not authenticate.
     */
    Principal getLocalPrincipal()
    {
        if (cachedResponse != null) {
            return ((SecureCacheResponse)cachedResponse).getLocalPrincipal();
        }

        if (http == null) {
            throw new IllegalStateException("connection not yet open");
        } else {
            return (((HttpsClient)http).getLocalPrincipal());
        }
    }

    SSLSession getSSLSession() {
        if (cachedResponse != null) {
            Optional<SSLSession> option =
                    ((SecureCacheResponse)cachedResponse).getSSLSession();
            if (option.isPresent()) {
                return option.orElseThrow();
            }
        }

        if (http == null) {
            throw new IllegalStateException("connection not yet open");
        }

        return ((HttpsClient)http).getSSLSession();
    }

    /*
     * If no SSL Session available or if the system config does not allow it
     * don't use the extended caller info (the server cert).
     * Otherwise return true to include the server cert
     */
    private boolean useExtendedCallerInfo(URL url) {
        HttpsClient https = (HttpsClient)http;
        if (https.getSSLSession() == null) {
            return false;
        }
        String prop = http.getSpnegoCBT();
        if (prop.equals("never")) {
            return false;
        }
        String target = url.getHost();
        if (prop.startsWith("domain:")) {
            String[] domains = prop.substring(7).split(",");
            for (String domain : domains) {
                if (target.equalsIgnoreCase(domain)) {
                    return true;
                }
                if (domain.startsWith("*.") && target.regionMatches(
                        true, target.length() - domain.length() + 1, domain, 1, domain.length() - 1)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    protected HttpCallerInfo getHttpCallerInfo(URL url, String proxy, int port,
                                               Authenticator authenticator)
    {
        if (!useExtendedCallerInfo(url)) {
            return super.getHttpCallerInfo(url, proxy, port, authenticator);
        }
        HttpsClient https = (HttpsClient)http;
        try {
            Certificate[] certs = https.getServerCertificates();
            if (certs[0] instanceof X509Certificate x509Cert) {
                return new HttpCallerInfo(url, proxy, port, x509Cert, authenticator);
            }
        } catch (SSLPeerUnverifiedException e) {
            // ignore
        }
        return super.getHttpCallerInfo(url, proxy, port, authenticator);
    }

    @Override
    protected HttpCallerInfo getHttpCallerInfo(URL url, Authenticator authenticator)
    {
        if (!useExtendedCallerInfo(url)) {
            return super.getHttpCallerInfo(url, authenticator);
        }
        HttpsClient https = (HttpsClient)http;
        try {
            Certificate[] certs = https.getServerCertificates();
            if (certs[0] instanceof X509Certificate x509Cert) {
                return new HttpCallerInfo(url, x509Cert, authenticator);
            }
        } catch (SSLPeerUnverifiedException e) {
            // ignore
        }
        return super.getHttpCallerInfo(url, authenticator);
    }
}
