/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
