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

//
// SunJSSE does not support dynamic system properties, no way to re-use
// system properties in samevm/agentvm mode.
//

/**
 * @test
 * @bug 4302026
 * @run main/othervm GetPeerHost
 * @summary make sure the server side doesn't do DNS lookup.
 */
import javax.net.*;

public class GetPeerHost {

    public static void main(String[] argv) throws Exception {

        String testRoot = System.getProperty("test.src", ".");
        System.setProperty("javax.net.ssl.trustStore", testRoot
                            + "/../../../../javax/net/ssl/etc/truststore");
        GetPeerHostServer server = new GetPeerHostServer();
        server.start();
        GetPeerHostClient client =
            new GetPeerHostClient(server.getServerPort());
        client.start();
        server.join ();
        if (!server.getPassStatus ()) {
            throw new Exception ("The test failed");
        }
    }
}
