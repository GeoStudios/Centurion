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

package nsk.jdi.MethodEntryRequest.addInstanceFilter;


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
 * MethodEntryRequest.
 *
 * The test checks that results of the method
 * <code>com.sun.jdi.MethodEntryRequest.addInstanceFilter()</code>
 * complies with its spec.
 *
 * The test checks up on the following assertion:
 *    Restricts the events generated by this request to those
 *    in which the currently executing instance is
 *    the object specified.
 *
 * The test works as follows.
 * - The debugger resumes the debuggee and waits for the BreakpointEvent.
 * - The debuggee creates a special array with three Object instances,
 *   and two threads, thread1 and thread2, and invokes
 *   the methodForCommunication to be suspended and
 *   to inform the debugger with the event.
 * - Upon getting the BreakpointEvent, the debugger
 *   - sets up a MethodEntryRequest
 *     within the method in the class instancefilter001aTestClass
 *     whose array element instances #0 and #1
 *     will be calling by the thread1 and the thread2 accordinly,
 *   - invokes addInstanceFilter() on array element #0 for the thread1
 *     and #2 for the thread2,
 *     thus restricting the MethodEntry event only for thread1,
 *   - resumes debuggee's main thread, and
 *   - waits for the event.
 * - Debuggee's main thread starts both threads.
 * - Upon getting the event, the debugger performs the checks required.
 */

public class instancefilter001 extends TestDebuggerType1 {

    public static void main (String argv[]) {
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    public static int run (String argv[], PrintStream out) {
        debuggeeName = "nsk.jdi.MethodEntryRequest.addInstanceFilter.instancefilter001a";
        return new instancefilter001().runThis(argv, out);
    }

    private String testedClassName =
      "nsk.jdi.MethodEntryRequest.addInstanceFilter.instancefilter001aTestClass";

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

        String property1 = "MethodEntryRequest1";
        String property2 = "MethodEntryRequest2";

        ReferenceType testClassReference = null;

        String         arrayName = "objTC";
        ObjectReference instance = null;

        for (int i = 0; ; i++) {

            if (!shouldRunAfterBreakpoint()) {
                vm.resume();
                break;
            }

            display(":::::: case: # " + i);

            switch (i) {

                case 0:
                testClassReference =
                     (ReferenceType) debuggee.classByName(testedClassName);

                thread1 = (ThreadReference) debuggeeClass.getValue(
                                            debuggeeClass.fieldByName(thread1Name));
                eventRequest1 = setting2MethodEntryRequest (thread1,
                                       testClassReference,
                                       EventRequest.SUSPEND_ALL, property1);
                instance = (ObjectReference)
                           ((ArrayReference) debuggeeClass.getValue(
                           debuggeeClass.fieldByName(arrayName)) ).getValue(0);
                ((MethodEntryRequest) eventRequest1).addInstanceFilter(instance);

                thread2 = (ThreadReference) debuggeeClass.getValue(
                                            debuggeeClass.fieldByName(thread2Name));
                eventRequest2 = setting2MethodEntryRequest (thread2,
                                       testClassReference,
                                       EventRequest.SUSPEND_ALL, property2);
                instance = (ObjectReference)
                           ((ArrayReference) debuggeeClass.getValue(
                           debuggeeClass.fieldByName(arrayName)) ).getValue(2);
                ((MethodEntryRequest) eventRequest2).addInstanceFilter(instance);

                display("......waiting for MethodEntryEvent in expected thread");
                Event newEvent = eventHandler.waitForRequestedEvent(new EventRequest[]{eventRequest1, eventRequest2}, waitTime, true);

                if ( !(newEvent instanceof MethodEntryEvent)) {
                    setFailedStatus("ERROR: new event is not MethodEntryEvent");
                } else {

                    String property = (String) newEvent.request().getProperty("number");
                    display("       got new MethodEntryEvent with property 'number' == " + property);

                    if ( !property.equals(property1) ) {
                        setFailedStatus("ERROR: property is not : " + property1);
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

    private MethodEntryRequest setting2MethodEntryRequest ( ThreadReference thread,
                                                            ReferenceType   testedClass,
                                                            int             suspendPolicy,
                                                            String          property       ) {
        try {
            display("......setting up MethodEntryRequest:");
            display("       thread: " + thread + "; class: " + testedClass +  "; property: " + property);

            MethodEntryRequest
            menr = eventRManager.createMethodEntryRequest();
            menr.putProperty("number", property);
            if (thread != null)
                menr.addThreadFilter(thread);
            menr.addClassFilter(testedClass);
            menr.setSuspendPolicy(suspendPolicy);

            display("      a MethodEntryRequest has been set up");
            return menr;
        } catch ( Exception e ) {
            throw new Failure("** FAILURE to set up MethodEntryRequest **");
        }
    }
}
