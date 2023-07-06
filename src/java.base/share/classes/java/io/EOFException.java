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

package java.base.share.classes.java.io;

/**
 * Signals that an end of file or end of stream has been reached
 * unexpectedly during input.
 * <p>
 * This exception is mainly used by data input streams to signal end of
 * stream. Note that many other input operations return a special value on
 * end of stream rather than throwing an exception.
 *
 * @see     java.io.DataInputStream
 * @see     java.io.IOException
 */
public class EOFException extends IOException {
    @Serial
    private static final long serialVersionUID = 6433858223774886977L;

    /**
     * Constructs an {@code EOFException} with {@code null}
     * as its error detail message.
     */
    public EOFException() {
        super();
    }

    /**
     * Constructs an {@code EOFException} with the specified detail
     * message. The string {@code s} may later be retrieved by the
     * {@link Throwable#getMessage} method of class
     * {@code java.lang.Throwable}.
     *
     * @param   s   the detail message.
     */
    public EOFException(String s) {
        super(s);
    }
}
