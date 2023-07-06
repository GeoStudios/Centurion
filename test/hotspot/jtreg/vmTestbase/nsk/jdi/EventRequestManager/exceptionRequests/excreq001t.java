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

package nsk.jdi.EventRequestManager.exceptionRequests;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * This is a debuggee class which loads several dummy classes.
 */
public class excreq001t {
    public static void main(String args[]) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        IOPipe pipe = argHandler.createDebugeeIOPipe();
// dummy clasees are below
        excreq001a dummyClsA = new excreq001a();
        excreq001b dummyClsB = new excreq001b();
        excreq001c dummyClsC = new excreq001c();

        pipe.println(excreq001.COMMAND_READY);
        String cmd = pipe.readln();
        if (!cmd.equals(excreq001.COMMAND_QUIT)) {
            System.err.println("TEST BUG: unknown debugger command: "
                + cmd);
            System.exit(excreq001.JCK_STATUS_BASE +
                excreq001.FAILED);
        }
        System.exit(excreq001.JCK_STATUS_BASE +
            excreq001.PASSED);
    }
}