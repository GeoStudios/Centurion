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
 * Thrown if an application attempts to access or modify a field, or
 * to call a method that it does not have access to.
 * <p>
 * Normally, this error is caught by the compiler; this error can
 * only occur at run time if the definition of a class has
 * incompatibly changed.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class IllegalAccessError extends IncompatibleClassChangeError {
    @java.io.Serial
    private static final long serialVersionUID = -8988904074992417891L;

    /**
     * Constructs an {@code IllegalAccessError} with no detail message.
     */
    public IllegalAccessError() {
        super();
    }

    /**
     * Constructs an {@code IllegalAccessError} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public IllegalAccessError(String s) {
        super(s);
    }
}
