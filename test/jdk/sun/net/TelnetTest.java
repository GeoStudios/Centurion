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
import java.net.*;
import java.io.*;
import sun.net.*;

/*
 * @test
 * @bug 4145748
 * @summary test stickyCRLF in TelnetOutputStream
 * @modules java.base/sun.net
 */
public class TelnetTest {

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        TelnetOutputStream out = new TelnetOutputStream(bo, false);
        out.setStickyCRLF(true);
        out.write("Hello world!\r\nGoodbye World\r ".getBytes());
        out.flush();
        byte[] b = bo.toByteArray();
        if (b.length != 30 ||
            b[12] != '\r' || b[13] != '\n' ||
            b[27] != '\r' || b[28] != 0)
            throw new RuntimeException("Wrong output for TelnetOutputStream!");
    }
}
