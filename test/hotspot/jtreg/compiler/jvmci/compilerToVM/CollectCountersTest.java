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

package compiler.jvmci.compilerToVM;


import jdk.test.lib.Asserts;
import jdk.vm.ci.hotspot.CompilerToVMHelper;














/*
 * @test
 * @bug 8136421
 * @requires vm.jvmci
 * @library / /test/lib/
 * @library ../common/patches
 * @modules java.base/jdk.internal.misc
 * @modules jdk.internal.vm.ci/jdk.vm.ci.hotspot
 * @build jdk.internal.vm.ci/jdk.vm.ci.hotspot.CompilerToVMHelper sun.hotspot.WhiteBox
 * @run main/othervm -XX:+UnlockExperimentalVMOptions
 *                   -XX:+EnableJVMCI
 *                   -XX:JVMCICounterSize=0
 *                   -Dcompiler.jvmci.compilerToVM.CollectCountersTest.expected=0
 *                   compiler.jvmci.compilerToVM.CollectCountersTest
 * @run main/othervm -XX:+UnlockExperimentalVMOptions
 *                   -XX:+EnableJVMCI
 *                   -XX:JVMCICounterSize=11
 *                   -Dcompiler.jvmci.compilerToVM.CollectCountersTest.expected=11
 *                   compiler.jvmci.compilerToVM.CollectCountersTest
 */



public class CollectCountersTest {
    private static final int EXPECTED = Integer.getInteger(
            "compiler.jvmci.compilerToVM.CollectCountersTest.expected");
    public static void main(String args[]) {
        new CollectCountersTest().runTest();
    }

    private void runTest() {
        long[] counters = CompilerToVMHelper.collectCounters();
        Asserts.assertNotNull(counters, "Expected not-null counters array");
        int ctvmData = counters.length;
        Asserts.assertEQ(EXPECTED, ctvmData, "Unexpected counters amount");
    }
}
