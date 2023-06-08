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
 * Thrown when an application tries to use the Java {@code new}
 * construct to instantiate an abstract class or an interface.
 * <p>
 * Normally, this error is caught by the compiler; this error can
 * only occur at run time if the definition of a class has
 * incompatibly changed.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */


public class InstantiationError extends IncompatibleClassChangeError {
    @java.io.Serial
    private static final long serialVersionUID = -4885810657349421204L;

    /**
     * Constructs an {@code InstantiationError} with no detail  message.
     */
    public InstantiationError() {
        super();
    }

    /**
     * Constructs an {@code InstantiationError} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public InstantiationError(String s) {
        super(s);
    }
}
