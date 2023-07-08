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

package nsk.jdi.EventRequestManager.createModificationWatchpointRequest;


import com.sun.jdi.VirtualMachine;
import com.sun.jdi.Field;
import com.sun.jdi.request.ModificationWatchpointRequest;
import com.sun.jdi.request.EventRequestManager;
import com.sun.jdi.VMMismatchException;
import java.io.*;
import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














/**
 * The test checks that the JDI method
 * <b>com.sun.jdi.request.EventRequestManager.createModificationWatchpointRequest()</b>
 * properly throws <code>NullPointerException</code> - if field is null.
 */
public class crmodwtchpreq002 {
    public static final int PASSED = 0;
    public static final int FAILED = 2;
    public static final int JCK_STATUS_BASE = 95;
    static final String DEBUGGEE_CLASS =
        "nsk.jdi.EventRequestManager.createModificationWatchpointRequest.crmodwtchpreq002t";
    static final String COMMAND_READY = "ready";
    static final String COMMAND_QUIT = "quit";

    private ArgumentHandler argHandler;
    private Log log;
    private IOPipe pipe;
    private Debugee debuggee;

    public static void main (String argv[]) {
        System.exit(run(argv,System.out) + JCK_STATUS_BASE);
    }

    public static int run(String argv[], PrintStream out) {
        return new crmodwtchpreq002().runIt(argv, out);
    }

    private int runIt(String args[], PrintStream out) {
        argHandler = new ArgumentHandler(args);
        log = new Log(out, argHandler);
        Binder binder = new Binder(argHandler, log);
        ModificationWatchpointRequest awpRequest;
        String cmd;
        Field fld = null;

        debuggee = binder.bindToDebugee(DEBUGGEE_CLASS);
        pipe = debuggee.createIOPipe();
        debuggee.redirectStderr(log, "crmodwtchpreq002t.err> ");
        VirtualMachine vm = debuggee.VM();
        EventRequestManager erManager = vm.eventRequestManager();
        debuggee.resume();
        cmd = pipe.readln();
        if (!cmd.equals(COMMAND_READY)) {
            log.complain("TEST BUG: unknown debuggee's command: "
                + cmd);
            return quitDebuggee(FAILED);
        }

        if ( !vm.canWatchFieldModification()  ) {
            log.display("  TEST CANCELLED due to:  vm.canWatchFieldModification() == false");
            return quitDebuggee(PASSED);
        }

// Trying to create ModificationWatchpointRequest for a null Field parameter
        try {
            awpRequest =
                erManager.createModificationWatchpointRequest(fld);
        } catch (NullPointerException e) {
            log.display("TEST PASSED: EventRequestManager.createModificationWatchpointRequest() throws expected "
                + e);
            return quitDebuggee(PASSED);
        } catch(VMMismatchException e) {
            log.complain("TEST FAILED: EventRequestManager.createModificationWatchpointRequest() throws unexpected "
                + e + "\n\tbut it should throw NullPointerException for a null field");
            return quitDebuggee(FAILED);
        } catch(UnsupportedOperationException e) { // specified only in jdk1.4
            log.complain("WARNING: test has no result. EventRequestManager.createModificationWatchpointRequest() throws "
                + e);
            return quitDebuggee(PASSED);
        }
        log.complain("TEST FAILED: EventRequestManager.createModificationWatchpointRequest() successfully done,\n\t"
            + "but it should throw NullPointerException for a null field");
        return quitDebuggee(FAILED);
    }

    private int quitDebuggee(int stat) {
        pipe.println(COMMAND_QUIT);
        debuggee.waitFor();
        return stat;
    }
}
