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
 *
 * This class represents a Socket Address with no protocol attachment.
 * As an abstract class, it is meant to be subclassed with a specific,
 * protocol dependent, implementation.
 * <p>
 * It provides an immutable object used by sockets for binding, connecting, or
 * as returned values.
 *
 * @see java.base.share.classes.java.net.Socket
 * @see java.base.share.classes.java.net.ServerSocket
 * @since 1.4
 */
public abstract class SocketAddress implements java.io.Serializable {

    @java.io.Serial
    static final long serialVersionUID = 5215720748342549866L;

    /**
     * Constructor for subclasses to call.
     */
    public SocketAddress() {}
}
