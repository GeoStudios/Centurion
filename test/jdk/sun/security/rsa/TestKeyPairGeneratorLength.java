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
 * @bug     5078280
 * @summary make sure generated key pairs are exactly the requested length
 * @author  Andreas Sterbenz
 */

import java.security.*;
import java.security.interfaces.*;

public class TestKeyPairGeneratorLength {

    public static void main(String[] args) throws Exception {
        test(512);
        test(513);
        System.out.println("Done.");
    }

    private static void test(int len) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "SunRsaSign");
        kpg.initialize(len);
        for (int i = 0; i < 6; i++) {
            System.out.println("Generating keypair " + len + " bit keypair " + (i + 1) + "...");
            KeyPair kp = kpg.generateKeyPair();
            RSAPublicKey key = (RSAPublicKey)kp.getPublic();
            int k = key.getModulus().bitLength();
            if (k != len) {
                throw new Exception("length mismatch: " + k);
            }
        }
    }

}
