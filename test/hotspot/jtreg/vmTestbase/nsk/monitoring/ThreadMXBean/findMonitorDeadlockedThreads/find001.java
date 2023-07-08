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

package nsk.monitoring.ThreadMXBean.findMonitorDeadlockedThreads;

import java.io.*;
import nsk.share.*;
import nsk.monitoring.share.*;

public class find001 {
    public static void main(String[] argv) {
        System.exit(Consts.JCK_STATUS_BASE + run(argv, System.out));
    }

    public static int run(String[] argv, PrintStream out) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        Log log = new Log(out, argHandler);
        ThreadMonitor monitor = Monitor.getThreadMonitor(log, argHandler);
        long id = Thread.currentThread().getId();
        long[] ids = monitor.findMonitorDeadlockedThreads();

        if (ids == null) {
            log.display("findCircularBlockedThread() returned null");
            return Consts.TEST_PASSED;
        }

        if (ids.length == 0) {
            log.display("findCircularBlockedThread() returned array of length "
                      + "0");
            return Consts.TEST_PASSED;
        }

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == id) {
                log.complain("TEST FAILED");
                log.complain("findCircularBlockedThread() returned current "
                           + "thread (id = " + id + ")");
                return Consts.TEST_FAILED;
            }
        }
        return Consts.TEST_PASSED;
    }
}
