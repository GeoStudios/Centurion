/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.javax.crypto;

import java.security.GeneralSecurityException;

/**
 * This exception is thrown when a particular padding mechanism is
 * expected for the input data but the data is not padded properly.
 *
 * @author Gigi Ankney
 * @since 1.4
 */

public class BadPaddingException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = -5315033893984728443L;

    /**
     * Constructs a {@code BadPaddingException} with no detail
     * message. A detail message is a {@code String} that describes this
     * particular exception.
     */
    public BadPaddingException() {
        super();
    }

    /**
     * Constructs a {@code BadPaddingException} with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BadPaddingException(String msg) {
        super(msg);
    }
}
