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

package nsk.jdi.ReferenceType.getValue;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.event.*;
import com.sun.jdi.request.*;
import java.util.*;
import java.io.*;














/**
 * The test for the implementation of an object of the type     <BR>
 * ReferenceType.                                               <BR>
 *                                                              <BR>
 * The test checks up that results of the method                <BR>
 * <code>com.sun.jdi.ReferenceType.getValue()</code>            <BR>
 * complies with its spec.                                      <BR>
 * Case for testing includes arrays of primitive types declared <BR>
 * in this type and its superclass.                             <BR>
 * <BR>
 * The test works as follows.                                           <BR>
 * Upon launching debuggee's VM which will be suspended,                <BR>
 * a debugger waits for the VMStartEvent within a predefined            <BR>
 * time interval. If no the VMStartEvent received, the test is FAILED.  <BR>
 * Upon getting the VMStartEvent, it makes the request                  <BR>
 * for debuggee's ClassPrepareEvent with SUSPEND_EVENT_THREAD,          <BR>
 * resumes the VM, and waits for the event within the predefined        <BR>
 * time interval. If no the ClassPrepareEvent received, the test is FAILED.<BR>
 * Upon getting the ClassPrepareEvent,                                  <BR>
 * the debugger sets up the breakpoint with SUSPEND_EVENT_THREAD,       <BR>
 * the debugger resumes the debuggee and waits for the BreakpointEvent. <BR>
 * The debuggee prepares new check and invokes the methodForCommunication<BR>
 * to be suspended and to inform the debugger with the event.           <BR>
 * Upon getting the BreakpointEvent, the debugger performs the check.   <BR>
 * At the end, the debuggee changes the value of the "instruction"      <BR>
 * to inform the debugger of checks finished, and both end.             <BR>
 */

public class getvalue002 extends JDIBase {

    public static void main (String argv[]) {

        int result = run(argv, System.out);

        System.exit(result + PASS_BASE);
    }

    public static int run (String argv[], PrintStream out) {

        int exitCode = new getvalue002().runThis(argv, out);

        if (exitCode != PASSED) {
            System.out.println("TEST FAILED");
        }
        return testExitCode;
    }

    //  ************************************************    test parameters

    private String debuggeeName =
        "nsk.jdi.ReferenceType.getValue.getvalue002a";

    private String testedClassName =
      "nsk.jdi.ReferenceType.getValue.getvalue002aTestClass";

    //====================================================== test program

    private int runThis (String argv[], PrintStream out) {

        argsHandler     = new ArgumentHandler(argv);
        logHandler      = new Log(out, argsHandler);
        Binder binder   = new Binder(argsHandler, logHandler);

        waitTime        = argsHandler.getWaitTime() * 60000;

        try {
            log2("launching a debuggee :");
            log2("       " + debuggeeName);
            if (argsHandler.verbose()) {
                debuggee = binder.bindToDebugee(debuggeeName + " -vbs");
            } else {
                debuggee = binder.bindToDebugee(debuggeeName);
            }
            if (debuggee == null) {
                log3("ERROR: no debuggee launched");
                return FAILED;
            }
            log2("debuggee launched");
        } catch ( Exception e ) {
            log3("ERROR: Exception : " + e);
            log2("       test cancelled");
            return FAILED;
        }

        debuggee.redirectOutput(logHandler);

        vm = debuggee.VM();

        eventQueue = vm.eventQueue();
        if (eventQueue == null) {
            log3("ERROR: eventQueue == null : TEST ABORTED");
            vm.exit(PASS_BASE);
            return FAILED;
        }

        log2("invocation of the method runTest()");
        switch (runTest()) {

            case 0 :  log2("test phase has finished normally");
                      log2("   waiting for the debuggee to finish ...");
                      debuggee.waitFor();

                      log2("......getting the debuggee's exit status");
                      int status = debuggee.getStatus();
                      if (status != PASS_BASE) {
                          log3("ERROR: debuggee returned UNEXPECTED exit status: " +
                              status + " != PASS_BASE");
                          testExitCode = FAILED;
                      } else {
                          log2("......debuggee returned expected exit status: " +
                              status + " == PASS_BASE");
                      }
                      break;

            default : log3("ERROR: runTest() returned unexpected value");

            case 1 :  log3("test phase has not finished normally: debuggee is still alive");
                      log2("......forcing: vm.exit();");
                      testExitCode = FAILED;
                      try {
                          vm.exit(PASS_BASE);
                      } catch ( Exception e ) {
                          log3("ERROR: Exception : e");
                      }
                      break;

            case 2 :  log3("test cancelled due to VMDisconnectedException");
                      log2("......trying: vm.process().destroy();");
                      testExitCode = FAILED;
                      try {
                          Process vmProcess = vm.process();
                          if (vmProcess != null) {
                              vmProcess.destroy();
                          }
                      } catch ( Exception e ) {
                          log3("ERROR: Exception : e");
                      }
                      break;
            }

        return testExitCode;
    }


