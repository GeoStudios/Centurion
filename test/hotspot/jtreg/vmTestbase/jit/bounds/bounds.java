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
 * @summary converted from VM Testbase jit/bounds.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.bounds.bounds
 */

package jit.bounds;

// This test makes sure that array bounds checking is enabled
// with the JIT on

import nsk.share.TestFailure;

public class bounds {
    public static void main(String[] argv) {
        int i;
        int a[] = new int[2];
        int k = 0;

        for(i=-1; i<3; i+=1) {
            try {
                a[i] = i;
            } catch(Throwable t) {
                k++;
            }
        }
        if (k == 2) {
           System.out.println("Test PASSES");
        } else {
           throw new TestFailure("Test FAILS");
        }
    }
}
