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

package jit.t.t110;


import nsk.share.GoldChecker;
import java.io.PrintWriter;
import java.io.StringWriter;














/*
 * @test
 *
 * @summary converted from VM Testbase jit/t/t110.
 * VM Testbase keywords: [jit, quick]
 * VM Testbase readme:
 * Clone of t085.  The pass file changed in JDK 1.2.
 *
 * @library /vmTestbase
 *          /test/lib
 * @build jit.t.t110.t110
 * @comment ExecDriver is used so golden file won't depend on jtreg
 * @run driver ExecDriver --java
 *      -Dtest.src=${test.src}
 *      jit.t.t110.t110
 */




// THIS TEST IS LINE NUMBER SENSITIVE

// Uncaught exception, one jit'd frame on the stack, implicit exception.

public class t110 {
    public static final GoldChecker goldChecker = new GoldChecker("t110");

    public static void main(String[] argv) {
        try {
            doit();
        } catch (Throwable t) {
            StringWriter sr = new StringWriter();
            t.printStackTrace(new PrintWriter(sr));
            t110.goldChecker.print(sr.toString().replace("\t", "        "));
        }
        t110.goldChecker.check();
    }

    static void doit() {
        int i = 0;
        int j = 39;
        j /= i;
    }
}
