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

package jit.t.t103;

import nsk.share.TestFailure;
import nsk.share.GoldChecker;

/*
 * @test
 *
 * @summary converted from VM Testbase jit/t/t103.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t103.t103
 */

// Like t101.java except the short intege type is byte instead of char.

public class t103
{
    public static final GoldChecker goldChecker = new GoldChecker( "t103" );

    public static void main(String[] argv)
    {
        byte a[] = new byte[8];
        byte x,x0,x1,x2,x3,x4,x5,x6,x7;
        int i;
        for(i=0; i<8; i+=1)
            a[i] = (byte) (i - 1);
        x = (byte) (
            (x0=(byte)(int)a[0]) +
            ((x1=(byte)(int)a[1]) +
            ((x2=(byte)(int)a[2]) +
            ((x3=(byte)(int)a[3]) +
            ((x4=(byte)(int)a[4]) +
            ((x5=(byte)(int)a[5]) +
            ((x6=(byte)(int)a[6]) +
            (x7=(byte)(int)a[7])))))))
            );

        t103.goldChecker.println("... and the answer is " + (int) x);
        t103.goldChecker.check();
    }
}