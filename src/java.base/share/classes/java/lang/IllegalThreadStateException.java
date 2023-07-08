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
 * Thrown to indicate that a thread is not in an appropriate state
 * for the requested operation. See, for example, the
 * {@code suspend} and {@code resume} methods in class
 * {@code Thread}.
 *
 * @see     java.lang.Thread#resume()
 * @see     java.lang.Thread#suspend()
 */
public class IllegalThreadStateException extends IllegalArgumentException {
    @java.io.Serial
    private static final long serialVersionUID = -7626246362397460174L;

    /**
     * Constructs an {@code IllegalThreadStateException} with no
     * detail message.
     */
    public IllegalThreadStateException() {
        super();
    }

    /**
     * Constructs an {@code IllegalThreadStateException} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public IllegalThreadStateException(String s) {
        super(s);
    }
}
