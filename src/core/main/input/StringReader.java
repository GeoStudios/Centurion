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

package core.main.input;

/**
 * A character stream whose source is a string.
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */
public class StringReader {

    private String str;
    private final int length;
    private int next = 0;
    private int mark = 0;

    /**
     * Creates a new string reader.
     *
     * @param s  String providing the character stream.
     */
    public StringReader(String s) {
        this.str = s;
        this.length = s.length();
    }

    /** Check to make sure that the stream has not been closed */
    public void ensureOpen() {
    }

    /**
     * Reads a single character.
     *
     * @return     The character read, or -1 if the end of the stream has been
     *             reached
     */
    public int read(char[] cbuf, int off, int len) {
        ensureOpen();
        if (len == 0) {
            return 0;
        }
        if (next >= length) {
            return -1;
        }
        int n = Math.min(length - next, len);
        str.getChars(next, next + n, cbuf, off);
        next += n;
        return n;
    }

    /**
     * Skips characters. If the stream is already at its end before this method
     * is invoked, then no characters are skipped and zero is returned.
     *
     * <p>The {@code n} parameter may be negative, even though the
     * {@code skip} method of the superclass throws
     * an exception in this case. Negative values of {@code n} cause the
     * stream to skip backwards. Negative return values indicate a skip
     * backwards. It is not possible to skip backwards past the beginning of
     * the string.
     *
     * <p>If the entire string has been read or skipped, then this method has
     * no effect and always returns {@code 0}.
     */
    public long skip(long n) {
        ensureOpen();
        if (next >= length) {
            return 0;
        }
        long r = Math.min(length - next, n);
        next += r;
        return r;
    }
    public boolean markSupported() {
        return true;
    }

    /**
     * Marks the present position in the stream.  Subsequent calls to reset()
     * will reposition the stream to this point.
     *
     * @param  readAheadLimit  Limit on the number of characters that may be
     *                         read while still preserving the mark.  Because
     *                         the stream's input comes from a string, there
     *                         is no actual limit, so this argument must not
     *                         be negative, but is otherwise ignored.
     *
     */
    public void mark(int readAheadLimit) {
        mark = next;
        ensureOpen();
    }

    /**
     * Resets the stream to the most recent mark, or to the beginning of the
     * string if it has never been marked.
     */
    public void reset() {
        ensureOpen();
        next = mark;
    }

    /**
     * Closes the stream and releases any system resources associated with
     * it. Once the stream has been closed, further read(),
     * ready(), mark(), or reset() invocations will throw an IOException.
     * Closing a previously closed stream has no effect. This method will block
     * while there is another thread blocking on the reader.
     */
    public void close() {
        str = null;
    }
}