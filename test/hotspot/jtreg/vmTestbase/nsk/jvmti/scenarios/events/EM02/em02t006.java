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

package nsk.jvmti.scenarios.events.EM02;

import java.io.PrintStream;
import nsk.share.*;
import nsk.share.jvmti.*;

public class em02t006 extends DebugeeClass {

    // run test from command line
    public static void main(String argv[]) {
        argv = nsk.share.jvmti.JVMTITest.commonInit(argv);

        // JCK-compatible exit
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    // run test from JCK-compatible environment
    public static int run(String argv[], PrintStream out) {
        return new em02t006().runIt(argv, out);
    }

    /* =================================================================== */

    static final int STEP_NUMBER = 3;
    static final int OBJECT_NUMBER = 100;

    // run debuggee
    public int runIt(String args[], PrintStream out) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        Log.Logger logger = new Log.Logger(new Log(out, argHandler),"debuggee> ");
        int status = Consts.TEST_PASSED;

        int k;
        for (int i = 0; i < STEP_NUMBER; i++) {
            for (int j = 0; j < OBJECT_NUMBER; j++) {
                k = j + 1;
                setTag(new Object(), k);
            }

            logger.display("ObjectFree:: Provoke JVMTI_EVENT_OBJECT_FREE");
            ClassUnloader.eatMemory();

            if (checkStatus(Consts.TEST_PASSED) == Consts.TEST_FAILED) {
                status = Consts.TEST_FAILED;
            }

        }
        return status;
    }

    native boolean setTag(Object object, long tag);

}
