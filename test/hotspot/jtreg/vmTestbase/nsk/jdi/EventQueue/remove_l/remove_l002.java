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

package nsk.jdi.EventQueue.remove_l;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.VMDisconnectedException;
import com.sun.jdi.event.*;
import java.io.*;
import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * The test checks that a VMDisconnectedException thrown by
 * the JDI method <b>com.sun.jdi.request.EventQueue.remove(long)</b>
 * will be preceded by a <code>VMDisconnectEvent</code>
 * after <code>com.sun.jdi.VirtualMachine.dispose()</code> call.
 */
public class remove_l002 {
    public static final int PASSED = 0;
    public static final int FAILED = 2;
    public static final int JCK_STATUS_BASE = 95;
    static final String DEBUGGEE_CLASS =
        "nsk.jdi.EventQueue.remove_l.remove_l002t";
    static final String COMMAND_READY = "ready";
    static final String COMMAND_QUIT = "quit";

    private ArgumentHandler argHandler;
    private Log log;
    private IOPipe pipe;
    private Debugee debuggee;
    private CheckEvents chkEvents;
    private EventQueue eventQ;
    private volatile int tot_res = FAILED;

    public static void main(String argv[]) {
        System.exit(run(argv,System.out) + JCK_STATUS_BASE);
    }

    public static int run(String argv[], PrintStream out) {
        return new remove_l002().runIt(argv, out);
    }

    private int runIt(String args[], PrintStream out) {
        argHandler = new ArgumentHandler(args);
        log = new Log(out, argHandler);
        Binder binder = new Binder(argHandler, log);

        debuggee = binder.bindToDebugee(DEBUGGEE_CLASS);
        debuggee.redirectStderr(log, "remove_l002t.err> ");
        pipe = debuggee.createIOPipe();
        VirtualMachine vm = debuggee.VM();

        eventQ = vm.eventQueue();
        chkEvents = new CheckEvents();
        chkEvents.setDaemon(true);
        chkEvents.start();

        debuggee.resume();
        String cmd = pipe.readln();
        if (!cmd.equals(COMMAND_READY)) {
            log.complain("TEST BUG: unknown debuggee's command: " + cmd);
            tot_res = FAILED;
            return quitDebuggee(FAILED);
        }

        log.display("Invoking VirtualMachine.dispose() ...");
        vm.dispose();

        return quitDebuggee(PASSED);
    }

    class CheckEvents extends Thread {
        public void run() {
            boolean gotVMDisconnect = false; // VMDisconnectEvent is received
            boolean gotVMDeath = false; // VMDeathEvent is received
            EventSet eventSet = null;

            log.display("CheckEvents: starts JDI events processing");
            while (true) {
                try {
                    eventSet = eventQ.remove(argHandler.getWaitTime()*30000);
                    if (eventSet == null) {
                        log.display("CheckEvents: specified time for the next available event has elapsed");
                        continue;
                    }
                    EventIterator eventIter = eventSet.eventIterator();
                    while (eventIter.hasNext()) {
                        Event event = eventIter.nextEvent();
                        if (event instanceof VMDisconnectEvent) {
                            gotVMDisconnect = true;
                            log.display("CheckEvents: got expected VMDisconnectEvent");
                            break;
                        } else if (event instanceof VMStartEvent) {
                            log.display("CheckEvents: got VMStartEvent");
                        } else if (event instanceof VMDeathEvent) {
                            log.display("CheckEvents: got VMDeathEvent");
                            gotVMDeath = true;
                        }
                        if (!gotVMDisconnect && !gotVMDeath &&
                                eventSet.suspendPolicy() !=
                                    EventRequest.SUSPEND_NONE) {
                            log.display("CheckEvents: calling EventSet.resume() ...");
                            eventSet.resume();
                        }
                    }
                } catch(InterruptedException e) {
                    log.complain("TEST INCOMPLETE: caught " + e);
                    tot_res = FAILED;
                } catch(VMDisconnectedException e) {
                    if (gotVMDisconnect) {
                        log.display("\nCHECK PASSED: caught expected VMDisconnectedException preceded by a VMDisconnectEvent\n");
                        tot_res = PASSED;
                    } else {
                        log.complain("\nTEST FAILED: caught VMDisconnectedException without preceding VMDisconnectEvent\n");
                        e.printStackTrace();
                        tot_res = FAILED;
                    }
                    break;
                }
            }
            log.display("CheckEvents: stopped JDI events processing");
        }
    }

    private int quitDebuggee(int stat) {
        if (chkEvents != null) {
            try {
                if (chkEvents.isAlive())
                    chkEvents.join(argHandler.getWaitTime()*60000);
            } catch (InterruptedException e) {
                log.complain("TEST INCOMPLETE: caught InterruptedException "
                    + e);
                tot_res = FAILED;
            }
            if (chkEvents.isAlive()) {
                if (stat == PASSED) {
                    log.complain("TEST FAILED: CheckEvents thread is still alive,\n"
                        + "\tbut it should stop JDI events processing and exit");
                    chkEvents.interrupt();
                    tot_res = FAILED;
                }
            }
        }

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
}