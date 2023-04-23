/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */


package javax.net.ssl;

/**
 * Reports an error in the operation of the SSL protocol.  Normally
 * this indicates a flaw in one of the protocol implementations.
 *
 * @since 1.4
 * @author David Brownell
 */
public class SSLProtocolException extends SSLException {
    @java.io.Serial
    private static final long serialVersionUID = 5445067063799134928L;

    /**
     * Constructs an exception reporting an SSL protocol error
     * detected by an SSL subsystem.
     *
     * @param reason describes the problem.
     */
    public SSLProtocolException(String reason) {
        super(reason);
    }

    /**
     * Creates a {@code SSLProtocolException} with the specified detail
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
    public SSLProtocolException(String message, Throwable cause) {
        super(message, cause);
    }
}
