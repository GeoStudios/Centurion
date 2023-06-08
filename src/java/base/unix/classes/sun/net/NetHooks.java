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

package java.base.unix.classes.sun.net;

import java.net.InetAddress;
import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Defines static methods to be invoked prior to binding or connecting TCP sockets.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public final class NetHooks {

    /**
     * A provider with hooks to allow sockets be converted prior to binding or
     * connecting a TCP socket.
     *
     * <p> Concrete implementations of this class should define a zero-argument
     * constructor and implement the abstract methods specified below.
     */
    public abstract static class Provider {
        /**
         * Initializes a new instance of this class.
         */
        protected Provider() {}

        /**
         * Invoked prior to binding a TCP socket.
         */
        public abstract void implBeforeTcpBind(FileDescriptor fdObj,
                                               InetAddress address,
                                               int port)
            throws IOException;

        /**
         * Invoked prior to connecting an unbound TCP socket.
         */
        public abstract void implBeforeTcpConnect(FileDescriptor fdObj,
                                                 InetAddress address,
                                                 int port)
            throws IOException;
    }

    /**
     * For now, we load the SDP provider on Solaris. In the future this may
     * be changed to use the ServiceLoader facility to allow the deployment of
     * other providers.
     */
    private static final Provider provider = new sun.net.sdp.SdpProvider();

    /**
     * Invoke prior to binding a TCP socket.
     */
    public static void beforeTcpBind(FileDescriptor fdObj,
                                     InetAddress address,
                                     int port)
        throws IOException
    {
        provider.implBeforeTcpBind(fdObj, address, port);
    }

    /**
     * Invoke prior to connecting an unbound TCP socket.
     */
    public static void beforeTcpConnect(FileDescriptor fdObj,
                                        InetAddress address,
                                        int port)
        throws IOException
    {
        provider.implBeforeTcpConnect(fdObj, address, port);
    }
}
