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

package nsk.jdi.ThreadReference.forceEarlyReturn.forceEarlyReturn008;

import nsk.share.jdi.*;

//    THIS TEST IS LINE NUMBER SENSITIVE
public class forceEarlyReturn008a extends AbstractJDIDebuggee {
    public static void main(String args[]) {
        new forceEarlyReturn008a().doTest(args);
    }

    class TestThread extends Thread {
        public void run() {
            methodForEarlyReturn();
        }

        public int methodForEarlyReturn() {
            int i = 0; // breakpointLine

            // dummy code, test thread shouldn't execute this code and StepEvents shouldn't be generated for this code
            for (i = 0; i < 100; i++) {
                int j = 0;
                j = j + i;
            }

            return 0;
        }
    }

    public static final String testThreadName = "forceEarlyReturn008a_TestThread";

    // start TestThread
    public static final String COMMAND_START_TEST_THREAD = "startTestThread";

    // wait when TestThread finish execution
    public static final String COMMAND_JOIN_TEST_THREAD = "joinTestThread";

    public static final String breakpointMethod = "methodForEarlyReturn";

    public static final int breakpointLine = 39;

    private TestThread testThread = new TestThread();

    public boolean parseCommand(String command) {
        if (super.parseCommand(command))
            return true;

        if (command.equals(COMMAND_START_TEST_THREAD)) {
            testThread.setName(testThreadName);
            testThread.start();

            return true;
        } else if (command.equals(COMMAND_JOIN_TEST_THREAD)) {
            try {
                testThread.join();
            } catch (InterruptedException e) {
                setSuccess(false);
                e.printStackTrace(log.getOutStream());
                log.complain("Unexpected exception: " + e);
            }

            return true;
        }

        return false;
    }

}
