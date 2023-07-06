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

package nsk.jdi.VirtualMachine.mirrorOf_long;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import java.util.*;
import java.io.*;

/**
 * The test for the implementation of an object of the type     <BR>
 * VirtualMachine.                                              <BR>
 *                                                              <BR>
 * The test checks up that results of the method                <BR>
 * <code>com.sun.jdi.VirtualMachine.mirrorOf_long()</code>      <BR>
 * complies with the spec for                                   <BR>
 * <code>com.sun.jdi.LongValue</code> methods                   <BR>
 * <BR>
 * The cases for testing are as follows         <BR>
 * ('val_i' means 'long_value_i') :             <BR>
 *                                                      <BR>
 * // LongValue val.value() method                      <BR>
 *                                                      <BR>
 *      val_i.value(Long.MIN_VALUE) == Long.MIN_VALUE   <BR>
 *      val_i.value(Long.MAX_VALUE) == Long.MAX_VALUE   <BR>
 *      val_i.value(0)     ==  0                        <BR>
 *      val_i.value(-1)    == -1                        <BR>
 *      val_i.value(+1)    == +1                        <BR>
 *                                              <BR>
 * // LongValue val.equals() method             <BR>
 *                                              <BR>
 *      val_i.value(1) == val_j.value(1)        <BR>
 *      val_i.value(1) != val_j.value(-1)       <BR>
 *                                              <BR>
 *      val_i.value(1) != integerValue.value(1) <BR>
 *                                              <BR>
 * // LongValue val.hashCode() method   <BR>
 *                                              <BR>
 *      val_i.hashCode() == val_i.hashCode()    <BR>
 *                                              <BR>
 *      if (val_i.value() == val_j.value()) {   <BR>
 *          val_i.hashCode() == val_j.hashCode()<BR>
 *      }                                       <BR>
 * <BR>
 */

public class mirrorof_long001 {

    //----------------------------------------------------- templete section
    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int PASS_BASE = 95;

    //----------------------------------------------------- templete parameters
    static final String
    sHeader1 = "\n==> nsk/jdi/VirtualMachine/mirrorOf_long/mirrorof_long001",
    sHeader2 = "--> mirrorof_long001: ",
    sHeader3 = "##> mirrorof_long001: ";

    //----------------------------------------------------- main method

    public static void main (String argv[]) {
        int result = run(argv, System.out);
        System.exit(result + PASS_BASE);
    }

    public static int run (String argv[], PrintStream out) {
        return new mirrorof_long001().runThis(argv, out);
    }

     //--------------------------------------------------   log procedures

    //private static boolean verbMode = false;

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

    private String debugeeName =
        "nsk.jdi.VirtualMachine.mirrorOf_long.mirrorof_long001a";

    //====================================================== test program

    static ArgumentHandler      argsHandler;
    static int                  testExitCode = PASSED;

    //------------------------------------------------------ common section

    private int runThis (String argv[], PrintStream out) {

        Debugee debugee;

        argsHandler     = new ArgumentHandler(argv);
        logHandler      = new Log(out, argsHandler);
        Binder binder   = new Binder(argsHandler, logHandler);

        if (argsHandler.verbose()) {
            debugee = binder.bindToDebugee(debugeeName + " -vbs");  // *** tp
        } else {
            debugee = binder.bindToDebugee(debugeeName);            // *** tp
        }

        IOPipe pipe     = new IOPipe(debugee);

        debugee.redirectStderr(out);
        log2("mirrorof_long001a debugee launched");
        debugee.resume();

        String line = pipe.readln();
        if ((line == null) || !line.equals("ready")) {
            log3("signal received is not 'ready' but: " + line);
            return FAILED;
        } else {
            log2("'ready' recieved");
        }

        VirtualMachine vm = debugee.VM();

    //------------------------------------------------------  testing section
        log1("      TESTING BEGINS");

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ variable part

        long smallest   =  Long.MIN_VALUE;
        long zero       =  0;
        long largest    =  Long.MAX_VALUE;
        long plus1      =  +1;
        long minus1     =  -1;

        LongValue val_1 = vm.mirrorOf(smallest);
        LongValue val_2 = vm.mirrorOf(zero);
        LongValue val_3 = vm.mirrorOf(largest);
        LongValue val_4 = vm.mirrorOf(plus1);
        LongValue val_5 = vm.mirrorOf(plus1);
        LongValue val_6 = vm.mirrorOf(minus1);

        FloatValue val_7 = vm.mirrorOf((float)plus1);

        int i;

        for (i = 0; ; i++) {

            int expresult = 0;

            log2("     new check: #" + i);

            switch (i) {

            // tests for LongValue.value()

            case 0: if (val_1.value() != smallest)
                        expresult = 1;
                    break;

            case 1: if (val_2.value() != zero)
                        expresult = 1;
                    break;

            case 2: if (val_3.value() != largest)
                        expresult = 1;
                    break;

            // tests for LongValue.equals()

            case 3: if (!val_4.equals(val_5))
                        expresult = 1;
                    break;

            case 4: if (val_4.equals(val_6))
                        expresult = 1;
                    break;

            case 5: if (val_4.equals(val_7))
                        expresult = 1;
                    break;

            // tests for LongValue.hashCode()

            case 6: if (val_1.hashCode() != val_1.hashCode())
                        expresult = 1;
                    break;

            case 7: if (val_4.hashCode() != val_5.hashCode())
                        expresult = 1;
                    break;

            default: expresult = 2;
                     break ;
            }

            if (expresult == 2) {
                log2("      test cases finished");
                break ;
            } else if (expresult == 1) {;
                log3("ERROR: expresult != true;  check # = " + i);
                testExitCode = FAILED;
            }

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        }
        log1("      TESTING ENDS");

    //--------------------------------------------------   test summary section

    //-------------------------------------------------    standard end section

        pipe.println("quit");
        log2("waiting for the debugee finish ...");
        debugee.waitFor();

        int status = debugee.getStatus();
        if (status != PASSED + PASS_BASE) {
            log3("debugee returned UNEXPECTED exit status: " +
                   status + " != PASS_BASE");
            testExitCode = FAILED;
        } else {
            log2("debugee returned expected exit status: " +
                   status + " == PASS_BASE");
        }

        if (testExitCode != PASSED) {
            logHandler.complain("TEST FAILED");
        }
        return testExitCode;
    }
}