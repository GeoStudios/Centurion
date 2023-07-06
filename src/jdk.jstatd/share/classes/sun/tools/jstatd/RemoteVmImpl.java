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

package jdk.jstatd.share.classes.sun.tools.jstatd;

import jdk.jstatd.share.classes.sun.jvmstat.monitor.*;
import jdk.jstatd.share.classes.sun.jvmstat.monitor.remote.*;

/**
 * Concrete implementation of the RemoteVm interface for the HotSpot PerfData
 * shared memory implementation of the jvmstat monitoring APIs. This class
 * providing remote access to the instrumentation exported by a local HotSpot
 * Java Virtual Machine. The instrumentation buffer is shipped in whole to
 * the remote machine, which is responsible for parsing and provide access
 * to the contained data.
 *
 */
public class RemoteVmImpl implements RemoteVm {

    private final BufferedMonitoredVm mvm;

    RemoteVmImpl(BufferedMonitoredVm mvm) {
        this.mvm = mvm;
    }

    public byte[] getBytes() {
        return mvm.getBytes();
    }

    public int getCapacity() {
        return mvm.getCapacity();
    }

    public void detach() {
        mvm.detach();
    }

    public int getLocalVmId() {
        return mvm.getVmIdentifier().getLocalVmId();
    }
}