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

package nsk.jdi.MethodExitEvent.returnValue.returnValue004;


import nsk.share.TestBug;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














/*
 * Debuggee class, handles commands for starting and stoping ForceEarlyReturnTestThread.
 */
public class returnValue004a extends AbstractJDIDebuggee {

    public static void main(String args[]) {
        new returnValue004a().doTest(args);
    }

    public static final String COMMAND_START_TEST_THREAD = "COMMAND_START_TEST_THREAD";

    public static final String COMMAND_STOP_TEST_THREAD = "COMMAND_STOP_TEST_THREAD";

    public String[] doInit(String args[]) {
        args = super.doInit(args);

        try {
            // load ForceEarlyReturnTestThread class to let debugger get instance of ReferenceType for this class
            Class.forName(NativeMethodsTestThread.class.getName());
        } catch (Throwable t) {
            setSuccess(false);
            System.out.println("Unexpected exception during initialization: " + t);
            t.printStackTrace();
            throw new TestBug("Unexpected exception during initialization: " + t);
        }

        return args;
    }

    private NativeMethodsTestThread testThread;

    public boolean parseCommand(String command) {
        if (super.parseCommand(command))
            return true;

        if (command.equals(COMMAND_START_TEST_THREAD)) {

            if (testThread != null)
                throw new TestBug("Thread is already created");

            testThread = new NativeMethodsTestThread(log, false, 1);
            testThread.start();
            testThread.startExecuion();

            return true;
        } else if (command.equals(COMMAND_STOP_TEST_THREAD)) {

            if (testThread == null)
                throw new TestBug("Thread isn't created");

            testThread.stopExecution();
            try {
                testThread.join();
            } catch (InterruptedException e) {
                setSuccess(false);
                log.complain("Unexpected exception: " + e);
                e.printStackTrace(log.getOutStream());
            }

            return true;
        }

        return false;
    }
}
