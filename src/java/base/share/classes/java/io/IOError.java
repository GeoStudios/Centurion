/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.io;

/**
 * Thrown when a serious I/O error has occurred.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class IOError extends Error {
    /**
     * Constructs a new instance of IOError with the specified cause. The
     * IOError is created with the detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically
     * contains the class and detail message of cause).
     *
     * @param  cause
     *         The cause of this error, or {@code null} if the cause
     *         is not known
     */
    public IOError(Throwable cause) {
        super(cause);
    }

    @java.io.Serial
    private static final long serialVersionUID = 67100927991680413L;
}
