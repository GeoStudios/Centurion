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

package java.base.share.classes.java.lang.reflect;


/**
 * Thrown when a syntactically malformed signature attribute is
 * encountered by a reflective method that needs to interpret the generic
 * signature information for a class or interface, method or constructor.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public class GenericSignatureFormatError extends ClassFormatError {
    @java.io.Serial
    private static final long serialVersionUID = 6709919147137911034L;

    /**
     * Constructs a new {@code GenericSignatureFormatError}.
     *
     */
    public GenericSignatureFormatError() {
        super();
    }

    /**
     * Constructs a new {@code GenericSignatureFormatError} with the
     * specified message.
     *
     * @param message the detail message, may be {@code null}
     */
    public GenericSignatureFormatError(String message) {
        super(message);
    }
}
