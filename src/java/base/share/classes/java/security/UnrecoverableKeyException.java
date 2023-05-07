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
 * This exception is thrown if a key in the keystore cannot be recovered.
 *
 *
 * @since 1.2
 */

public class UnrecoverableKeyException extends UnrecoverableEntryException {

    @java.io.Serial
    private static final long serialVersionUID = 7275063078190151277L;

    /**
     * Constructs an {@code UnrecoverableKeyException} with no detail message.
     */
    public UnrecoverableKeyException() {
        super();
    }

    /**
     * Constructs an {@code UnrecoverableKeyException} with the specified detail
     * message, which provides more information about why this exception
     * has been thrown.
     *
     * @param msg the detail message.
     */
   public UnrecoverableKeyException(String msg) {
       super(msg);
    }
}
