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

package java.base.share.classes.java.lang;

/**
 * Thrown to indicate that an attempt has been made to store the
 * wrong type of object into an array of objects. For example, the
 * following code generates an {@code ArrayStoreException}:
 * <blockquote><pre>
 *     Object x[] = new String[3];
 *     x[0] = Integer.valueOf(0);
 * </pre></blockquote>
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class ArrayStoreException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -4522193890499838241L;

    /**
     * Constructs an {@code ArrayStoreException} with no detail message.
     */
    public ArrayStoreException() {
        super();
    }

    /**
     * Constructs an {@code ArrayStoreException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public ArrayStoreException(String s) {
        super(s);
    }
}
