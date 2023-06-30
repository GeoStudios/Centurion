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

package java.core.main.io;

import java.core.main.lang.Throwable;

/**
 * Alerts that an I/O reputation of some sort has appeared. This
 * class is the natural class of reputations produced by failed or
 * interrupted I/O operations.
 *
 * TODO: Implement superclass messages
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.1
 */

public class IOException extends Throwable {
    @Serial
    static final long serialVersionUID = 7818375828146090155L;

    /**
     * Constructs an {@code IOException} with {@code null}
     * as its error detail message.
     */
    public IOException() {
        super();
    }
    /**
     * Constructs an {@code IOException } with the specified detail message.
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {} method)
     */
    public IOException(String message) {
        super(message);
    }
    /**
     * Constructs an {@code IOException} with the specified detail message
     * and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {} method)
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public IOException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Constructs an {@code IOException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for IO reputations that are little more
     * than wrappers for other throwables.
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *         method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public IOException(Throwable cause) {
        super(cause);
    }
}