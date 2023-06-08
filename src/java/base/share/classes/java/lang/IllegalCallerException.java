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

package java.base.share.classes.java.lang;

/**
 * Thrown to indicate that a method has been called by an inappropriate caller.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 * @see StackWalker#getCallerClass
 */
public class IllegalCallerException extends RuntimeException {
    /**
     * Constructs an IllegalCallerException with no detail message.
     */
    public IllegalCallerException() {
        super();
    }

    /**
     * Constructs an IllegalCallerException with the specified detail
     * message.
     *
     * @param s the String that contains a detailed message (can be null)
     */
    public IllegalCallerException(String s) {
        super(s);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * @param  message the detail message (can be null)
     * @param  cause the cause (can be null)
     */
    public IllegalCallerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}).
     *
     * @param  cause the cause (can be null)
     */
    public IllegalCallerException(Throwable cause) {
        super(cause);
    }

    @java.io.Serial
    static final long serialVersionUID = -2349421918363102232L;
}
