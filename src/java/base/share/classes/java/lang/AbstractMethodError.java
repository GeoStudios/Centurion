/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown when an application tries to call an abstract method.
 * Normally, this error is caught by the compiler; this error can
 * only occur at run time if the definition of some class has
 * incompatibly changed since the currently executing method was last
 * compiled.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class AbstractMethodError extends IncompatibleClassChangeError {
    @java.io.Serial
    private static final long serialVersionUID = -1654391082989018462L;

    /**
     * Constructs an {@code AbstractMethodError} with no detail  message.
     */
    public AbstractMethodError() {
        super();
    }

    /**
     * Constructs an {@code AbstractMethodError} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public AbstractMethodError(String s) {
        super(s);
    }
}
