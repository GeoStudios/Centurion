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

package nsk.jdi.BScenarios.hotswap;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

//    THIS TEST IS LINE NUMBER SENSITIVE

/**
 *  <code>tc05x001a</code> is deugee's part of the tc05x001.
 */
public class tc05x001a {

    public final static String brkpMethodName = "method_A";
    public final static int brkpLineNumber = 71;
    private static Log log;

    public static void main (String argv[]) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        log = new Log(System.err, argHandler);
        IOPipe pipe = argHandler.createDebugeeIOPipe(log);
        pipe.println("ready");

        String instr;
        do {
            instr = pipe.readln();
            if (instr.equals("perform")) {
                runIt();
            } else if (instr.equals("quit")) {
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

    private static void runIt() {
        tc05x001a obj = new tc05x001a();
        obj.method_A();
    }

    public void method_A() {
        method_B(); // brkpLineNumber
//        log.display("new line");
//        try inserting code here
    }

    public void method_B() {
        method_C();
    }

    public void method_C() {
        log.display("method_C:: line 1");
        log.display("method_C:: line 2");
        log.display("method_C:: line 3");
        log.display("method_C:: line 4");
    }
}
