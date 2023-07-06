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

package nsk.jdi.ThreadReference.forceEarlyReturn.forceEarlyReturn004;

import nsk.share.Consts;
import nsk.share.jdi.*;

public class forceEarlyReturn004a extends AbstractJDIDebuggee {
    static {
        try {
            System.loadLibrary("forceEarlyReturn004a");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("UnsatisfiedLinkError when load library 'forceEarlyReturn004a'");
            e.printStackTrace(System.out);
            System.exit(Consts.JCK_STATUS_BASE + Consts.TEST_FAILED);
        }
    }

    public static void main(String args[]) {
        new forceEarlyReturn004a().doTest(args);
    }

    public final static String testThreadInNativeName = "forceEarlyReturn004aTestThreadInNative";

    public final static String COMMAND_STOP_THREAD_IN_NATIVE = "stopInNative";

    public boolean parseCommand(String command) {
        if (super.parseCommand(command))
            return true;

        if (command.equals(COMMAND_STOP_THREAD_IN_NATIVE)) {
            stopThreadInNative();
            return true;
        } else

            return false;
    }

    private Thread testThreadInNative;

    private void stopThreadInNative() {
        testThreadInNative = new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName(testThreadInNativeName);
                log.display("Enter native method");
                nativeMethod(forceEarlyReturn004a.this);
            }
        });

        testThreadInNative.start();

        while (!threadInNative)
            Thread.yield();
    }

    public volatile boolean threadInNative;

    private static native int nativeMethod(Object object);
}
