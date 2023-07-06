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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.linux.x86;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.linux.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.basic.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.x86.*;















final public class LinuxX86CFrame extends BasicCFrame {
   // package/class internals only
   public LinuxX86CFrame(LinuxDebugger dbg, Address ebp, Address pc) {
      super(dbg.getCDebugger());
      this.ebp = ebp;
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
      return ebp;
   }

   public CFrame sender(ThreadProxy thread) {
      X86ThreadContext context = (X86ThreadContext) thread.getContext();
      /*
       * Native code fills in the stack pointer register value using index
       * X86ThreadContext.SP.
       * See file LinuxDebuggerLocal.c macro REG_INDEX(reg).
       *
       * Be sure to use SP, or UESP which is aliased to SP in Java code,
       * for the frame pointer validity check.
       */
      Address esp = context.getRegisterAsAddress(X86ThreadContext.SP);

      if ( (ebp == null) || ebp.lessThan(esp) ) {
        return null;
      }

      // Check alignment of ebp
      if ( dbg.getAddressValue(ebp) % ADDRESS_SIZE != 0) {
        return null;
      }

      Address nextEBP = ebp.getAddressAt(0);
      if (nextEBP == null || nextEBP.lessThanOrEqual(ebp)) {
        return null;
      }
      Address nextPC  = ebp.getAddressAt(ADDRESS_SIZE);
      if (nextPC == null) {
        return null;
      }
      return new LinuxX86CFrame(dbg, nextEBP, nextPC);
   }

   private static final int ADDRESS_SIZE = 4;
   private final Address pc;
   private final Address ebp;
   private final LinuxDebugger dbg;
}
