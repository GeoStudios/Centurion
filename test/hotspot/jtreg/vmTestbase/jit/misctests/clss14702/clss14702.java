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

package jit.misctests.clss14702;

import nsk.share.TestFailure;

/*
 * @test
 *
 * @summary converted from VM Testbase jit/misctests/clss14702.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.misctests.clss14702.clss14702
 */

public class  clss14702 {
    static int ML = 1;
    public static void main(String argv[]) {
        clss14702 test = null;
        for (int i = 0; i < ML; i++)
            try {
                if ( test.equals(null) ) {
                    System.out.println("Error! NullPointerException should be thrown.");
                }
                throw new TestFailure("Error! No exception.");
            } catch (Exception e) {
                if ( ! NullPointerException.class.isInstance(e) ) {
                    throw new TestFailure("Error! Exception: " + e);
                }
            }
            System.out.println("Passed");
    }
}