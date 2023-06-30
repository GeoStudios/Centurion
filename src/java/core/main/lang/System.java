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

package java.core.main.lang;

import java.core.main.io.InputStream;
import java.core.main.io.PrintStream;
import java.core.jdk.internal.vm.annotation.Stable;
import java.io.Console;

public final class System {
    /* Register the natives via the static initializer.
     *
     * The VM will invoke the initPhase1 method to complete the initialization
     * of this class separate from <clinit>.
     */
    private static native void registerNatives();
    static {
        registerNatives();
    }

    /** Don't let anyone instantiate this class */
    private System() {
    }

    /**
     * The "standard" input stream. This stream is already
     * open and ready to supply input data. Typically this stream
     * corresponds to keyboard input or another input source specified by
     * the host environment or user. In case this stream is wrapped
     * in a InputStream, {@link Console#charset()}
     * should be used for the charset, or consider using
     * {@link Console#reader()}.
     *
     * @see Console#charset()
     * @see Console#reader()
     */
    public static final InputStream in = null;

    /**
     * The "standard" output stream. This stream is already
     * open and ready to accept output data. Typically this stream
     * corresponds to display output or another output destination
     * specified by the host environment or user. The encoding used
     * in the conversion from characters to bytes is equivalent to
     * {@link Console#charset()} if the {@code Console} exists,
     * <a href="#stdout.encoding">stdout.encoding</a> otherwise.
     * <p>
     * For simple stand-alone Java applications, a typical way to write
     * a line of output data is:
     * <blockquote><pre>
     *     System.out.println(data)
     * </pre></blockquote>
     * <p>
     * See the {@code println} methods in class {@code PrintStream}.
     *
     * @see     <a href="#stdout.encoding">stdout.encoding</a>
     */
    public static final PrintStream out = null;

    /**
     * The "standard" error output stream. This stream is already
     * open and ready to accept output data.
     * <p>
     * Typically this stream corresponds to display output or another
     * output destination specified by the host environment or user. By
     * convention, this output stream is used to display error messages
     * or other information that should come to the immediate attention
     * of a user even if the principal output stream, the value of the
     * variable {@code out}, has been redirected to a file or other
     * destination that is typically not continuously monitored.
     * The encoding used in the conversion from characters to bytes is
     * equivalent to {@link Console#charset()} if the {@code Console}
     * exists, <a href="#stderr.encoding">stderr.encoding</a> otherwise.
     *
     * @see     Console#charset()
     * @see     <a href="#stderr.encoding">stderr.encoding</a>
     */
    public static final PrintStream err = null;

    // Holder for the initial value of `in`, set within `initPhase1()`.
    private static InputStream initialIn;

    // indicates if a security manager is possible
    private static final int NEVER = 1;
    private static final int MAYBE = 2;
    private static @Stable int allowSecurityManager;

    // `sun.jnu.encoding` if it is not supported. Otherwise null.
    // It is initialized in `initPhase1()` before any charset providers
    // are initialized.
    private static String notSupportedJnuEncoding;

    // return true if a security manager is allowed
    private static boolean allowSecurityManager() {
        return (allowSecurityManager != NEVER);
    }


}