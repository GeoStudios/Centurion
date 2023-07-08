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

package nsk.jdi.EventRequest._bounds_;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














//    THIS TEST IS LINE NUMBER SENSITIVE

/**
 *  <code>eventrequest001a</code> is deugee's part of the eventrequest001.
 */
public class eventrequest001a {

    public final static String brkpMethodName = "main";
    public final static int brkpLineNumber = 47;
    private static Log log;
    private static IOPipe pipe;

    public static void main (String argv[]) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        log = new Log(System.err, argHandler);
        pipe = argHandler.createDebugeeIOPipe(log);
        pipe.println(eventrequest001.SGNL_READY);
        String instr = pipe.readln(); // brkpLineNumber
        if (instr.equals(eventrequest001.SGNL_QUIT)) {
            log.display("constructor> completed succesfully.");
            System.exit(Consts.TEST_PASSED + Consts.JCK_STATUS_BASE);
        }

        log.complain("constructor> unexpected signal of debugger.");
        System.exit(Consts.TEST_FAILED + Consts.JCK_STATUS_BASE);
    }
}
