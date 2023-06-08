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
 * Signals that an ICMP Port Unreachable message has been
 * received on a connected datagram.
 *
 * @since   1.4
 */

public class PortUnreachableException extends SocketException {
    @java.io.Serial
    private static final long serialVersionUID = 8462541992376507323L;

    /**
     * Constructs a new {@code PortUnreachableException} with a
     * detail message.
     * @param msg the detail message
     */
    public PortUnreachableException(String msg) {
        super(msg);
    }

    /**
     * Construct a new {@code PortUnreachableException} with no
     * detailed message.
     */
    public PortUnreachableException() {}
}
