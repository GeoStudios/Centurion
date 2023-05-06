/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown when a stack overflow occurs because an application
 * recurses too deeply.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class StackOverflowError extends VirtualMachineError {
    @java.io.Serial
    private static final long serialVersionUID = 8609175038441759607L;

    /**
     * Constructs a {@code StackOverflowError} with no detail message.
     */
    public StackOverflowError() {
        super();
    }

    /**
     * Constructs a {@code StackOverflowError} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public StackOverflowError(String s) {
        super(s);
    }
}
