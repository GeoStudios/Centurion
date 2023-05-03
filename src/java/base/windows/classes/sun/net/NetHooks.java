/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.net;

import java.net.InetAddress;
import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Defines static methods to ensure that any installed net hooks are invoked
 * prior to binding or connecting TCP sockets.
 *
 * @since Java 1
 * @author Logan Abernathy
 * @edited 19/4/2023
 */

public final class NetHooks {

    /**
     * Invoke prior to binding a TCP socket.
     */
    public static void beforeTcpBind(FileDescriptor fdObj,
                                     InetAddress address,
                                     int port)
        throws IOException
    {
        // nothing to do
    }

    /**
     * Invoke prior to connecting an unbound TCP socket.
     */
    public static void beforeTcpConnect(FileDescriptor fdObj,
                                        InetAddress address,
                                        int port)
        throws IOException
    {
        // nothing to do
    }
}