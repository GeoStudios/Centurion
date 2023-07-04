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
 * @bug 4474391
 * @summary url: file:///D|/Projects/tmp/test.html: urlConnection.getInputStream() broken.
 */
import java.io.*;
import java.net.*;

public class FileURLTest {

    public static void main(String [] args)
    {
        String name = System.getProperty("os.name");
        if (name.startsWith("Windows")) {
            String urlStr = "file:///C|/nonexisted.txt";

            try {
                URL url = new URL(urlStr);
                URLConnection urlConnection = url.openConnection();
                InputStream in = urlConnection.getInputStream();
                in.close();
            } catch (IOException e) {
                if (e.getMessage().startsWith("C:\\nonexisted.txt")) {
                    System.out.println("Test passed!");
                } else {
                    throw new RuntimeException("Can't handle '|' in place of ':' in file urls");
                }
            }
        }
    }
}
