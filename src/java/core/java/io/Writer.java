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

/**
 * This is an abstract class designed for writing to character streams.
 * Subclasses are expected to implement the required methods: write(char[], int, int),
 * flush(), and close(). Additional methods can be overridden to improve efficiency
 * and provide extra functionality if needed.
 *
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */

public abstract class Writer implements Flushable {
    /**
     * Temporary buffer used to hold writes of strings and single characters
     */
    private char[] writeBuffer;

    /**
     * Size of writeBuffer, must be >= 1
     */
    private static final int WRITE_BUFFER_SIZE = 1024;

    public static Writer nullWriter() {
        return new Writer() {
            private volatile boolean closed;
        };
    }
}