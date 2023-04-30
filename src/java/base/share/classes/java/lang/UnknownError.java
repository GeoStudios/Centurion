/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown when an unknown but serious exception has occurred in the
 * Java Virtual Machine.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class UnknownError extends VirtualMachineError {
    @java.io.Serial
    private static final long serialVersionUID = 2524784860676771849L;

    /**
     * Constructs an {@code UnknownError} with no detail message.
     */
    public UnknownError() {
        super();
    }

    /**
     * Constructs an {@code UnknownError} with the specified detail
     * message.
     *
     * @param   s   the detail message.
     */
    public UnknownError(String s) {
        super(s);
    }
}
