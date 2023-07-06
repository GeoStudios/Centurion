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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.windows.amd64;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.amd64.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.basic.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.windbg.*;

public class WindowsAMD64CFrame extends BasicCFrame {
  private final Address rbp;
  private final Address pc;

  private static final int ADDRESS_SIZE = 8;

  /** Constructor for topmost frame */
  public WindowsAMD64CFrame(WindbgDebugger dbg, Address rbp, Address pc) {
    super(dbg.getCDebugger());
    this.rbp = rbp;
    this.pc  = pc;
    this.dbg = dbg;
  }

  public CFrame sender(ThreadProxy thread) {
    AMD64ThreadContext context = (AMD64ThreadContext) thread.getContext();
    Address rsp = context.getRegisterAsAddress(AMD64ThreadContext.RSP);

    if ( (rbp == null) || rbp.lessThan(rsp) ) {
      return null;
    }

    // Check alignment of rbp
    if ( dbg.getAddressValue(rbp) % ADDRESS_SIZE != 0) {
        return null;
    }

    Address nextRBP = rbp.getAddressAt(0);
    if (nextRBP == null || nextRBP.lessThanOrEqual(rbp)) {
      return null;
    }
    Address nextPC  = rbp.getAddressAt(ADDRESS_SIZE);
    if (nextPC == null) {
      return null;
    }
    return new WindowsAMD64CFrame(dbg, nextRBP, nextPC);
  }

  public Address pc() {
    return pc;
  }

  public Address localVariableBase() {
    return rbp;
  }

  private final WindbgDebugger dbg;
}
