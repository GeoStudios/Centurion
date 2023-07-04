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
 * @bug 4378278
 * @summary java.io.PrintStream(..., String encoding) constructor
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class EncodingConstructor {

    public static void main(String args[]) throws Exception {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        String s = "xyzzy";
        int n = s.length();
        try (PrintStream ps = new PrintStream(bo, false, "UTF-8")) {
            ps.print(s);
        }
        byte[] ba = bo.toByteArray();
        if (ba.length != n)
            throw new Exception("Length mismatch: " + n + " " + ba.length);
        for (int i = 0; i < n; i++) {
            if (ba[i] != (byte)s.charAt(i))
                throw new Exception("Content mismatch: "
                                    + i + " "
                                    + Integer.toString(ba[i]) + " "
                                    + Integer.toString(s.charAt(i)));
        }
    }

}
