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

/*
 * @test
 * @bug 8252219 8256535
 * @requires vm.compiler2.enabled
 * @summary Tests that different combinations of stress options and
 *          -XX:StressSeed=N are accepted.
 * @run main/othervm -XX:+UnlockDiagnosticVMOptions -XX:+StressIGVN
 *      compiler.arguments.TestStressOptions
 * @run main/othervm -XX:+UnlockDiagnosticVMOptions -XX:+StressIGVN -XX:StressSeed=42
 *      compiler.arguments.TestStressOptions
 * @run main/othervm -XX:+UnlockDiagnosticVMOptions -XX:+StressCCP
 *      compiler.arguments.TestStressOptions
 * @run main/othervm -XX:+UnlockDiagnosticVMOptions -XX:+StressCCP -XX:StressSeed=42
 *      compiler.arguments.TestStressOptions
 * @run main/othervm -XX:+UnlockDiagnosticVMOptions -XX:+StressLCM
 *      compiler.arguments.TestStressOptions
 * @run main/othervm -XX:+UnlockDiagnosticVMOptions -XX:+StressLCM -XX:StressSeed=42
 *      compiler.arguments.TestStressOptions
 * @run main/othervm -XX:+UnlockDiagnosticVMOptions -XX:+StressGCM
 *      compiler.arguments.TestStressOptions
 * @run main/othervm -XX:+UnlockDiagnosticVMOptions -XX:+StressGCM -XX:StressSeed=42
 *      compiler.arguments.TestStressOptions
 */

public class TestStressOptions {

    static public void main(String[] args) {
        System.out.println("Passed");
    }
}
