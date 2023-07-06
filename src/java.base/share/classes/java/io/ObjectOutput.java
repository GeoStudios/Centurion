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
 * ObjectOutput extends the DataOutput interface to include writing of objects.
 * DataOutput includes methods for output of primitive types, ObjectOutput
 * extends that interface to include objects, arrays, and Strings.
 *
 * @see java.io.InputStream
 * @see java.io.ObjectOutputStream
 * @see java.io.ObjectInputStream
 */
public interface ObjectOutput extends DataOutput, AutoCloseable {
    /**
     * Write an object to the underlying storage or stream.  The
     * class that implements this interface defines how the object is
     * written.
     *
     * @param     obj the object to be written
     * @throws    IOException Any of the usual Input/Output related exceptions.
     */
    void writeObject(Object obj)
      throws IOException;

    /**
     * Writes a byte. This method will block until the byte is actually
     * written.
     * @param     b the byte
     * @throws    IOException If an I/O error has occurred.
     */
    void write(int b) throws IOException;

    /**
     * Writes an array of bytes. This method will block until the bytes
     * are actually written.
     * @param     b the data to be written
     * @throws    IOException If an I/O error has occurred.
     */
    void write(byte[] b) throws IOException;

    /**
     * Writes a sub array of bytes.
     * @param     b the data to be written
     * @param     off       the start offset in the data
     * @param     len       the number of bytes that are written
     * @throws    IOException If an I/O error has occurred.
     */
    void write(byte[] b, int off, int len) throws IOException;

    /**
     * Flushes the stream. This will write any buffered
     * output bytes.
     * @throws    IOException If an I/O error has occurred.
     */
    void flush() throws IOException;

    /**
     * Closes the stream. This method must be called
     * to release any resources associated with the
     * stream.
     * @throws    IOException If an I/O error has occurred.
     */
    void close() throws IOException;
}