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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.util;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Utility class that implements a sequence of bytes which can be read
 * via the `readByte()' method. This is used to implement a wrapper for the
 * Java byte code stream to gain some more readability.
 *
 */
public final class ByteSequence extends DataInputStream {

    private final ByteArrayStream byteStream;


    public ByteSequence(final byte[] bytes) {
        super(new ByteArrayStream(bytes));
        byteStream = (ByteArrayStream) in;
    }


    public int getIndex() {
        return byteStream.getPosition();
    }


    void unreadByte() {
        byteStream.unreadByte();
    }

    private static final class ByteArrayStream extends ByteArrayInputStream {

        ByteArrayStream(final byte[] bytes) {
            super(bytes);
        }

        int getPosition() {
            // pos is protected in ByteArrayInputStream
            return pos;
        }

        void unreadByte() {
            if (pos > 0) {
                pos--;
            }
        }
    }
}
