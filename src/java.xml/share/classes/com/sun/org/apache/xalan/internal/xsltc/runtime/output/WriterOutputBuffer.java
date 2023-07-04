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

package com.sun.org.apache.xalan.internal.xsltc.runtime.output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import jdk.xml.internal.SecuritySupport;

/**
 * @LastModified: Sep 2017
 */
class WriterOutputBuffer implements OutputBuffer {
    private static final int KB = 1024;
    private static final int BUFFER_SIZE = 4 * KB;

    private final Writer _writer;

    /**
     * Initializes a WriterOutputBuffer by creating an instance of a
     * BufferedWriter. The size of the buffer in this writer may have
     * a significant impact on throughput.
     */
    public WriterOutputBuffer(Writer writer) {
        _writer = new BufferedWriter(writer, BUFFER_SIZE);
    }

    public String close() {
        try {
            _writer.flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        return "";
    }

    public OutputBuffer append(String s) {
        try {
            _writer.write(s);
        }
        catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        return this;
    }

    public OutputBuffer append(char[] s, int from, int to) {
        try {
            _writer.write(s, from, to);
        }
        catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        return this;
    }

    public OutputBuffer append(char ch) {
        try {
            _writer.write(ch);
        }
        catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        return this;
    }
}
