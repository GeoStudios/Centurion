/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.security;

/**
 * This exception is thrown when a particular security provider is
 * requested but is not available in the environment.
 *
 * @author Benjamin Renaud
 * @since 1.1
 */

public class NoSuchProviderException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = 8488111756688534474L;

    /**
     * Constructs a {@code NoSuchProviderException} with no detail message. A
     * detail message is a {@code String} that describes this particular
     * exception.
     */
    public NoSuchProviderException() {
        super();
    }

    /**
     * Constructs a {@code NoSuchProviderException} with the specified detail
     * message. A detail message is a {@code String} that describes this
     * particular exception.
     *
     * @param msg the detail message.
     */
    public NoSuchProviderException(String msg) {
        super(msg);
    }
}
