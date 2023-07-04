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
 * @bug 8005653
 * @summary A lambda containing an inner class referencing an external type var
 * @author  Robert Field
 * @modules jdk.compiler
 *          jdk.jdeps/com.sun.tools.javap
 * @run main LambdaInnerTypeVarReflect
 */

import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class LambdaInnerTypeVarReflect {

    static int assertionCount = 0;

    static void assertTrue(boolean cond) {
        assertionCount++;
        if (!cond)
            throw new AssertionError();
    }

    interface I {
        C doit();
    }

    abstract class C {
        abstract Object it();
    }

    class TV {
        C go() {
            return foo("Frump").doit();
        }

        <RRRRR> I foo(RRRRR r) {
            return () -> new C() {
                public RRRRR xxxxx = r;
                @Override
                    Object it() { return xxxxx; };
            };
        }
    }

    void test1() throws IOException {
        char[] buffer = new char[1024];
        String innerName = new TV().go().getClass().getName();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        int exitCode = com.sun.tools.javap.Main.run(new String[] {innerName}, pw);
        assertTrue(exitCode == 0);

        String javapOut = sw.toString();
        assertTrue(javapOut.contains(innerName));
        assertTrue(javapOut.contains("RRRRR"));
    }

    public static void main(String[] args) throws IOException {
        LambdaInnerTypeVarReflect t = new LambdaInnerTypeVarReflect();
        t.test1();
        assertTrue(assertionCount == 3);
    }
}
