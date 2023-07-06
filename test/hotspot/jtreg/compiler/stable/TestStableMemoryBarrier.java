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

package compiler.stable;

import jdk.internal.vm.annotation.Stable;

/*
 * @test TestStableMemoryBarrier
 * @bug 8139758
 * @summary tests memory barrier correctly inserted for stable fields
 * @modules java.base/jdk.internal.vm.annotation
 *
 * @run main/bootclasspath/othervm -Xcomp -XX:CompileOnly=::testCompile
 *                                 compiler.stable.TestStableMemoryBarrier
 *
 * @author hui.shi@linaro.org
 */

public class TestStableMemoryBarrier {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000000; i++) {
            NotDominate.testCompile(i);
        }
    }
    /* ====================================================
     * Stable field initialized in method, but its allocation
     * doesn't dominate MemBar Release at the end of method.
     */
    private static class NotDominate {
        public @Stable int v;
        public static int[] array = new int[100];

        public static NotDominate testCompile(int n) {
            if ((n % 2) == 0) return null;
            // add a loop here, trigger PhaseIdealLoop::verify_dominance
            for (int i = 0; i < 100; i++) {
                array[i] = n;
            }
            NotDominate nm = new NotDominate();
            nm.v = n;
            return nm;
        }

    }
}
