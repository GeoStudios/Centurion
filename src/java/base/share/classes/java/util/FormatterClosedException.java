/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.util;

/**
 * Unchecked exception thrown when the formatter has been closed.
 *
 * <p> Unless otherwise specified, passing a {@code null} argument to any
 * method or constructor in this class will cause a {@link
 * NullPointerException} to be thrown.
 *
 * @since 1.5
 */
public class FormatterClosedException extends IllegalStateException {

    @java.io.Serial
    private static final long serialVersionUID = 18111216L;

    /**
     * Constructs an instance of this class.
     */
    public FormatterClosedException() { }
}
