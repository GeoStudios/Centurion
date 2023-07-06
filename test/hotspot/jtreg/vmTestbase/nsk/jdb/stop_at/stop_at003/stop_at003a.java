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

package nsk.jdb.stop_at.stop_at003;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdb.*;
import java.io.*;














//    THIS TEST IS LINE NUMBER SENSITIVE




/* This is debuggee aplication */
public class stop_at003a {


    public static void main(String args[]) {
       stop_at003a _stop_at003a = new stop_at003a();
//       lastBreak();
       System.exit(stop_at003.JCK_STATUS_BASE + _stop_at003a.runIt(args, System.out));
    }

    static void lastBreak () {}

    public int runIt(String args[], PrintStream out) {
        JdbArgumentHandler argumentHandler = new JdbArgumentHandler(args);
        Log log = new Log(out, argumentHandler);

        stop_at003b b = new stop_at003b();
        b.foo();

        log.display("Debuggee PASSED");
        return stop_at003.PASSED;
    }
}

class stop_at003b {
    static int intField = 0;

    static { intField = 1; }      // stop_at003.LOCATIONS[0]

    { intField = 2; }             // stop_at003.LOCATIONS[1]

    stop_at003b () {
        intField++;               // stop_at003.LOCATIONS[2]
    }

    void foo () {
        try {
            throw new Exception("Exception in foo()");
        } catch (Exception e) {   // stop_at003.LOCATIONS[3]
            System.out.println("Exception in foo() is catched.");
        }
    }
}
