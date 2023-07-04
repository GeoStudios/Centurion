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

/*
 * @test
 * @bug 4165948
 * @summary java.net.PlainSocketImpl {set,get}Option throws SocketException
 *          when socket is closed.
 */

import java.io.*;
import java.net.*;

public class SetOption {

    public static void main(String args[]) throws Exception {

        InetAddress loopback = InetAddress.getLoopbackAddress();
        ServerSocket ss = new ServerSocket(0, 0, loopback);

        Socket s1 = new Socket(loopback, ss.getLocalPort());
        Socket s2 = ss.accept();

        s1.close();
        boolean exc_thrown = false;
        try {
            s1.setSoTimeout(1000);
        } catch (SocketException e) {
            exc_thrown = true;
        }

        if (!exc_thrown) {
            throw new Exception("SocketException not thrown on closed socket");
        }
    }
}
