/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */


package java.base.share.classes.javax.net.ssl;

import java.io.IOException;

/**
 * Indicates some kind of error detected by an SSL subsystem.
 * This class is the general class of exceptions produced
 * by failed SSL-related operations.
 *
 * @since 1.4
 * @author David Brownell
 */
public class SSLException extends IOException {
    @java.io.Serial
    private static final long serialVersionUID = 4511006460650708967L;

    /**
     * Constructs an exception reporting an error found by
     * an SSL subsystem.
     *
     * @param reason describes the problem.
     */
    public SSLException(String reason) {
        super(reason);
    }

    /**
     * Creates a {@code SSLException} with the specified
     * detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval
     *          by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     *          {@link #getCause()} method).  (A {@code null} value is
     *          permitted, and indicates that the cause is nonexistent or
     *          unknown.)
     * @since 1.5
     */
    public SSLException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a {@code SSLException} with the specified cause
     * and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *          {@link #getCause()} method).  (A {@code null} value is
     *          permitted, and indicates that the cause is nonexistent or
     *          unknown.)
     * @since 1.5
     */
    public SSLException(Throwable cause) {
        super(cause);
    }
}
