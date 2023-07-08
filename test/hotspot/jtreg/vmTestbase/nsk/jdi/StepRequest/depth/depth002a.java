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

package nsk.jdi.StepRequest.depth;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














/**
 * This class is used as debuggee application for the depth002 JDI test.
 */

public class depth002a {

    //----------------------------------------------------- templete section

    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int PASS_BASE = 95;

    static ArgumentHandler argHandler;
    static Log log;

    //--------------------------------------------------   log procedures

    public static void log1(String message) {
        log.display("**> debuggee: " + message);
    }

    private static void logErr(String message) {
        log.complain("**> debuggee: " + message);
    }

    //====================================================== test program

    static Thread1depth002a thread1 = null;

    static depth002aTestClass10 obj = new depth002aTestClass10();

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
                            thread1 = new Thread1depth002a("thread1");

                            synchronized (lockObj) {
                                threadStart(thread1);
                                log1("methodForCommunication();----");
                                methodForCommunication();
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
}

class depth002aTestClass10{
    static void m10() {
        depth002a.log1("entered: m10");
    }
}
class depth002aTestClass11 extends depth002aTestClass10{
    static void m11() {
        depth002a.log1("entered: m11");
        depth002aTestClass10.m10();
    }
}

class Thread1depth002a extends Thread {

    String tName = null;

    public Thread1depth002a(String threadName) {
        super(threadName);
        tName = threadName;
    }

    public void run() {
        depth002a.log1("  'run': enter  :: threadName == " + tName);
        synchronized(depth002a.waitnotifyObj) {
            depth002a.waitnotifyObj.notify();
        }
        synchronized(depth002a.lockObj) {
            depth002aTestClass11.m11();
        }
        depth002a.log1("  'run': exit   :: threadName == " + tName);
        return;
    }
}
