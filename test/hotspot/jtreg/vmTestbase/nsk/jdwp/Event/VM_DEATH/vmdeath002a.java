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

package nsk.jdwp.Event.VM_DEATH;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdwp.*;
import java.io.*;

/**
 * This class represents debuggee part in the test.
 */
public class vmdeath002a {

    public static void main(String args[]) {
        vmdeath002a _vmdeath002a = new vmdeath002a();
        System.exit(vmdeath002.JCK_STATUS_BASE + _vmdeath002a.runIt(args, System.err));
    }

    public int runIt(String args[], PrintStream out) {
        //make log for debugee messages
        ArgumentHandler argumentHandler = new ArgumentHandler(args);
        Log log = new Log(out, argumentHandler);

        // exit debugee
        log.display("Debugee PASSED");
        return vmdeath002.PASSED;
    }

}