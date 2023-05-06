/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.net;

/**
 * Signals that an error occurred while attempting to bind a
 * socket to a local address and port.  Typically, the port is
 * in use, or the requested local address could not be assigned.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public class BindException extends SocketException {
    @java.io.Serial
    private static final long serialVersionUID = -5945005768251722951L;

    /**
     * Constructs a new BindException with the specified detail
     * message as to why the bind error occurred.
     * A detail message is a String that gives a specific
     * description of this error.
     * @param msg the detail message
     */
    public BindException(String msg) {
        super(msg);
    }

    /**
     * Construct a new BindException with no detailed message.
     */
    public BindException() {}
}
