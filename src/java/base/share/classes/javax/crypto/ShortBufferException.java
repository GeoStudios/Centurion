/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.javax.crypto;

import java.security.GeneralSecurityException;

/**
 * This exception is thrown when an output buffer provided by the user
 * is too short to hold the operation result.
 *
 * @author Jan Luehe
 *
 * @since 1.4
 */

public class ShortBufferException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = 8427718640832943747L;

    /**
     * Constructs a {@code ShortBufferException} with no detail
     * message. A detail message is a {@code String}  that describes this
     * particular exception.
     */
    public ShortBufferException() {
        super();
    }

    /**
     * Constructs a {@code ShortBufferException} with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ShortBufferException(String msg) {
        super(msg);
    }
}
