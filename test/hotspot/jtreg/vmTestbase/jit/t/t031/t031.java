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

package jit.t.t031;


import nsk.share.TestFailure;
import nsk.share.GoldChecker;














/*
 * @test
 *
 * @summary converted from VM Testbase jit/t/t031.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t031.t031
 */




// opc_fcmpg, opc_fcmpl

public class t031
{
    public static final GoldChecker goldChecker = new GoldChecker( "t031" );

    public static void main(String argv[])
    {
        float d = 1.0f, e = 0.5f;

        t031.goldChecker.println("d==" + d + "  e==" + e);
        if(d < e) t031.goldChecker.println("1l");
        if(d > e) t031.goldChecker.println("2g");
        if(e < d) t031.goldChecker.println("3l");
        if(e > d) t031.goldChecker.println("4g");

        d *= 1e30f;
        e /= 1e30f;

        t031.goldChecker.println("d==" + d + "  e==" + e);
        if(d < e) t031.goldChecker.println("1l");
        if(d > e) t031.goldChecker.println("2g");
        if(e < d) t031.goldChecker.println("3l");
        if(e > d) t031.goldChecker.println("4g");

        d /= e;

        t031.goldChecker.println("d==" + d + "  e==" + e);
        if(d < e) t031.goldChecker.println("1l");
        if(d > e) t031.goldChecker.println("2g");
        if(e < d) t031.goldChecker.println("3l");
        if(e > d) t031.goldChecker.println("4g");

        d = 0.0f/0.0f;

        t031.goldChecker.println("d==" + d + "  e==" + e);
        if(d < e) t031.goldChecker.println("1l");
        if(d > e) t031.goldChecker.println("2g");
        if(e < d) t031.goldChecker.println("3l");
        if(e > d) t031.goldChecker.println("4g");

        t031.goldChecker.check();
    }
}
