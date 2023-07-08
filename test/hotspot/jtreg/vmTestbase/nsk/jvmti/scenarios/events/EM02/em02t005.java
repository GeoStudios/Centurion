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














public class em02t005 extends DebugeeClass {

    // run test from command line
    public static void main(String argv[]) {
        argv = nsk.share.jvmti.JVMTITest.commonInit(argv);

        // JCK-compatible exit
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    // run test from JCK-compatible environment
    public static int run(String argv[], PrintStream out) {
        return new em02t005().runIt(argv, out);
    }

    /* =================================================================== */

    static final int STEP_NUMBER = 3;
    static final int NUMBER_OF_INVOCATIONS = 1000;
    static Log.Logger logger;
    static final String PACKAGE_NAME = "nsk.jvmti.scenarios.events.EM02";
    static final String TESTED_CLASS_NAME = PACKAGE_NAME + ".em02t005a";

    // run debuggee
    public int runIt(String args[], PrintStream out) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        logger = new Log.Logger(new Log(out, argHandler),"debuggee> ");
        int status = Consts.TEST_PASSED;

        String path;
        if (args.length == 0) {
            path = "loadclass";
        } else {
            path = args[0];
        }

        ClassUnloader unloader = new ClassUnloader();
        Class cls;

        for (int i = 0; i < STEP_NUMBER; i++) {

            try {
                unloader.loadClass(TESTED_CLASS_NAME, path);
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
                return Consts.TEST_FAILED;
            }

            cls = unloader.getLoadedClass();
            for(int j = 0; j < NUMBER_OF_INVOCATIONS; j++) {
                try {
                    cls.newInstance();
                } catch (Exception e) {
                    logger.complain("Unexpedcted " + e);
                    status = Consts.TEST_FAILED;
                }
            }

            if (checkStatus(Consts.TEST_PASSED) == Consts.TEST_FAILED) {
                status = Consts.TEST_FAILED;
            }

            cls = null;
            if (!unloader.unloadClass()) {
                logger.complain("WARNING:: Class couldn't be unloaded");
            }

        }

        return status;
    }

}
