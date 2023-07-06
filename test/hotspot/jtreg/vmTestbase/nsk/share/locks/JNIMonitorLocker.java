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

package nsk.share.locks;

import nsk.share.Consts;
import nsk.share.Wicket;

/*
 *  Class used for deadlock creation, acquires lock using JNI MonitorEnter
 */
public class JNIMonitorLocker extends DeadlockLocker {
    static {
        try {
            System.loadLibrary("JNIMonitorLocker");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Unexpected UnsatisfiedLinkError on loading library 'JNIMonitorLocker'");
            e.printStackTrace(System.out);
            System.exit(Consts.JCK_STATUS_BASE + Consts.TEST_FAILED);
        }
    }

    public JNIMonitorLocker(Wicket step1, Wicket step2, Wicket readyWicket) {
        super(step1, step2, readyWicket);
    }

    public Object getLock() {
        return this;
    }

    protected native void doLock();
}
