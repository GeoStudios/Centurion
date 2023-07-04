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

package javax.print.attribute;

import java.io.Serial;

/**
 * Thrown to indicate that the requested operation cannot be performed because
 * the set is unmodifiable.
 *
 */
public class UnmodifiableSetException extends RuntimeException {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 2255250308571511731L;

    /**
     * Constructs an {@code UnsupportedOperationException} with no detail
     * message.
     */
    public UnmodifiableSetException() {
    }

    /**
     * Constructs an {@code UnmodifiableSetException} with the specified detail
     * message.
     *
     * @param  message the detail message
     */
    public UnmodifiableSetException(String message) {
        super(message);
    }
}
