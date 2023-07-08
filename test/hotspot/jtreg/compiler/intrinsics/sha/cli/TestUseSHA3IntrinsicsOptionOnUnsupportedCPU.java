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

package compiler.intrinsics.sha.cli;

import compiler.intrinsics.sha.cli.testcases.GenericTestCaseForOtherCPU;
import compiler.intrinsics.sha.cli.testcases.GenericTestCaseForUnsupportedAArch64CPU;
import compiler.intrinsics.sha.cli.testcases.GenericTestCaseForUnsupportedX86CPU;
import compiler.intrinsics.sha.cli.testcases.UseSHAIntrinsicsSpecificTestCaseForUnsupportedCPU;

/**
 * @test
 * @bug 8252204
 * @summary Verify UseSHA3Intrinsics option processing on unsupported CPU.
 * @library /test/lib /
 * @requires vm.flagless
 *
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run main/othervm -Xbootclasspath/a:. -XX:+UnlockDiagnosticVMOptions
 *                   -XX:+WhiteBoxAPI
 *                   compiler.intrinsics.sha.cli.TestUseSHA3IntrinsicsOptionOnUnsupportedCPU
 */

public class TestUseSHA3IntrinsicsOptionOnUnsupportedCPU {
    public static void main(String args[]) throws Throwable {
        new DigestOptionsBase(
                new GenericTestCaseForUnsupportedX86CPU(
                        DigestOptionsBase.USE_SHA3_INTRINSICS_OPTION),
                new GenericTestCaseForUnsupportedAArch64CPU(
                        DigestOptionsBase.USE_SHA3_INTRINSICS_OPTION),
                new UseSHAIntrinsicsSpecificTestCaseForUnsupportedCPU(
                        DigestOptionsBase.USE_SHA3_INTRINSICS_OPTION),
                new GenericTestCaseForOtherCPU(
                        DigestOptionsBase.USE_SHA3_INTRINSICS_OPTION)).test();
    }
}
