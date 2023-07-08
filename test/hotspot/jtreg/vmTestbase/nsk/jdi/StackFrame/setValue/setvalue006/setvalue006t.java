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

package nsk.jdi.StackFrame.setValue.setvalue006;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














//    THIS TEST IS LINE NUMBER SENSITIVE

/**
 * This is a debuggee class.
 */
public class setvalue006t {
    public static void main(String args[]) {
        System.exit(run(args) + Consts.JCK_STATUS_BASE);
    }

    public static int run(String args[]) {
        return new setvalue006t().runIt(args);
    }

    private int runIt(String args[]) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        Log log = argHandler.createDebugeeLog();
        IOPipe pipe = argHandler.createDebugeeIOPipe();

        Thread.currentThread().setName(setvalue006.DEBUGGEE_THRDNAME);

        // dummy local vars used by debugger to provoke InvalidTypeException
        byte    byteVar = Byte.MAX_VALUE;
        short   shortVar = Short.MAX_VALUE;
        int     intVar = Integer.MAX_VALUE;
        long    longVar = Long.MAX_VALUE;
        float   floatVar = Float.MAX_VALUE;
        double  doubleVar = Double.MAX_VALUE;
        char    charVar = Character.MAX_VALUE;
        boolean booleanVar = false;
        String  strVar = "local var";
        DummyType setvalue006tFindMe = new DummyType();

        // Now the debuggee is ready
        pipe.println(setvalue006.COMMAND_READY);
        String cmd = pipe.readln();
        if (cmd.equals(setvalue006.COMMAND_QUIT)) {
            log.complain("Debuggee: exiting due to the command "
                    + cmd);
            return Consts.TEST_PASSED;
        }

        int stopMeHere = 0; // setvalue006.DEBUGGEE_STOPATLINE

        cmd = pipe.readln();
        if (!cmd.equals(setvalue006.COMMAND_QUIT)) {
            log.complain("TEST BUG: unknown debugger command: "
                + cmd);
            return Consts.TEST_FAILED;
        }
        return Consts.TEST_PASSED;
    }
}

// Dummy reference type used by debugger for testing
class DummyType {}
