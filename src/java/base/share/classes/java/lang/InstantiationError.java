/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
