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

package nsk.jdi.MethodExitEvent._itself_;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import java.io.*;

//    THIS TEST IS LINE NUMBER SENSITIVE
// This class is the debugged application in the test
public class methodexit001a {

    // exit status constants
    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int JCK_STATUS_BASE = 95;

    // synchronization commands
    static final String COMMAND_READY = "ready";
    static final String COMMAND_QUIT  = "quit";
    static final String COMMAND_GO    = "go";
    static final String COMMAND_DONE  = "done";

    // line numbers for auxilary breakpoints
    public static final int STARTING_BREAKPOINT_LINE = 81;
    public static final int ENDING_BREAKPOINT_LINE = 90;

    // scaffold objects
    static private ArgumentHandler argHandler;
    static private Log log;
    static private IOPipe pipe;

    // flags and counters
    static private boolean methodInvoked;

    // start debuggee
    public static void main(String args[]) {
        methodexit001a _methodexit001a = new methodexit001a();
        System.exit(JCK_STATUS_BASE + _methodexit001a.run(args, System.err));
    }

    // perform the test
    static int run(String args[], PrintStream out) {
        argHandler = new ArgumentHandler(args);
        log = new Log(out, argHandler);
        pipe = argHandler.createDebugeeIOPipe();

        // notify debugger that debuggee has been started
        pipe.println(COMMAND_READY);

        // wait for GO commnad from debugger
        String command = pipe.readln();
        if (!command.equals(COMMAND_GO)) {
            log.complain("TEST BUG: Debugee: unknown command: " + command);
            return FAILED;
        }

        methodInvoked = false; // STARTING_BREAKPOINT_LINE

        // invoke checked method with exception caught
        try {
            foo();
        } catch (methodexit001e e) {
            // do not invoke any methods here to prevent from generation of events
        }

        methodInvoked = true; // ENDING_BREAKPOINT_LINE

        // notify debugger that checked method has been invoked
        pipe.println(COMMAND_DONE);

        // wait for command QUIT from debugger
        command = pipe.readln();
        if (!command.equals(COMMAND_QUIT)) {
            System.err.println("TEST BUG: Debugee: unknown command: " + command);
            return FAILED;
        }

        return PASSED;
    }

    // checked method
    static void foo() throws methodexit001e {
        throw new methodexit001e();
    }
}

// checked exception
class methodexit001e extends Exception {}
