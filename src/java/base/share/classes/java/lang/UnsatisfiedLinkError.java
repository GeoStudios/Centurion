/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown if the Java Virtual Machine cannot find an appropriate
 * native-language definition of a method declared {@code native}.
 *
 * @see     java.base.share.classes.java.lang.Runtime
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class UnsatisfiedLinkError extends LinkageError {
    @java.io.Serial
    private static final long serialVersionUID = -4019343241616879428L;

    /**
     * Constructs an {@code UnsatisfiedLinkError} with no detail message.
     */
    public UnsatisfiedLinkError() {
        super();
    }

    /**
     * Constructs an {@code UnsatisfiedLinkError} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public UnsatisfiedLinkError(String s) {
        super(s);
    }
}
