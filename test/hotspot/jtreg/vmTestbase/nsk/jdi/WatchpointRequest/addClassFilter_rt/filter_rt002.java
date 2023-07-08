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

package nsk.jdi.WatchpointRequest.addClassFilter_rt;


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
 * WatchpointRequest.
 *
 * The test checks that results of the method
 * <code>com.sun.jdi.WatchpointRequest.addClassFilter(ReferenceType)</code>
 * complies with its spec.
 *
 * The test checks up on the following assertion:
 *    Restricts the events generated by this request to those whose
 *    location is in the given reference type or any of its subtypes.
 *    An event will be generated for any location in a reference type
 *    that can be safely cast to the given reference type.
 * The case to test include ModificationWatchpointRequest.
 *
 * The test works as follows.
 * - The debugger resumes the debuggee and waits for the BreakpointEvent.
 * - The debuggee creates objects of ClassTypes to test and invokes
 *   the methodForCommunication to be suspended and
 *   to inform the debugger with the event.
 * - Upon getting the BreakpointEvent, the debugger
 *   - sets up a WatchpointRequest,
 *   - gets ReferenecType and, using it, restricts the Request,
 *   - resumes the debuggee and waits for expected WatchpointEvent.
 * - The debuggee creates and starts two threads, thread1 and thread2,
 *   that being run, invokes methods performing access to fileds,
 *   used to generate Event and to test the filters.
 * - Upon getting the event, the debugger performs checks required.
 */

public class filter_rt002 extends TestDebuggerType1 {

    public static void main (String argv[]) {
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    public static int run (String argv[], PrintStream out) {
        debuggeeName = "nsk.jdi.WatchpointRequest.addClassFilter_rt.filter_rt002a";
        return new filter_rt002().runThis(argv, out);
    }

    //  ************************************************    test parameters

    String className1 = "nsk.jdi.WatchpointRequest.addClassFilter_rt.filter_rt002aTestClass10";
    String className2 = "nsk.jdi.WatchpointRequest.addClassFilter_rt.filter_rt002aTestClass11";

    protected void testRun() {

        if ( !vm.canWatchFieldModification() ) {
            display("......vm.canWatchFieldModification == false :: test cancelled");
            vm.exit(Consts.JCK_STATUS_BASE);
            return;
        }

        EventRequest  eventRequest1 = null;
        String        property1 = "ModificationWatchpointRequest1";
        ThreadReference thread1 = null;
        String thread1Name = "thread1";
        String fieldName1 = "var101";
        ReferenceType testClassReference = null;
        Event newEvent = null;

        for (int i = 0; ; i++) {

            if (!shouldRunAfterBreakpoint()) {
                vm.resume();
                break;
            }

            display(":::::: case: # " + i);

            switch (i) {
                case 0:
                testClassReference =
                     (ReferenceType) vm.classesByName(className1).get(0);

                eventRequest1 = setting21ModificationWatchpointRequest (null,
                                       testClassReference, fieldName1,
                                       EventRequest.SUSPEND_ALL, property1);

                testClassReference =
                     (ReferenceType) vm.classesByName(className2).get(0);

                ((ModificationWatchpointRequest) eventRequest1).addClassFilter(testClassReference);

                display("......waiting for ModificationWatchpointEvent in tested thread");
                newEvent = eventHandler.waitForRequestedEvent(new EventRequest[]{eventRequest1}, waitTime, true);

                if ( !(newEvent instanceof ModificationWatchpointEvent)) {
                    setFailedStatus("new event is not ModificationWatchpointEvent");
                } else {
                    String property = (String) newEvent.request().getProperty("number");
                    display("       got new ModificationWatchpointEvent with property 'number' == " + property);

                    if ( !property.equals(property1) ) {
                        setFailedStatus("property is not : " + property1);
                    }
                }
                vm.resume();
                break;

                default:
                throw new Failure("** default case 1 **");
            }
        }
        return;
    }

    private ModificationWatchpointRequest setting21ModificationWatchpointRequest (
                                                  ThreadReference thread,
                                                  ReferenceType   fieldClass,
                                                  String          fieldName,
                                                  int             suspendPolicy,
                                                  String          property        ) {
        try {
            display("......setting up ModificationWatchpointRequest:");
            display("       thread: " + thread + "; fieldClass: " + fieldClass + "; fieldName: " + fieldName);
            Field field = fieldClass.fieldByName(fieldName);

            ModificationWatchpointRequest
            awr = eventRManager.createModificationWatchpointRequest(field);
            awr.putProperty("number", property);

            if (thread != null)
                awr.addThreadFilter(thread);
            awr.setSuspendPolicy(suspendPolicy);

            display("      ModificationWatchpointRequest has been set up");
            return awr;
        } catch ( Exception e ) {
            throw new Failure("** FAILURE to set up ModificationWatchpointRequest **");
        }
    }
}
