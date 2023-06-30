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

package java.core.main.lang;

import java.core.main.io.Serial;

/**
 * Thrown to indicate that the application has attempted to convert
 * a string to one of the numeric types, but that the string does not
 * have the appropriate format.
 *
 * @see Integer(String)
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */

public class NumberFormatException extends IllegalArgumentException {

    @Serial
    private static final long serialVersionUID = -2848938806368998894L;

    /**
     * Constructs a <code>NumberFormatException</code> with no detail message.
     */
    public NumberFormatException() {
        super();
    }

    /**
     * Constructs a <code>NumberFormatException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public NumberFormatException(String s) {
        super(s);
    }

    /**
     * Factory method for making a <code>NumberFormatException</code>
     * given the specified input which caused the error.
     *
     * @param   s   the input causing the error
     */
    static NumberFormatException forInputString(String s, int radix) {
        return new NumberFormatException("For input string: \"" + s + "\"" +
                (radix == 10 ?
                        "" :
                        " under radix " + radix));
    }

    /**
     * Factory method for making a {@code NumberFormatException}
     * given the specified input which caused the error.
     *
     * @param   s   the input causing the error
     * @param   beginIndex   the beginning index, inclusive.
     * @param   endIndex     the ending index, exclusive.
     * @param   errorIndex   the index of the first error in s
     */
    static NumberFormatException forCharSequence(CharSequence s, int beginIndex, int endIndex, int errorIndex) {
        return new NumberFormatException("Error at index "
                + (errorIndex - beginIndex) + " in: \""
                + s.subSequence(beginIndex, endIndex) + "\"");
    }
}