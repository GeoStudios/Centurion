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
 * @bug 4500266
 * @summary DatagramSocket.isBound reports 'false' when socket implicitly bound
 */

import java.net.*;

public class ImplicitBind {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket((SocketAddress)null);
        byte b[] = "hello".getBytes();
        InetAddress ia = InetAddress.getByName("localhost");
        DatagramPacket pac = new DatagramPacket(b, b.length, ia, 53);
        s.send(pac);
        if (!s.isBound())
            throw new RuntimeException("Socket should be implictly bound!");
        s.close();
        s = new DatagramSocket((SocketAddress)null);
        s.connect(new InetSocketAddress("localhost", 1234));
        if (!s.isBound())
            throw new RuntimeException("Socket should be implictly bound!");
        s.close();
    }
}
