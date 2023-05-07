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

package java.base.share.classes.java.net;

import java.io.IOException;

/**
 * Thrown to indicate that there is an error in the underlying
 * protocol, such as a TCP error.
 *
 * @author  Chris Warth
 * @since   1.0
 */
public class ProtocolException extends IOException {
    @java.io.Serial
    private static final long serialVersionUID = -6098449442062388080L;

    /**
     * Constructs a new {@code ProtocolException} with the
     * specified detail message.
     *
     * @param   message   the detail message.
     */
    public ProtocolException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ProtocolException} with no detail message.
     */
    public ProtocolException() {
    }
}
