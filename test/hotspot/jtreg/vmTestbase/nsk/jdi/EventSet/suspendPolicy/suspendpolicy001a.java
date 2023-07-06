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

package nsk.jdi.EventSet.suspendPolicy;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * This class is used as debuggee application for the suspendpolicy001 JDI test.
 */

public class suspendpolicy001a {

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

        int exitCode = PASSED;

        label0:
            for (int i = 0; ; i++) {

                if (instruction > maxInstr) {
                    logErr("ERROR: unexpected instruction: " + instruction);
                    exitCode = FAILED;
                    break ;
                }

                switch (i) {

    //------------------------------------------------------  section tested

// Below, there is no the loop of interaction with the debugger for each Event
// because JVMS doesn't guaranties ordering ClassPrepareEvents.
// Therefore the debugger doesn't rely on the event order.

                    case 0:
                            TestClass0 tcObject0 = new TestClass0();
//                          methodForCommunication();
//                          break;

                    case 1:
                            TestClass1 tcObject1 = new TestClass1();
//                          methodForCommunication();
//                          break;
                    case 2:
                            TestClass2 tcObject2 = new TestClass2();
//                          methodForCommunication();
//                          break;

                    case 3:
                            TestClass3 tcObject3 = new TestClass3();
//                          methodForCommunication();
//                          break;

                    case 4:
                            TestClass4 tcObject4 = new TestClass4();
//                          methodForCommunication();
//                          break;

                    case 5:
                            TestClass5 tcObject5 = new TestClass5();
//                          methodForCommunication();
//                          break;

                    case 6:
                            TestClass6 tcObject6 = new TestClass6();

    //-------------------------------------------------    standard end section

                    default:
                                instruction = end;
                                methodForCommunication();
                                break label0;
                }
            }

        System.exit(exitCode + PASS_BASE);
    }

}
class TestClass0 {
    static int var1 = 0;
}
class TestClass1 {
    static int var1 = 0;
}
class TestClass2 {
    static int var1 = 0;
}
class TestClass3 {
    static int var1 = 0;
}
class TestClass4 {
    static int var1 = 0;
}
class TestClass5 {
    static int var1 = 0;
}
class TestClass6 {
    static int var1 = 0;
}