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
 * Thrown to indicate that the code has attempted to cast an object
 * to a subclass of which it is not an instance. For example, the
 * following code generates a {@code ClassCastException}:
 * <blockquote><pre>
 *     Object x = Integer.valueOf(0);
 *     System.out.println((String)x);
 * </pre></blockquote>
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class ClassCastException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -9223365651070458532L;

    /**
     * Constructs a {@code ClassCastException} with no detail message.
     */
    public ClassCastException() {
        super();
    }

    /**
     * Constructs a {@code ClassCastException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public ClassCastException(String s) {
        super(s);
    }
}
