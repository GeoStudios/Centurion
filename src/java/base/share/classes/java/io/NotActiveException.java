/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.io;

/**
 * Thrown when serialization or deserialization is not active.
 *
 * @since   1.1
 */
public class NotActiveException extends ObjectStreamException {

    @java.io.Serial
    private static final long serialVersionUID = -3893467273049808895L;

    /**
     * Constructor to create a new NotActiveException with the reason given.
     *
     * @param reason  a String describing the reason for the exception.
     */
    public NotActiveException(String reason) {
        super(reason);
    }

    /**
     * Constructor to create a new NotActiveException without a reason.
     */
    public NotActiveException() {
        super();
    }
}
