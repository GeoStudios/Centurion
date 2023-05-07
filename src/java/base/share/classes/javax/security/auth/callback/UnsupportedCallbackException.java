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

package java.base.share.classes.javax.security.auth.callback;

/**
 * Signals that a {@code CallbackHandler} does not
 * recognize a particular {@code Callback}.
 *
 * @since 1.4
 */
public class UnsupportedCallbackException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = -6873556327655666839L;

    /**
     * @serial
     */
    @SuppressWarnings("serial") // Not statically typed as Serializable
    private final Callback callback;

    /**
     * Constructs an {@code UnsupportedCallbackException}
     * with no detail message.
     *
     * @param callback the unrecognized {@code Callback}.
     */
    public UnsupportedCallbackException(Callback callback) {
        super();
        this.callback = callback;
    }

    /**
     * Constructs a UnsupportedCallbackException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param callback the unrecognized {@code Callback}.
     *
     * @param msg the detail message.
     */
    public UnsupportedCallbackException(Callback callback, String msg) {
        super(msg);
        this.callback = callback;
    }

    /**
     * Get the unrecognized {@code Callback}.
     *
     * @return the unrecognized {@code Callback}.
     */
    public Callback getCallback() {
        return callback;
    }
}
