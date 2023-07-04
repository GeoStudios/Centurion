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
 * @summary Test to check that -j option works with more than one value
 * @bug 8071629
 * @author sogoel
 * @library /tools/lib
 * @modules jdk.compiler/com.sun.tools.javac.api
 *          jdk.compiler/com.sun.tools.javac.main
 *          jdk.compiler/com.sun.tools.sjavac
 * @build Wrapper toolbox.ToolBox
 * @run main Wrapper ParallelCompilations
 */

import java.io.*;
import java.nio.file.*;
import com.sun.tools.sjavac.Main;

class ParallelCompilations extends SJavacTester {
  public static void main(String[] args) throws Exception {
    new ParallelCompilations().run();
  }

  public void run() throws Exception {
    // Generate 10 files
    for (int i = 0; i < 10; i++) {
      String content = "package foo"+ i + ";\n" +
                       "public class Test" + i + "{\n" +
                       "  public static void main(String[] args) {}\n" +
                       "\n}";
      Path srcDir = Paths.get("src");
      tb.writeJavaFiles(srcDir, content);
    }
    // Method will throw an exception if compilation fails
    compile("src",
            "-d", BIN.toString(),
            "--state-dir=" + BIN,
            "-j", "10",
            "--log=debug");
  }
}
