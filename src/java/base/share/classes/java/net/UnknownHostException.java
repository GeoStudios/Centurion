/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.net;

import java.io.IOException;

/**
 * Thrown to indicate that the IP address of a host could not be determined.
 *
 * @author  Jonathan Payne
 * @since   1.0
 */
public class UnknownHostException extends IOException {
    @java.io.Serial
    private static final long serialVersionUID = -4639126076052875403L;

    /**
     * Constructs a new {@code UnknownHostException} with the
     * specified detail message.
     *
     * @param   message   the detail message.
     */
    public UnknownHostException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code UnknownHostException} with no detail
     * message.
     */
    public UnknownHostException() {
    }
}
