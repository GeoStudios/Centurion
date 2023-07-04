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
 * @summary converted from VM Testbase jit/t/t086.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t086.t086
 */

package jit.t.t086;

import nsk.share.TestFailure;
import nsk.share.GoldChecker;

// Exception at patch time.

class foo
{
    static int i;
    static int j;
    static
    {
        i = 0;
        j = 409;
        j /= i;
    }

    static void heydee()
    {
        t086.goldChecker.println("Heydee ho.");
    }
}

public class t086
{
    public static final GoldChecker goldChecker = new GoldChecker( "t086" );

    public static void main(String argv[])
    {
        t086.goldChecker.println("Hi.");
        try
        {
            foo.heydee();
        }
        catch(Throwable t)
        {
            t086.goldChecker.println("Caught exception.");
        }
        t086.goldChecker.println("G'bye.");
        t086.goldChecker.check();
    }
}
