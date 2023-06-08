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
 * Thrown to indicate that the Java Virtual Machine is broken or has
 * run out of resources necessary for it to continue operating.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public abstract class VirtualMachineError extends Error {
    @java.io.Serial
    private static final long serialVersionUID = 4161983926571568670L;

    /**
     * Constructs a {@code VirtualMachineError} with no detail message.
     */
    public VirtualMachineError() {
        super();
    }

    /**
     * Constructs a {@code VirtualMachineError} with the specified
     * detail message.
     *
     * @param   message   the detail message.
     */
    public VirtualMachineError(String message) {
        super(message);
    }

    /**
     * Constructs a {@code VirtualMachineError} with the specified
     * detail message and cause.  <p>Note that the detail message
     * associated with {@code cause} is <i>not</i> automatically
     * incorporated in this error's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.8
     */
    public VirtualMachineError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a {@code VirtualMachineError} with the specified
     * cause and a detail message of {@code (cause==null ? null :
     * cause.toString())} (which typically contains the class and
     * detail message of {@code cause}).
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.8
     */
    public VirtualMachineError(Throwable cause) {
        super(cause);
    }
}
