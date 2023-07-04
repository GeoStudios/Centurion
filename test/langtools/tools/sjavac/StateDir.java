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
 * @summary Verify that --state-dir=bar works
 * @bug 8054689
 * @author Fredrik O
 * @author sogoel (rewrite)
 * @library /tools/lib
 * @modules jdk.compiler/com.sun.tools.javac.api
 *          jdk.compiler/com.sun.tools.javac.main
 *          jdk.compiler/com.sun.tools.sjavac
 * @build Wrapper toolbox.ToolBox
 * @run main Wrapper StateDir
 */

import java.util.*;
import java.nio.file.*;

public class StateDir extends SJavacTester {
    public static void main(String... args) throws Exception {
        StateDir sd = new StateDir();
        sd.test();
    }

    void test() throws Exception {
        Path BAR = TEST_ROOT.resolve("bar");
        Files.createDirectories(BAR);
        Files.createDirectories(BIN);

        Map<String,Long> previous_bin_state = collectState(BIN);
        Map<String,Long> previous_bar_state = collectState(BAR);

        tb.writeFile(GENSRC.resolve("alfa/omega/A.java"),
                     "package alfa.omega; public class A { }");

        compile("--state-dir=" + BAR,
                "-src", GENSRC.toString(),
                "-d", BIN.toString());

        Map<String,Long> new_bin_state = collectState(BIN);
        verifyThatFilesHaveBeenAdded(previous_bin_state, new_bin_state,
                                     BIN + "/alfa/omega/A.class");
        Map<String,Long> new_bar_state = collectState(BAR);
        verifyThatFilesHaveBeenAdded(previous_bar_state, new_bar_state,
                                     BAR + "/javac_state");
    }
}
