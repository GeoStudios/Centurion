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
 * @bug 4251209
 * @summary Make sure that implicit filenames will be returned as "/"
 * @modules java.base/sun.net
 *          java.base/sun.net.www.http
 * @library /test/lib
 */

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.ServerSocket;
import sun.net.www.http.HttpClient;
import jdk.test.lib.net.URIBuilder;

public class ImplicitFileName {

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket();
        InetAddress loopback = InetAddress.getLoopbackAddress();
        ss.bind(new InetSocketAddress(loopback, 0));

        URL url = URIBuilder.newBuilder()
            .scheme("http")
            .loopback()
            .port(ss.getLocalPort())
            .toURL();

        HttpClient c = HttpClient.New(url);

        if (!c.getURLFile().equals("/")) {
            throw new Exception("Implicit filename in URL " +
                                url.toString() + " is not '/'");
        }
    }
}
