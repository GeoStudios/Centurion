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
 * @bug      8203176
 * @summary  javadoc handles non-ASCII characters incorrectly
 * @library  /tools/lib ../../lib
 * @modules jdk.javadoc/jdk.javadoc.internal.tool
 * @build    toolbox.ToolBox javadoc.tester.*
 * @run main TestUnicode
 */

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javadoc.tester.JavadocTester;
import toolbox.ToolBox;

public class TestUnicode extends JavadocTester {

    public static void main(String... args) throws Exception {
        TestUnicode tester = new TestUnicode();
        tester.runTests();
    }

    ToolBox tb = new ToolBox();

    @Test
    public void test() throws Exception {
        char ellipsis = '\u2026';
        Path src = Files.createDirectories(Paths.get("src"));
        tb.writeJavaFiles(src,
                "/** Hel" + ellipsis + "lo {@code World(" + ellipsis + ")}. */\n"
                + "public class Code { }\n");

        javadoc("-d", "out",
                "-encoding", "utf-8",
                src.resolve("Code.java").toString());
        checkExit(Exit.OK);

        checkOutput("Code.html", true,
                "<div class=\"block\">Hel" + ellipsis + "lo <code>World(" + ellipsis + ")</code>.</div>");
        checkOutput("Code.html", false,
                "\\u");
    }
}
