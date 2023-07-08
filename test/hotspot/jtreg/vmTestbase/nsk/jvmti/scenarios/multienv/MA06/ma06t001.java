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

package nsk.jvmti.scenarios.multienv.MA06;

import java.io.PrintStream;
import nsk.share.*;
import nsk.share.jvmti.*;

public class ma06t001 extends DebugeeClass {

    // run test from command line
    public static void main(String argv[]) {
        argv = nsk.share.jvmti.JVMTITest.commonInit(argv);

        // JCK-compatible exit
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    // run test from JCK-compatible environment
    public static int run(String argv[], PrintStream out) {
        return new ma06t001().runIt(argv, out);
    }

    /* =================================================================== */

    // scaffold objects
    ArgumentHandler argHandler = null;
    int status = Consts.TEST_PASSED;
    Log log = null;
    long timeout = 0;

    // tested class
    ma06t001a t = null;

    // run debuggee
    public int runIt(String argv[], PrintStream out) {
        argHandler = new ArgumentHandler(argv);
        log = new Log(out, argHandler);
        timeout = argHandler.getWaitTime() * 60 * 1000;

        t = new ma06t001a();
        int value = t.check();
        log.display("Before redefining: 0x" + Integer.toHexString(value));
        if (value != 0x12345678) {
            log.complain("Wrong value: 0x" + Integer.toHexString(value) +
                ", expected: 0x" + Integer.toHexString(0x12345678));
            status = Consts.TEST_FAILED;
        }

        // redefining ma06t001a class in the 1st agent
        status = checkStatus(status);
        value = t.check();
        log.display("After redefinition #1: 0x" + Integer.toHexString(value));
        if (value != 1) {
            log.complain("Wrong value: 0x" + Integer.toHexString(value) +
                ", expected: 0x" + Integer.toHexString(1));
            status = Consts.TEST_FAILED;
        }

        status = checkStatus(status);

        // redefining ma06t001a class in the 2nd agent
        status = checkStatus(status);
        value = t.check();
        log.display("After redefinition #2: 0x" + Integer.toHexString(value));
        if (value != 2) {
            log.complain("Wrong value: 0x" + Integer.toHexString(value) +
                ", expected: 0x" + Integer.toHexString(2));
            status = Consts.TEST_FAILED;
        }

        log.display("Debugee finished");
        return status;
    }
}

/* =================================================================== */

class ma06t001a {

    public int check() {
        return 0x12345678;
    }
}
