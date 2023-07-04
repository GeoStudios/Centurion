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
 * @bug 4448658
 * @summary javac produces the inconsistent variable debug in while loops.
 * @comment converted from test/jdk/com/sun/jdi/GetLocalVariables3Test.sh
 *
 * @library /test/lib
 * @compile -g GetLocalVariables3Test.java
 * @run main/othervm GetLocalVariables3Test
 */

import jdk.test.lib.process.OutputAnalyzer;
import lib.jdb.JdbCommand;
import lib.jdb.JdbTest;


class GetLocalVariables3Targ {
    public static void main(String[] args) {
        System.out.println("Howdy!");
        int i = 1, j, k;
        while ((j = i) > 0) {
            k = j; i = k - 1;    // @1 breakpoint
        }
        System.out.println("Goodbye from GetLocalVariables3Targ!");
    }
}


public class GetLocalVariables3Test extends JdbTest {
    public static void main(String argv[]) {
        new GetLocalVariables3Test().run();
    }

    private GetLocalVariables3Test() {
        super(DEBUGGEE_CLASS);
    }

    private static final String DEBUGGEE_CLASS = GetLocalVariables3Targ.class.getName();

    @Override
    protected void runCases() {
        setBreakpointsFromTestSource("GetLocalVariables3Test.java", 1);
        // Run to breakpoint #1
        jdb.command(JdbCommand.run());

        jdb.command(JdbCommand.locals());

        jdb.contToExit(1);

        new OutputAnalyzer(getJdbOutput())
                .shouldContain("j = 1");
        new OutputAnalyzer(getDebuggeeOutput())
                .shouldContain("Howdy")
                .shouldContain("Goodbye from GetLocalVariables3Targ");
    }

}
