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

package nsk.jdwp.ObjectReference.IsCollected;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdwp.*;
import java.io.*;














/**
 * This class represents debuggee part in the test.
 */
public class iscollected001a {

    // name for the tested thread
    public static final String OBJECT_FIELD_NAME = "object";

    public static void main(String args[]) {
        iscollected001a _iscollected001a = new iscollected001a();
        System.exit(iscollected001.JCK_STATUS_BASE + _iscollected001a.runIt(args, System.err));
    }

    public int runIt(String args[], PrintStream out) {
        //make log for debugee messages
        ArgumentHandler argumentHandler = new ArgumentHandler(args);
        Log log = new Log(out, argumentHandler);

        // make communication pipe to debugger
        log.display("Creating pipe");
        IOPipe pipe = argumentHandler.createDebugeeIOPipe(log);

        // load tested class and create tested thread
        log.display("Creating object of tested class");
        TestedClass.object = new TestedClass();

        // send debugger signal READY
        log.display("Sending signal to debugger: " + iscollected001.READY);
        pipe.println(iscollected001.READY);

        // wait for signal QUIT from debugeer
        log.display("Waiting for signal from debugger: " + iscollected001.QUIT);
        String signal = pipe.readln();
        log.display("Received signal from debugger: " + signal);

        // check received signal
        if (signal == null || !signal.equals(iscollected001.QUIT)) {
            log.complain("Unexpected communication signal from debugee: " + signal
                        + " (expected: " + iscollected001.QUIT + ")");
            log.display("Debugee FAILED");
            return iscollected001.FAILED;
        }

        // exit debugee
        log.display("Debugee PASSED");
        return iscollected001.PASSED;
    }

    // tested class
    public static class TestedClass {

        // static field with the tested object value
        public static volatile TestedClass object = null;

        private int foo = 0;

        public TestedClass() {
            foo = 100;
        }
    }

}
