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

package jdk.jdi.share.classes.com.sun.tools.jdi;

import jdk.jdi.share.classes.com.sun.jdi.InternalException;
import jdk.jdi.share.classes.com.sun.jdi.InvalidStackFrameException;
import jdk.jdi.share.classes.com.sun.jdi.MonitorInfo;
import jdk.jdi.share.classes.com.sun.jdi.ObjectReference;
import jdk.jdi.share.classes.com.sun.jdi.ThreadReference;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;

public class MonitorInfoImpl extends MirrorImpl
    implements MonitorInfo, ThreadListener {

    /* Once false, monitorInfo should not be used.
     * access synchronized on (vm.state())
     */
    private boolean isValid = true;

    ObjectReference monitor;
    ThreadReference thread;
    int stack_depth;

    MonitorInfoImpl(VirtualMachine vm, ObjectReference mon,
                    ThreadReferenceImpl thread, int dpth) {
        super(vm);
        this.monitor = mon;
        this.thread = thread;
        this.stack_depth = dpth;
        thread.addListener(this);
    }

    /*
     * ThreadListener implementation
     * Must be synchronized since we must protect against
     * sending defunct (isValid == false) stack ids to the back-end.
     */
    public boolean threadResumable(ThreadAction action) {
        synchronized (vm.state()) {
            if (isValid) {
                isValid = false;
                return false;   /* remove this stack frame as a listener */
            } else {
                throw new InternalException(
                                  "Invalid stack frame thread listener");
            }
        }
    }

    private void validateMonitorInfo() {
        if (!isValid) {
            throw new InvalidStackFrameException("Thread has been resumed");
        }
    }

    public ObjectReference monitor() {
        validateMonitorInfo();
        return monitor;
    }

    public int stackDepth() {
        validateMonitorInfo();
        return stack_depth;
    }

    public ThreadReference thread() {
        validateMonitorInfo();
        return thread;
    }
}