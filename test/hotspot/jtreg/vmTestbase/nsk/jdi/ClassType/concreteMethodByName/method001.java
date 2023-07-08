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

package nsk.jdi.ClassType.concreteMethodByName;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import java.util.*;
import java.io.*;














/**
 * The test for the implementation of an object of the type     <BR>
 * ClassType.                                                   <BR>
 *                                                              <BR>
 * The test checks up that results of the method                <BR>
 * <code>com.sun.jdi.ClassType.concreteMethodByName()</code>    <BR>
 * complies with its spec.                                      <BR>
 * <BR>
 * The cases for testing are as follows.        <BR>
 *                                              <BR>
 * 1)    8 methods with return types of         <BR>
 *       all primitive types (cases 0-7 below)  <BR>
 * 2)    1 method with the void type (case 8)   <BR>
 * 3)    3 methods with return types of         <BR>
 *       all reference types (cases 9-11)       <BR>
 * <BR>
 */

public class method001 {

    //----------------------------------------------------- templete section
    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int PASS_BASE = 95;

    //----------------------------------------------------- templete parameters
    static final String
    sHeader1 = "\n==> nsk/jdi/ClassType/concreteMethodByName/method001",
    sHeader2 = "--> method001: ",
    sHeader3 = "##> method001: ";

    //----------------------------------------------------- main method

    public static void main (String argv[]) {
        int result = run(argv, System.out);
        System.exit(result + PASS_BASE);
    }

    public static int run (String argv[], PrintStream out) {
        return new method001().runThis(argv, out);
    }

     //--------------------------------------------------   log procedures

    private static boolean verbMode = false;

    private static Log  logHandler;

    private static void log1(String message) {
        logHandler.display(sHeader1 + message);
    }
    private static void log2(String message) {
        logHandler.display(sHeader2 + message);
    }
    private static void log3(String message) {
        logHandler.complain(sHeader3 + message);
    }

    //  ************************************************    test parameters

    private String debuggeeName =
        "nsk.jdi.ClassType.concreteMethodByName.method001a";

    String mName = "nsk.jdi.ClassType.concreteMethodByName";

    //====================================================== test program

    static ArgumentHandler      argsHandler;
    static int                  testExitCode = PASSED;

    //------------------------------------------------------ common section

    private int runThis (String argv[], PrintStream out) {

        Debugee debuggee;

        argsHandler     = new ArgumentHandler(argv);
        logHandler      = new Log(out, argsHandler);
        Binder binder   = new Binder(argsHandler, logHandler);

        if (argsHandler.verbose()) {
            debuggee = binder.bindToDebugee(debuggeeName + " -vbs");  // *** tp
        } else {
            debuggee = binder.bindToDebugee(debuggeeName);            // *** tp
        }

        IOPipe pipe     = new IOPipe(debuggee);

        debuggee.redirectStderr(out);
        log2("method001a debuggee launched");
        debuggee.resume();

        String line = pipe.readln();
        if ((line == null) || !line.equals("ready")) {
            log3("signal received is not 'ready' but: " + line);
            return FAILED;
        } else {
            log2("'ready' recieved");
        }

        VirtualMachine vm = debuggee.VM();

    //------------------------------------------------------  testing section
        log1("      TESTING BEGINS");

        for (int i = 0; ; i++) {
        pipe.println("newcheck");
            line = pipe.readln();

            if (line.equals("checkend")) {
                log2("     : returned string is 'checkend'");
                break ;
            } else if (!line.equals("checkready")) {
                log3("ERROR: returned string is not 'checkready'");
                testExitCode = FAILED;
                break ;
            }

            log1("new check: #" + i);

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ variable part

            ReferenceType classRefType = null;

            Method        m            = null;

            int i2;

            for (i2 = 0; ; i2++) {

                int expresult = 0;

                log2("new check: #" + i2);

                switch (i2) {

                case 0:         // Class1 without abstract methods

                        List list1 = vm.classesByName(mName + ".Class1ForCheck");

                        ClassType classType1 =
                                     (ClassType) (ReferenceType) list1.get(0);
                        if (classType1 == null) {
                            log3("ERROR : classType1 == null in case: Class1");
                            expresult = 1;
                            break;
                        }

                        int i3;
                        boolean endFlag = false;

                        for (i3 = 0; ; i3++) {
                            switch (i3) {

                            case 0 :  m = classType1.concreteMethodByName
                                          ("blValue", "()Z");
                                      break ;

                            case 1 :  m = classType1.concreteMethodByName
                                          ("btValue", "()B");
                                      break ;

                            case 2 :  m = classType1.concreteMethodByName
                                          ("chValue", "()C");
                                      break ;

                            case 3 :  m = classType1.concreteMethodByName
                                          ("dbValue", "()D");
                                      break ;

                            case 4 :  m = classType1.concreteMethodByName
                                          ("flValue", "()F");
                                      break ;

                            case 5 :  m = classType1.concreteMethodByName
                                          ("inValue", "()I");
                                      break ;

                            case 6 :  m = classType1.concreteMethodByName
                                          ("lnValue", "()J");
                                      break ;

                            case 7 :  m = classType1.concreteMethodByName
                                          ("shValue", "()S");
                                      break ;


                            case 8 :  m = classType1.concreteMethodByName
                                          ("vdValue", "()V");
                                      break ;


                            case 9 :  m = classType1.concreteMethodByName
                                          ("inArray", "()[I");
                                      break ;

                            case 10 : m = classType1.concreteMethodByName
                                          ("classMethod", "()Lnsk/jdi/ClassType/concreteMethodByName/Class2ForCheck;");
                                      break ;

                            case 11 : m = classType1.concreteMethodByName
                                          ("ifaceMethod", "()Lnsk/jdi/ClassType/concreteMethodByName/IfaceForCheck;");
                                      break ;


                            default:  endFlag = true;
                                      break ;
                            }

                            if (m == null) {
                                expresult = 1;
                                log3("ERROR : no methods returned   :  " + i3);
                            }
                            if (endFlag) break ;
                        }

                        break;


                default: expresult = 2;
                         break ;
                }

                if (expresult == 2) {
                    log2("      test cases finished");
                    break ;
                } else if (expresult == 1) {
                    log3("ERROR: expresult != true;  check # = " + i);
                    testExitCode = FAILED;
                }
            }
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        }
        log1("      TESTING ENDS");

    //--------------------------------------------------   test summary section
    //-------------------------------------------------    standard end section

        pipe.println("quit");
        log2("waiting for the debuggee to finish ...");
        debuggee.waitFor();

        int status = debuggee.getStatus();
        if (status != PASSED + PASS_BASE) {
            log3("debuggee returned UNEXPECTED exit status: " +
                    status + " != PASS_BASE");
            testExitCode = FAILED;
        } else {
            log2("debuggee returned expected exit status: " +
                    status + " == PASS_BASE");
        }

        if (testExitCode != PASSED) {
            logHandler.complain("TEST FAILED");
        }
        return testExitCode;
    }
}
