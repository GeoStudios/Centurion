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

package nsk.monitoring.ThreadInfo.isInNative;


import java.lang.management.*;
import java.io.*;
import nsk.share.*;














public class isinnative001 {
    public static void main(String[] argv) {
        System.exit(Consts.JCK_STATUS_BASE + run(argv, System.out));
    }

    public static int run(String[] argv, PrintStream out) {
        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        Thread thread = Thread.currentThread();
        long id = thread.getId();
        ThreadInfo info = mbean.getThreadInfo(id, Integer.MAX_VALUE);
        boolean isInNative = info.isInNative();

        if (isInNative) {
            out.println("TEST FAILED");
            out.println("ThreadInfo.isInNative() returned true, in pure java "
                      + "thread.");
            return Consts.TEST_FAILED;
        }
        return Consts.TEST_PASSED;
    }
}
