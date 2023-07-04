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
 * @bug 8006582 8008658
 * @summary javac should generate method parameters correctly.
 * @modules jdk.jdeps/com.sun.tools.classfile
 * @build MethodParametersTester ClassFileVisitor ReflectionVisitor
 * @compile -parameters LocalClassTest.java
 * @run main MethodParametersTester LocalClassTest LocalClassTest.out
 */

class LocalClassTest {
    void foo() {
        class Local_default_constructor {
            public void foo() {}
            public void foo(int m, int nm) {}
        }
        class Local_has_constructor {
            public Local_has_constructor() {}
            public Local_has_constructor(int a, int ba) {}
            public void foo() {}
            public void foo(int m, int nm) {}
        }
        new LocalClassTest().foo();
    }

    void test(final int i) {
        class CapturingLocal {
            CapturingLocal(final int j) {
               this(new Object() { void test() { int x = i; } });
            }
            CapturingLocal(Object o) { }
        }
        new CapturingLocal(i) { };
    }
}



