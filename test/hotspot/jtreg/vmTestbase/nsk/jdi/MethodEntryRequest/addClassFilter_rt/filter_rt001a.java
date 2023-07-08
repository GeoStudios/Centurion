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

package nsk.jdi.MethodEntryRequest.addClassFilter_rt;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














/**
 * This class is used as debuggee application for the filter_rt001 JDI test.
 */

public class filter_rt001a {

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

    static filter_rt001aThread1 thread1 = null;

    static filter_rt001aTestClass10 obj10 = new filter_rt001aTestClass10();
    static filter_rt001aTestClass11 obj11 = new filter_rt001aTestClass11();
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


            log1("methodForCommunication();");
            methodForCommunication();
            if (instruction == end)
                break;

            if (instruction > maxInstr) {
                logErr("ERROR: unexpected instruction: " + instruction);
                exitCode = FAILED;
                break ;
            }

            switch (i) {

//------------------------------------------------------  section tested

                case 0:
                thread1 = new filter_rt001aThread1("thread1");

                log1("new filter_rt001a().run1(thread1);");
                new filter_rt001a().run1(thread1);

//-------------------------------------------------    standard end section

                default:
                instruction = end;
                break;
            }
        }

        log1("debuggee exits");
        System.exit(exitCode + PASS_BASE);
    }

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

    public void run1(Thread t) {
        t.start();
        try {
            t.join();
        } catch ( InterruptedException e ) {
        }
    }
}


class filter_rt001aTestClass10{
    static void m10() {
        filter_rt001a.log1("entered: m10()");
    }
}
class filter_rt001aTestClass11 extends filter_rt001aTestClass10{
    static void m11() {
        filter_rt001a.log1("entered: m11()");
        filter_rt001aTestClass10.m10();
    }
}

class filter_rt001aThread1 extends Thread {

    String tName = null;

    public filter_rt001aThread1(String threadName) {
        super(threadName);
        tName = threadName;
    }

    public void run() {
        filter_rt001a.log1("  'run': enter  :: threadName == " + tName);
        filter_rt001aTestClass21.m21();
        filter_rt001aTestClass11.m11();
        filter_rt001a.log1("  'run': exit   :: threadName == " + tName);
        return;
    }
}

class filter_rt001aTestClass20{
    static void m20() {
        filter_rt001a.log1("entered: m20()");
    }
}

class filter_rt001aTestClass21 extends filter_rt001aTestClass20{
    static void m21() {
        filter_rt001a.log1("entered: m21()");
        filter_rt001aTestClass20.m20();
    }
}
