/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.security;

/**
 * This exception is thrown if an entry in the keystore cannot be recovered.
 *
 *
 * @since 1.5
 */

public class UnrecoverableEntryException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = -4527142945246286535L;

    /**
     * Constructs an {@code UnrecoverableEntryException} with no detail message.
     */
    public UnrecoverableEntryException() {
        super();
    }

    /**
     * Constructs an {@code UnrecoverableEntryException} with the specified
     * detail message, which provides more information about why this exception
     * has been thrown.
     *
     * @param msg the detail message.
     */
   public UnrecoverableEntryException(String msg) {
       super(msg);
    }
}
