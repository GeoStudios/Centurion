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
 * @bug 4788864
 * @summary TTY: 'catch caught' with no class pattern throws NullPointerException
 * @comment converted from test/jdk/com/sun/jdi/CatchCaughtTest.sh
 *
 * @library /test/lib
 * @build CatchCaughtTest
 * @run main/othervm CatchCaughtTest
 */

import jdk.test.lib.process.OutputAnalyzer;
import lib.jdb.JdbCommand;
import lib.jdb.JdbTest;

class CatchCaughtTestTarg {
    public void bar() {
        System.out.println("bar");        // @1 breakpoint
    }

    public static void main(String[] args) {
        CatchCaughtTestTarg my = new CatchCaughtTestTarg();
        my.bar();
    }
}

public class CatchCaughtTest extends JdbTest {
    public static void main(String argv[]) {
        new CatchCaughtTest().run();
    }

    private CatchCaughtTest() {
        super(DEBUGGEE_CLASS);
    }

    private static final String DEBUGGEE_CLASS = CatchCaughtTestTarg.class.getName();

    @Override
    protected void runCases() {
        setBreakpointsFromTestSource("CatchCaughtTest.java", 1);
        // Run to breakpoint #1
        jdb.command(JdbCommand.run());

        jdb.command(JdbCommand.catch_(JdbCommand.ExType.caught, ""));

        jdb.contToExit(1);

        new OutputAnalyzer(getJdbOutput())
                .shouldNotContain("Internal exception")
                .shouldContain("Usage: catch");
    }

}
