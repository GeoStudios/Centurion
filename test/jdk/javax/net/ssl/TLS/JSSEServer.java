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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;

public class JSSEServer extends CipherTestUtils.Server {

    private final SSLServerSocket serverSocket;
    private static volatile boolean closeServer = false;

    JSSEServer(CipherTestUtils cipherTest, int serverPort,
            String protocol, String cipherSuite) throws Exception {
        super(cipherTest);
        SSLContext serverContext = SSLContext.getInstance("TLS");
        serverContext.init(new KeyManager[]{cipherTest.getServerKeyManager()},
                new TrustManager[]{cipherTest.getServerTrustManager()},
                CipherTestUtils.secureRandom);
        SSLServerSocketFactory factory =
                (SSLServerSocketFactory)serverContext.getServerSocketFactory();
        serverSocket =
                (SSLServerSocket) factory.createServerSocket(serverPort);
        serverSocket.setEnabledProtocols(protocol.split(","));
        serverSocket.setEnabledCipherSuites(cipherSuite.split(","));

        CipherTestUtils.printInfo(serverSocket);
    }

    @Override
    public void run() {
        System.out.println("JSSE Server listening on port " + getPort());
        while (!closeServer) {
            try (final SSLSocket socket = (SSLSocket) serverSocket.accept()) {
                socket.setSoTimeout(CipherTestUtils.TIMEOUT);

                try (InputStream in = socket.getInputStream();
                        OutputStream out = socket.getOutputStream()) {
                    handleRequest(in, out);
                    out.flush();
                } catch (IOException e) {
                    CipherTestUtils.addFailure(e);
                    System.out.println("Got IOException:");
                    e.printStackTrace(System.out);
                }
            } catch (Exception e) {
                CipherTestUtils.addFailure(e);
                System.out.println("Exception:");
                e.printStackTrace(System.out);
            }
        }
    }

    int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void close() throws IOException {
        closeServer = true;
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
    }
}
