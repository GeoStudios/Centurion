/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown if an application tries to create an array with negative size.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class NegativeArraySizeException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -8960118058596991861L;

    /**
     * Constructs a {@code NegativeArraySizeException} with no
     * detail message.
     */
    public NegativeArraySizeException() {
        super();
    }

    /**
     * Constructs a {@code NegativeArraySizeException} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public NegativeArraySizeException(String s) {
        super(s);
    }
}
