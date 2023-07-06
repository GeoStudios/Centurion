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

package compiler.compilercontrol;

import jdk.test.lib.dcmd.CommandExecutor;

/*
 * @test TestCompilerDirectivesCompatibilityFlag
 * @bug 8137167
 * @library /test/lib /
 * @modules java.base/jdk.internal.misc
 *          java.compiler
 *          java.management
 *
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run testng/othervm -Xbootclasspath/a:. -Xmixed -XX:+UnlockDiagnosticVMOptions
 *      -XX:+PrintAssembly -XX:+WhiteBoxAPI
 *      compiler.compilercontrol.TestCompilerDirectivesCompatibilityFlag
 * @summary Test compiler control compatibility with compile command
 */

public class TestCompilerDirectivesCompatibilityFlag extends TestCompilerDirectivesCompatibilityBase {

    public void testCompatibility(CommandExecutor executor, int comp_level) throws Exception {

        // Call all validation twice to catch error when overwriting a directive
        // Flag is default on
        expect(WB.getBooleanVMFlag("PrintAssembly"));
        expect(WB.shouldPrintAssembly(method, comp_level));
        expect(WB.shouldPrintAssembly(nomatch, comp_level));
        expect(WB.shouldPrintAssembly(method, comp_level));
        expect(WB.shouldPrintAssembly(nomatch, comp_level));

        // load directives that turn it off
        executor.execute("Compiler.directives_add " + control_off);
        expect(!WB.shouldPrintAssembly(method, comp_level));
        expect(WB.shouldPrintAssembly(nomatch, comp_level));
        expect(!WB.shouldPrintAssembly(method, comp_level));
        expect(WB.shouldPrintAssembly(nomatch, comp_level));

        // remove and see that it is true again
        executor.execute("Compiler.directives_remove");
        expect(WB.shouldPrintAssembly(method, comp_level));
        expect(WB.shouldPrintAssembly(nomatch, comp_level));
        expect(WB.shouldPrintAssembly(method, comp_level));
        expect(WB.shouldPrintAssembly(nomatch, comp_level));
    }
}