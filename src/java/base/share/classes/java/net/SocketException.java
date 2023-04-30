/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.net;

import java.io.IOException;

/**
 * Thrown to indicate that there is an error creating or accessing a Socket.
 *
 * @author  Jonathan Payne
 * @since   1.0
 */
public class SocketException extends IOException {
    @java.io.Serial
    private static final long serialVersionUID = -5935874303556886934L;

    /**
     * Constructs a new {@code SocketException} with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SocketException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new {@code SocketException} with no detail message.
     */
    public SocketException() {
    }

    /**
     * Constructs a new {@code SocketException} with the
     * specified detail message and cause.
     *
     * @param msg the detail message.
     * @param cause the cause
     * @since 19
     */
    public SocketException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructs a new {@code SocketException} with the
     * specified cause.
     *
     * @param cause the cause
     * @since 19
     */
    public SocketException(Throwable cause) {
        super(cause);
    }
}
