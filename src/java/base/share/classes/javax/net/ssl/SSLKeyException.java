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

/**
 * Reports a bad SSL key.  Normally, this indicates misconfiguration
 * of the server or client SSL certificate and private key.
 *
 * @since 1.4
 * @author David Brownell
 */
public class SSLKeyException extends SSLException {
    @java.io.Serial
    private static final long serialVersionUID = -8071664081941937874L;

    /**
     * Constructs an exception reporting a key management error
     * found by an SSL subsystem.
     *
     * @param reason describes the problem.
     */
    public SSLKeyException(String reason) {
        super(reason);
    }

    /**
     * Creates a {@code SSLKeyException} with the specified detail
     * message and cause.
     *
     * @param message the detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A {@code null} value is
     *        permitted, and indicates that the cause is nonexistent or
     *        unknown.)
     * @since 19
     */
    public SSLKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
