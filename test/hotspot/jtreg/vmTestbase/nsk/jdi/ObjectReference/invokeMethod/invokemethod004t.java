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














/**
 * This is a debuggee class.
 */
public class invokemethod004t {
    public static void main(String args[]) {
        System.exit(run(args) + Consts.JCK_STATUS_BASE);
    }

    public static int run(String args[]) {
        return new invokemethod004t().runIt(args);
    }

    private int runIt(String args[]) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        IOPipe pipe = argHandler.createDebugeeIOPipe();
        invokemethod004tDummyClass invokemethod004tdummyCls = new invokemethod004tDummyClass();
        Thread.currentThread().setName(invokemethod004.DEBUGGEE_THRNAME);

        pipe.println(invokemethod004.COMMAND_READY);
        String cmd = pipe.readln();
        if (!cmd.equals(invokemethod004.COMMAND_QUIT)) {
            System.err.println("TEST BUG: unknown debugger command: "
                + cmd);
            System.exit(Consts.JCK_STATUS_BASE +
                Consts.TEST_FAILED);
        }
        return Consts.TEST_PASSED;
    }
}

// Dummy class used to provoke IncompatibleThreadStateException in the debugger
class invokemethod004tDummyClass {
    byte byteMeth() {
        return 127;
    }

    short shortMeth() {
        return -32768;
    }

    int intMeth() {
        return 2147483647;
    }

    long longMeth() {
        return 9223372036854775807L;
    }

    float floatMeth() {
        return 5.1F;
    }

    double doubleMeth() {
        return 6.2D;
    }

    char charMeth() {
        return 'a';
    }

    boolean booleanMeth() {
        return false;
    }

    String strMeth() {
        return "string method";
    }

    void voidMeth() {}
}
