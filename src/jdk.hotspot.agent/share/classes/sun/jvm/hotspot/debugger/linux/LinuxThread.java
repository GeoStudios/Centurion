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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.linux;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;















class LinuxThread implements ThreadProxy {
    private final LinuxDebugger debugger;
    private final int           lwp_id;

    /** The address argument must be the address of the _thread_id in the
        OSThread. It's value is result ::gettid() call. */
    LinuxThread(LinuxDebugger debugger, Address addr) {
        this.debugger = debugger;
        // FIXME: size of data fetched here should be configurable.
        // However, making it so would produce a dependency on the "types"
        // package from the debugger package, which is not desired.
        int pid = (int)addr.getCIntegerAt(0, 4, true);
        if (debugger instanceof LinuxDebuggerLocal) {
            int hostPID = ((LinuxDebuggerLocal)debugger).getHostPID(pid);
            // Debuggee is not running in the container
            if (hostPID != -1) {
                pid = hostPID;
            }
        }
        this.lwp_id = pid;

    }

    LinuxThread(LinuxDebugger debugger, long id) {
        this.debugger = debugger;
        this.lwp_id = (int) id;
    }

    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof LinuxThread)) {
            return false;
        }

        return (((LinuxThread) obj).lwp_id == lwp_id);
    }

    public int hashCode() {
        return lwp_id;
    }

    public String toString() {
        return Integer.toString(lwp_id);
    }

    public ThreadContext getContext() throws IllegalThreadStateException {
        long[] data = debugger.getThreadIntegerRegisterSet(lwp_id);
        ThreadContext context = LinuxThreadContextFactory.createThreadContext(debugger);
        // null means we failed to get the register set for some reason. The caller
        // is responsible for dealing with the set of null registers in that case.
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                context.setRegister(i, data[i]);
            }
        }
        return context;
    }

    public boolean canSetContext() throws DebuggerException {
        return false;
    }

    public void setContext(ThreadContext context)
      throws IllegalThreadStateException, DebuggerException {
        throw new DebuggerException("Unimplemented");
    }
}
