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

package compiler.c1;
















/*
 * @test
 * @bug 8268366
 * @run main/othervm -Xbatch -XX:+TieredCompilation -XX:TieredStopAtLevel=1
 *                   compiler.c1.TestLinearScanHasFPURegisters
 */


public class TestLinearScanHasFPURegisters {
    void test(String[] args) {
        String arr[] = new String[4];
        float f = -1;
        try {
            arr[0] = "-1"; // exception edge 1 with value -1
            if (args.length > 1) {
                f = 42;
                arr[1] = "42"; // exception edge 2 with value 42
            }
        } catch (Exception e) {
            // exception handler block with incoming phi for "f"
            for (int i = 0; i < 1; ++i) {
                f = f; // generates bytecodes, but no JIT IR
            }
        }
    }
    public static void main(String[] args) {
        TestLinearScanHasFPURegisters t = new TestLinearScanHasFPURegisters();
        for (int i = 0; i < 1000; ++i) {
            t.test(args);
        }
    }
}
