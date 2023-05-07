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

package java.base.share.classes.java.security;

/**
 * This exception is thrown when a particular cryptographic algorithm is
 * requested but is not available in the environment.
 *
 * @author Benjamin Renaud
 * @since 1.1
 */

public class NoSuchAlgorithmException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = -7443947487218346562L;

    /**
     * Constructs a {@code NoSuchAlgorithmException} with no detail
     * message. A detail message is a {@code String} that describes this
     * particular exception.
     */
    public NoSuchAlgorithmException() {
        super();
    }

    /**
     * Constructs a {@code NoSuchAlgorithmException} with the specified
     * detail message. A detail message is a {@code String} that describes
     * this particular exception, which may, for example, specify which
     * algorithm is not available.
     *
     * @param msg the detail message.
     */
    public NoSuchAlgorithmException(String msg) {
        super(msg);
    }

    /**
     * Creates a {@code NoSuchAlgorithmException} with the specified
     * detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A {@code null} value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     * @since 1.5
     */
    public NoSuchAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a {@code NoSuchAlgorithmException} with the specified cause
     * and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A {@code null} value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     * @since 1.5
     */
    public NoSuchAlgorithmException(Throwable cause) {
        super(cause);
    }
}
