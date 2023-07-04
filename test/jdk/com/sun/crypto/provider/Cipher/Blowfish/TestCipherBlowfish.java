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

import java.security.NoSuchAlgorithmException;

/*
 * @test
 * @bug 8048601
 * @library ../
 * @summary Test Blowfish cipher with different MODES and padding
 */

public class TestCipherBlowfish extends TestCipher {

    TestCipherBlowfish(String[] modes, String[] paddings) throws NoSuchAlgorithmException {
        super("Blowfish", modes, paddings, 32, 448);
    }

    public static void main(String[] args) throws Exception {
        new TestCipherBlowfish(new String[]{ "CBC", "ECB", "PCBC",
            //CFBx
            "CFB", "CFB8", "CFB16", "CFB24", "CFB32", "CFB40",
            "CFB48", "CFB56", "CFB64",
            //OFBx
            "OFB", "OFB8", "OFB16", "OFB24", "OFB32", "OFB40",
            "OFB48", "OFB56", "OFB64"},
        new String[]{ "NoPaDDing", "PKCS5Padding"}).runAll();
        new TestCipherBlowfish(new String[]{ "CTR", "CTS" },
            new String[]{ "NoPaDDing" }).runAll();
    }
}
