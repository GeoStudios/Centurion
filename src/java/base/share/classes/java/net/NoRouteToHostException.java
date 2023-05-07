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
 * Signals that an error occurred while attempting to connect a
 * socket to a remote address and port.  Typically, the remote
 * host cannot be reached because of an intervening firewall, or
 * if an intermediate router is down.
 *
 * @since   1.1
 */
public class NoRouteToHostException extends SocketException {
    @java.io.Serial
    private static final long serialVersionUID = -1897550894873493790L;

    /**
     * Constructs a new NoRouteToHostException with the specified detail
     * message as to why the remote host cannot be reached.
     * A detail message is a String that gives a specific
     * description of this error.
     * @param msg the detail message
     */
    public NoRouteToHostException(String msg) {
        super(msg);
    }

    /**
     * Construct a new NoRouteToHostException with no detailed message.
     */
    public NoRouteToHostException() {}
}
