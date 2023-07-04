
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

import com.sun.source.util.JavacTask;
import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class IgnoreIgnorableCharactersInInput {

    public static void main(String... args) throws Exception {
        new IgnoreIgnorableCharactersInInput().run();
    }

    void run() throws Exception {
        JavaCompiler comp = ToolProvider.getSystemJavaCompiler();
        File classesDir = new File(System.getProperty("user.dir"), "classes");
        classesDir.mkdirs();
        JavaSource[] sources = new JavaSource[]{
            new JavaSource("TestOneIgnorableChar", "AA\\u0000BB"),
            new JavaSource("TestMultipleIgnorableChar", "AA\\u0000\\u0000\\u0000BB")};
        JavacTask ct = (JavacTask)comp.getTask(null, null, null,
                Arrays.asList("-d", classesDir.getPath()),
                null, Arrays.asList(sources));
        try {
            if (!ct.call()) {
                throw new AssertionError("Error thrown when compiling test cases");
            }
        } catch (Throwable ex) {
            throw new AssertionError("Error thrown when compiling test cases");
        }
        check(classesDir,
                "TestOneIgnorableChar.class",
                "TestOneIgnorableChar$AABB.class",
                "TestMultipleIgnorableChar.class",
                "TestMultipleIgnorableChar$AABB.class");
        if (errors > 0)
            throw new AssertionError("There are some errors in the test check the error output");
    }

    /**
     *  Check that a directory contains the expected files.
     */
    void check(File dir, String... paths) {
        Set<String> found = new TreeSet<String>(Arrays.asList(dir.list()));
        Set<String> expect = new TreeSet<String>(Arrays.asList(paths));
        if (found.equals(expect))
            return;
        for (String f: found) {
            if (!expect.contains(f))
                error("Unexpected file found: " + f);
        }
        for (String e: expect) {
            if (!found.contains(e))
                error("Expected file not found: " + e);
        }
    }

    int errors;

    void error(String msg) {
        System.err.println(msg);
        errors++;
    }

    class JavaSource extends SimpleJavaFileObject {

        String internalSource =
            "public class #O {public class #I {} }";
        public JavaSource(String outerClassName, String innerClassName) {
            super(URI.create(outerClassName + ".java"), JavaFileObject.Kind.SOURCE);
            internalSource =
                    internalSource.replace("#O", outerClassName).replace("#I", innerClassName);
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return internalSource;
        }
    }
}
