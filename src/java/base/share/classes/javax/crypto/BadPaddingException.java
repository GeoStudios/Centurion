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
