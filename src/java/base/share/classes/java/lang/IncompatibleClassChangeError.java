/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown when an incompatible class change has occurred to some class
 * definition. The definition of some class, on which the currently
 * executing method depends, has since changed.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class IncompatibleClassChangeError extends LinkageError {
    @java.io.Serial
    private static final long serialVersionUID = -4914975503642802119L;

    /**
     * Constructs an {@code IncompatibleClassChangeError} with no
     * detail message.
     */
    public IncompatibleClassChangeError () {
        super();
    }

    /**
     * Constructs an {@code IncompatibleClassChangeError} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public IncompatibleClassChangeError(String s) {
        super(s);
    }
}
