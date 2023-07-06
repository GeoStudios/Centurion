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

package compiler.compilercontrol.logcompilation;

import compiler.compilercontrol.share.processors.LogProcessor;
import compiler.compilercontrol.share.scenario.Scenario;

/*
 * @test
 * @bug 8137167
 * @summary Tests LogCompilation executed standalone without log commands or directives
 *
 * @requires !vm.graal.enabled
 * @modules java.base/jdk.internal.misc
 * @library /test/lib /
 *
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run driver compiler.compilercontrol.logcompilation.LogTest
 */

public class LogTest {
    public static void main(String[] args) {
        Scenario.Builder builder = Scenario.getBuilder();
        builder.addFlag("-XX:+UnlockDiagnosticVMOptions");
        builder.addFlag("-Xbootclasspath/a:.");
        builder.addFlag("-XX:+WhiteBoxAPI");
        builder.addFlag("-XX:+LogCompilation");
        builder.addFlag("-XX:LogFile=" + LogProcessor.LOG_FILE);
        Scenario scenario = builder.build();
        scenario.execute();
    }
}