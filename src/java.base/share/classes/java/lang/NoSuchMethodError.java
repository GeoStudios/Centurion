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
 * Thrown if an application tries to call a specified method of a
 * class (either static or instance), and that class no longer has a
 * definition of that method.
 * <p>
 * Normally, this error is caught by the compiler; this error can
 * only occur at run time if the definition of a class has
 * incompatibly changed.
 *
 */
public class NoSuchMethodError extends IncompatibleClassChangeError {
    @java.io.Serial
    private static final long serialVersionUID = -3765521442372831335L;

    /**
     * Constructs a {@code NoSuchMethodError} with no detail message.
     */
    public NoSuchMethodError() {
        super();
    }

    /**
     * Constructs a {@code NoSuchMethodError} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public NoSuchMethodError(String s) {
        super(s);
    }
}