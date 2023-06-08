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

package java.base.share.classes.java.io;

/**
 * Superclass of all exceptions specific to Object Stream classes.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public abstract class ObjectStreamException extends IOException {

    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = 7260898174833392607L;

    /**
     * Create an ObjectStreamException with the specified argument.
     *
     * @param message the detailed message for the exception
     */
    protected ObjectStreamException(String message) {
        super(message);
    }

    /**
     * Create an ObjectStreamException with the specified message and
     * cause.
     *
     * @param message the detailed message for the exception
     * @param cause the cause
     * @since 19
     */
    protected ObjectStreamException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create an ObjectStreamException.
     */
    protected ObjectStreamException() {
        super();
    }

    /**
     * Create an ObjectStreamException with the specified cause.
     *
     * @param cause the cause
     * @since 19
     */
    protected ObjectStreamException(Throwable cause) {
        super(cause);
    }
}
