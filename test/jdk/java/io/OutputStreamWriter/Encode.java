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
 * @bug 4802209
 * @summary check that the right utf-8 encoder is used
 */

import java.io.*;
import java.net.*;

public class Encode implements Runnable {
    public static void main(String args[]) throws Exception {
        new Encode();
    }

    final ServerSocket ss = new ServerSocket(0);

    Encode() throws Exception {
        (new Thread(this)).start();
        String toEncode = "\uD800\uDC00 \uD801\uDC01 ";
        String enc1 = URLEncoder.encode(toEncode, "UTF-8");
        byte bytes[] = {};
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        InputStreamReader reader = new InputStreamReader( bais, "8859_1");
        String url = "http://localhost:" + Integer.toString(ss.getLocalPort()) +
            "/missing.nothtml";
        HttpURLConnection uc =  (HttpURLConnection)new URL(url).openConnection();
        uc.connect();
        try {
            String enc2 = URLEncoder.encode(toEncode, "UTF-8");
            if (!enc1.equals(enc2)) {
                System.out.println("test failed");
                throw new RuntimeException("test failed");
            }
        } finally {
            uc.disconnect();
        }
    }

    public void run() {
        try (ServerSocket serv = ss;
             Socket s = serv.accept();
             BufferedReader in =
                 new BufferedReader(new InputStreamReader(s.getInputStream())))
        {
            String req = in.readLine();
            try (OutputStream os = s.getOutputStream();
                 BufferedOutputStream bos = new BufferedOutputStream(os);
                 PrintStream out = new PrintStream(bos))
            {
                out.print("HTTP/1.1 403 Forbidden\r\n");
                out.print("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
