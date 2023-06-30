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
 * Methods to access the character-based console device, if any, associated
 * with the current Java virtual machine.
 *
 * <p> Whether a virtual machine has a console is dependent upon the
 * underlying platform and also upon the manner in which the virtual
 * machine is invoked.  If the virtual machine is started from an
 * interactive command line without redirecting the standard input and
 * output streams then its console will exist and will typically be
 * connected to the keyboard and display from which the virtual machine
 * was launched.  If the virtual machine is started automatically, for
 * example by a background job scheduler, then it may not
 * have a console.
 * <p>
 * If this virtual machine has a console then it is represented by a
 * unique instance of this class which can be obtained by invoking the
 * {@link java.lang.System#console()} method.  If no console device is
 * available then an invocation of that method will return {@code null}.
 * <p>
 * Read and write operations are synchronized to guarantee the atomic
 * completion of critical operations; therefore invoking methods
 * {@link #readLine()}, {@link #readPassword()}, {@link #format format()},
 * {@link #printf printf()} as well as the read, format and write operations
 * on the objects returned by {@link #reader()} and {@link #writer()} may
 * block in multithreaded scenarios.
 * <p>
 * Invoking {@code close()} on the objects returned by the {@link #reader()}
 * and the {@link #writer()} will not close the underlying stream of those
 * objects.
 * <p>
 * The console-read methods return {@code null} when the end of the
 * console input stream is reached, for example by typing control-D on
 * Unix or control-Z on Windows.  Subsequent read operations will succeed
 * if additional characters are later entered on the console's input
 * device.
 * <p>
 * Unless otherwise specified, passing a {@code null} argument to any method
 * in this class will cause a {@link NullPointerException} to be thrown.
 * <p>
 * <b>Security note:</b>
 * If an application needs to read a password or other secure data, it should
 * use {@link #readPassword()} or {@link #readPassword(String, Object...)} and
 * manually zero the returned character array after processing to minimize the
 * lifetime of sensitive data in memory.
 *
 * <blockquote><pre>{@code
 * Console cons;
 * char[] passwd;
 * if ((cons = System.console()) != null &&
 *     (passwd = cons.readPassword("[%s]", "Password:")) != null) {
 *     ...
 *     java.util.Arrays.fill(passwd, ' ');
 * }
 * }</pre></blockquote>
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */

public class Console implements Flushable {

    /**
     * Retrieves the unique {@link java.core.java.io.PrintWriter PrintWriter} object
     * associated with this console.
     *
     * @return  The printwriter associated with this console
     */
    public PrintWriter writer() {
        return pw;
    }

    /**
     * Retrieves the unique {@link java.io.Reader Reader} object associated
     * with this console.
     * <p>
     * This method is intended to be used by sophisticated applications, for
     * example, a {@link java.util.Scanner} object which utilizes the rich
     * parsing/scanning functionality provided by the {@code Scanner}:
     * <blockquote><pre>
     * Console con = System.console();
     * if (con != null) {
     *     Scanner sc = new Scanner(con.reader());
     *     ...
     * }
     * </pre></blockquote>
     * <p>
     * For simple applications requiring only line-oriented reading, use
     *
     * <p>
     * The bulk read operations {@link java.io.Reader#read(char[]) read(char[]) },
     * {@link java.io.Reader#read(char[], int, int) read(char[], int, int) } and
     * {@link java.io.Reader#read(java.nio.CharBuffer) read(java.nio.CharBuffer)}
     * on the returned object will not read in characters beyond the line
     * bound for each invocation, even if the destination buffer has space for
     * more characters. The {@code Reader}'s {@code read} methods may block if a
     * line bound has not been entered or reached on the console's input device.
     * A line bound is considered to be any one of a line feed ({@code '\n'}),
     * a carriage return ({@code '\r'}), a carriage return followed immediately
     * by a linefeed, or an end of stream.
     *
     * @return  The reader associated with this console
     */
    public Reader reader() {
        return reader;
    }
}