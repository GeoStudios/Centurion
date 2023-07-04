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
 * @bug 4326250
 * @summary Test Socket.setReceiveBufferSize() throwin IllegalArgumentException
 *
 */

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class SetReceiveBufferSize {
    public static void main(String[] args) throws Exception {
        SetReceiveBufferSize s = new SetReceiveBufferSize();
    }

    public SetReceiveBufferSize() throws Exception {
        ServerSocket ss = new ServerSocket();
        InetAddress loopback = InetAddress.getLoopbackAddress();
        ss.bind(new InetSocketAddress(loopback, 0));
        Socket s = new Socket(loopback, ss.getLocalPort());
        Socket accepted = ss.accept();
        try {
            s.setReceiveBufferSize(0);
        } catch (IllegalArgumentException e) {
            return;
        } catch (Exception ex) {
        } finally {
            ss.close();
            s.close();
            accepted.close();
        }
        throw new RuntimeException("IllegalArgumentException not thrown!");
    }
}
