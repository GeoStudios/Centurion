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

package java.base.share.classes.java.security.spec;

import java.security.GeneralSecurityException;

/**
 * This is the exception for invalid key specifications.
 *
 * @author Jan Luehe
 *
 *
 * @see KeySpec
 *
 * @since 1.2
 */

public class InvalidKeySpecException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = 3546139293998810778L;

    /**
     * Constructs an InvalidKeySpecException with no detail message. A
     * detail message is a String that describes this particular
     * exception.
     */
    public InvalidKeySpecException() {
        super();
    }

    /**
     * Constructs an InvalidKeySpecException with the specified detail
     * message. A detail message is a String that describes this
     * particular exception.
     *
     * @param msg the detail message.
     */
    public InvalidKeySpecException(String msg) {
        super(msg);
    }

    /**
     * Creates an {@code InvalidKeySpecException} with the specified
     * detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A {@code null} value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     * @since 1.5
     */
    public InvalidKeySpecException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates an {@code InvalidKeySpecException} with the specified cause
     * and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A {@code null} value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     * @since 1.5
     */
    public InvalidKeySpecException(Throwable cause) {
        super(cause);
    }
}
