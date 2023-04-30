/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.javax.net.ssl;

/**
 * Indicates that the client and server could not negotiate the
 * desired level of security.  The connection is no longer usable.
 *
 * @since 1.4
 * @author David Brownell
 */
public class SSLHandshakeException extends SSLException {
    @java.io.Serial
    private static final long serialVersionUID = -5045881315018326890L;

    /**
     * Constructs an exception reporting an error found by
     * an SSL subsystem during handshaking.
     *
     * @param reason describes the problem.
     */
    public SSLHandshakeException(String reason) {
        super(reason);
    }

    /**
     * Creates a {@code SSLHandshakeException} with the specified detail
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
    public SSLHandshakeException(String message, Throwable cause) {
        super(message, cause);
    }
}
