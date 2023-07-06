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

package nsk.jdi.ThreadReference.threadGroup;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * This class is used as debuggee application for the threadgroup001 JDI test.
 */

public class threadgroup001a {

    //----------------------------------------------------- templete section

    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int PASS_BASE = 95;

    //--------------------------------------------------   log procedures

    static boolean verbMode = false;

    public static void log1(String message) {
        if (verbMode)
            System.err.println("**> mainThread: " + message);
    }
    public static void log2(String message) {
        if (verbMode)
            System.err.println("**> " + message);
    }

    private static void logErr(String message) {
        if (verbMode)
            System.err.println("!!**> mainThread: " + message);
    }

    //====================================================== test program

    public static final String mainthreadGroupName = "mainthreadGroup";
    public static final String threadGroup2Name    = "threadGroup2";
    public static final String threadGroup3Name    = "threadGroup3";
    public static final String threadGroup4Name    = "threadGroup4";

    public static ThreadGroup threadGroup1Obj    = new ThreadGroup(mainthreadGroupName);
    public static ThreadGroup threadGroup2Obj    = new ThreadGroup(threadGroup1Obj, threadGroup2Name);
    public static ThreadGroup threadGroup3Obj    = new ThreadGroup(threadGroup1Obj, threadGroup3Name);
    public static ThreadGroup threadGroup4Obj    = new ThreadGroup(threadGroup3Obj, threadGroup4Name);

    private static Threadthreadgroup001a thread2 = null;
    private static Threadthreadgroup001a thread3 = null;
    private static Threadthreadgroup001a thread4 = null;

    //----------------------------------------------------   main method

    public static void main (String argv[]) {

        for (int i=0; i<argv.length; i++) {
            if ( argv[i].equals("-vbs") || argv[i].equals("-verbose") ) {
                verbMode = true;
                break;
            }
        }
        log1("debuggee started!");

        // informing a debugger of readyness
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        IOPipe pipe = argHandler.createDebugeeIOPipe();
        pipe.println("ready");

        int exitCode = PASSED;
        for (int i = 0; ; i++) {

            String instruction;

            log1("waiting for an instruction from the debugger ...");
            instruction = pipe.readln();
            if (instruction.equals("quit")) {
                log1("'quit' recieved");
                break ;

            } else if (instruction.equals("newcheck")) {
                switch (i) {

    //------------------------------------------------------  section tested

                case 0:
                         thread2 = new
                              Threadthreadgroup001a(threadGroup2Obj, "Thread2");
                         log1("       thread2 is created");
                         thread3 = new
                              Threadthreadgroup001a(threadGroup3Obj, "Thread3");
                         log1("       thread3 is created");
                         thread4 = new
                              Threadthreadgroup001a(threadGroup4Obj, "Thread4");
                         log1("       thread4 is created");

                         label:
                         synchronized (Threadthreadgroup001a.lockingObject) {
                             synchronized (Threadthreadgroup001a.waitnotifyObj) {
                                 log1("       synchronized (waitnotifyObj) { enter");
                                 log1("       before: thread2.start()");
                                 thread2.start();

                                 try {
                                     log1("       before:   waitnotifyObj.wait();");
                                     Threadthreadgroup001a.waitnotifyObj.wait();
                                     log1("       after:    waitnotifyObj.wait();");

                                     log1("       before: thread3.start()");
                                     thread3.start();

                                     log1("       before:   waitnotifyObj.wait();");
                                     Threadthreadgroup001a.waitnotifyObj.wait();
                                     log1("       after:    waitnotifyObj.wait();");

                                     log1("       before: thread4.start()");
                                     thread4.start();

                                     log1("       before:   waitnotifyObj.wait();");
                                     Threadthreadgroup001a.waitnotifyObj.wait();
                                     log1("       after:    waitnotifyObj.wait();");

                                     pipe.println("checkready");
                                     instruction = pipe.readln();
                                     if (!instruction.equals("continue")) {
                                         logErr("ERROR: unexpected instruction: " + instruction);
                                         exitCode = FAILED;
                                         break label;
                                     }
                                     pipe.println("docontinue");
                                 } catch ( Exception e2) {
                                     log1("       Exception e2 exception: " + e2 );
                                     pipe.println("waitnotifyerr");
                                 }
                             }
                         }
                         log1("mainThread is out of: synchronized (lockingObject) {");

                         break ;

    //-------------------------------------------------    standard end section

                default:
                                pipe.println("checkend");
                                break ;
                }

            } else {
                logErr("ERRROR: unexpected instruction: " + instruction);
                exitCode = FAILED;
                break ;
            }
        }

        System.exit(exitCode + PASS_BASE);
    }
}

class Threadthreadgroup001a extends Thread {

    public Threadthreadgroup001a(String threadName) {
        super(threadName);
    }
    public Threadthreadgroup001a(ThreadGroup groupName, String threadName) {
        super(groupName, threadName);
    }

    public static Object waitnotifyObj = new Object();
    public static Object lockingObject = new Object();

    private int i1 = 0, i2 = 10;

    public void run() {
        log("method 'run' enter");

        synchronized (waitnotifyObj)                                    {
            log("entered into block:  synchronized (waitnotifyObj)");
            waitnotifyObj.notify();                                     }
        log("exited from block:  synchronized (waitnotifyObj)");
        synchronized (lockingObject)                                    {
            log("entered into block:  synchronized (lockingObject)");   }
        log("exited from block:  synchronized (lockingObject)");
        i1++;
        i2--;

        log("method 'run' exit");
        return;
    }

    void log(String str) {
        threadgroup001a.log2("thread2/4: " + str);
    }

}