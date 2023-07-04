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
 * @bug 6558476 5071352
 * @summary com/sun/tools/javac/Main.compile don't release file handles on return
 * @library /tools/lib
 * @modules jdk.compiler/com.sun.tools.javac.api
 *          jdk.compiler/com.sun.tools.javac.main
 *          jdk.jdeps/com.sun.tools.javap
 * @build toolbox.ToolBox toolbox.JarTask toolbox.JavacTask
 * @run main/othervm -Xmx512m -Xms512m  T6558476
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.sun.tools.javac.Main;

import toolbox.JarTask;
import toolbox.JavacTask;
import toolbox.ToolBox;

public class T6558476 {

    private static final String classFoo = "class Foo {}";
    private static final String classMyFoo =
        "class MyFoo {\n" +
         "  public static void main(String[] args) {\n"+
         "    new Foo();\n"+
         "  }\n"+
        "}";

    public static void main(String[] args) throws IOException {
        ToolBox tb = new ToolBox();

        new JavacTask(tb)
            .sources(classFoo)
            .run();
        new JarTask(tb, "foo.jar")
            .files("Foo.class")
            .run();

        new JavacTask(tb)
            .classpath("foo.jar")
            .sources(classMyFoo)
            .run();
        File foo_jar = new File("foo.jar");
        if (foo_jar.delete()) {
            System.out.println("jar file successfully deleted");
        } else {
            throw new Error("Error deleting file \"" + foo_jar.getPath() + "\"");
        }
    }
}
