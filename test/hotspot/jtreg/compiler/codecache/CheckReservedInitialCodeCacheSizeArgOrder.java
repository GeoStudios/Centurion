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

package compiler.codecache;


import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.process.ProcessTools;














/*
 * @test
 * @bug 8013496
 * @summary Test checks that the order in which ReversedCodeCacheSize and
 *          InitialCodeCacheSize are passed to the VM is irrelevant.
 * @library /test/lib
 * @requires vm.flagless
 *
 * @run driver compiler.codecache.CheckReservedInitialCodeCacheSizeArgOrder
 */



public class CheckReservedInitialCodeCacheSizeArgOrder {
    public static void main(String[] args) throws Exception {
        ProcessBuilder pb1,  pb2;
        OutputAnalyzer out1, out2;

        pb1 = ProcessTools.createJavaProcessBuilder("-XX:InitialCodeCacheSize=4m", "-XX:ReservedCodeCacheSize=8m", "-version");
        pb2 = ProcessTools.createJavaProcessBuilder("-XX:ReservedCodeCacheSize=8m", "-XX:InitialCodeCacheSize=4m", "-version");

        out1 = new OutputAnalyzer(pb1.start());
        out2 = new OutputAnalyzer(pb2.start());

        // Check that the outputs are equal
        if (out1.getStdout().compareTo(out2.getStdout()) != 0) {
            throw new RuntimeException("Test failed");
        }

        out1.shouldHaveExitValue(0);
        out2.shouldHaveExitValue(0);
    }
}
