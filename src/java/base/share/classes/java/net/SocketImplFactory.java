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
