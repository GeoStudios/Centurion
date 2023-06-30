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

package java.core.java.io;

public abstract class ObjectStreamException extends IOException {

    @Serial
    private static final long serialVersionUID = 7260898174833392607L;

    /**
     * Create an ObjectStreamException with the specified argument.
     *
     * @param message the detailed message for the exception
     */
    protected ObjectStreamException(String message) {
        super(message);
    }

    /**
     * Create an ObjectStreamException with the specified message and
     * cause.
     *
     * @param message the detailed message for the exception
     * @param cause the cause
     * @since 19
     */
    protected ObjectStreamException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create an ObjectStreamException.
     */
    protected ObjectStreamException() {
        super();
    }

    /**
     * Create an ObjectStreamException with the specified cause.
     *
     * @param cause the cause
     * @since 19
     */
    protected ObjectStreamException(Throwable cause) {
        super(cause);
    }
}