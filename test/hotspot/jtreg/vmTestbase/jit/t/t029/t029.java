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

package jit.t.t029;


import nsk.share.TestFailure;
import nsk.share.GoldChecker;














/*
 * @test
 *
 * @summary converted from VM Testbase jit/t/t029.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t029.t029
 */



// opc_drem, opc_frem, opc_dreturn opc_freturn

public class t029
{
    public static final GoldChecker goldChecker = new GoldChecker( "t029" );

    static float frem(float f, float g)
    {
        return f % g;
    }

    static double drem(double d, double e)
    {
        return d % e;
    }

    public static void main(String argv[])
    {
        double d = 39.12345, e = 402.001;
        float f = 39.12345f, g = 402.001f;
        double x;
        float y;

        // Both pos.

        t029.goldChecker.println();
        t029.goldChecker.println(d%e);
        t029.goldChecker.println(e%(x=d));
        t029.goldChecker.println(f%g);
        t029.goldChecker.println(g%(y=f));

        d = -d;
        f = -f;

        // First neg; second pos.

        t029.goldChecker.println();
        t029.goldChecker.println(d%e);
        t029.goldChecker.println(e%(x=d));
        t029.goldChecker.println(f%g);
        t029.goldChecker.println(g%(y=f));

        e = -e;
        g = -g;

        // Both neg.

        t029.goldChecker.println();
        t029.goldChecker.println(d%e);
        t029.goldChecker.println(e%(x=d));
        t029.goldChecker.println(f%g);
        t029.goldChecker.println(g%(y=f));

        d = -d;
        f = -f;

        // First pos; second neg;

        t029.goldChecker.println();
        t029.goldChecker.println(d%e);
        t029.goldChecker.println(e%(x=d));
        t029.goldChecker.println(f%g);
        t029.goldChecker.println(g%(y=f));

        e = -e;
        g = -g;

        // Both pos.

        t029.goldChecker.println();
        t029.goldChecker.println(drem(d,e));
        t029.goldChecker.println(drem(e, x=d));
        t029.goldChecker.println(frem(f, g));
        t029.goldChecker.println(frem(g, y=f));

        d = -d;
        f = -f;

        // First neg; second pos.

        t029.goldChecker.println();
        t029.goldChecker.println(drem(d,e));
        t029.goldChecker.println(drem(e, x=d));
        t029.goldChecker.println(frem(f, g));
        t029.goldChecker.println(frem(g, y=f));

        e = -e;
        g = -g;

        // Both neg.

        t029.goldChecker.println();
        t029.goldChecker.println(drem(d,e));
        t029.goldChecker.println(drem(e, x=d));
        t029.goldChecker.println(frem(f, g));
        t029.goldChecker.println(frem(g, y=f));

        d = -d;
        f = -f;

        // First pos; second neg.

        t029.goldChecker.println();
        t029.goldChecker.println(drem(d,e));
        t029.goldChecker.println(drem(e, x=d));
        t029.goldChecker.println(frem(f, g));
        t029.goldChecker.println(frem(g, y=f));
        t029.goldChecker.check();
    }
}
