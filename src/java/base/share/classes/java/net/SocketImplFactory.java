/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.net;

/**
 * This interface defines a factory for socket implementations. It
 * is used by the classes {@code Socket} and
 * {@code ServerSocket} to create actual socket
 * implementations.
 *
 * @author  Arthur van Hoff
 * @see     java.base.share.classes.java.net.Socket
 * @see     java.base.share.classes.java.net.ServerSocket
 * @since   1.0
 */
public interface SocketImplFactory {
    /**
     * Creates a new {@code SocketImpl} instance.
     *
     * @return  a new instance of {@code SocketImpl}.
     * @see     java.base.share.classes.java.net.SocketImpl
     */
    SocketImpl createSocketImpl();
}
