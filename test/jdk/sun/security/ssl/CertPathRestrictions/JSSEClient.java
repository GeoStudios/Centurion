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

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/*
 * A SSL socket client.
 */
public class JSSEClient {

    public static void main(String[] args) throws Exception {
        System.out.println("Client: arguments=" + String.join("; ", args));

        int port = Integer.valueOf(args[0]);
        String[] trustNames = args[1].split(TLSRestrictions.DELIMITER);
        String[] certNames = args[2].split(TLSRestrictions.DELIMITER);
        String constraint = args[3];

        TLSRestrictions.setConstraint("Client", constraint);

        SSLContext context = TLSRestrictions.createSSLContext(
                trustNames, certNames);
        SSLSocketFactory socketFactory = context.getSocketFactory();
        try (SSLSocket socket = (SSLSocket) socketFactory.createSocket()) {
            socket.connect(new InetSocketAddress("localhost", port),
                    TLSRestrictions.TIMEOUT);
            socket.setSoTimeout(TLSRestrictions.TIMEOUT);
            System.out.println("Client: connected");

            InputStream sslIS = socket.getInputStream();
            OutputStream sslOS = socket.getOutputStream();
            sslOS.write('C');
            sslOS.flush();
            sslIS.read();
            System.out.println("Client: finished");
        } catch (Exception e) {
            throw new RuntimeException("Client: failed.", e);
        }
    }
}
