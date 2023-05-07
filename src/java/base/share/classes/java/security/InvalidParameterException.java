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
 * This exception, designed for use by the JCA/JCE engine classes,
 * is thrown when an invalid parameter is passed
 * to a method.
 *
 * @author Benjamin Renaud
 * @since 1.1
 */

public class InvalidParameterException extends IllegalArgumentException {

    @java.io.Serial
    private static final long serialVersionUID = -857968536935667808L;

    /**
     * Constructs an {@code InvalidParameterException} with no detail message.
     * A detail message is a {@code String} that describes this particular
     * exception.
     */
    public InvalidParameterException() {
        super();
    }

    /**
     * Constructs an {@code InvalidParameterException} with the specified
     * detail message.  A detail message is a {@code String} that describes
     * this particular exception.
     *
     * @param msg the detail message.
     */
    public InvalidParameterException(String msg) {
        super(msg);
    }

    /**
     * Constructs an {@code InvalidParameterException} with the specified
     * detail message and cause. A detail message is a {@code String}
     * that describes this particular exception.
     *
     * <p>Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     *
     * @param  msg the detail message (which is saved for later retrieval
     *         by the {@link Throwable#getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method). (A {@code null} value
     *         is permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     *
     * @since  20
     */
    public InvalidParameterException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructs an {@code InvalidParameterException} with the specified cause
     * and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables.
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method). (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     *
     * @since  20
     */
    public InvalidParameterException(Throwable cause) {
        super(cause);
    }
}
