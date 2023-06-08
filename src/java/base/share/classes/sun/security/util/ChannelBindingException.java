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
