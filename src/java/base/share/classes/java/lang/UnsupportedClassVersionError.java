/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown when the Java Virtual Machine attempts to read a class
 * file and determines that the major and minor version numbers
 * in the file are not supported.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class UnsupportedClassVersionError extends ClassFormatError {
    @java.io.Serial
    private static final long serialVersionUID = -7123279212883497373L;

    /**
     * Constructs a {@code UnsupportedClassVersionError}
     * with no detail message.
     */
    public UnsupportedClassVersionError() {
        super();
    }

    /**
     * Constructs a {@code UnsupportedClassVersionError} with
     * the specified detail message.
     *
     * @param   s   the detail message.
     */
    public UnsupportedClassVersionError(String s) {
        super(s);
    }
}
