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

package nsk.jdi.ClassType.newInstance;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














//    THIS TEST IS LINE NUMBER SENSITIVE
/**
 *  <code>newinstance003a</code> is deugee's part of the newinstance003.
 */
public class newinstance003a {

    public final static String anotherClassName = "newinstance003b";
    public final static String class2Check = "newinstance003Child";
    public final static String method = "insteadOfConstructor";
    public final static String brkpMethodName = "main";
    public final static int brkpLineNumber = 49;

    public static void main (String argv[]) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        Log log = new Log(System.err, argHandler);
        IOPipe pipe = argHandler.createDebugeeIOPipe(log);
        newinstance003Child child = new newinstance003Child(1);
        pipe.println(newinstance003.SGNL_READY);
        String instr = pipe.readln();
        log.display("breakpoint line"); // brkpLineNumber
        instr = pipe.readln();
        if (instr.equals(newinstance003.SGNL_QUIT)) {
            log.display("completed succesfully.");
            System.exit(Consts.TEST_PASSED + Consts.JCK_STATUS_BASE);
        }

        log.complain("DEBUGEE> unexpected signal of debugger.");
        System.exit(Consts.TEST_FAILED + Consts.JCK_STATUS_BASE);

    }

    protected static long sum;
    newinstance003a(int one) {
        sum = one;
    }
}

class newinstance003Child extends newinstance003a {
    newinstance003Child(int one) {
        super(one);
    }

    public static void insteadOfConstructor() {
    }
}
