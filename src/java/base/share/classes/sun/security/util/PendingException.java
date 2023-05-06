/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

/**
 * An exception that denotes that an operation is pending.
 * Currently used by LoginContext.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class PendingException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = -5201837247928788640L;

    /**
     * Constructs a PendingException with no detail message. A detail
     * message is a String that describes this particular exception.
     */
    public PendingException() {
        super();
    }

    /**
     * Constructs a PendingException with the specified detail message.
     * A detail message is a String that describes this particular
     * exception.
     *
     * @param msg the detail message.
     */
    public PendingException(String msg) {
        super(msg);
    }
}
