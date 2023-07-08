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
 * Unchecked exception thrown when the argument corresponding to the format
 * specifier is of an incompatible type.
 *
 * <p> Unless otherwise specified, passing a {@code null} argument to any
 * method or constructor in this class will cause a {@link
 * NullPointerException} to be thrown.
 *
 */
public class IllegalFormatConversionException extends IllegalFormatException {

    @java.io.Serial
    private static final long serialVersionUID = 17000126L;

    private final char c;
    private final Class<?> arg;

    /**
     * Constructs an instance of this class with the mismatched conversion and
     * the corresponding argument class.
     *
     * @param  c
     *         Inapplicable conversion
     *
     * @param  arg
     *         Class of the mismatched argument
     */
    public IllegalFormatConversionException(char c, Class<?> arg) {
        if (arg == null)
            throw new NullPointerException();
        this.c = c;
        this.arg = arg;
    }

    /**
     * Returns the inapplicable conversion.
     *
     * @return  The inapplicable conversion
     */
    public char getConversion() {
        return c;
    }

    /**
     * Returns the class of the mismatched argument.
     *
     * @return   The class of the mismatched argument
     */
    public Class<?> getArgumentClass() {
        return arg;
    }

    // javadoc inherited from Throwable.java
    public String getMessage() {
        return String.format("%c != %s", c, arg.getName());
    }
}
