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














/**
 * This is a debuggee part of the test.
 */
public class remove_l003t {
    public static void main(String args[]) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        Log log = argHandler.createDebugeeLog();
        IOPipe pipe = argHandler.createDebugeeIOPipe();

        log.display("Debuggee: ready");
        pipe.println(remove_l003.COMMAND_READY);
        String cmd = pipe.readln();
        if (!cmd.equals(remove_l003.COMMAND_QUIT)) {
            System.err.println("TEST BUG: unknown debugger command: "
                + cmd + "\nDebuggee: exiting");
            System.exit(remove_l003.JCK_STATUS_BASE +
                remove_l003.FAILED);
        }
        log.display("Debuggee: exiting");
        System.exit(remove_l003.JCK_STATUS_BASE +
            remove_l003.PASSED);
    }
}
