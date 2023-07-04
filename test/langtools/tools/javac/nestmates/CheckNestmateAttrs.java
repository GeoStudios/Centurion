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
 * @summary Smoke test for nestmate classfile support
 * @run main CheckNestmateAttrs
 * @modules
 *      jdk.compiler
 *      jdk.jdeps/com.sun.tools.javap
 */

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;

public class CheckNestmateAttrs {

    private void test() { }

    class Inner {
        void m() {
            class LocalInner {
                void testInner() {
                    test();
                }
            }
        }
    }

    static class Nested {
        void s() {
            class LocalNested { }
        }
    }

    public static void main(String[] args) {
        new CheckNestmateAttrs().run();
    }

    void run() {
        String [] params = new String [] { "-v",
                                            Paths.get(System.getProperty("test.classes"),
                                                "CheckNestmateAttrs.class").toString() };
        runCheck(params, new String [] {
                        "NestMembers:" +
                        "  CheckNestmateAttrs$Nested" +
                        "  CheckNestmateAttrs$Nested$1LocalNested" +
                        "  CheckNestmateAttrs$Inner" +
                        "  CheckNestmateAttrs$Inner$1LocalInner"
                        });

        params = new String [] { "-v",
                                 Paths.get(System.getProperty("test.classes"),
                                     "CheckNestmateAttrs$Inner.class").toString() };

        runCheck(params, new String [] { "NestHost: class CheckNestmateAttrs" });

        params = new String [] { "-v",
                                 Paths.get(System.getProperty("test.classes"),
                                     "CheckNestmateAttrs$Nested.class").toString() };

        runCheck(params, new String [] { "NestHost: class CheckNestmateAttrs" });

        params = new String [] { "-v",
                                 Paths.get(System.getProperty("test.classes"),
                                     "CheckNestmateAttrs$Inner$1LocalInner.class").toString() };

        runCheck(params, new String [] {
                        "NestHost: class CheckNestmateAttrs",
                        "0: aload_0",
                        "1: getfield      #1                  // Field this$1:LCheckNestmateAttrs$Inner;",
                        "4: getfield      #13                 // Field CheckNestmateAttrs$Inner.this$0:LCheckNestmateAttrs;",
                        "7: invokevirtual #19                 // Method CheckNestmateAttrs.test:()V",
                        "10: return"
        });

        params = new String [] { "-v",
                                 Paths.get(System.getProperty("test.classes"),
                                     "CheckNestmateAttrs$Nested$1LocalNested.class").toString() };

        runCheck(params, new String [] { "NestHost: class CheckNestmateAttrs" });
     }

     void runCheck(String [] params, String [] expectedOut) {
        StringWriter s;
        String out;

        try (PrintWriter pw = new PrintWriter(s = new StringWriter())) {
            com.sun.tools.javap.Main.run(params, pw);
            out = s.toString();
        }
        for (String eo: expectedOut) {
            if (!out.contains(eo)) {
                System.out.println("Actual output: " + out);
                throw new AssertionError("Missing output: " + eo);
            }
        }
    }
}
