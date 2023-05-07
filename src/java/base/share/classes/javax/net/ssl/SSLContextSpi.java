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

package java.base.share.classes.javax.net.ssl;

import java.security.*;

/**
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>)
 * for the {@code SSLContext} class.
 *
 * <p> All the abstract methods in this class must be implemented by each
 * cryptographic service provider who wishes to supply the implementation
 * of a particular SSL context.
 *
 * @since 1.4
 * @see SSLContext
 */
public abstract class SSLContextSpi {
    /**
     * Constructor for subclasses to call.
     */
    public SSLContextSpi() {}

    /**
     * Initializes this context.
     *
     * @param km the sources of authentication keys
     * @param tm the sources of peer authentication trust decisions
     * @param sr the source of randomness
     * @throws KeyManagementException if this operation fails
     * @see SSLContext#init(KeyManager[], TrustManager[], SecureRandom)
     */
    protected abstract void engineInit(KeyManager[] km, TrustManager[] tm,
        SecureRandom sr) throws KeyManagementException;

    /**
     * Returns a {@code SocketFactory} object for this
     * context.
     *
     * @return the {@code SocketFactory} object
     * @throws UnsupportedOperationException if the underlying provider
     *         does not implement the operation.
     * @throws IllegalStateException if the SSLContextImpl requires
     *         initialization and the {@code engineInit()}
     *         has not been called
     * @see java.base.share.classes.javax.net.ssl.SSLContext#getSocketFactory()
     */
    protected abstract SSLSocketFactory engineGetSocketFactory();

    /**
     * Returns a {@code ServerSocketFactory} object for
     * this context.
     *
     * @return the {@code ServerSocketFactory} object
     * @throws UnsupportedOperationException if the underlying provider
     *         does not implement the operation.
     * @throws IllegalStateException if the SSLContextImpl requires
     *         initialization and the {@code engineInit()}
     *         has not been called
     * @see java.base.share.classes.javax.net.ssl.SSLContext#getServerSocketFactory()
     */
    protected abstract SSLServerSocketFactory engineGetServerSocketFactory();

    /**
     * Creates a new {@code SSLEngine} using this context.
     * <P>
     * Applications using this factory method are providing no hints
     * for an internal session reuse strategy. If hints are desired,
     * {@link #engineCreateSSLEngine(String, int)} should be used
     * instead.
     * <P>
     * Some cipher suites (such as Kerberos) require remote hostname
     * information, in which case this factory method should not be used.
     *
     * @implNote
     * It is provider-specific if the returned SSLEngine uses client or
     * server mode by default for the (D)TLS connection. The JDK SunJSSE
     * provider implementation uses server mode by default.  However, it
     * is recommended to always set the desired mode explicitly by calling
     * {@link SSLEngine#setUseClientMode(boolean) SSLEngine.setUseClientMode()}
     * before invoking other methods of the SSLEngine.
     *
     * @return  the {@code SSLEngine} Object
     * @throws IllegalStateException if the SSLContextImpl requires
     *         initialization and the {@code engineInit()}
     *         has not been called
     *
     * @see     SSLContext#createSSLEngine()
     *
     * @since   1.5
     */
    protected abstract SSLEngine engineCreateSSLEngine();

    /**
     * Creates a {@code SSLEngine} using this context.
     * <P>
     * Applications using this factory method are providing hints
     * for an internal session reuse strategy.
     * <P>
     * Some cipher suites (such as Kerberos) require remote hostname
     * information, in which case peerHost needs to be specified.
     *
     * @implNote
     * It is provider-specific if the returned SSLEngine uses client or
     * server mode by default for the (D)TLS connection. The JDK SunJSSE
     * provider implementation uses server mode by default.  However, it
     * is recommended to always set the desired mode explicitly by calling
     * {@link SSLEngine#setUseClientMode(boolean) SSLEngine.setUseClientMode()}
     * before invoking other methods of the SSLEngine.
     *
     * @param host the non-authoritative name of the host
     * @param port the non-authoritative port
     * @return  the {@code SSLEngine} Object
     * @throws IllegalStateException if the SSLContextImpl requires
     *         initialization and the {@code engineInit()}
     *         has not been called
     *
     * @see     SSLContext#createSSLEngine(String, int)
     *
     * @since   1.5
     */
    protected abstract SSLEngine engineCreateSSLEngine(String host, int port);

    /**
     * Returns a server {@code SSLSessionContext} object for
     * this context.
     *
     * @return the {@code SSLSessionContext} object
     * @see java.base.share.classes.javax.net.ssl.SSLContext#getServerSessionContext()
     */
    protected abstract SSLSessionContext engineGetServerSessionContext();

    /**
     * Returns a client {@code SSLSessionContext} object for
     * this context.
     *
     * @return the {@code SSLSessionContext} object
     * @see java.base.share.classes.javax.net.ssl.SSLContext#getClientSessionContext()
     */
    protected abstract SSLSessionContext engineGetClientSessionContext();

    private SSLSocket getDefaultSocket() {
        try {
            SSLSocketFactory factory = engineGetSocketFactory();
            return (SSLSocket)factory.createSocket();
        } catch (java.io.IOException e) {
            throw new UnsupportedOperationException("Could not obtain parameters", e);
        }
    }

    /**
     * Returns a copy of the SSLParameters indicating the default
     * settings for this SSL context.
     *
     * <p>The parameters will always have the ciphersuite and protocols
     * arrays set to non-null values.
     *
     * <p>The default implementation obtains the parameters from an
     * SSLSocket created by calling the
     * {@linkplain javax.net.SocketFactory#createSocket
     * SocketFactory.createSocket()} method of this context's SocketFactory.
     *
     * @return a copy of the SSLParameters object with the default settings
     * @throws UnsupportedOperationException if the default SSL parameters
     *   could not be obtained.
     *
     * @since 1.6
     */
    protected SSLParameters engineGetDefaultSSLParameters() {
        SSLSocket socket = getDefaultSocket();
        return socket.getSSLParameters();
    }

    /**
     * Returns a copy of the SSLParameters indicating the maximum supported
     * settings for this SSL context.
     *
     * <p>The parameters will always have the ciphersuite and protocols
     * arrays set to non-null values.
     *
     * <p>The default implementation obtains the parameters from an
     * SSLSocket created by calling the
     * {@linkplain javax.net.SocketFactory#createSocket
     * SocketFactory.createSocket()} method of this context's SocketFactory.
     *
     * @return a copy of the SSLParameters object with the maximum supported
     *   settings
     * @throws UnsupportedOperationException if the supported SSL parameters
     *   could not be obtained.
     *
     * @since 1.6
     */
    protected SSLParameters engineGetSupportedSSLParameters() {
        SSLSocket socket = getDefaultSocket();
        SSLParameters params = socket.getSSLParameters();
        params.setCipherSuites(socket.getSupportedCipherSuites());
        params.setProtocols(socket.getSupportedProtocols());
        return params;
    }
}
