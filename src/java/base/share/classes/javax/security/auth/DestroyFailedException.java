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

package java.base.share.classes.javax.security.auth;

/**
 * Signals that a {@code destroy} operation failed.
 *
 * <p> This exception is thrown by credentials implementing
 * the {@code Destroyable} interface when the {@code destroy}
 * method fails.
 *
 * @since 1.4
 */
public class DestroyFailedException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = -7790152857282749162L;

    /**
     * Constructs a DestroyFailedException with no detail message. A detail
     * message is a String that describes this particular exception.
     */
    public DestroyFailedException() {
        super();
    }

    /**
     * Constructs a DestroyFailedException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param msg the detail message.
     */
    public DestroyFailedException(String msg) {
        super(msg);
    }
}
