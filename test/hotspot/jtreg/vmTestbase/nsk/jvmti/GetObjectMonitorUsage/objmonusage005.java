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

package nsk.jvmti.GetObjectMonitorUsage;


import nsk.share.Wicket;
import java.io.PrintStream;














public class objmonusage005 {

    final static int JCK_STATUS_BASE = 95;
    final static int WAIT_TIME = 100;

    static {
        try {
            System.loadLibrary("objmonusage005");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load objmonusage005 library");
            System.err.println("java.library.path:"
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    public static Wicket startingBarrier;
    static Object lockCheck = new Object();

    native static void check(Object obj);
    native static int getRes();

    public static void main(String args[]) {
        args = nsk.share.jvmti.JVMTITest.commonInit(args);

        // produce JCK-like exit status.
        System.exit(run(args, System.out) + JCK_STATUS_BASE);
    }

    public static int run(String args[], PrintStream out) {
        LockingThread thr = new LockingThread();
        startingBarrier = new Wicket();
        thr.start();
        startingBarrier.waitFor();
        check(lockCheck);
        thr.letItGo();
        return getRes();
    }

    static class LockingThread extends Thread {
        private volatile boolean flag = true;

        public synchronized void run() {
            synchronized (lockCheck) {
                startingBarrier.unlock();
                int i = 0;
                int n = 1000;
                while (flag) {
                    if (n <= 0) {
                        n = 1000;
                    }
                    if (i > n) {
                        i = 0;
                        n--;
                    }
                    i++;
                }
            }
        }

        public void letItGo() {
            flag = false;
        }
    }
}
