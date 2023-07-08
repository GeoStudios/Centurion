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

package compiler.intrinsics.bmi;


import sun.hotspot.cpuinfo.CPUInfo;














/**
 * @test
 * @key randomness
 * @bug 8031321
 * @summary Verify that results of computations are the same w/
 *          and w/o usage of BLSI instruction
 * @library /test/lib /
 * @modules java.base/jdk.internal.misc
 *          java.management
 *
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run main/othervm -Xbootclasspath/a:. -XX:+UnlockDiagnosticVMOptions
 *                   -XX:+WhiteBoxAPI
 *                   compiler.intrinsics.bmi.TestBlsiI
 */



public class TestBlsiI {

    public static void main(String args[]) throws Throwable {
        if (!CPUInfo.hasFeature("bmi1")) {
            System.out.println("INFO: CPU does not support bmi1 feature.");
        }

        BMITestRunner.runTests(BlsiIExpr.class, args,
                               "-XX:+IgnoreUnrecognizedVMOptions",
                               "-XX:+UseBMI1Instructions");
        BMITestRunner.runTests(BlsiICommutativeExpr.class, args,
                               "-XX:+IgnoreUnrecognizedVMOptions",
                               "-XX:+UseBMI1Instructions");
    }

    public static class BlsiIExpr extends Expr.BMIUnaryIntExpr {

        public int intExpr(int src) {
            return -src & src;
        }

        public int intExpr(Expr.MemI src) {
            return -src.value & src.value;
        }

    }

    public static class BlsiICommutativeExpr extends Expr.BMIUnaryIntExpr {

        public int intExpr(int src) {
            return src & -src;
        }

        public int intExpr(Expr.MemI src) {
            return src.value & -src.value;
        }

    }

}
