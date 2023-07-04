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

/*
 * @test
 * @bug 8210244 8261976
 * @summary {@value} should be permitted in module documentation
 * @library /tools/lib ../../lib
 * @modules jdk.javadoc/jdk.javadoc.internal.tool
 *          jdk.compiler/com.sun.tools.javac.api
 *          jdk.compiler/com.sun.tools.javac.main
 * @build javadoc.tester.*
 * @run main TestValueTagInModule
 */


import java.nio.file.Path;
import java.nio.file.Paths;

import toolbox.ModuleBuilder;
import toolbox.ToolBox;

import javadoc.tester.JavadocTester;

public class TestValueTagInModule extends JavadocTester {

    final ToolBox tb;

    public static void main(String... args) throws Exception {
        TestValueTagInModule tester = new TestValueTagInModule();
        tester.runTests(m -> new Object[]{Paths.get(m.getName())});
    }

    TestValueTagInModule() {
        tb = new ToolBox();
    }

    @Test
    public void test(Path base) throws Exception {
        Path srcDir = base.resolve("src");
        createTestClass(srcDir);

        Path outDir = base.resolve("out");
        javadoc("-d", outDir.toString(),
                "--module-source-path", srcDir.toString(),
                "--module", "m1");

        checkExit(Exit.OK);

        checkOutput("m1/module-summary.html", true,
                """
                    <section class="module-description" id="module-description">
                    <!-- ============ MODULE DESCRIPTION =========== -->
                    <div class="block">value of field CONS : <a href="pkg/A.html#CONS">100</a></div>""");
    }

    void createTestClass(Path srcDir) throws Exception {
        new ModuleBuilder(tb, "m1")
                .comment("value of field CONS : {@value pkg.A#CONS}")
                .exports("pkg")
                .classes("package pkg; public class A{ public static final int CONS = 100;}")
                .write(srcDir);
    }
}
