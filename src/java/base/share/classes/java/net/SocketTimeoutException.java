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
 * Signals that a timeout has occurred on a socket read or accept.
 *
 * @since   1.4
 */

public class SocketTimeoutException extends java.io.InterruptedIOException {
    @java.io.Serial
    private static final long serialVersionUID = -8846654841826352300L;

    /**
     * Constructs a new SocketTimeoutException with a detail
     * message.
     * @param msg the detail message
     */
    public SocketTimeoutException(String msg) {
        super(msg);
    }

    /**
     * Construct a new SocketTimeoutException with no detailed message.
     */
    public SocketTimeoutException() {}
}
