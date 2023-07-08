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

package java.base.share.classes.java.lang;

/**
 * Thrown if an application tries to create an array with negative size.
 *
 */
public class NegativeArraySizeException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -8960118058596991861L;

    /**
     * Constructs a {@code NegativeArraySizeException} with no
     * detail message.
     */
    public NegativeArraySizeException() {
        super();
    }

    /**
     * Constructs a {@code NegativeArraySizeException} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public NegativeArraySizeException(String s) {
        super(s);
    }
}
