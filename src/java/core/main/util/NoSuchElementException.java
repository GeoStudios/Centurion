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

package java.core.main.util;

import java.core.main.io.Serial;
import java.core.main.lang.RuntimeException;

/**
 * Thrown by various accessor methods to indicate that the element being requested
 * does not exist.
 *
 * @see java.util.Enumeration#nextElement()
 * @see java.core.main.util.Iterator#next()
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */

public class NoSuchElementException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6769829250639411880L;

    /**
     * Constructs a {@code NoSuchElementException} with {@code null}
     * as its error message string.
     */
    public NoSuchElementException() {
        super();
    }
}