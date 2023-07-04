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

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

/*
 * This class is present during compilation, but will be deleted before execution.
 */
class MyUnresolvedClass {
    static void bar() {
    }
}

class MyRedefinedClass {
    static void foo() {
        MyUnresolvedClass.bar();
    }
}

public class UnresolvedClassAgent {
    public static void main(String... args) {
    }

    public static void premain(String args, Instrumentation inst) throws Exception {
        try {
            MyRedefinedClass.foo();
        } catch(NoClassDefFoundError err) {
            System.out.println("NoClassDefFoundError (expected)");
        }

        File f = new File(System.getProperty("test.classes"), "MyRedefinedClass.class");
        byte[] buf = new byte[(int)f.length()];
        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            dis.readFully(buf);
        }
        ClassDefinition cd = new ClassDefinition(MyRedefinedClass.class, buf);
        inst.redefineClasses(new ClassDefinition[] {cd});

        try {
            MyRedefinedClass.foo();
        } catch(NoClassDefFoundError err) {
            System.out.println("NoClassDefFoundError (expected again)");
        }
    }
}
