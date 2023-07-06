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

package nsk.jdi.ReferenceType.allMethods;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import java.io.*;
import java.util.*;

/**
 * The debugger application of the test.
 */
public class allmethods006 {

    //------------------------------------------------------- immutable common fields

    final static String SIGNAL_READY = "ready";
    final static String SIGNAL_GO    = "go";
    final static String SIGNAL_QUIT  = "quit";

    private static int waitTime;
    private static int exitStatus;
    private static ArgumentHandler     argHandler;
    private static Log                 log;
    private static Debugee             debuggee;
    private static ReferenceType       debuggeeClass;

    //------------------------------------------------------- mutable common fields

    private final static String prefix = "nsk.jdi.ReferenceType.allMethods.";
    private final static String className = "allmethods006";
    private final static String debuggerName = prefix + className;
    private final static String debuggeeName = debuggerName + "a";

    //------------------------------------------------------- test specific fields

    private final static String[] testedFieldNames = {"f1", "f2", "f3"};

    // Inherited from java.lang.Enum:
    private final static String[] testedMethodNames = {
        "clone",
        "compareTo",
        "equals",
        "getDeclaringClass",
        "hashCode",
        "name",
        "ordinal",
        "toString"
        };

    //------------------------------------------------------- immutable common methods

    public static void main(String argv[]) {
        System.exit(Consts.JCK_STATUS_BASE + run(argv, System.out));
    }

    private static void display(String msg) {
        log.display("debugger > " + msg);
    }

    private static void complain(String msg) {
        log.complain("debugger FAILURE > " + msg);
    }

    public static int run(String argv[], PrintStream out) {

        exitStatus = Consts.TEST_PASSED;

        argHandler = new ArgumentHandler(argv);
        log = new Log(out, argHandler);
        waitTime = argHandler.getWaitTime() * 60000;

        debuggee = Debugee.prepareDebugee(argHandler, log, debuggeeName);

        debuggeeClass = debuggee.classByName(debuggeeName);
        if ( debuggeeClass == null ) {
            complain("Class '" + debuggeeName + "' not found.");
            exitStatus = Consts.TEST_FAILED;
        }

        execTest();

        debuggee.quit();

        return exitStatus;
    }

    //------------------------------------------------------ mutable common method

    private static void execTest() {
        //debuggee.receiveExpectedSignal(SIGNAL_GO);

        for (int i=0; i < testedFieldNames.length; i++) {
            check(testedFieldNames[i]);
            display("");
        }

        display("Checking completed!");
    }

    //--------------------------------------------------------- test specific methods

    private static void check (String fieldName) {
        try {
            ClassType checkedClass = (ClassType)debuggeeClass.fieldByName(fieldName).type();
            String className = checkedClass.name();

            // check existence of inherited methods for this enum type
            List<Method> l = checkedClass.allMethods();
            if (l.isEmpty()) {
                complain("\t ReferenceType.allMethods() returned empty list for type: " + className);
                exitStatus = Consts.TEST_FAILED;
            } else {
                Vector<String> methodNames = new Vector<String>();
                Iterator<Method> it = l.iterator();
                while (it.hasNext()) {
                    methodNames.add(it.next().name());
                }

                for (int i = 0; i < testedMethodNames.length; i++) {
                    String methodName = testedMethodNames[i];
                    if (methodNames.contains(methodName)) {
                        display("CHECK" + (i+1) + " PASSED: " +  className + " has method " + methodName);
                    } else {
                        complain("CHECK" + (i+1) + " FAILED: " + className + " does not have method " + methodName);
                        exitStatus = Consts.TEST_FAILED;
                    }
                }
            }

        } catch (Exception e) {
            complain("Unexpected exception while checking of " + className + ": " + e);
            e.printStackTrace(System.out);
            exitStatus = Consts.TEST_FAILED;
        }
    }
}
//--------------------------------------------------------- test specific classes
