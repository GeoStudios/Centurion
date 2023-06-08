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
 * Thrown by methods in {@link Locale} and {@link Locale.Builder} to
 * indicate that an argument is not a well-formed BCP 47 tag.
 *
 * @see Locale
 * @since 1.7
 */
public class IllformedLocaleException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = -5245986824925681401L;

    private int _errIdx = -1;

    /**
     * Constructs a new {@code IllformedLocaleException} with no
     * detail message and -1 as the error index.
     */
    public IllformedLocaleException() {
        super();
    }

    /**
     * Constructs a new {@code IllformedLocaleException} with the
     * given message and -1 as the error index.
     *
     * @param message the message
     */
    public IllformedLocaleException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code IllformedLocaleException} with the
     * given message and the error index.  The error index is the approximate
     * offset from the start of the ill-formed value to the point where the
     * parse first detected an error.  A negative error index value indicates
     * either the error index is not applicable or unknown.
     *
     * @param message the message
     * @param errorIndex the index
     */
    public IllformedLocaleException(String message, int errorIndex) {
        super(message + ((errorIndex < 0) ? "" : " [at index " + errorIndex + "]"));
        _errIdx = errorIndex;
    }

    /**
     * Returns the index where the error was found. A negative value indicates
     * either the error index is not applicable or unknown.
     *
     * @return the error index
     */
    public int getErrorIndex() {
        return _errIdx;
    }
}
