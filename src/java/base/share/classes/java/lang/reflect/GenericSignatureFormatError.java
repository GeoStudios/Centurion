/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.reflect;


/**
 * Thrown when a syntactically malformed signature attribute is
 * encountered by a reflective method that needs to interpret the generic
 * signature information for a class or interface, method or constructor.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public class GenericSignatureFormatError extends ClassFormatError {
    @java.io.Serial
    private static final long serialVersionUID = 6709919147137911034L;

    /**
     * Constructs a new {@code GenericSignatureFormatError}.
     *
     */
    public GenericSignatureFormatError() {
        super();
    }

    /**
     * Constructs a new {@code GenericSignatureFormatError} with the
     * specified message.
     *
     * @param message the detail message, may be {@code null}
     */
    public GenericSignatureFormatError(String message) {
        super(message);
    }
}
