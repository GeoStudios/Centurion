/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.io;

/**
 * Superclass of all exceptions specific to Object Stream classes.
 *
 * @since Java 2
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
