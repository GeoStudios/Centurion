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

package java.base.share.classes.sun.security.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;

/**
 * This class provides a simple way for servers to support conventional
 * use of the Secure Sockets Layer (SSL).  Application code uses an
 * SSLServerSocketImpl exactly like it uses a regular TCP ServerSocket; the
 * difference is that the connections established are secured using SSL.
 *
 * <P> Also, the constructors take an explicit authentication context
 * parameter, giving flexibility with respect to how the server socket
 * authenticates itself.  That policy flexibility is not exposed through
 * the standard SSLServerSocketFactory API.
 *
 * <P> System security defaults prevent server sockets from accepting
 * connections if the authentication context has not been given
 * a certificate chain and its matching private key.  If the clients
 * of your application support "anonymous" cipher suites, you may be
 * able to configure a server socket to accept those suites.
 *
 * @see SSLSocketImpl
 * @see SSLServerSocketFactoryImpl
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

final class SSLServerSocketImpl extends SSLServerSocket {
    private final SSLContextImpl        sslContext;
    private final SSLConfiguration      sslConfig;
    private final ReentrantLock         serverSocketLock = new ReentrantLock();

    SSLServerSocketImpl(SSLContextImpl sslContext) throws IOException {

        super();
        this.sslContext = sslContext;
        this.sslConfig = new SSLConfiguration(sslContext, false);
    }

    SSLServerSocketImpl(SSLContextImpl sslContext,
            int port, int backlog) throws IOException {

        super(port, backlog);
        this.sslContext = sslContext;
        this.sslConfig = new SSLConfiguration(sslContext, false);
    }

    SSLServerSocketImpl(SSLContextImpl sslContext,
            int port, int backlog, InetAddress address) throws IOException {

        super(port, backlog, address);
        this.sslContext = sslContext;
        this.sslConfig = new SSLConfiguration(sslContext, false);
    }

    @Override
    public String[] getEnabledCipherSuites() {
        serverSocketLock.lock();
        try {
            return CipherSuite.namesOf(sslConfig.enabledCipherSuites);
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public void setEnabledCipherSuites(String[] suites) {
        serverSocketLock.lock();
        try {
            sslConfig.enabledCipherSuites =
                    CipherSuite.validValuesOf(suites);
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return CipherSuite.namesOf(sslContext.getSupportedCipherSuites());
    }

    @Override
    public String[] getSupportedProtocols() {
        return ProtocolVersion.toStringArray(
                sslContext.getSupportedProtocolVersions());
    }

    @Override
    public String[] getEnabledProtocols() {
        serverSocketLock.lock();
        try {
            return ProtocolVersion.toStringArray(sslConfig.enabledProtocols);
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public void setEnabledProtocols(String[] protocols) {
        serverSocketLock.lock();
        try {
            if (protocols == null) {
                throw new IllegalArgumentException("Protocols cannot be null");
            }

            sslConfig.enabledProtocols = ProtocolVersion.namesOf(protocols);
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public void setNeedClientAuth(boolean need) {
        serverSocketLock.lock();
        try {
            sslConfig.clientAuthType =
                    (need ? ClientAuthType.CLIENT_AUTH_REQUIRED :
                            ClientAuthType.CLIENT_AUTH_NONE);
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public boolean getNeedClientAuth() {
        serverSocketLock.lock();
        try {
            return (sslConfig.clientAuthType ==
                        ClientAuthType.CLIENT_AUTH_REQUIRED);
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public void setWantClientAuth(boolean want) {
        serverSocketLock.lock();
        try {
            sslConfig.clientAuthType =
                    (want ? ClientAuthType.CLIENT_AUTH_REQUESTED :
                            ClientAuthType.CLIENT_AUTH_NONE);
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public boolean getWantClientAuth() {
        serverSocketLock.lock();
        try {
            return (sslConfig.clientAuthType ==
                        ClientAuthType.CLIENT_AUTH_REQUESTED);
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public void setUseClientMode(boolean useClientMode) {
        serverSocketLock.lock();
        try {
            /*
             * If we need to change the client mode and the enabled
             * protocols and cipher suites haven't specifically been
             * set by the user, change them to the corresponding
             * default ones.
             */
            if (sslConfig.isClientMode != useClientMode) {
                if (sslContext.isDefaultProtocolVesions(
                        sslConfig.enabledProtocols)) {
                    sslConfig.enabledProtocols =
                        sslContext.getDefaultProtocolVersions(!useClientMode);
                }

                if (sslContext.isDefaultCipherSuiteList(
                        sslConfig.enabledCipherSuites)) {
                    sslConfig.enabledCipherSuites =
                        sslContext.getDefaultCipherSuites(!useClientMode);
                }

                sslConfig.toggleClientMode();
            }
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public boolean getUseClientMode() {
        serverSocketLock.lock();
        try {
            return sslConfig.isClientMode;
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public void setEnableSessionCreation(boolean flag) {
        serverSocketLock.lock();
        try {
            sslConfig.enableSessionCreation = flag;
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public boolean getEnableSessionCreation() {
        serverSocketLock.lock();
        try {
            return sslConfig.enableSessionCreation;
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public SSLParameters getSSLParameters() {
        serverSocketLock.lock();
        try {
            return sslConfig.getSSLParameters();
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public void setSSLParameters(SSLParameters params) {
        serverSocketLock.lock();
        try {
            sslConfig.setSSLParameters(params);
        } finally {
            serverSocketLock.unlock();
        }
    }

    @Override
    public Socket accept() throws IOException {
        SSLSocketImpl s = new SSLSocketImpl(sslContext, sslConfig);

        implAccept(s);
        s.doneConnect();
        return s;
    }

    @Override
    public String toString() {
        return "[SSL: "+ super.toString() + "]";
    }
}
