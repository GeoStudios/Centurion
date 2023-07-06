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
 *  <code>tc04x001a</code> is deugee's part of the tc02x001.
 */
public class tc04x001a {

    public final static int checkExBrkpLine = 69;
    static Log log;

    public static void main (String argv[]) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        log = new Log(System.err, argHandler);
        IOPipe pipe = argHandler.createDebugeeIOPipe(log);
        pipe.println(tc04x001.SGL_READY);

        tc04x001a obj = null;
        String instr;
        do {
            instr = pipe.readln();
            log.display("instruction \"" + instr +"\"");
            if (instr.equals(tc04x001.SGL_LOAD)) {
                tc04x001aException.loadThis = true;
                performTest();
            } else if (instr.equals(tc04x001.SGL_QUIT)) {
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
        log.display("performTest:: throwing tc04x001aException");
        try {
            throw new tc04x001aException(); // checkExBrkpLine
        } catch (tc04x001aException e) {
        }
    }
}

class tc04x001aException extends RuntimeException {
    static boolean loadThis = false;
}
