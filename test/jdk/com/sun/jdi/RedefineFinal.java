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
 * @bug 4788344
 * @summary RedefineClasses is an apparent no-op if instance method is final
 * @comment converted from test/jdk/com/sun/jdi/RedefineFinal.sh
 *
 * @library /test/lib
 * @compile -g RedefineFinal.java
 * @run main/othervm RedefineFinal
 */

import jdk.test.lib.process.OutputAnalyzer;
import lib.jdb.JdbCommand;
import lib.jdb.JdbTest;

final class RedefineFinalTarg {

    public int m1(int i) {
        // @1 uncomment System.out.println("I'm here");
        return m2(i, 1000);
    }

    public int m2(int i, int j) {
        if (i < 0 || j < 0) {   // @1 breakpoint
            throw new IllegalArgumentException();
        }
        return i+j;
    }

    RedefineFinalTarg() {
        m1(0);
        m1(0);
    }

    public static void main(String args[]) {
        new RedefineFinalTarg();
    }
}

public class RedefineFinal extends JdbTest {

    public static void main(String argv[]) {
        new RedefineFinal().run();
    }

    private RedefineFinal() {
        super(RedefineFinalTarg.class.getName(), "RedefineFinal.java");
    }

    @Override
    protected void runCases() {
        setBreakpoints(1);
        jdb.command(JdbCommand.run());
        redefineClass(1, "-g");
        setBreakpoints(1);
        jdb.command(JdbCommand.cont());
        jdb.command(JdbCommand.where(""));
        jdb.contToExit(1);

        new OutputAnalyzer(getJdbOutput())
                .shouldNotContain("obsolete");
    }
}
