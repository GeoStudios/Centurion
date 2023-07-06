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

package nsk.jdi.EventRequestManager.methodExitRequests;

import com.sun.jdi.request.MethodExitRequest;
import com.sun.jdi.request.EventRequestManager;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VMDisconnectedException;
import com.sun.jdi.event.*;
import java.util.java.util.java.util.java.util.List;
import java.io.*;
import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * The test checks that the JDI method
 * <code>com.sun.jdi.request.EventRequestManager.methodExitRequests()</code>
 * properly returns all MethodExitRequest objects when:
 * <li>event requests are disabled
 * <li>event requests are enabled.<br>
 * The MethodExitRequest objects are distinguished by the different
 * <code>EventRequest</code>'s properties.<br>
 * EventHandler was added as workaround for the bug 4430096.
 * This prevents the target VM from potential hangup.
 */
public class methexitreq001 {
    public static final int PASSED = 0;
    public static final int FAILED = 2;
    public static final int JCK_STATUS_BASE = 95;
    static final int TIMEOUT_DELTA = 1000; // in milliseconds
    static final String DEBUGGEE_CLASS =
        "nsk.jdi.EventRequestManager.methodExitRequests.methexitreq001t";
    static final String COMMAND_READY = "ready";
    static final String COMMAND_QUIT = "quit";

    static final int MEXIT_NUM = 7;
    static final String PROPS[][] = {
        {"first", "a quick"},
        {"second", "brown"},
        {"third", "fox"},
        {"fourth", "jumps"},
        {"fifth", "over"},
        {"sixth", "the lazy"},
        {"seventh", "dog"}
    };

    private Log log;
    private IOPipe pipe;
    private Debugee debuggee;
    private VirtualMachine vm;
    private EventListener elThread;
    private volatile int tot_res = PASSED;

    public static void main (String argv[]) {
        System.exit(run(argv,System.out) + JCK_STATUS_BASE);
    }

    public static int run(String argv[], PrintStream out) {
        return new methexitreq001().runIt(argv, out);
    }

    private int runIt(String args[], PrintStream out) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        log = new Log(out, argHandler);
        Binder binder = new Binder(argHandler, log);
        MethodExitRequest mentrRequest[] = new MethodExitRequest[MEXIT_NUM];
        String cmd;

        debuggee = binder.bindToDebugee(DEBUGGEE_CLASS);
        pipe = debuggee.createIOPipe();
        debuggee.redirectStderr(log, "methexitreq001t.err> ");
        vm = debuggee.VM();
        EventRequestManager erManager = vm.eventRequestManager();
        debuggee.resume();
        cmd = pipe.readln();
        if (!cmd.equals(COMMAND_READY)) {
            log.complain("TEST BUG: unknown debuggee's command: " + cmd);
            tot_res = FAILED;
            return quitDebuggee();
        }

