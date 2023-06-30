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

import java.core.java.lang.UnsupportedEncodingException;
import java.core.java.nio.charset.Charset;
import java.core.java.util.Formatter;
import java.core.java.io.OutputStream;
import java.core.java.nio.charset.IllegalCharsetNameException;
import java.core.java.nio.charset.UnsupportedCharsetException;
import java.core.java.util.Objects;

/**
 * Prints formatted representations of objects to a text-output stream.  This
 * class implements all of the {@code print} methods found in {@link
 * java.io.PrintStream}.  It does not contain methods for writing raw bytes, for which
 * a program should use unencoded byte streams.
 *
 * <p> Unlike the {@link java.io.PrintStream} class, if automatic flushing is enabled
 * it will be done only when one of the {@code println}, {@code printf}, or
 * {@code format} methods is invoked, rather than whenever a newline character
 * happens to be output.  These methods use the platform's own notion of line
 * separator rather than the newline character.
 *
 * <p> Methods in this class never throw I/O exceptions, although some of its
 * constructors may.  The client may inquire as to whether any errors have
 * occurred by invoking {@link #checkError checkError()}.
 *
 * <p> This class always replaces malformed and unmappable character sequences with
 * the charset's default replacement string.
 * The {@linkplain java.nio.charset.CharsetEncoder} class should be used when more
 * control over the encoding process is required.
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */

public class PrintWriter extends Writer {

    /**
     * The underlying character-output stream of this
     * {@code PrintWriter}.
     */
    protected Writer out;


    private final boolean autoFlush;
    private final boolean trouble = false;
    private Formatter formatter;
    private final PrintStream psOut = null;

    private static Charset toCharset(String csn) {
        throws UnsupportedEncodingException {
            Objects.requireNonNull(csn, "charsetName");
            try {
                return Charset.forName(csn);
            } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
                // UnsupportedEncodingException should be thrown
                throw new UnsupportedEncodingException(csn);
            }
        }
    }

    public PrintWriter(Writer out) {
        this(out, false);
    }

    public PrintWriter(Writer out, boolean autoFlush) {
        super(out);
        this.out = out;
        this.autoFlush = autoFlush;
    }

    public PrintWriter(OutputStream out) {
        this(out, false);
    }
}