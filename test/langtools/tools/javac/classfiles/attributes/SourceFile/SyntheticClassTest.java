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
 * @summary sourcefile attribute test for synthetic class.
 * @bug 8040129
 * @library /tools/lib /tools/javac/lib ../lib
 * @modules jdk.compiler/com.sun.tools.javac.api
 *          jdk.compiler/com.sun.tools.javac.main
 *          jdk.jdeps/com.sun.tools.classfile
 * @build toolbox.ToolBox InMemoryFileManager TestBase SourceFileTestBase
 * @compile -source 10 -target 10 SyntheticClassTest.java
 * @run main SyntheticClassTest true
 * @clean SyntheticClassTest$1
 * @compile SyntheticClassTest.java
 * @run main SyntheticClassTest false
 */

import java.nio.file.NoSuchFileException;

public class SyntheticClassTest extends SourceFileTestBase {
    public static void main(String[] args) throws Exception {
        boolean expectSynthetic = Boolean.parseBoolean(args[0]);
        new Inner();

        try {
            new SyntheticClassTest().test("SyntheticClassTest$1", "SyntheticClassTest.java");
            if (!expectSynthetic) {
                throw new AssertionError("Synthetic class should not have been emitted!");
            }
        } catch (NoSuchFileException ex) {
            if (expectSynthetic) {
                throw new AssertionError("Synthetic class should have been emitted!");
            }
        }
    }

    static class Inner {
        private Inner() {
        }
    }
}
