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

/* @test
 * @bug 6847092
 * @summary test if isLegalReplacement() works correctly for ascii charsets
 */

import java.util.*;
import java.nio.*;
import java.nio.charset.*;

public class FindASCIIReplBugs {
    private static int failures = 0;

    public static void main(String[] args) throws Exception {
        Charset ascii = Charset.forName("ASCII");
        for (Map.Entry<String,Charset> e
                 : Charset.availableCharsets().entrySet()) {
            String csn = e.getKey();
            Charset cs = e.getValue();
            if (!cs.contains(ascii) ||
                csn.matches(".*2022.*") ||             //iso2022 family
                csn.matches(".*UTF-[16|32].*"))        //multi-bytes
                continue;
            if (! cs.canEncode()) continue;

            byte[] sc_subs = { 'A'};
            byte[] mc_subs = { 'A', 'S'};
            if (!cs.newEncoder().isLegalReplacement (sc_subs) ||
                !cs.newEncoder().isLegalReplacement (mc_subs)) {
                System.out.printf(" %s: isLegalReplacement failed!%n", csn);
                failures++;
            }
        }
        if (failures > 0)
            throw new Exception(failures + "tests failed");
    }
}
