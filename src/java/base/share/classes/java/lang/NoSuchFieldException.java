/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Signals that the class doesn't have a field of a specified name.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class NoSuchFieldException extends ReflectiveOperationException {
    @java.io.Serial
    private static final long serialVersionUID = -6143714805279938260L;

    /**
     * Constructor.
     */
    public NoSuchFieldException() {
        super();
    }

    /**
     * Constructor with a detail message.
     *
     * @param s the detail message
     */
    public NoSuchFieldException(String s) {
        super(s);
    }
}
