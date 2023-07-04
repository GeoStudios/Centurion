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
 * @bug 8202624
 * @summary javadoc generates references to enum constructors, which are not documented
 * @library /tools/lib ../../lib
 * @modules jdk.javadoc/jdk.javadoc.internal.tool
 * @build javadoc.tester.*
 * @run main TestEnumConstructor
 */


import java.nio.file.Path;
import java.nio.file.Paths;

import toolbox.ToolBox;

import javadoc.tester.JavadocTester;

public class TestEnumConstructor extends JavadocTester {

    final ToolBox tb;

    public static void main(String... args) throws Exception {
        TestEnumConstructor tester = new TestEnumConstructor();
        tester.runTests(m -> new Object[]{Paths.get(m.getName())});
    }

    TestEnumConstructor() {
        tb = new ToolBox();
    }

    @Test
    public void test1(Path base) throws Exception {
        Path srcDir = base.resolve("src");
        createEnum(srcDir);

        Path outDir = base.resolve("out");
        javadoc("-d", outDir.toString(),
                "-private",
                "-sourcepath", srcDir.toString(),
                "pkg");

        checkExit(Exit.OK);
        checkOrder("pkg/TestEnum.html",
                "Constructor Summary",
                "Modifier", "Constructor",
                "private", """
                    <a href="#%3Cinit%3E(int)" class="member-name-link">TestEnum</a><wbr>(int&nbsp;val)""");
        checkOutput("index-all.html", true,
                """
                    <a href="pkg/TestEnum.html#%3Cinit%3E(int)" class="member-name-link">TestEnum(int)</a>""");

    }

    @Test
    public void test2(Path base) throws Exception {
        Path srcDir = base.resolve("src");
        createEnum(srcDir);

        Path outDir = base.resolve("out");
        javadoc("-d", outDir.toString(),
                "-package",
                "-sourcepath", srcDir.toString(),
                "pkg");

        checkExit(Exit.OK);
        checkOutput("pkg/TestEnum.html", false, "Constructor Summary");
        checkOutput("index-all.html", false,
                """
                    <a href="pkg/TestEnum.html#%3Cinit%3E(int)">TestEnum(int)</a>""");
    }

    void createEnum(Path srcDir) throws Exception {
        tb.writeJavaFiles(srcDir,
                """
                    package pkg;
                    public enum TestEnum{
                    CONST1(100),CONST2(50),CONST3(10);
                    private int val;
                    TestEnum(int val){this.val=val;}
                    }""");
    }
}
