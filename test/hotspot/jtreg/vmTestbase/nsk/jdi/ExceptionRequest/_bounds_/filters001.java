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

package nsk.jdi.ExceptionRequest._bounds_;

import nsk.share.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.request.*;
import java.io.*;

/**
 * The test checks up the                <br>
 *  - addThreadFilter(ThreadReference)   <br>
 *  - addInstanceFilter(ObjectReference) <br>
 *  - addClassFilter(ReferenceType)      <br>
 *  - addClassFilter(String)             <br>
 *  - addClassExclusionFilter(String)    <br>
 * methods with <code>null</code> argument value.
 * In any cases <code>NullPointerException</code> is expected.
 */
/**
    com.sun.jdi.request.ExceptionRequest.addThreadFilter(ThreadReference)
    com.sun.jdi.request.ExceptionRequest.addInstanceFilter(ObjectReference)
    com.sun.jdi.request.ExceptionRequest.addClassFilter(ReferenceType)
    com.sun.jdi.request.ExceptionRequest.addClassFilter(String)
    com.sun.jdi.request.ExceptionRequest.addClassExclusionFilter(String)
 */
public class filters001 {

    private final static String prefix = "nsk.jdi.ExceptionRequest._bounds_.";
    private final static String debuggerName = prefix + "filters001";
    private final static String debugeeName = debuggerName + "a";

    private static int exitStatus;
    private static Log log;
    private static Debugee debugee;

    private static void display(String msg) {
        log.display("debugger> " + msg);
    }

    private static void complain(String msg) {
        log.complain("debugger FAILURE> " + msg + "\n");
    }

    public static void main(String argv[]) {
        System.exit(Consts.JCK_STATUS_BASE + run(argv, System.out));
    }

    public static int run(String argv[], PrintStream out) {

        exitStatus = Consts.TEST_PASSED;

        filters001 thisTest = new filters001();

        ArgumentHandler argHandler = new ArgumentHandler(argv);
        log = new Log(out, argHandler);

        debugee = Debugee.prepareDebugee(argHandler, log, debugeeName);

        thisTest.execTest();
        display("execTest finished. exitStatus = " + exitStatus);

        return exitStatus;
    }

    private void execTest() {

        display("");
        display(">>>creating ExceptionRequest");

        ExceptionRequest request =
        debugee.getEventRequestManager().createExceptionRequest(null, true, true);

        display("");
        addThreadFilter(request, null);

        display("");
        addInstanceFilter(request, null);

        display("");
        addClassFilter(request, (ReferenceType )null);

        display("");
        addClassFilter(request, (String )null);

        display("");
        addClassExclusionFilter(request, (String )null);

        display("");
        debugee.quit();
    }

    private void addThreadFilter(ExceptionRequest request, ThreadReference thread) {
        String tmp = "addThreadFilter         :thread name> ";
        tmp += (thread == null) ? "<null>" : thread.name();
        display("----------------------");
        display(tmp);

        try {
            request.addThreadFilter(thread);
            if (thread==null){
                complain("*****NullPointerException is not thrown");
                exitStatus = Consts.TEST_FAILED;
            }
        } catch (NullPointerException e) {
            if (thread == null){
                display("!!!Expected " + e);
            } else {
                complain("*****Unexpected " + e);
                exitStatus = Consts.TEST_FAILED;
            }
        } catch (Exception e) {
            complain("Unexpected " + e);
            exitStatus = Consts.TEST_FAILED;
        }
    }

    private void addInstanceFilter(ExceptionRequest request,
                                            ObjectReference instance) {
        String tmp = "addInstanceFilter       :object value> ";
        tmp += (instance == null) ? "<null>" : instance.toString();
        display("----------------------");
        display(tmp);

        try {
            request.addInstanceFilter(instance);
            if (instance==null){
                complain("*****NullPointerException is not thrown");
                exitStatus = Consts.TEST_FAILED;
            }
        } catch (NullPointerException e) {
            if (instance == null){
                display("!!!Expected " + e);
            } else {
                complain("*****Unexpected " + e);
                exitStatus = Consts.TEST_FAILED;
            }
        } catch (Exception e) {
            complain("Unexpected " + e);
            exitStatus = Consts.TEST_FAILED;
        }
    }

    private void addClassFilter(ExceptionRequest request, ReferenceType refType) {

        display("addClassFilter          :ReferenceType> null");

        try {
            request.addClassFilter(refType);
            complain("*****NullPointerException is not thrown");
            exitStatus = Consts.TEST_FAILED;
        } catch (NullPointerException e) {
            display("!!!Expected " + e);
        } catch (Exception e) {
            complain("*****Unexpected " + e);
            exitStatus = Consts.TEST_FAILED;
        }
    }

    private void addClassFilter(ExceptionRequest request, String classPattern) {

        display("addClassFilter          :classPattern> null");
        try {
            request.addClassFilter(classPattern);
            complain("*****NullPointerException is not thrown");
            exitStatus = Consts.TEST_FAILED;
        } catch (NullPointerException e) {
            display("!!!Expected " + e);
        } catch (Exception e) {
            complain("*****Unexpected " + e);
            exitStatus = Consts.TEST_FAILED;
        }
    }

    private void addClassExclusionFilter(ExceptionRequest request,
                                                    String classPattern) {
        display("addExclusionClassFilter :classPattern> null");
        try {
            request.addClassExclusionFilter(classPattern);
            complain("*****NullPointerException is not thrown");
            exitStatus = Consts.TEST_FAILED;
        } catch (NullPointerException e) {
            display("!!!Expected " + e);
        } catch (Exception e) {
            complain("*****Unexpected " + e);
            exitStatus = Consts.TEST_FAILED;
        }
    }

}
