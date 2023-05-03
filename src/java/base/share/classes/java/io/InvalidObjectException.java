/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.io;

/**
 * Indicates that one or more deserialized objects failed validation
 * tests.  The argument should provide the reason for the failure.
 *
 * @see ObjectInputValidation
 * @since 1.1
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class InvalidObjectException extends ObjectStreamException {

    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = 3233174318281839583L;

    /**
     * Constructs an {@code InvalidObjectException}.
     * @param reason Detailed message explaining the reason for the failure.
     *
     * @see ObjectInputValidation
     */
    public InvalidObjectException(String reason) {
        super(reason);
    }

    /**
     * Constructs an {@code InvalidObjectException} with the given
     * reason and cause.
     *
     * @param reason Detailed message explaining the reason for the failure.
     * @param cause the cause
     *
     * @see ObjectInputValidation
     * @since 19
     */
    public InvalidObjectException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
