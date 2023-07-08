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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.proc.aarch64;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.aarch64.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.proc.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;

public class ProcAARCH64Thread implements ThreadProxy {
    private final ProcDebugger debugger;
    private final int         id;

    public ProcAARCH64Thread(ProcDebugger debugger, Address addr) {
        this.debugger = debugger;

        // FIXME: the size here should be configurable. However, making it
        // so would produce a dependency on the "types" package from the
        // debugger package, which is not desired.
        this.id       = (int) addr.getCIntegerAt(0, 4, true);
    }

    public ProcAARCH64Thread(ProcDebugger debugger, long id) {
        this.debugger = debugger;
        this.id = (int) id;
    }

    public ThreadContext getContext() throws IllegalThreadStateException {
        ProcAARCH64ThreadContext context = new ProcAARCH64ThreadContext(debugger);
        long[] regs = debugger.getThreadIntegerRegisterSet(id);
        if (Assert.ASSERTS_ENABLED) {
            Assert.that(regs.length == AARCH64ThreadContext.NPRGREG, "size mismatch");
        }
        for (int i = 0; i < regs.length; i++) {
            context.setRegister(i, regs[i]);
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

    public String toString() {
        return "t@" + id;
    }

    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof ProcAARCH64Thread)) {
            return false;
        }

        return (((ProcAARCH64Thread) obj).id == id);
    }

    public int hashCode() {
        return id;
    }
}
