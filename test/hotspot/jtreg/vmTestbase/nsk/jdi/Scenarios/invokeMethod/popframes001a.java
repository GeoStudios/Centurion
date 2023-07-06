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

package nsk.jdi.Scenarios.invokeMethod;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import java.io.*;














/**
 *  <code>popframes001a</code> is deugee's part of the popframes001.
 */

public class popframes001a {

    volatile public static boolean finishIt = false;

    public static void main(String argv[]) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        Log log = new Log(System.out, argHandler);

        popframes001b.loadClass = true;

        while (!finishIt) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }

        log.display("completed succesfully.");
        System.exit(Consts.TEST_PASSED + Consts.JCK_STATUS_BASE);
    }
}

class popframes001b {
    public final static int INITIAL_VALUE        = 0;

    public static boolean loadClass = false;
    public static int flag = INITIAL_VALUE;

    public final static String methodName = "runIt";
    public final static String methodNameCaller = "runItCaller";
    public final static String flagName = "flag";

    public static void runIt() {
        flag = INITIAL_VALUE;
    }

    // We need to call runIt() from a java function.
    // This is because jvmti function popFrame() requires that
    // both calling and called methods are java functions.
    public static void runItCaller() {
        runIt();
    }
}
