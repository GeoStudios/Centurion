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
 * Thrown to indicate that the {@code clone} method in class
 * {@code Object} has been called to clone an object, but that
 * the object's class does not implement the {@code Cloneable}
 * interface.
 * <p>
 * Applications that override the {@code clone} method can also
 * throw this exception to indicate that an object could not or
 * should not be cloned.
 *
 * @see     java.base.share.classes.java.lang.Cloneable
 * @see     java.base.share.classes.java.lang.Object#clone()
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class CloneNotSupportedException extends Exception {
    @java.io.Serial
    private static final long serialVersionUID = 5195511250079656443L;

    /**
     * Constructs a {@code CloneNotSupportedException} with no
     * detail message.
     */
    public CloneNotSupportedException() {
        super();
    }

    /**
     * Constructs a {@code CloneNotSupportedException} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public CloneNotSupportedException(String s) {
        super(s);
    }
}
