/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */


package java.base.share.classes.java.lang.reflect;

/**
 * Thrown when a semantically malformed parameterized type is
 * encountered by a reflective method that needs to instantiate it.
 * For example, if the number of type arguments to a parameterized type
 * is wrong.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public class MalformedParameterizedTypeException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -5696557788586220964L;

    /**
     * Constructs a {@code MalformedParameterizedTypeException} with
     * no detail message.
     */
    public MalformedParameterizedTypeException() {
        super();
    }

    /**
     * Constructs a {@code MalformedParameterizedTypeException} with
     * the given detail message.
     * @param message the detail message; may be {@code null}
     */
    public MalformedParameterizedTypeException(String message) {
        super(message);
    }
}
