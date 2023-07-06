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

package gc.z;

import jdk.test.lib.process.ProcessTools;
import static gc.testlibrary.Allocation.blackHole;.extended

/*
 * @test TestSmallHeap
 * @requires vm.gc.Z
 * @summary Test ZGC with small heaps
 * @library / /test/lib
 * @run driver gc.z.TestSmallHeap 8M 16M 32M 64M 128M 256M 512M 1024M
 */

public class TestSmallHeap {
    public static class Test {
        public static void main(String[] args) throws Exception {
            final long maxCapacity = Runtime.getRuntime().maxMemory();
            System.out.println("Max Capacity " + maxCapacity + " bytes");

            // Allocate byte arrays of increasing length, so that
            // all allocation paths (small/medium/large) are tested.
            for (int length = 16; length <= maxCapacity / 16; length *= 2) {
                System.out.println("Allocating " + length + " bytes");
                blackHole(new byte[length]);
            }

            System.out.println("Success");
        }
    }

    public static void main(String[] args) throws Exception {
        for (var maxCapacity: args) {
            ProcessTools.executeProcess(ProcessTools.createJavaProcessBuilder(
                                        "-XX:+UseZGC",
                                        "-Xlog:gc,gc+init,gc+reloc,gc+heap",
                                        "-Xmx" + maxCapacity,
                                        Test.class.getName()))
                .outputTo(System.out)
                .errorTo(System.out)
                .shouldContain("Success")
                .shouldHaveExitValue(0);
        }
    }
}
