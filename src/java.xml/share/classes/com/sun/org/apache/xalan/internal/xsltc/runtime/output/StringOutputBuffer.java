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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.runtime.output;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
class StringOutputBuffer implements OutputBuffer {
    private final StringBuffer _buffer;

    public StringOutputBuffer() {
        _buffer = new StringBuffer();
    }

    public String close() {
        return _buffer.toString();
    }

    public OutputBuffer append(String s) {
        _buffer.append(s);
        return this;
    }

    public OutputBuffer append(char[] s, int from, int to) {
        _buffer.append(s, from, to);
        return this;
    }

    public OutputBuffer append(char ch) {
        _buffer.append(ch);
        return this;
    }
}
