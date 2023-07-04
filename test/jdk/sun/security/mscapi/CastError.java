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

import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * @test
 * @bug 8143913
 * @requires os.family == "windows"
 * @summary MSCAPI keystore should accept Certificate[] in setEntry()
 */

public class CastError {
    public static void main(String[] args) throws Exception {
        KeyStore ks = KeyStore.getInstance(
                new File(System.getProperty("test.src"),
                        "../tools/jarsigner/JarSigning.keystore"),
                "bbbbbb".toCharArray());

        PrivateKey pk = (PrivateKey) ks.getKey("c", "bbbbbb".toCharArray());
        Certificate cert = ks.getCertificate("c");

        ks = KeyStore.getInstance("Windows-MY");
        ks.load(null, null);

        ks.setKeyEntry("8143913", pk, null, new Certificate[]{cert});
        ks.deleteEntry("8143913");
    }
}
