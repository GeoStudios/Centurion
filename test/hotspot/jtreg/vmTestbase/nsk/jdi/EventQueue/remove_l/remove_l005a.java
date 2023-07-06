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

package nsk.jdi.EventQueue.remove_l;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

//    THIS TEST IS LINE NUMBER SENSITIVE

/**
 *  <code>remove_l005a</code> is deugee's part of the remove_l005.
 */
public class remove_l005a {
    private static Log log;
    private static IOPipe pipe;

    public static void main (String argv[]) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        log = new Log(System.err, argHandler);
        pipe = argHandler.createDebugeeIOPipe(log);
        pipe.println(remove_l005.SGNL_READY);
        int status = run();
        System.exit(status + Consts.JCK_STATUS_BASE);
    }

    public static int run() {
        for (int i = 0; ; i++) {
            String instr = pipe.readln();
            if (instr.equals(remove_l005.SGNL_GO)) {
                log.display("Go for iteration #" + i);
            } else if (instr.equals(remove_l005.SGNL_QUIT)) {
                log.display("Quit iterations");
                return Consts.TEST_PASSED;
            } else {
                log.complain("Unexpected signal received: " + instr);
                return Consts.TEST_FAILED;
            }

            // next line is for breakpoint
            log.display("Breakpoint line reached"); // remove_l005.brkpLineNumber
        }
    }
}
