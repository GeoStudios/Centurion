/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.java.security;

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
