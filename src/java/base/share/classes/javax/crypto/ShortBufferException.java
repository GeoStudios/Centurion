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
