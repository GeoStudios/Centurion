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

package nsk.jdi.BreakpointRequest.addThreadFilter;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * This class is used as debuggee application for the threadfilter003 JDI test.
 */

public class threadfilter003a {

    //----------------------------------------------------- templete section

    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int PASS_BASE = 95;

    static ArgumentHandler argHandler;
    static Log log;

    //--------------------------------------------------   log procedures

    private static void log1(String message) {
        log.display("**> debuggee: " + message);
    }

    private static void logErr(String message) {
        log.complain("**> debuggee: " + message);
    }

    //====================================================== test program

    static Threadthreadfilter003a thread1 = null;

    static threadfilter003aTestClass objTC = new threadfilter003aTestClass();

    //------------------------------------------------------ common section

    static int exitCode = PASSED;

    static int instruction = 1;
    static int end         = 0;
                                   //    static int quit        = 0;
                                   //    static int continue    = 2;
    static int maxInstr    = 1;    // 2;

    static int lineForComm = 2;

    private static void methodForCommunication() {
        int i1 = instruction;
        int i2 = i1;
        int i3 = i2;
    }
    //----------------------------------------------------   main method

    public static void main (String argv[]) {

        argHandler = new ArgumentHandler(argv);
        log = argHandler.createDebugeeLog();

        log1("debuggee started!");

        label0:
            for (int i = 0; ; i++) {

                if (instruction > maxInstr) {
                    logErr("ERROR: unexpected instruction: " + instruction);
                    exitCode = FAILED;
                    break ;
                }

                switch (i) {

    //------------------------------------------------------  section tested

                    case 0:
                            thread1 = new Threadthreadfilter003a("thread1");
                            break;

                    case 1:
                            synchronized (lockObj) {
                                threadStart(thread1);
                                log1("methodForCommunication();");
                                methodForCommunication();
                            }
                            break;

                    case 2:
                            try {
                                thread1.join();
                            } catch ( InterruptedException e) {
                            }

    //-------------------------------------------------    standard end section

                    default:
                            instruction = end;
                            break;
                }
                log1("methodForCommunication();");
                methodForCommunication();
                if (instruction == end)
                    break;
            }

        log1("debuggee exits");
        System.exit(exitCode + PASS_BASE);
    }

    static Object lockObj       = new Object();
    static Object waitnotifyObj = new Object();

    static int threadStart(Thread t) {
        synchronized (waitnotifyObj) {
            t.start();
            try {
                waitnotifyObj.wait();
            } catch ( Exception e) {
                exitCode = FAILED;
                logErr("       Exception : " + e );
                return FAILED;
            }
        }
        return PASSED;
    }

    static class Threadthreadfilter003a extends Thread {

        public Threadthreadfilter003a(String threadName) {
            super(threadName);
        }

        public void run() {
            log1("  'run': enter ");

                synchronized (waitnotifyObj) {
                    waitnotifyObj.notify();
                }
            synchronized (lockObj) {
                log1("  'run': exit ");
            }
            return;
        }
    }

}

class threadfilter003aTestClass {

    static int breakpointLine = 3;
    static String awFieldName = "var1";
    static String mwFieldName = "var2";

    static int var1 = 0;
    static int var2 = 0;
    static int var3 = 0;

    static void method () {
        var1 = 1;
        var3 = var1;
        var2 = var3;
    }
}
