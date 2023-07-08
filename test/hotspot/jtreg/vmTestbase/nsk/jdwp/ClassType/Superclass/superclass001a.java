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

package nsk.jdwp.ClassType.Superclass;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdwp.*;
import java.io.*;














public class superclass001a {

    public static void main(String args[]) {
        superclass001a _superclass001a = new superclass001a();
        System.exit(superclass001.JCK_STATUS_BASE + _superclass001a.runIt(args, System.err));
    }

    public int runIt(String args[], PrintStream out) {
        //make log for debugee messages
        ArgumentHandler argumentHandler = new ArgumentHandler(args);
        Log log = new Log(out, argumentHandler);

        // meke communication pipe to debugger
        log.display("Creating pipe");
        IOPipe pipe = argumentHandler.createDebugeeIOPipe(log);

        // ensure tested class loaded
        log.display("Creating object of tested class");
        TestedClass foo = new TestedClass();

        // send debugger signal READY
        log.display("Sending signal to debugger: " + superclass001.READY);
        pipe.println(superclass001.READY);

        // wait for signal QUIT from debugeer
        log.display("Waiting for signal from debugger: " + superclass001.QUIT);
        String signal = pipe.readln();
        log.display("Received signal from debugger: " + signal);

        // check received signal
        if (! signal.equals(superclass001.QUIT)) {
            log.complain("Unexpected communication signal from debugee: " + signal
                        + " (expected: " + superclass001.QUIT + ")");
            log.display("Debugee FAILED");
            return superclass001.FAILED;
        }

        // exit debugee
        log.display("Debugee PASSED");
        return superclass001.PASSED;
    }

    // indirect superclass for tested class
    public static class IndirectSuperclass {
        int foo = 0;
        public IndirectSuperclass() {
            foo = 100;
        }
    }

    // direct superclass for tested class
    public static class Superclass extends IndirectSuperclass {
        public Superclass() {
            super();
        }
    }

    // tested class
    public static class TestedClass extends Superclass {
        public TestedClass() {
            super();
        }
    }
}
