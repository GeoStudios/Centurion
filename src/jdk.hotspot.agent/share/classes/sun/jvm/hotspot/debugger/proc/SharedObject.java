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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.proc;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.posix.*;

/** A Object can represent either a .so or an a.out file. */

class SharedObject extends DSO {
  SharedObject(ProcDebugger dbg, String filename, long size, Address relocation) {
    super(filename, size, relocation);
    this.dbg     = dbg;
  }

  protected Address newAddress(long address) {
    return dbg.newAddress(address);
  }

  protected long getAddressValue(Address addr) {
    return dbg.getAddressValue(addr);
  }

  public ClosestSymbol closestSymbolToPC(Address pcAsAddr) throws DebuggerException {
    return dbg.lookup(dbg.getAddressValue(pcAsAddr));
  }

  private final ProcDebugger   dbg;
}
