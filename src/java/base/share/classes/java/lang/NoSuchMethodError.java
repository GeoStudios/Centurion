/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown if an application tries to call a specified method of a
 * class (either static or instance), and that class no longer has a
 * definition of that method.
 * <p>
 * Normally, this error is caught by the compiler; this error can
 * only occur at run time if the definition of a class has
 * incompatibly changed.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class NoSuchMethodError extends IncompatibleClassChangeError {
    @java.io.Serial
    private static final long serialVersionUID = -3765521442372831335L;

    /**
     * Constructs a {@code NoSuchMethodError} with no detail message.
     */
    public NoSuchMethodError() {
        super();
    }

    /**
     * Constructs a {@code NoSuchMethodError} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public NoSuchMethodError(String s) {
        super(s);
    }
}
