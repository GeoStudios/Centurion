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

package java.instrument.share.classes.java.lang.instrument;

/**
 * Thrown by an implementation of
 * {@link java.lang.instrument.ClassFileTransformer#transform ClassFileTransformer.transform}
 * when its input parameters are invalid.
 * This may occur either because the initial class file bytes were
 * invalid or a previously applied transform corrupted the bytes.
 *
 * @see     java.lang.instrument.ClassFileTransformer#transform
 */
public class IllegalClassFormatException extends Exception {
    private static final long serialVersionUID = -3841736710924794009L;

    /**
     * Constructs an <code>IllegalClassFormatException</code> with no
     * detail message.
     */
    public
    IllegalClassFormatException() {
        super();
    }

    /**
     * Constructs an <code>IllegalClassFormatException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public
    IllegalClassFormatException(String s) {
        super(s);
    }
}
