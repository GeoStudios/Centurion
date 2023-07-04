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
   The init02.java test checks if a JIT changes the order in which
   classes are initialized. Java semantics do not allow a class to be
   initialized until it is actually used.
*/


/*
 * @test
 *
 * @summary converted from VM Testbase jit/init/init02.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.init.init02.init02
 */

package jit.init.init02;

import nsk.share.TestFailure;

public class init02 {
    public static boolean failed = false;
    public static void main(String args[]) {
        int i, x;
        for (i = 0; i < 10; i++) {
            x = i * 10;
            if (x < 0) {
                inittest.foo(x);
            }
        }

        if (failed)
            throw new TestFailure("\n\nInitializing inittest, test FAILS\n");
    }
}

class inittest {
    static {
        init02.failed = true;
    }

    public static void foo(int x) {
        System.out.println("foo value = " + x);
    }
}
