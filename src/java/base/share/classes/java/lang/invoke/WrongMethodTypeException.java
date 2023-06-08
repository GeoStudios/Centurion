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

package java.base.share.classes.java.lang.invoke;

/**
 * Thrown to indicate that code has attempted to call a method handle
 * via the wrong method type.  As with the bytecode representation of
 * normal Java method calls, method handle calls are strongly typed
 * to a specific type descriptor associated with a call site.
 * <p>
 * This exception may also be thrown when two method handles are
 * composed, and the system detects that their types cannot be
 * matched up correctly.  This amounts to an early evaluation
 * of the type mismatch, at method handle construction time,
 * instead of when the mismatched method handle is called.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public class WrongMethodTypeException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = 292L;

    /**
     * Constructs a {@code WrongMethodTypeException} with no detail message.
     */
    public WrongMethodTypeException() {
        super();
    }

    /**
     * Constructs a {@code WrongMethodTypeException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public WrongMethodTypeException(String s) {
        super(s);
    }

    /**
     * Constructs a {@code WrongMethodTypeException} with the specified
     * detail message and cause.
     *
     * @param s the detail message.
     * @param cause the cause of the exception, or null.
     */
    //FIXME: make this public in MR1
    /*non-public*/
    WrongMethodTypeException(String s, Throwable cause) {
        super(s, cause);
    }

    /**
     * Constructs a {@code WrongMethodTypeException} with the specified
     * cause.
     *
     * @param cause the cause of the exception, or null.
     */
    //FIXME: make this public in MR1
    /*non-public*/
    WrongMethodTypeException(Throwable cause) {
        super(cause);
    }
}
