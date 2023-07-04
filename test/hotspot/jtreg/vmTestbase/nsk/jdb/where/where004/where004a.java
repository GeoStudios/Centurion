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

//    THIS TEST IS LINE NUMBER SENSITIVE

package nsk.jdb.where.where004;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdb.*;

import java.io.*;

/* This is debuggee aplication */
public class where004a {
    public static void main(String args[]) {
       where004a _where004a = new where004a();
       lastBreak();
       System.exit(where004.JCK_STATUS_BASE + _where004a.runIt(args, System.out)); // where004.FRAMES[6]
    }

    static void lastBreak () {}

    public int runIt(String args[], PrintStream out) {
        JdbArgumentHandler argumentHandler = new JdbArgumentHandler(args);
        Log log = new Log(out, argumentHandler);

        int i = func1(10); // where004.FRAMES[5]

        log.display("Debuggee PASSED");
        return where004.PASSED;
    }

    public int func1(int i) {
        return func2(i) + 1; // where004.FRAMES[4]
    }

    public int func2(int i) {
        return func3(i) + 1; // where004.FRAMES[3]
    }

    public int func3(int i) {
        return func4(i) + 1; // where004.FRAMES[2]
    }

    public int func4(int i) {
       return func5(i) + 1; // where004.FRAMES[1]
    }

    public int func5(int i) {
       return func6(i) + 1; // this is line for breakpoint // where004.FRAMES[0]
    }

    public int func6(int i) {
        return i-5;
    }
}
