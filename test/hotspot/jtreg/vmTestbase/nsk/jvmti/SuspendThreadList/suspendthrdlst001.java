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

package nsk.jvmti.SuspendThreadList;


import java.io.PrintStream;
import nsk.share.*;
import nsk.share.jvmti.*;














public class suspendthrdlst001 extends DebugeeClass {

    // load native library if required
    static {
        System.loadLibrary("suspendthrdlst001");
    }

    // run test from command line
    public static void main(String argv[]) {
        argv = nsk.share.jvmti.JVMTITest.commonInit(argv);

        // JCK-compatible exit
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    // run test from JCK-compatible environment
    public static int run(String argv[], PrintStream out) {
        return new suspendthrdlst001().runIt(argv, out);
    }

    /* =================================================================== */

    // scaffold objects
    ArgumentHandler argHandler = null;
    Log log = null;
    long timeout = 0;
    int status = Consts.TEST_PASSED;

    // constants
    public static final int DEFAULT_THREADS_COUNT = 10;

    // tested thread
    suspendthrdlst001Thread threads[] = null;
    int threadsCount = 0;

    // run debuggee
    public int runIt(String argv[], PrintStream out) {
        argHandler = new ArgumentHandler(argv);
        log = new Log(out, argHandler);
        timeout = argHandler.getWaitTime() * 60 * 1000; // milliseconds

        threadsCount = argHandler.findOptionIntValue("threads", DEFAULT_THREADS_COUNT);

        // create tested threads
        threads = new suspendthrdlst001Thread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new suspendthrdlst001Thread("TestedThread #" + i);
        }

        // run tested threads
        log.display("Staring tested threads");
        try {
            for (int i = 0; i < threadsCount; i++) {
                threads[i].start();
                if (!threads[i].checkReady()) {
                    throw new Failure("Unable to prepare tested thread: " + threads[i]);
                }
            }

            // testing sync
            log.display("Sync: thread started");
            status = checkStatus(status);
        } finally {
            // let threads to finish
            for (int i = 0; i < threadsCount; i++) {
                threads[i].letFinish();
            }
        }

        // wait for thread to finish
        log.display("Finishing tested threads");
        try {
            for (int i = 0; i < threadsCount; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            throw new Failure(e);
        }

        // testing sync
        log.display("Sync: thread finished");
        status = checkStatus(status);

        return status;
    }
}

/* =================================================================== */

// basic class for tested threads
class suspendthrdlst001Thread extends Thread {
    private volatile boolean threadReady = false;
    private volatile boolean shouldFinish = false;

    // make thread with specific name
    public suspendthrdlst001Thread(String name) {
        super(name);
    }

    // run thread continuously
    public void run() {
        // run in a loop
        threadReady = true;
        int i = 0;
        int n = 1000;
        while (!shouldFinish) {
            if (n <= 0) {
                n = 1000;
            }
            if (i > n) {
                i = 0;
                n = n - 1;
            }
            i = i + 1;
        }
    }

    // check if thread is ready
    public boolean checkReady() {
        try {
            while (!threadReady) {
                sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new Failure("Interruption while preparing tested thread: \n\t" + e);
        }
        return threadReady;
    }

    // let thread to finish
    public void letFinish() {
        shouldFinish = true;
    }
}
