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
 * Thrown when Java language access checks cannot be suppressed.
 *
 * @see AccessibleObject#setAccessible(boolean)
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public class InaccessibleObjectException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = 4158786093378140901L;

    /**
     * Constructs an {@code InaccessibleObjectException} with no detail message.
     */
    public InaccessibleObjectException() {
    }

    /**
     * Constructs an {@code InaccessibleObjectException} with the given detail
     * message.
     *
     * @param msg
     *        The detail message
     */
    public InaccessibleObjectException(String msg) {
        super(msg);
    }

}
