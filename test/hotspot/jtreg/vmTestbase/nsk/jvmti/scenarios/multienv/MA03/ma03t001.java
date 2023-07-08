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

package nsk.jvmti.scenarios.multienv.MA03;

import java.io.PrintStream;
import nsk.share.*;
import nsk.share.jvmti.*;

public class ma03t001 extends DebugeeClass {

    // run test from command line
    public static void main(String argv[]) {
        argv = nsk.share.jvmti.JVMTITest.commonInit(argv);

        // JCK-compatible exit
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    // run test from JCK-compatible environment
    public static int run(String argv[], PrintStream out) {
        return new ma03t001().runIt(argv, out);
    }

    /* =================================================================== */

    // scaffold objects
    ArgumentHandler argHandler = null;
    Log log = null;
    long timeout = 0;
    int status = Consts.TEST_PASSED;

    // tested thread
    ma03t001Thread thread = null;

    // run debuggee
    public int runIt(String argv[], PrintStream out) {
        argHandler = new ArgumentHandler(argv);
        log = new Log(out, argHandler);
        timeout = argHandler.getWaitTime() * 60 * 1000;

        thread = new ma03t001Thread("Debuggee Thread");
        thread.start();
        thread.startingBarrier.waitFor();
        thread.endingBarrier.unlock();

        // wait for thread finish
        try {
            thread.join(timeout);
        } catch (InterruptedException e) {
            throw new Failure(e);
        }

        log.display("Debugee finished");
        return checkStatus(status);
    }
}

/* =================================================================== */

class ma03t001Thread extends Thread {
    public Wicket startingBarrier = new Wicket();
    public Wicket endingBarrier = new Wicket();

    public ma03t001Thread(String name) {
        super(name);
    }

    public void run() {

        // notify about starting
        startingBarrier.unlock();

        // wait until main thread release monitor
        endingBarrier.waitFor();
    }
}
