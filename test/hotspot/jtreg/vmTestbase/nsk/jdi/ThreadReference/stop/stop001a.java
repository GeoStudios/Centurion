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

package nsk.jdi.ThreadReference.stop;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














/**
 * This class is used as debuggee application for the stop001 JDI test.
 *
 * corrected 5/13/2001
 */

public class stop001a {

    //----------------------------------------------------- templete section

    static final int PASSED = 0;
//    static final int FAILED = 2;   // see int FAILED below
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

    public static void logErr(String message) {
        if (verbMode)
            System.err.println("!!**> mainThread: " + message);
    }

    //====================================================== test program

    private static Threadstop001a thread2 = null;

    public static String strException = "testException";

    private static Throwable throwableObj = new Exception(strException);
    public  static Throwable tObj         = null;

    public static       int exitCode = PASSED;
    public static final int FAILED   = 2;

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


//        int exitCode = PASSED;
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
                              Threadstop001a("Thread2");
                         log1("       thread2 is created");


                     label: {

                         synchronized (Threadstop001a.lockingObject) {
                             synchronized (Threadstop001a.waitnotifyObj) {
                                 log1("       synchronized (waitnotifyObj) { enter");
                                 log1("       before: thread2.start()");
                                 thread2.start();

                                 try {
                                     log1("       before:   waitnotifyObj.wait();");
                                     Threadstop001a.waitnotifyObj.wait();
                                     log1("       after:    waitnotifyObj.wait();");

                                     pipe.println("checkready");
                                     instruction = pipe.readln();
                                     if (!instruction.equals("continue")) {
                                         logErr("ERROR: unexpected instruction: " + instruction);
                                         exitCode = FAILED;
                                         break label;
                                     }
//                                   pipe.println("docontinue");
                                 } catch ( Exception e2) {
                                     log1("       Exception e2 exception: " + e2 );
                                     pipe.println("waitnotifyerr");
                                 }
                             }
                         }
                         synchronized (Threadstop001a.lockingObject2) {
                             String str = "NOT_equal";

                             if (tObj == null) {
                                 log1("tObj == null");
                                 str = "null";
                             } else if (tObj.getMessage().equals(strException)){
                                 log1("tObj.getMessage().equals(strException)");
                                 str = "equal";
                             }

                             log1("tObj is " + str + " : " + (tObj == null? "" : tObj.getMessage()));
                             pipe.println(str);
                         }

                     }  // closing to 'label:'

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

class Threadstop001a extends Thread {

    public Threadstop001a(String threadName) {
        super(threadName);
    }
    public Threadstop001a(ThreadGroup groupName, String threadName) {
        super(groupName, threadName);
    }

    public static Object waitnotifyObj = new Object();
    public static Object lockingObject = new Object();

    public static Object lockingObject2 = new Object();

    private int i1 = 0, i2 = 10;

    public void run() {
        log("method 'run' enter");

        synchronized (lockingObject2) {
            try {
                synchronized (waitnotifyObj) {
                    log("entered into block:  synchronized (waitnotifyObj)");
                    waitnotifyObj.notify();
                }
                log("exited from block:  synchronized (waitnotifyObj)");
                synchronized (lockingObject) {
                    log("entered into block:  synchronized (lockingObject)");
                }
                logerr("ERROR:  normal exit from block:  synchronized (lockingObject)");
                stop001a.exitCode = stop001a.FAILED;
            } catch ( Exception e1 ) {
                log("Exception: " + e1.getMessage());
                stop001a.tObj = e1;
            }
        }

        log("method 'run' exit");
        return;
    }


    void log(String str) {
        stop001a.log2("thread2: " + str);
    }
    void logerr(String str) {
        stop001a.logErr("thread2: " + str);
    }
}
