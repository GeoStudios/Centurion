/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.java.util;

/**
 * Thrown by a {@code Scanner} to indicate that the token
 * retrieved does not match the pattern for the expected type, or
 * that the token is out of range for the expected type.
 *
 * @see     java.base.share.classes.java.util.Scanner
 * @since   1.5
 */
public class InputMismatchException extends NoSuchElementException {
    @java.io.Serial
    private static final long serialVersionUID = 8811230760997066428L;

    /**
     * Constructs an {@code InputMismatchException} with {@code null}
     * as its error message string.
     */
    public InputMismatchException() {
        super();
    }

    /**
     * Constructs an {@code InputMismatchException}, saving a reference
     * to the error message string {@code s} for later retrieval by the
     * {@code getMessage} method.
     *
     * @param   s   the detail message.
     */
    public InputMismatchException(String s) {
        super(s);
    }
}
