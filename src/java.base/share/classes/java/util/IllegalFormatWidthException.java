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
 * Unchecked exception thrown when the format width is a negative value other
 * than {@code -1} or is otherwise unsupported. If a given format width is not
 * representable by an {@code int} type, then the value
 * {@code Integer.MIN_VALUE} will be used in the exception.
 *
 */
public class IllegalFormatWidthException extends IllegalFormatException {

    @java.io.Serial
    private static final long serialVersionUID = 16660902L;

    private final int w;

    /**
     * Constructs an instance of this class with the specified width.
     *
     * @param  w
     *         The width
     */
    public IllegalFormatWidthException(int w) {
        this.w = w;
    }

    /**
     * Returns the width. If the width is not representable by an {@code int},
     * then returns {@code Integer.MIN_VALUE}.
     *
     * @return  The width
     */
    public int getWidth() {
        return w;
    }

    public String getMessage() {
        return Integer.toString(w);
    }
}
