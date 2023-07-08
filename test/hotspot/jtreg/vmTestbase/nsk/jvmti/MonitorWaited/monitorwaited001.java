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

package nsk.jvmti.MonitorWaited;


import java.io.PrintStream;
import nsk.share.*;
import nsk.share.jvmti.*;














public class monitorwaited001 extends DebugeeClass {

    // load native library if required
    static {
        loadLibrary("monitorwaited001");
    }

    // run test from command line
    public static void main(String argv[]) {
        argv = nsk.share.jvmti.JVMTITest.commonInit(argv);

        // JCK-compatible exit
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    // run test from JCK-compatible environment
    public static int run(String argv[], PrintStream out) {
        return new monitorwaited001().runIt(argv, out);
    }

    /* =================================================================== */

    // scaffold objects
    ArgumentHandler argHandler = null;
    Log log = null;
    int status = Consts.TEST_PASSED;
    static long timeout = 0;

    // tested thread
    monitorwaited001Thread thread = null;

    // run debuggee
    public int runIt(String argv[], PrintStream out) {
        argHandler = new ArgumentHandler(argv);
        log = new Log(out, argHandler);
        timeout = argHandler.getWaitTime() * 60000; // milliseconds
        log.display("Timeout = " + timeout + " msc.");

        thread = new monitorwaited001Thread("Debuggee Thread");

        // run thread
        try {
            // start thread
            synchronized (thread.startingMonitor) {
                thread.start();
                thread.startingMonitor.wait(timeout);
            }
        } catch (InterruptedException e) {
            throw new Failure(e);
        }

        Thread.yield();
        log.display("Thread started");

        synchronized (thread.waitingMonitor) {
            thread.waitingMonitor.notify();
        }

        // wait for thread finish
        try {
            thread.join(timeout);
        } catch (InterruptedException e) {
            throw new Failure(e);
        }

        log.display("Sync: thread finished");
        status = checkStatus(status);

        return status;
    }
}

/* =================================================================== */

class monitorwaited001Thread extends Thread {
    public Object startingMonitor = new Object();
    public Object waitingMonitor = new Object();

    public monitorwaited001Thread(String name) {
        super(name);
    }

    public void run() {
        synchronized (waitingMonitor) {

            monitorwaited001.checkStatus(Consts.TEST_PASSED);

            // notify about starting
            synchronized (startingMonitor) {
                startingMonitor.notify();
            }

            // wait until main thread notify
            try {
                waitingMonitor.wait(monitorwaited001.timeout);
            } catch (InterruptedException e) {
                throw new Failure(e);
            }
        }
    }
}
