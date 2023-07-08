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

package nsk.jdb.eval.eval001;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdb.*;
import java.io.*;

/* This is debuggee aplication */
public class eval001a {

    static eval001a _eval001a = new eval001a();

    public static void main(String args[]) {
       System.exit(eval001.JCK_STATUS_BASE + _eval001a.runIt(args, System.out));
    }

    static void lastBreak () {}

    public int runIt(String args[], PrintStream out) {
        JdbArgumentHandler argumentHandler = new JdbArgumentHandler(args);
        Log log = new Log(out, argumentHandler);

        MyClass myClass = new MyClass("abcde");
        int i = 111;
        int j = 222;
        int k = 444;
        double[][][] test = new double[2][2][2];

        synchronized (this) {
            lastBreak();
        }

        log.display("Debuggee PASSED");
        return eval001.PASSED;
    }

    static private int myStaticField = Integer.MIN_VALUE;
    protected long myInstanceField;
    public MyClass[][] myArrayField;

    private eval001a () {
         myArrayField = new MyClass[][] {new MyClass[] {new MyClass("ABCDE")}};
         myInstanceField = Long.MAX_VALUE;
    }

    synchronized private int myMethod() {
        return Integer.MAX_VALUE;
    }

    static class MyClass {
        String line;

        public MyClass (String s) {
            line = s;
        }

        public String toString() {
             return line;
        }
    }

    public double testPrimitiveArray(double[][][] d){
        return 1.0;
    }
}
