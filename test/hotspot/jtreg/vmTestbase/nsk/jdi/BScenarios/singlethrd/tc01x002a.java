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

package nsk.jdi.BScenarios.singlethrd;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

//    THIS TEST IS LINE NUMBER SENSITIVE

/**
 *  <code>tc01x002a</code> is deugee's part of the tc01x002.
 */
public class tc01x002a {

    public final static String brkpMethodName = "performTest";
    public final static int brkpLineNumber = 69;

    public final static int checkLastLine = 76;
    static Log log;

    public static void main (String argv[]) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        log = new Log(System.err, argHandler);
        IOPipe pipe = argHandler.createDebugeeIOPipe(log);
        pipe.println(tc01x002.SGL_READY);

        tc01x002a obj = null;
        String instr;
        do {
            instr = pipe.readln();
            if (instr.equals(tc01x002.SGL_PERFORM)) {
                performTest();
            } else if (instr.equals(tc01x002.SGL_QUIT)) {
                log.display(instr);
                break;
            } else {
                log.complain("DEBUGEE> unexpected signal of debugger.");
                System.exit(Consts.TEST_FAILED + Consts.JCK_STATUS_BASE);
            }
        } while (true);
        log.display("completed succesfully.");
        System.exit(Consts.TEST_PASSED + Consts.JCK_STATUS_BASE);
    }

    public static void performTest() {
        log.display("performTest::line 0");
        log.display("performTest::breakpoint line"); // brkpLineNumber
        log.display("performTest::creating tc01x002aClass1 object");
        new tc01x002aClass1();
    }
}

class tc01x002aClass1 {
    tc01x002aClass1() { // checkLastLine
        tc01x002a.log.display("tc01x002aClass1::constructor is called");
    }
}
