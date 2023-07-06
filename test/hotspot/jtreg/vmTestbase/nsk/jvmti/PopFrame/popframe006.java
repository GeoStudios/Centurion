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

package nsk.jvmti.PopFrame;

import java.io.PrintStream;

public class popframe006 {

    final static int FAILED = 2;
    final static int JCK_STATUS_BASE = 95;

    static {
        try {
            System.loadLibrary("popframe006");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load popframe006 library");
            System.err.println("java.library.path:"
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    native static void getReady(Thread thr);
    native static int getRes();

    public static void main(String args[]) {
        args = nsk.share.jvmti.JVMTITest.commonInit(args);

        // produce JCK-like exit status.
        System.exit(run(args, System.out) + JCK_STATUS_BASE);
    }

    public static int run(String args[], PrintStream out) {
        TestThread thr = new TestThread();
        getReady(thr);

        thr.start();
        try {
            thr.join();
        } catch (InterruptedException ex) {
            out.println("# Unexpected " + ex);
            return FAILED;
        }

        return getRes();
    }

    static class TestThread extends Thread {
        public void run() {
            A();
        }

        static void A() {
            B();
        }

        static void B() {
            C();
        }

        static void C() {
        }
    }
}
