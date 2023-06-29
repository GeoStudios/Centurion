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
 * Performs efficient reading of characters, arrays, and lines by intelligently
 * buffering characters read from a character-input stream.
 *
 * <p>
 * You have the option to specify the buffer size or utilize the default
 * size, which is designed to be sufficiently large for most common purposes.
 *
 * <p> To optimize performance, it is recommended to wrap a BufferedReader
 * around any Reader that involves potentially expensive read() operations.
 * This approach ensures that each read request made to the Reader results
 * in an efficient corresponding read request to the underlying character or
 * byte stream. This is particularly beneficial for Readers such as FileReaders
 * and InputStreamReaders.
 *
 * <pre>
 * BufferedReader in
 *  = new BufferedReader(new FileReader("foo.in"));
 *  </pre>
 *
 * This will apply buffering, and the input from the specified file is intelligently
 * buffered. It prevents the need for repetitive reading of bytes from the file,
 * conversion into characters, and subsequent return for each invocation of read()
 * or readLine(). This buffering mechanism significantly enhances efficiency,
 * eliminating unnecessary overhead and optimizing the overall process.
 *
 * <p>
 * To achieve localization in programs that involve textual input using DataInputStreams,
 * a recommended approach is to replace each instance of DataInputStream with an
 * appropriate BufferedReader. This substitution allows for seamless adaptation and
 * localization of the program, ensuring compatibility with different languages and
 * character encodings. By employing BufferedReader instead of DataInputStream, the
 * program becomes more versatile and adaptable to various localization requirements.
 *
 * @Author: Logan Abernathy
 * @since Alpha CDK 0.2
 */

public class ReaderBuffer extends Reader {
    private char[] cb;

    private static final int INVALIDATED = -2;
    private static final int UNMARKED = -1;
    private final int markedChar = UNMARKED;
    private final int readAheadLimit = 0; /* Valid only when markedChar > 0 */

    /** If the next character is a line feed, skip it */
    private final boolean skipLF = false;

    /** The skipLF flag when the mark was set */
    private final boolean markedSkipLF = false;

    private static final int defaultCharBufferSize = 8192;
    private static final int defaultExpectedLineLength = 80;
}