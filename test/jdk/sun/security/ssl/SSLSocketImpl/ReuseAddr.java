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
 * @bug 4482446
 * @summary java.net.SocketTimeoutException on 98, NT, 2000 for JSSE
 * @library /javax/net/ssl/templates
 * @run main ReuseAddr
 *
 *     SunJSSE does not support dynamic system properties, no way to re-use
 *     system properties in samevm/agentvm mode.
 * @author Brad Wetmore
 */

import java.net.ServerSocket;

public class ReuseAddr extends SSLSocketTemplate {

    @Override
    protected void doServerSide() throws Exception {
        super.doServerSide();

        // Note that if this port is already used by another test,
        // this test will fail.
        System.out.println("Try rebinding to same port: " + serverPort);
        try (ServerSocket server = new ServerSocket(serverPort)) {
            System.out.println("Server port: " + server.getLocalPort());
        }
    }

    public static void main(String[] args) throws Exception {
        new ReuseAddr().run();
    }
}
