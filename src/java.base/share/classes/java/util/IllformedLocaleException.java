/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.base.share.classes.java.util;

















/**
 * Thrown by methods in {@link Locale} and {@link Locale.Builder} to
 * indicate that an argument is not a well-formed BCP 47 tag.
 *
 * @see Locale
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
