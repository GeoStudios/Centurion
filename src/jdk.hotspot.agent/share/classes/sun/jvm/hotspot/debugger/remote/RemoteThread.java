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

package sun.jvm.hotspot.debugger.remote;

import sun.jvm.hotspot.debugger.*;

public abstract class RemoteThread implements ThreadProxy {
   protected RemoteDebuggerClient debugger;
   protected Address addr;
   protected long id;

   public RemoteThread(RemoteDebuggerClient debugger, Address addr) {
      this.debugger = debugger;
      this.addr = addr;
      this.id = -1L;  // invalid, but don't depend on it. check null for addr
   }

   public RemoteThread(RemoteDebuggerClient debugger, long id) {
      this.debugger = debugger;
      this.addr = null;
      this.id = id;
   }

   public boolean canSetContext() throws DebuggerException {
     return false;
   }

   public void setContext(ThreadContext context)
     throws IllegalThreadStateException, DebuggerException {
     throw new DebuggerException("Unimplemented");
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      }

      if (! (o instanceof RemoteThread other)) {
         return false;
      }
      boolean isOtherAddress = (other.addr != null);
      boolean isAddress = (addr != null);

      if (isAddress) {
         return (isOtherAddress)? debugger.areThreadsEqual(addr, other.addr) :
                                  debugger.areThreadsEqual(addr, other.id);
      } else {
         return (isOtherAddress)? debugger.areThreadsEqual(id, other.addr) :
                                  debugger.areThreadsEqual(id, other.id);
      }
   }

   public int hashCode() {
      return (addr != null)? debugger.getThreadHashCode(addr) :
                             debugger.getThreadHashCode(id);
   }

   public String toString() {
      return "t@ " + hashCode();
   }
}
