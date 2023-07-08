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

package jit.t.t006;

import nsk.share.TestFailure;
import nsk.share.GoldChecker;

/*
 * @test
 *
 * @summary converted from VM Testbase jit/t/t006.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t006.t006
 */

public class t006
{
    public static final GoldChecker goldChecker = new GoldChecker( "t006" );

    public static void main(String argv[])
    {
        long i = 0x87654321fedcba98l;
        int i1, i31, i32, i63;
        i1 = 1;
        i31 = 31;
        i32 = 32;
        i63 = 63;

        t006.goldChecker.println(i >> 1);
        t006.goldChecker.println(i >> 31);
        t006.goldChecker.println(i >> 32);
        t006.goldChecker.println(i >> 63);

        t006.goldChecker.println("");
        t006.goldChecker.println(i << 1);
        t006.goldChecker.println(i << 31);
        t006.goldChecker.println(i << 32);
        t006.goldChecker.println(i << 63);

        t006.goldChecker.println("");
        t006.goldChecker.println(i >>> 1);
        t006.goldChecker.println(i >>> 31);
        t006.goldChecker.println(i >>> 32);
        t006.goldChecker.println(i >>> 63);

        t006.goldChecker.println("");
        t006.goldChecker.println(i >> i1);
        t006.goldChecker.println(i >> i31);
        t006.goldChecker.println(i >> i32);
        t006.goldChecker.println(i >> i63);

        t006.goldChecker.println("");
        t006.goldChecker.println(i << i1);
        t006.goldChecker.println(i << i31);
        t006.goldChecker.println(i << i32);
        t006.goldChecker.println(i << i63);

        t006.goldChecker.println("");
        t006.goldChecker.println(i >>> i1);
        t006.goldChecker.println(i >>> i31);
        t006.goldChecker.println(i >>> i32);
        t006.goldChecker.println(i >>> i63);

        t006.goldChecker.check();
    }
}
