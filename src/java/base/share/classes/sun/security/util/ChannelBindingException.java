/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import java.security.GeneralSecurityException;

/**
 * Thrown by TlsChannelBinding if an error occurs
 */
public class ChannelBindingException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = -5021387249782788460L;

    /**
     * Constructs a ChannelBindingException with no detail message. A detail
     * message is a String that describes this particular exception.
     */
    public ChannelBindingException() {
        super();
    }

    /**
     * Constructs a ChannelBindingException with a detail message and
     * specified cause.
     */
    public ChannelBindingException(String msg, Exception e) {
        super(msg, e);
    }

    /**
     * Constructs a ChannelBindingException with a detail message
     */
    public ChannelBindingException(String msg) {
        super(msg);
    }
}
