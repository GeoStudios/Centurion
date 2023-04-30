/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown when a particular method cannot be found.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class NoSuchMethodException extends ReflectiveOperationException {
    @java.io.Serial
    private static final long serialVersionUID = 5034388446362600923L;

    /**
     * Constructs a {@code NoSuchMethodException} without a detail message.
     */
    public NoSuchMethodException() {
        super();
    }

    /**
     * Constructs a {@code NoSuchMethodException} with a detail message.
     *
     * @param      s   the detail message.
     */
    public NoSuchMethodException(String s) {
        super(s);
    }
}
