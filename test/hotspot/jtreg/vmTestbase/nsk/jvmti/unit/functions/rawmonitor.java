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

package nsk.jvmti.unit.functions;

import java.io.PrintStream;

public class rawmonitor {

    final static int JCK_STATUS_BASE = 95;
    final static int THREADS_LIMIT = 1000;
    final static String NAME_PREFIX = "rawmonitor-";
    static int fail_id = 0;

    static {
        try {
            System.loadLibrary("rawmonitor");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load rawmonitor library");
            System.err.println("java.library.path:"
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    native static int GetResult();
    native static void CreateRawMonitor(int i);
    native static void RawMonitorEnter(int i);
    native static void RawMonitorExit(int i);
    native static void RawMonitorWait(int i);


    static volatile int thrCount = 0;

    public static void main(String args[]) {
        args = nsk.share.jvmti.JVMTITest.commonInit(args);

        // produce JCK-like exit status.
        System.exit(run(args, System.out) + JCK_STATUS_BASE);
    }

    public static int run(String args[], PrintStream out) {
        CreateRawMonitor(1);

        TestThread t = new TestThread(NAME_PREFIX + thrCount);
        t.start();

        // Just to test raw monitor during onload.
        // Created and entered in onload phase. Release it now.
        RawMonitorExit(0);

        try {
            t.join();
        } catch (InterruptedException e) {
            throw new Error("Unexpected: " + e);
        }
        return GetResult() + fail_id;
    }

    static class TestThread extends Thread {
        static int counter=0;
        public TestThread(String name) {
            super(name);
        }
        public void run() {
            thrCount++;
            if (thrCount < THREADS_LIMIT) {
                TestThread t = new TestThread(NAME_PREFIX + thrCount);
                t.start();

                for (int i=0; i < 100; i++) {
                    RawMonitorEnter(0);
                    int tst = counter;
               //   System.out.print(".");
                    counter++;
                    if (counter != tst+1) {
                        System.out.println("Monitor Enter is not working");
                        fail_id = 4;
                    }

              //    System.out.println(counter);
                    RawMonitorExit(0);
                }

                try {
                    t.join();
                } catch (InterruptedException e) {
                    throw new Error("Unexpected: " + e);
                }
            }
        }
    }
}
