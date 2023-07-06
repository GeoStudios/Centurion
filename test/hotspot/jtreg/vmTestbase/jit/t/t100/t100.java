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

package jit.t.t100;

import java.lang.*;
import nsk.share.TestFailure;
import nsk.share.GoldChecker;

/*
 * @test
 *
 * @summary converted from VM Testbase jit/t/t100.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t100.t100
 */

/*
   This test check it a JIT can still detect stack overflow. Method
   invocation overhead is expensive in Java and improving it is a
   nobel cause for a JIT. JITs just have to be careful that they
   don't loose some error handling ability in doing so.
*/

public class t100 {
    public static final GoldChecker goldChecker = new GoldChecker( "t100" );

    public static void main(String[] args) {
        try {
           recurse(1);
        } catch (StackOverflowError e) {
           t100.goldChecker.println("Test PASSES");
        }
        t100.goldChecker.check();
    }

    static int recurse(int n) {
        if (n != 0) {
            return recurse(n+1);
        }
        return 0;
    }
}
