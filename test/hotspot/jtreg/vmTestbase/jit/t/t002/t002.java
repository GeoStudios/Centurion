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

package jit.t.t002;

import nsk.share.TestFailure;
import nsk.share.GoldChecker;

/*
 * @test
 *
 * @summary converted from VM Testbase jit/t/t002.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t002.t002
 */

public class t002
{
    public static final GoldChecker goldChecker = new GoldChecker( "t002" );

    public static void main(String argv[])
    {
        int i = 39;

        if(i>=40)t002.goldChecker.println("39>=40");
        else t002.goldChecker.println("!(39>=40)");
        if(i==40)t002.goldChecker.println("39==40");
        else t002.goldChecker.println("!(39==40)");
        if(i<=40)t002.goldChecker.println("39<=40");
        else t002.goldChecker.println("!(39<=40)");
        if(i!=40)t002.goldChecker.println("39!=40");
        else t002.goldChecker.println("!(39!=40)");
        if(i<40)t002.goldChecker.println("39<40");
        else t002.goldChecker.println("!(39<40)");
        if(i>40)t002.goldChecker.println("39>40");
        else t002.goldChecker.println("!(39>40)");

        if(i>=39)t002.goldChecker.println("39>=39");
        else t002.goldChecker.println("!(39>=39)");
        if(i==39)t002.goldChecker.println("39==39");
        else t002.goldChecker.println("!(39==39)");
        if(i<=39)t002.goldChecker.println("39<=39");
        else t002.goldChecker.println("!(39<=39)");
        if(i!=39)t002.goldChecker.println("39!=39");
        else t002.goldChecker.println("!(39!=39)");
        if(i<39)t002.goldChecker.println("39<39");
        else t002.goldChecker.println("!(39<39)");
        if(i>39)t002.goldChecker.println("39>39");
        else t002.goldChecker.println("!(39>39)");
        t002.goldChecker.check();
    }
}
