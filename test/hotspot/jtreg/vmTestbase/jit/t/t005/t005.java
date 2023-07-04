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
 * @summary converted from VM Testbase jit/t/t005.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t005.t005
 */

package jit.t.t005;

import nsk.share.TestFailure;
import nsk.share.GoldChecker;

public class t005
{
    public static final GoldChecker goldChecker = new GoldChecker( "t005" );

    public static void main(String argv[])
    {
        long i = 39;
        long j = 40;

        if(i>=j)t005.goldChecker.println("39>=40");
        else t005.goldChecker.println("!(39>=40)");
        if(i==j)t005.goldChecker.println("39==40");
        else t005.goldChecker.println("!(39==40)");
        if(i<=j)t005.goldChecker.println("39<=40");
        else t005.goldChecker.println("!(39<=40)");
        if(i!=j)t005.goldChecker.println("39!=40");
        else t005.goldChecker.println("!(39!=40)");
        if(i<j)t005.goldChecker.println("39<40");
        else t005.goldChecker.println("!(39<40)");
        if(i>j)t005.goldChecker.println("39>40");
        else t005.goldChecker.println("!(39>40)");

        if(i>=i)t005.goldChecker.println("39>=39");
        else t005.goldChecker.println("!(39>=39)");
        if(i==i)t005.goldChecker.println("39==39");
        else t005.goldChecker.println("!(39==39)");
        if(i<=i)t005.goldChecker.println("39<=39");
        else t005.goldChecker.println("!(39<=39)");
        if(i!=i)t005.goldChecker.println("39!=39");
        else t005.goldChecker.println("!(39!=39)");
        if(i<i)t005.goldChecker.println("39<39");
        else t005.goldChecker.println("!(39<39)");
        if(i>i)t005.goldChecker.println("39>39");
        else t005.goldChecker.println("!(39>39)");
        t005.goldChecker.check();
    }
}
