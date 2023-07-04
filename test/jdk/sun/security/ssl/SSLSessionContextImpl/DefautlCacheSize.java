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

/**
 * @test
 * @bug 8210985
 * @summary Update the default SSL session cache size to 20480
 * @run main/othervm DefautlCacheSize
 */

// The SunJSSE provider cannot use System Properties in samevm/agentvm mode.
// Please run JSSE test in othervm mode.

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;

public class DefautlCacheSize {

    public static void main(String[] args) throws Exception {
        SSLServerSocketFactory sssf =
                (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();

        try (SSLServerSocket serverSocket =
                    (SSLServerSocket)sssf.createServerSocket()) {

            String[] protocols = serverSocket.getSupportedProtocols();
            for (int i = 0; i < protocols.length; i++) {
                if (protocols[i].equals("SSLv2Hello")) {
                    continue;
                }
                SSLContext sslContext = SSLContext.getInstance(protocols[i]);
                SSLSessionContext sessionContext =
                        sslContext.getServerSessionContext();
                if (sessionContext.getSessionCacheSize() == 0) {
                    throw new Exception(
                        "the default server session cache size is infinite");
                }

                sessionContext = sslContext.getClientSessionContext();
                if (sessionContext.getSessionCacheSize() == 0) {
                    throw new Exception(
                        "the default client session cache size is infinite");
                }
            }
        }
    }
}
