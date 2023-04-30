/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
