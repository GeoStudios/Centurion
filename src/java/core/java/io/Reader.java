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
 * This abstract class provides a foundation for reading character streams.
 * Subclasses are required to implement the read(char[], int, int) and close()
 * methods, while also having the flexibility to override other methods defined
 * in this class. This allows subclasses to enhance efficiency, add additional
 * functionality, or achieve a combination of both. Such design enables greater
 * customization and optimization possibilities, allowing subclasses to tailor
 * their behavior according to specific requirements and maximize overall performance.
 *
 * @see java.core.java.io.ReaderBuffer
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */

public abstract class Reader {

    private static final int TRANSFER_BUFFER_SIZE = 8192;

    /**
     * This method creates a new instance of a {@code Reader} that does not read any characters.
     * The returned stream is initially open and can be closed by invoking the {@code close()} method.
     * It's important to note that subsequent calls to {@code close()} will have no effect as the stream
     * is already closed. This functionality provides flexibility in managing the reader and ensures
     * consistent behavior when handling cases where character reading is not required.
     *
     * <p> While the stream remains open, several methods such as {@code read()}, {@code read(char[])},
     * {@code read(char[], int, int)}, {@code read(CharBuffer)}, {@code ready()}, {@code skip(long)},
     * and {@code transferTo()} behave as if the end of the stream has been reached. However, upon closing
     * the stream, these methods will throw an {@code IOException} to indicate that the stream has been
     * closed and further operations are not allowed. This behavior ensures proper handling and notifies
     * the user when attempting to invoke these methods after the stream has been closed.
     *
     * <p> In terms of functionality, the {@code markSupported()} method returns {@code false} to indicate
     * that marking is not supported. Attempts to use the {@code mark()} and {@code reset()} methods will
     * result in an {@code IOException} being thrown. This behavior maintains consistency in stream operations
     * by clearly indicating the lack of support for marking and preventing any erroneous usage of the mark
     * and reset functionalities.
     *
     * <p> The specific object used for synchronization of operations on the returned {@code Reader} is intentionally
     * unspecified.
     *
     * @return a {@code Reader} instance that does not read any characters
     */
}