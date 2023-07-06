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

package nsk.jdi.ObjectReference.invokeMethod;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














//    THIS TEST IS LINE NUMBER SENSITIVE

/**
 * This is a debuggee class.
 */
public class invokemethod008t {
    static Log log;

    public static void main(String args[]) {
        System.exit(run(args) + Consts.JCK_STATUS_BASE);
    }

    public static int run(String args[]) {
        return new invokemethod008t().runIt(args);
    }

    private int runIt(String args[]) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        IOPipe pipe = argHandler.createDebugeeIOPipe();
        log = argHandler.createDebugeeLog();

        // force debuggee VM to load dummy classes
        invokemethod008tDummyClass invokemethod008tdummyCls =
            new invokemethod008tDummyClass();
        invokemethod008tDummySuperClass invokemethod008tdummySCls =
            new invokemethod008tDummySuperClass();
        invokemethod008tDummySuperSuperClass invokemethod008tdummySSCls =
            new invokemethod008tDummySuperSuperClass();

        Thread.currentThread().setName(invokemethod008.DEBUGGEE_THRNAME);

        pipe.println(invokemethod008.COMMAND_READY);
        String cmd = pipe.readln();
        if (cmd.equals(invokemethod008.COMMAND_QUIT)) {
            log.complain("Debuggee: exiting due to the command "
                + cmd);
            return Consts.TEST_PASSED;
        }

        int stopMeHere = 0; // invokemethod008.DEBUGGEE_STOPATLINE

        cmd = pipe.readln();
        if (!cmd.equals(invokemethod008.COMMAND_QUIT)) {
            log.complain("TEST BUG: unknown debugger command: "
                + cmd);
            System.exit(Consts.JCK_STATUS_BASE +
                Consts.TEST_FAILED);
        }
        return Consts.TEST_PASSED;
    }
}

// Dummy super super class used to check the flag
// ObjectReference.INVOKE_NONVIRTUAL in the debugger
class invokemethod008tDummySuperSuperClass {
    protected long longProtMeth(long l) {
        invokemethod008t.log.display("invokemethod008tDummySuperSuperClass: longProtMeth invoked");
        return (l - 2);
    }

    long longMeth(long l) {
        invokemethod008t.log.display("invokemethod008tDummySuperSuperClass: longMeth invoked");
        return (l - 2);
    }
}

// Dummy super class used to check the flag
// ObjectReference.INVOKE_NONVIRTUAL in the debugger
class invokemethod008tDummySuperClass extends invokemethod008tDummySuperSuperClass {
    protected long longProtMeth(long l) {
        invokemethod008t.log.display("invokemethod008tDummySuperClass: longProtMeth invoked");
        return (l - 1);
    }

    long longMeth(long l) {
        invokemethod008t.log.display("invokemethod008tDummySuperClass: longMeth invoked");
        return (l - 1);
    }
}

// Dummy class used to check the flag
// ObjectReference.INVOKE_NONVIRTUAL in the debugger
class invokemethod008tDummyClass extends invokemethod008tDummySuperClass {
    protected long longProtMeth(long l) {
        invokemethod008t.log.display("invokemethod008tDummyClass: longProtMeth invoked");
        return l;
    }

    long longMeth(long l) {
        invokemethod008t.log.display("invokemethod008tDummyClass: longMeth invoked");
        return l;
    }
}
