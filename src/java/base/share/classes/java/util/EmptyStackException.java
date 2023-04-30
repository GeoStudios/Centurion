/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.util;

/**
 * Thrown by methods in the {@code Stack} class to indicate
 * that the stack is empty.
 *
 * @author  Jonathan Payne
 * @see     java.base.share.classes.java.util.Stack
 * @since   1.0
 */
public class EmptyStackException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = 5084686378493302095L;

    /**
     * Constructs a new {@code EmptyStackException} with {@code null}
     * as its error message string.
     */
    public EmptyStackException() {
    }
}
