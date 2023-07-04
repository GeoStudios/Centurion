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

package java.util;

/**
 * Unchecked exception thrown when a character with an invalid Unicode code
 * point as defined by {@link Character#isValidCodePoint} is passed to the
 * {@link Formatter}.
 *
 * <p> Unless otherwise specified, passing a {@code null} argument to any
 * method or constructor in this class will cause a {@link
 * NullPointerException} to be thrown.
 *
 */
public class IllegalFormatCodePointException extends IllegalFormatException {

    @java.io.Serial
    private static final long serialVersionUID = 19080630L;

    private final int c;

    /**
     * Constructs an instance of this class with the specified illegal code
     * point as defined by {@link Character#isValidCodePoint}.
     *
     * @param  c
     *         The illegal Unicode code point
     */
    public IllegalFormatCodePointException(int c) {
        this.c = c;
    }

    /**
     * Returns the illegal code point as defined by {@link
     * Character#isValidCodePoint}.
     *
     * @return  The illegal Unicode code point
     */
    public int getCodePoint() {
        return c;
    }

    public String getMessage() {
        return String.format("Code point = %#x", c);
    }
}
