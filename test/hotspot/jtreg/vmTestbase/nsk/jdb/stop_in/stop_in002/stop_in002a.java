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

package nsk.jdb.stop_in.stop_in002;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdb.*;

import java.io.*;

/* This is debuggee aplication */
public class stop_in002a {


    public static void main(String args[]) {
       stop_in002a _stop_in002a = new stop_in002a();
//       lastBreak();
       System.exit(stop_in002.JCK_STATUS_BASE + _stop_in002a.runIt(args, System.out));
    }

    static void lastBreak () {}

    public int runIt(String args[], PrintStream out) {
        JdbArgumentHandler argumentHandler = new JdbArgumentHandler(args);
        Log log = new Log(out, argumentHandler);

        stop_in002b b = new stop_in002b();
        stop_in002b.StaticNested.m1();
        b.inn.m2();
        b.foo(1);

        log.display("Debuggee PASSED");
        return stop_in002.PASSED;
    }
}

class stop_in002b {
    static int intField = 0;
    Inner inn = null;

    static { intField = 1; }

    stop_in002b () {
        intField++;
        inn = new Inner();
    }

    static class StaticNested {
        public static void m1() {}
    }

    class Inner {
        public void m2() {}
    }

    final int foo (int i) {return i++;}
}
