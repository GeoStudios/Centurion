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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.linux.aarch64;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.aarch64.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.linux.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.basic.*;

final public class LinuxAARCH64CFrame extends BasicCFrame {
   public LinuxAARCH64CFrame(LinuxDebugger dbg, Address fp, Address pc) {
      super(dbg.getCDebugger());
      this.fp = fp;
      this.pc = pc;
      this.dbg = dbg;
   }

   // override base class impl to avoid ELF parsing
   public ClosestSymbol closestSymbolToPC() {
      // try native lookup in debugger.
      return dbg.lookup(dbg.getAddressValue(pc()));
   }

   public Address pc() {
      return pc;
   }

   public Address localVariableBase() {
      return fp;
   }

   public CFrame sender(ThreadProxy thread) {
      AARCH64ThreadContext context = (AARCH64ThreadContext) thread.getContext();
      Address rsp = context.getRegisterAsAddress(AARCH64ThreadContext.SP);

      if ((fp == null) || fp.lessThan(rsp)) {
        return null;
      }

      // Check alignment of fp
      if (dbg.getAddressValue(fp) % (2 * ADDRESS_SIZE) != 0) {
        return null;
      }

      Address nextFP = fp.getAddressAt(0);
      if (nextFP == null || nextFP.lessThanOrEqual(fp)) {
        return null;
      }
      Address nextPC  = fp.getAddressAt(ADDRESS_SIZE);
      if (nextPC == null) {
        return null;
      }
      return new LinuxAARCH64CFrame(dbg, nextFP, nextPC);
   }

   // package/class internals only
   private static final int ADDRESS_SIZE = 8;
   private final Address pc;
   private Address sp;
   private final Address fp;
   private final LinuxDebugger dbg;
}
