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

package jit.t.t059;


import nsk.share.TestFailure;
import nsk.share.GoldChecker;














/*
 * @test
 *
 * @summary converted from VM Testbase jit/t/t059.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t059.t059
 */



public class t059
{
    public static final GoldChecker goldChecker = new GoldChecker( "t059" );

    static int a[][] =
    {
        {0,1,2},
        {0,1},
        {0,1,2,3}
    };

    final int b[][] =
    {
        {0, 1, 2, 3, 4},
        {5, 6, 7, 8, 9},
        {10, 11, 12, 13, 14},
        {15, 16, 17, 18, 19}
    };

    public static void main(String argv[])
    {
        int i, j;
        t059 o = new t059();

        for(i=0; i<a.length; i+=1)
        {
            for(j=0; j<a[i].length; j+=1)
                t059.goldChecker.print(a[i][j]);
            t059.goldChecker.println();
        }

        t059.goldChecker.println();
        for(i=0; i<o.b.length; i+=1)
        {
            for(j=0; j<o.b[i].length; j+=1)
                t059.goldChecker.print(o.b[i][j]);
            t059.goldChecker.println();
        }
        t059.goldChecker.check();
    }
}
