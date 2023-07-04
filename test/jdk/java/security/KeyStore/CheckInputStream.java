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
 * @bug 8136534
 * @summary The input stream supplied to KeyStore.load should remain open.
 */

import java.io.*;
import java.security.*;

public class CheckInputStream {
    private final static String DIR = System.getProperty("test.src", ".");
    private static final char[] PASSWORD = "passphrase".toCharArray();
    private static final String KEYSTORE = DIR + "/keystore.jks";

    public static final void main(String[] args) throws Exception {

        KeyStore keystore = KeyStore.getInstance("JKS");
        try (FileInputStream inStream = new FileInputStream(KEYSTORE)) {
            System.out.println("Loading JKS keystore: " + KEYSTORE);
            keystore.load(inStream, PASSWORD);
            // check that the stream is still open
            inStream.available();
            System.out.println("OK");
        }
    }
}
