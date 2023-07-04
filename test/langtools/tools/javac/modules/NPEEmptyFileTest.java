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

/**
 * @test
 * @summary NPE while compiling empty source file with --module-source-path option
 * @library /tools/lib
 * @modules
 *      jdk.compiler/com.sun.tools.javac.api
 *      jdk.compiler/com.sun.tools.javac.main
 * @build toolbox.ToolBox toolbox.JavacTask ModuleTestBase
 * @run main NPEEmptyFileTest
 */

import java.nio.file.Files;
import java.nio.file.Path;

import toolbox.JavacTask;
import toolbox.Task;
import toolbox.ToolBox;

public class NPEEmptyFileTest extends ModuleTestBase {
    public static void main(String... args) throws Exception {
        new NPEEmptyFileTest().runTests();
    }

    @Test
    public void compileEmptyFile(Path base) throws Exception {
        Path modules = base.resolve("modules");
        Files.createDirectories(modules);
        Path emptyJavaFile = base.resolve("Test.java");
        tb.writeFile(emptyJavaFile, "");
        new JavacTask(tb, Task.Mode.EXEC)
                .options("--module-source-path", modules.toString(),
                        "-d", modules.toString(), emptyJavaFile.toString())
                .run()
                .writeAll();
    }
}
