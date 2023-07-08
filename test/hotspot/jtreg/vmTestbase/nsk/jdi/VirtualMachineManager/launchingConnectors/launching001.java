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

package nsk.jdi.VirtualMachineManager.launchingConnectors;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import java.util.*;
import java.io.*;

/**
 * The test for the implementation of an object of the type     <BR>
 * VirtualMachineManager.                                       <BR>
 *                                                              <BR>
 * The test checks up that results of the method                <BR>
 * <code>com.sun.jdi.VirtualMachineManager.launchingConnectors()</code> <BR>
 * complies with its specification.                             <BR>
 * <BR>
 * The test checks up that invoking the method                  <BR>
 *   VirtualMachineManager.launchingConnectors() doesn't throw  <BR>
 *  an exception and returnes a List of non-null values of      <BR>
 *  the LaunchingConnector type each.                           <BR>
 */

public class launching001 {

    //----------------------------------------------------- templete section
    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int PASS_BASE = 95;

    //----------------------------------------------------- templete parameters
    static final String
    sHeader1 = "\n==> nsk/jdi/VirtualMachineManager/launchingConnectors/launching001  ",
    sHeader2 = "--> debugger: ",
    sHeader3 = "##> debugger: ";

    //----------------------------------------------------- main method

    public static void main (String argv[]) {
        int result = run(argv, System.out);
        System.exit(result + PASS_BASE);
    }

    public static int run (String argv[], PrintStream out) {
        return new launching001().runThis(argv, out);
    }

    //--------------------------------------------------   log procedures

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
    //====================================================== test program
    //------------------------------------------------------ common section

    static ArgumentHandler      argsHandler;

    static int waitTime;

    static int  testExitCode = PASSED;

    static final int returnCode0 = 0;
    static final int returnCode1 = 1;
    static final int returnCode2 = 2;
    static final int returnCode3 = 3;
    static final int returnCode4 = 4;

    //------------------------------------------------------ methods

    private int runThis (String argv[], PrintStream out) {

        argsHandler     = new ArgumentHandler(argv);
        logHandler      = new Log(out, argsHandler);

    //------------------------------------------------------  testing section
        log1("      TESTING BEGINS");
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ variable part

        log2("......call to Bootstrap.virtualMachineManager()");
        VirtualMachineManager vmm = Bootstrap.virtualMachineManager();
        if (vmm == null) {
            log3("ERROR: null returned");
            testExitCode = FAILED;
        } else {

            try {
                log2("......call to vmm.launchingConnectors()");
                List connectors = vmm.launchingConnectors();
                log2("       size of List == " + connectors.size());

                log2("......checking up on nulls and casts to LaunchingConnector");
                ListIterator li = connectors.listIterator();
                for (; li.hasNext(); ) {
                    LaunchingConnector launchingConnector = (LaunchingConnector) li.next();
                    if (launchingConnector == null) {
                        log3("ERROR: launchingConnector == null");
                        testExitCode = FAILED;
                    }
                }
            } catch ( Exception e) {
                log3("ERROR: Exception : " + e);
                testExitCode = FAILED;
            }
        }

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        log1("      TESTING ENDS");

    //--------------------------------------------------   test summary section
    //-------------------------------------------------    standard end section

        if (testExitCode != PASSED) {
            logHandler.complain("TEST FAILED");
        }
        return testExitCode;
    }
}
