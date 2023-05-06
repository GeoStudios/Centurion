/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown if an application tries to access or modify a specified
 * field of an object, and that object no longer has that field.
 * <p>
 * Normally, this error is caught by the compiler; this error can
 * only occur at run time if the definition of a class has
 * incompatibly changed.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class NoSuchFieldError extends IncompatibleClassChangeError {
    @java.io.Serial
    private static final long serialVersionUID = -3456430195886129035L;

    /**
     * Constructs a {@code NoSuchFieldError} with no detail message.
     */
    public NoSuchFieldError() {
        super();
    }

    /**
     * Constructs a {@code NoSuchFieldError} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public NoSuchFieldError(String s) {
        super(s);
    }
}
