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

package nsk.jdi.MethodEntryRequest.addClassFilter_s;

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
 * <code>com.sun.jdi.MethodEntryRequest.addClassFilter()</code>
 * complies with its spec.
 *
 * The test checks up on the following assertion:
 *    Restricts the events generated by this request to those
 *    whose method is in a class whose name does not match
 *    this restricted regular expression. e.g. "java.*" or "*.Foo".
 * The cases to check include both a pattern that begin with '*' and
 * one that end with '*'.
 *
 * The test works as follows.
 * - The debugger
 *   - sets up two MethodEntryRequests,
 *   - restricts the Requests using patterns that begins with '*' and
 *     ends with *,
 *   - resumes the debuggee, and
 *   - waits for expected MethodEntryEvents.
 * - The debuggee creates and starts two threads, thread1 and thread2,
 *   that being run, invoke methods used
 *   to generate Events and to test the filters.
 * - Upon getting the events, the debugger performs checks required.
 */

public class filter_s001 extends TestDebuggerType1 {

    public static void main (String argv[]) {
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    public static int run (String argv[], PrintStream out) {
        debuggeeName = "nsk.jdi.MethodEntryRequest.addClassFilter_s.filter_s001a";
        return new filter_s001().runThis(argv, out);
    }

    private String testedClassName1 = "TestClass11";
    private String testedClassName2 =
      "nsk.jdi.MethodEntryRequest.addClassFilter_s.Thread2filter_s001a";

    protected void testRun() {

        EventRequest  eventRequest1      = null;
        EventRequest  eventRequest2      = null;

        String        property1          = "MethodEntryRequest1";
        String        property2          = "MethodEntryRequest2";

        Event newEvent = null;

        for (int i = 0; ; i++) {

            if (!shouldRunAfterBreakpoint()) {
                vm.resume();
                break;
            }

            display(":::::: case: # " + i);

            switch (i) {

                case 0:

                eventRequest1 = setting22MethodEntryRequest(null, "*" + testedClassName1,
                                                           EventRequest.SUSPEND_ALL, property1);

                display("......waiting for MethodEntryEvent in expected class");
                newEvent = eventHandler.waitForRequestedEvent(new EventRequest[]{eventRequest1}, waitTime, true);

                if ( !(newEvent instanceof MethodEntryEvent)) {
                    setFailedStatus("ERROR: new event is not MethodEntryEvent");
                } else {
                    String str = ((MethodEntryEvent)newEvent).location().declaringType().name();
                    if (!str.endsWith(testedClassName1)) {
                        setFailedStatus("Received MethodEntryEvent for unexpected class: \n\t" + str);
                    } else {
                        display("Received MethodEntryEvent for expected class: \n\t" + str);
                    }

                    String property = (String) newEvent.request().getProperty("number");
                    if ( !property.equals(property1) ) {
                        setFailedStatus("ERROR: property is not : " + property1);
                    }
                }

                vm.resume();
                break;

                case 1:
                eventRequest2 = setting22MethodEntryRequest(null, testedClassName2 + "*",
                                                           EventRequest.SUSPEND_ALL, property2);

                display("......waiting for MethodEntryEvent in expected class");
                newEvent = eventHandler.waitForRequestedEvent(new EventRequest[]{eventRequest2}, waitTime, true);

                if ( !(newEvent instanceof MethodEntryEvent)) {
                    setFailedStatus("ERROR: new event is not MethodEntryEvent");
                } else {

                    String str = ((MethodEntryEvent)newEvent).location().declaringType().name();
                    if (!str.endsWith(testedClassName2)) {
                        setFailedStatus("Received MethodEntryEvent for unexpected class: \n\t" + str);
                    } else {
                        display("Received MethodEntryEvent for expected class: \n\t" + str);
                    }

                    String property = (String) newEvent.request().getProperty("number");
                    if ( !property.equals(property2) ) {
                        setFailedStatus("ERROR: property is not : " + property2);
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

    private MethodEntryRequest setting22MethodEntryRequest ( ThreadReference thread,
                                                             String          testedClass,
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
