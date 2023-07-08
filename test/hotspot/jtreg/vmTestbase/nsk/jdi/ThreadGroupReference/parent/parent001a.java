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

package nsk.jdi.ThreadGroupReference.parent;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














/**
 * This class is used as debuggee application for the parent001 JDI test.
 */

public class parent001a {

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
    public static ThreadGroup mainthreadGroupObj = new ThreadGroup(mainthreadGroupName);

    public static ThreadGroup mainParent = null;

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
                         mainParent = mainthreadGroupObj.getParent();

                         Threadparent001a test_thread =
                             new Threadparent001a("testedThread");
                         log1("       thread2 is created");

                         label:
                         synchronized (Threadparent001a.lockingObject) {
                             synchronized (Threadparent001a.waitnotifyObj) {
                                 log1("       synchronized (waitnotifyObj) { enter");
                                 log1("       before: test_thread.start()");
                                 test_thread.start();

                                 try {
                                     log1("       before:   waitnotifyObj.wait();");
                                     Threadparent001a.waitnotifyObj.wait();
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


class Threadparent001a extends Thread {

    public Threadparent001a(String threadName) {
        super(threadName);
    }

    public static Object waitnotifyObj = new Object();
    public static Object lockingObject = new Object();

    private static ThreadGroup thread2GroupObj1 = null;
    private static ThreadGroup thread2GroupObj2 = null;

    private int i1 = 0, i2 = 10;

    public void run() {
        log("method 'run' enter");

        thread2GroupObj1 = new ThreadGroup(parent001a.mainthreadGroupObj, group2Name);
        thread2GroupObj2 = new ThreadGroup(thread2GroupObj1, group3Name);

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

    public static String obj0Name = "mainParent";
    public static String obj1Name = "mainthreadGroupObj";
    public static String obj2Name = "thread2GroupObj1";
    public static String obj3Name = "thread2GroupObj2";

    public static String group1Name = parent001a.mainthreadGroupName;
    public static String group2Name = "thread2Group1";
    public static String group3Name = "thread2Group2";

    void log(String str) {
        parent001a.log2("thread2: " + str);
    }

}
