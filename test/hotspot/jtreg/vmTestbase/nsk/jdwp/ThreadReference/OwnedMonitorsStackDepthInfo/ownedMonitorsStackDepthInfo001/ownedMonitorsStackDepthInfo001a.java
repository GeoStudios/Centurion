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

package nsk.jdwp.ThreadReference.OwnedMonitorsStackDepthInfo.ownedMonitorsStackDepthInfo001;

import java.util.*;
import nsk.share.TestBug;
import nsk.share.jdwp.*;
import nsk.share.locks.LockingThread;

public class ownedMonitorsStackDepthInfo001a extends AbstractJDWPDebuggee {
    public static Object monitor1;

    public static int depth1;

    public static Object monitor2;

    public static int depth2;

    public static Object monitor3;

    public static int depth3;

    public static Object monitor4;

    public static int depth4;

    public static Object monitor5;

    public static int depth5;

    public static Object monitor6;

    public static int depth6;

    public static int expectedMonitorCounts = 6;

    public static String lockingThreadName = "LockingThread";

    public static LockingThread lockingThread;

    protected void init(String args[]) {
        super.init(args);

        List<String> locksTypes = new ArrayList<String>();

        // LockingThread acquire 6 different monitors
        locksTypes.add(LockingThread.SYNCHRONIZED_METHOD);
        locksTypes.add(LockingThread.JNI_MONITOR_ENTER);
        locksTypes.add(LockingThread.SYNCHRONIZED_THREAD_METHOD);
        locksTypes.add(LockingThread.SYNCHRONIZED_STATIC_THREAD_METHOD);
        locksTypes.add(LockingThread.SYNCHRONIZED_OBJECT_BLOCK);
        locksTypes.add(LockingThread.SYNCHRONIZED_STATIC_METHOD);

        lockingThread = new LockingThread(log, locksTypes);
        lockingThread.setName(lockingThreadName);
        lockingThread.start();
        lockingThread.waitState();

        // get information about acquired monitors and save it in static fields to simplify
        // access to this information for debugger
        LockingThread.DebugMonitorInfo monitorsInfo[] = lockingThread.getMonitorsInfo(true);

        if (monitorsInfo.length != 6) {
            throw new TestBug("Locking thread return invalid monitors count: " + monitorsInfo.length + ", expected value is " + 6);
        }

        monitor1 = monitorsInfo[0].monitor;
        depth1 = monitorsInfo[0].stackDepth;

        monitor2 = monitorsInfo[1].monitor;
        depth2 = monitorsInfo[1].stackDepth;

        monitor3 = monitorsInfo[2].monitor;
        depth3 = monitorsInfo[2].stackDepth;

        monitor4 = monitorsInfo[3].monitor;
        depth4 = monitorsInfo[3].stackDepth;

        monitor5 = monitorsInfo[4].monitor;
        depth5 = monitorsInfo[4].stackDepth;

        monitor6 = monitorsInfo[5].monitor;
        depth6 = monitorsInfo[5].stackDepth;
    }

    public static void main(String args[]) {
        new ownedMonitorsStackDepthInfo001a().doTest(args);
    }
}