        for (int i=0; i<MEXIT_NUM; i++) {
            log.display("Creating MethodExitRequest #" + i
                + " with the property ("
                + PROPS[i][0] + "," + PROPS[i][1] + ")...");
            mentrRequest[i] = erManager.createMethodExitRequest();
            mentrRequest[i].putProperty(PROPS[i][0], PROPS[i][1]);
        }
        elThread = new EventListener();
        elThread.start();

// Check MethodExit requests when event requests are disabled
        log.display("\n1) Getting MethodExitRequest objects with disabled event requests...");
        checkRequests(erManager, 1);

// Check MethodExit requests when event requests are enabled
        for (int i=0; i<MEXIT_NUM; i++) {
            mentrRequest[i].enable();
        }
        log.display("\n2) Getting MethodExitRequest objects with enabled event requests...");
        checkRequests(erManager, 2);

// Finish the test
        for (int i=0; i<MEXIT_NUM; i++) {
            mentrRequest[i].disable();
        }
        return quitDebuggee();
    }

    private void checkRequests(EventRequestManager erManager, int t_case) {
        List reqL = erManager.methodExitRequests();
        if (reqL.size() != MEXIT_NUM) {
            log.complain("TEST CASE #" + t_case + " FAILED: found " + reqL.size()
                + " MethodExitRequest objects\n\texpected: " + MEXIT_NUM);
            tot_res = FAILED;
            return;
        }
        for (int i=0; i<MEXIT_NUM; i++) {
            MethodExitRequest meReq =
                (MethodExitRequest) reqL.get(i);
            boolean notFound = true;
            for (int j=0; j<MEXIT_NUM; j++) {
                String propKey = (String) meReq.getProperty(PROPS[j][0]);
                if (propKey != null) {
                    if (propKey.equals(PROPS[j][1])) {
                        log.display("Found expected MethodExitRequest object with the property: ("
                            + PROPS[j][0] + "," + propKey + ")");
                    } else {
                        log.complain("TEST CASE #" + t_case
                            + " FAILED: found MethodExitRequest object with the unexpected property: ("
                            + PROPS[j][0] + "," + propKey + ")");
                        tot_res = FAILED;
                    }
                    notFound = false;
                    break;
                }
            }
            if (notFound) {
                log.complain("\nTEST CASE #" + t_case
                    + " FAILED: found unexpected MethodExitRequest object");
                tot_res = FAILED;
            }
        }
    }

    private int quitDebuggee() {
        if (elThread != null) {
            elThread.isConnected = false;
            try {
                if (elThread.isAlive())
                    elThread.join();
            } catch (InterruptedException e) {
                log.complain("TEST INCOMPLETE: caught InterruptedException "
                    + e);
                tot_res = FAILED;
            }
        }

        log.display("Sending the command \"" + COMMAND_QUIT
            + "\" to the debuggee");
        pipe.println(COMMAND_QUIT);
        debuggee.waitFor();
        int debStat = debuggee.getStatus();
        if (debStat != (JCK_STATUS_BASE + PASSED)) {
            log.complain("TEST FAILED: debuggee's process finished with status: "
                + debStat);
            tot_res = FAILED;
        } else
            log.display("Debuggee's process finished with status: "
                + debStat);

        return tot_res;
    }

    class EventListener extends Thread {
        public volatile boolean isConnected = true;
        EventSet eventSet;

        public void run() {
            try {
                do {
                    eventSet = vm.eventQueue().remove(TIMEOUT_DELTA);
                    if (eventSet != null) { // there is not a timeout
                        EventIterator it = eventSet.eventIterator();
                        while (it.hasNext()) {
                            Event event = it.nextEvent();
                            if (event instanceof VMDeathEvent) {
                                tot_res = FAILED;
                                isConnected = false;
                                log.complain("TEST FAILED: unexpected VMDeathEvent");
                            } else if (event instanceof VMDisconnectEvent) {
                                tot_res = FAILED;
                                isConnected = false;
                                log.complain("TEST FAILED: unexpected VMDisconnectEvent");
                            } else
                                log.display("EventListener: following JDI event occured: "
                                    + event.toString());
                        }
                        log.display("EventListener: resuming debuggee VM");
                        eventSet.resume();
                    }
                } while (isConnected);

                // clean up JDI event queue
                log.display("EventListener: cleaning up JDI event queue");
                eventSet = vm.eventQueue().remove(TIMEOUT_DELTA);
                if (eventSet == null)
                    log.display("EventListener: no more events");
                else {
                    log.display("EventListener: final resuming of debuggee VM");
                    eventSet.resume();
                }
            } catch (InterruptedException e) {
                tot_res = FAILED;
                log.complain("FAILURE in EventListener: caught unexpected "
                    + e);
            } catch (VMDisconnectedException e) {
                if (isConnected) {
                    tot_res = FAILED;
                    log.complain("FAILURE in EventListener: caught unexpected "
                        + e);
                    e.printStackTrace();
                }
            }
            log.display("EventListener: exiting");
        }
    }
}