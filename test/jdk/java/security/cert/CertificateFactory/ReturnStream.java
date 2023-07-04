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
 * @bug 6813340
 * @summary X509Factory should not depend on is.available()==0
 */
import java.io.*;
import java.security.cert.*;

/**
 * Tests ol'Mac style file, end witha  single '\r'
 */
public class ReturnStream {

    public static void main(String[] args) throws Exception {
        FileInputStream fin = new FileInputStream(new File(new File(
                System.getProperty("test.src", "."), "openssl"), "pem"));
        byte[] buffer = new byte[4096];
        int size = 0;
        while (true) {
            int len = fin.read(buffer, size, 4096-size);
            if (len < 0) break;
            size += len;
        }
        fin.close();

        // Make a copy
        System.arraycopy(buffer, 0, buffer, size, size);
        size += size;

        // Create a ol'Mac style file.
        for (int i=0; i<size; i++) {
            if (buffer[i] == '\n') buffer[i] = '\r';
        }

        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        if (factory.generateCertificates(
                new ByteArrayInputStream(buffer, 0, size)).size() != 2) {
            throw new Exception("Cert not OK");
        }
    }
}
