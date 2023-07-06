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

package compiler.arguments;

/**
 * @test
 * @bug 8031321
 * @summary Verify processing of UseBMI1Instructions option on CPU with
 *          BMI1 feature support.
 * @library /test/lib /
 * @requires vm.flagless
 * @modules java.base/jdk.internal.misc
 *          java.management
 *
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run main/othervm -Xbootclasspath/a:. -XX:+UnlockDiagnosticVMOptions
 *                   -XX:+WhiteBoxAPI
 *                   compiler.arguments.TestUseBMI1InstructionsOnSupportedCPU
 */

public class TestUseBMI1InstructionsOnSupportedCPU
     extends BMISupportedCPUTest {

    public TestUseBMI1InstructionsOnSupportedCPU() {
        super("UseBMI1Instructions", BMI1_WARNING, "bmi1");
    }

    public static void main(String args[]) throws Throwable {
        new TestUseBMI1InstructionsOnSupportedCPU().test();
    }
}
