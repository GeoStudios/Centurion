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

package nsk.jdi.MethodExitRequest.addInstanceFilter;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.event.*;
import com.sun.jdi.request.*;
import java.util.*;
import java.io.*;














/**
 * The test for the implementation of an object of the type
 * MethodExitRequest.
 *
 * The test checks that results of the method
 * <code>com.sun.jdi.MethodExitRequest.addInstanceFilter()</code>
 * complies with its spec.
 *
 * The test checks up on the following assertion:
 *    Restricts the events generated by this request to those in which
 *    the currently executing instance is the object specified.
 * The cases to test includes re-invocation of the method
 * addInstanceFilter() on the same MethodExitRequest object.
 * There are two BreakpointRequests to check as follows:
 * (1) For MethodExitRequest2, both invocations are with different
 * ObjectReferences restricting one MethodExit event to two objects.
 * The test expects no MethodExit event will be received.
 * (2) For MethodExitRequest1, both invocations are with the same
 * ObjectReference restricting one MethodExit event to one object.
 * The test expects this MethodExit event will be received.
 *
 * The test works as follows.
 * - The debugger resumes the debuggee and waits for the BreakpointEvent.
 * - The debuggee creates two threads, thread1 and thread2, and invokes
 *   the methodForCommunication to be suspended and
 *   to inform the debugger with the event.
 * - Upon getting the BreakpointEvent, the debugger
 *   - sets up MethodExitRequests 1&2 within the method
 *     in the class TestClass which will be calling by both threads,
 *   - restricts the MethodExitRequest1 to the tread1 and
 *     to the array elements #0 & #0 calling only within thread1,
 *   - restricts the MethodExitRequest2 to the thread2 and
 *     the array elements #0 & #1 calling within thread1 and thread2,
 *   - resumes debuggee's main thread, and
 *   - waits for the event.
 * - Debuggee's main thread starts both threads.
 * - Upon getting the event, the debugger performs the checks required.
 */

public class instancefilter004 extends TestDebuggerType1 {

    public static void main (String argv[]) {
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    public static int run (String argv[], PrintStream out) {
        debuggeeName = "nsk.jdi.MethodExitRequest.addInstanceFilter.instancefilter004a";
        return new instancefilter004().runThis(argv, out);
    }

    private String testedClassName =
      "nsk.jdi.MethodExitRequest.addInstanceFilter.instancefilter004aTestClass";


    protected void testRun() {
        if ( !vm.canUseInstanceFilters() ) {
            display("......vm.canUseInstanceFilters == false :: test cancelled");
            vm.exit(Consts.JCK_STATUS_BASE);
            return;
        }

        EventRequest eventRequest1 = null;
        EventRequest eventRequest2 = null;

        ThreadReference thread1 = null;
        ThreadReference thread2 = null;

        String thread1Name = "thread1";
        String thread2Name = "thread2";

        String property1 = "MethodExitRequest1";
        String property2 = "MethodExitRequest2";

        ReferenceType testClassReference = null;

        String         arrayName = "objTC";

        ObjectReference instance1 = null;
        ObjectReference instance2 = null;

        for (int i = 0; ; i++) {

            if (!shouldRunAfterBreakpoint()) {
                vm.resume();
                break;
            }

            display(":::::: case: # " + i);

            switch (i) {

                case 0:
                testClassReference =
                     (ReferenceType) vm.classesByName(testedClassName).get(0);

                thread1 = (ThreadReference) debuggeeClass.getValue(
                                            debuggeeClass.fieldByName(thread1Name));
                thread2 = (ThreadReference) debuggeeClass.getValue(
                                            debuggeeClass.fieldByName(thread2Name));

                eventRequest1 = setting2MethodExitRequest (thread1,
                                       testClassReference,
                                       EventRequest.SUSPEND_ALL, property1);
                eventRequest2 = setting2MethodExitRequest (thread2,
                                       testClassReference,
                                       EventRequest.SUSPEND_ALL, property2);

                instance1 = (ObjectReference)
                            ((ArrayReference) debuggeeClass.getValue(
                            debuggeeClass.fieldByName(arrayName)) ).getValue(0);
                instance2 = (ObjectReference)
                            ((ArrayReference) debuggeeClass.getValue(
                            debuggeeClass.fieldByName(arrayName)) ).getValue(1);

                ((MethodExitRequest) eventRequest1).addInstanceFilter(instance1);
                ((MethodExitRequest) eventRequest1).addInstanceFilter(instance1);
                ((MethodExitRequest) eventRequest2).addInstanceFilter(instance1);
                ((MethodExitRequest) eventRequest2).addInstanceFilter(instance2);

                display("......waiting for MethodExitEvent in expected thread");
                MethodExitEvent newEvent = (MethodExitEvent)eventHandler.waitForRequestedEvent(new EventRequest[]{eventRequest1, eventRequest2}, waitTime, true);

                EventRequest newEventRequest = newEvent.request();
                if ( newEventRequest.equals(eventRequest1) ) {
                    display("       received expected event: " + newEvent);

                    ThreadReference newEventThread = newEvent.thread();
                    String threadName = newEventThread.name();
                    display("       the event is in thread == " + newEventThread.name());
                    if ( !newEventThread.equals(thread1) ) {
                        setFailedStatus("ERROR: the event is not in thread1");
                    }
                }
                vm.resume();
                break;

                default:
                throw new Failure("** default case 2 **");
            }
        }
        return;
    }

    private MethodExitRequest setting2MethodExitRequest ( ThreadReference thread,
                                                          ReferenceType   testedClass,
                                                          int             suspendPolicy,
                                                          String          property       ) {
        try {
            display("......setting up MethodExitRequest:");
            display("       thread: " + thread + "; class: " + testedClass + "; property: " + property);

            MethodExitRequest
            menr = eventRManager.createMethodExitRequest();
            menr.putProperty("number", property);
            if (thread != null)
                menr.addThreadFilter(thread);
            menr.addClassFilter(testedClass);
            menr.setSuspendPolicy(suspendPolicy);

            display("      MethodExitRequest has been set up");
            return menr;
        } catch ( Exception e ) {
            throw new Failure("** FAILURE to set up MethodExitRequest **");
        }
    }
}
