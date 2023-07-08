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

package nsk.jdi.VMStartEvent.thread;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














// This class is the debugged application in the test

class thread001a {
    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int JCK_STATUS_BASE = 95;
    static final String COMMAND_READY = "ready";
    static final String COMMAND_QUIT = "quit";

    public static void main(String args[]) {
        thread001a _thread001a = new thread001a();
        System.exit(JCK_STATUS_BASE + _thread001a.communication(args));
    }

    int communication( String args[]) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        IOPipe pipe = argHandler.createDebugeeIOPipe();

        // notify debbugger that debuggee started
        pipe.println(COMMAND_READY);

        // wait from debugger command QUIT
        String command = pipe.readln();
        if (!command.equals(COMMAND_QUIT)) {
              System.err.println("TEST BUG: Debugee: unknown command: " +
                   command);
              return FAILED;
        }
        return PASSED;
    }
}
