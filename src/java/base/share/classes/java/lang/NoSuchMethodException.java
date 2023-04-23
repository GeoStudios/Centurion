/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.lang;

/**
 * Thrown when a particular method cannot be found.
 *
 * @since      1.0
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