   /*
    * Return value: 0 - normal end of the test
    *               1 - ubnormal end of the test
    *               2 - VMDisconnectedException while test phase
    */

    private int runTest() {

        try {
            testRun();

            log2("waiting for VMDeathEvent");
            getEventSet();
            if (eventIterator.nextEvent() instanceof VMDeathEvent)
                return 0;

            log3("ERROR: last event is not the VMDeathEvent");
            return 1;
        } catch ( VMDisconnectedException e ) {
            log3("ERROR: VMDisconnectedException : " + e);
            return 2;
        } catch ( Exception e ) {
            log3("ERROR: Exception : " + e);
            return 1;
        }

    }

    private void testRun()
                 throws JDITestRuntimeException, Exception {

        eventRManager = vm.eventRequestManager();

        ClassPrepareRequest cpRequest = eventRManager.createClassPrepareRequest();
        cpRequest.setSuspendPolicy( EventRequest.SUSPEND_EVENT_THREAD);
        cpRequest.addClassFilter(debuggeeName);

        cpRequest.enable();
        vm.resume();
        getEventSet();
        cpRequest.disable();

        ClassPrepareEvent event = (ClassPrepareEvent) eventIterator.next();
        debuggeeClass = event.referenceType();

        if (!debuggeeClass.name().equals(debuggeeName))
           throw new JDITestRuntimeException("** Unexpected ClassName for ClassPrepareEvent **");

        log2("      received: ClassPrepareEvent for debuggeeClass");

        String bPointMethod = "methodForCommunication";
        String lineForComm  = "lineForComm";
        BreakpointRequest bpRequest;

        try {
            bpRequest = settingBreakpoint(debuggee.threadByNameOrThrow("main"),
                                          debuggeeClass,
                                          bPointMethod, lineForComm, "zero");
        } catch ( Exception e ) {
            throw e;
        }
        bpRequest.enable();

    //------------------------------------------------------  testing section

        log1("     TESTING BEGINS");

        for (int i = 0; ; i++) {

            vm.resume();
            breakpointForCommunication();

            int instruction = ((IntegerValue)
                               (debuggeeClass.getValue(debuggeeClass.fieldByName("instruction")))).value();

            if (instruction == 0) {
                vm.resume();
                break;
            }

            log1("  new check: # " + i);

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ variable part

            log2("      name of tested class : " + testedClassName);
            log2("      getting: List classes = vm.classByName(testedClassName);");
            List classes = vm.classesByName(testedClassName);
            if (classes.size() != 1) {
                log3("ERROR: classes.size() != 1 : " + classes.size());
                testExitCode = FAILED;
                break;
            }
            log2("      getting: ReferenceType testedClass = (ReferenceType) classes.get(0);");
            ReferenceType testedClass = (ReferenceType) classes.get(0);


            log2("------case for testing: arrays of primitive values");

            String fieldNames[] = {
                       // "fl1",
                       // "in1",
                       // "ln1",
                       // "sh1"
                       // "fl1",
                       "in1",
                       "ln1",
                       "sh1"
            };

            for (int i1 = 0; i1 < fieldNames.length; i1++) {

                log2("......field tested : " + fieldNames[i1]);
                log2("      getting: Field field = testedClass.fieldByName(fieldNames[i1]);");
                Field field = testedClass.fieldByName(fieldNames[i1]);
                if (field == null) {
                    log3("ERROR: field == null");
                    testExitCode = FAILED;
                    continue;
                }
                log2("      getting: Value value = testedClass.getValue(field);");
                Value value = testedClass.getValue(field);
                if (value == null) {
                    log3("ERROR: value == null");
                    testExitCode = FAILED;
                    continue;
                }
                try {
                    log2("      getting Class cast: ObjectReference objectReferenceValue = (ObjectReference) value;");
                    ObjectReference objectReferenceValue = (ObjectReference) value;
                } catch ( ClassCastException e ) {
                    log3("ERROR: ClassCastException");
                    testExitCode = FAILED;
                }
            }
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        }
        log1("    TESTING ENDS");
        return;
    }

}
