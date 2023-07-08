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

package nsk.jvmti.scenarios.hotswap.HS203.hs203t004;
















public class MyThread extends Thread {

    public static volatile boolean stop = true;

    public int threadState = 100;

    public final static int run_for = 10000;

    public MyThread() {
        System.out.println(" MyThread :: MyThread().");
    }

    public void run() {
        doThisFunction();
        doTask3();
    }

    public void doThisFunction() {
        System.out.println(" MyThread.doThisFunction().");

        for (int i = 0; i < run_for; i++) {
            doTask2();
        }

        // it would wait for some time.
        while (stop);

        System.out.println(" End of doThisFunction.");
    }

    /**
     * some method would be compiled / inlined.
     */
    public void doTask2() {
        int p = 0;
        for (int i = 0; i < 1; i++) {
            threadState++;
            p++;
        }
    }

    public void doTask3() {
        // take some time
        for (int i = 0; i < run_for; i++) {
            doTask2();
        }
    }

}
