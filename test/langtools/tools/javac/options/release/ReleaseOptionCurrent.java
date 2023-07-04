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
 * @bug 8202387
 * @summary Verify that javac can handle --release <current>
 * @modules jdk.compiler/com.sun.tools.javac.code
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.sun.tools.javac.code.Source;

public class ReleaseOptionCurrent {
    public static void main(String... args) throws IOException {
        new ReleaseOptionCurrent().run();
    }

    void run() throws IOException {
        String lineSep = System.getProperty("line.separator");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null)) {
            Iterable<? extends JavaFileObject> input =
                    fm.getJavaFileObjects(System.getProperty("test.src") + "/ReleaseOption.java");
            List<String> options = Arrays.asList("-d", ".", "--release", Source.DEFAULT.name);

            boolean result = compiler.getTask(null, fm, null, options, null, input).call();
            if (!result) {
                throw new AssertionError("Compilation failed unexpectedly.");
            }
        }
    }
}
