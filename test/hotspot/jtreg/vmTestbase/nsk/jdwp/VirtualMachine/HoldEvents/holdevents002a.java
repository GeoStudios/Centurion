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

package nsk.jdwp.VirtualMachine.HoldEvents;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdwp.*;
import java.io.*;

//    THIS TEST IS LINE NUMBER SENSITIVE

/**
 * This class represents debuggee part in the test.
 */
public class holdevents002a {

    static final int BREAKPOINT_LINE = 80;

    static ArgumentHandler argumentHandler = null;
    static Log log = null;

    public static void main(String args[]) {
        holdevents002a _holdevents002a = new holdevents002a();
        System.exit(holdevents002.JCK_STATUS_BASE + _holdevents002a.runIt(args, System.err));
    }

    public int runIt(String args[], PrintStream out) {
        //make log for debugee messages
        argumentHandler = new ArgumentHandler(args);
        log = new Log(out, argumentHandler);

        // ensure tested class is loaded
        log.display("Creating object of tested class");
        TestedClass object = new TestedClass();
        log.display("  ... object created");

        // invoke method with breakpoint
        log.display("Invoking method with breakpoint");
        object.run();
        log.display("  ... method invoked");

        // exit debugee
        log.display("Debugee PASSED");
        return holdevents002.PASSED;
    }

    // tested class
    public static class TestedClass {
        int foo = 0;

        public TestedClass() {
            foo = 1000;
        }

        public void run() {
            log.display("Breakpoint line reached");
            // next line is for breakpoint
            foo = 0; // BREAKPOINT_LINE
            log.display("Breakpoint line passed");
        }
    }
}