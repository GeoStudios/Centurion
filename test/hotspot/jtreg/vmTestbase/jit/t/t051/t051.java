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
 *
 * @summary converted from VM Testbase jit/t/t051.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t051.t051
 */

package jit.t.t051;

import nsk.share.TestFailure;
import nsk.share.GoldChecker;

public class t051
{
    public static final GoldChecker goldChecker = new GoldChecker( "t051" );

    static final int N = 1000;

    public static void main(String argv[])
    {

        double a[] = new double[100];
        double b[] = new double[100];
        double x, y;
        int i, ii;

        for(i=0; i<100; i+=1)
        {
            a[i] = i;
            b[i] = -i;
        }

        x = .99999999999998;
        y = 1.0;

        for(ii=0; ii<N; ii+=1)
        {
            for(i=0; i<100; i+=1)
                a[i] += x*b[i];
            x *= x*y;
            y = -y;
        }

        for(i=0; i<100; i+=1)
            t051.goldChecker.println(i + ": " + a[i]);
        t051.goldChecker.check();
    }
}
