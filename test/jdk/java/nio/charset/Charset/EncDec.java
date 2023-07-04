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

/* @test
 * @summary Unit test for encode/decode convenience methods
 */


import java.nio.*;
import java.nio.charset.*;


public class EncDec {

    public static void main(String[] args) throws Exception {
        String s = "Hello, world!";
        ByteBuffer bb = ByteBuffer.allocate(100);
        bb.put(Charset.forName("ISO-8859-15").encode(s)).flip();
        String t = Charset.forName("UTF-8").decode(bb).toString();
        System.err.println(t);
        if (!t.equals(s))
            throw new Exception("Mismatch: " + s + " != " + t);
    }

}
