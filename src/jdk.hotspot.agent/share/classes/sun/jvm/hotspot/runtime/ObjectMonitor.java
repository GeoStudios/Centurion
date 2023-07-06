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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime;

import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class ObjectMonitor extends VMObject {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    heap = VM.getVM().getObjectHeap();
    Type type  = db.lookupType("ObjectMonitor");
    sun.jvm.hotspot.types.Field f = type.getField("_header");
    headerFieldOffset = f.getOffset();
    f = type.getField("_object");
    objectFieldOffset = f.getOffset();
    f = type.getField("_owner");
    ownerFieldOffset = f.getOffset();
    f = type.getField("_next_om");
    nextOMFieldOffset = f.getOffset();
    contentionsField  = type.getJIntField("_contentions");
    waitersField = type.getJIntField("_waiters");
    recursionsField = type.getCIntegerField("_recursions");
  }

  public ObjectMonitor(Address addr) {
    super(addr);
  }

  public Mark header() {
    return new Mark(addr.addOffsetTo(headerFieldOffset));
  }

  // FIXME
  //  void      set_header(markWord hdr);

  // FIXME: must implement and delegate to platform-dependent implementation
  //  public boolean isBusy();
  public boolean isEntered(sun.jvm.hotspot.runtime.Thread current) {
    Address o = owner();
      return current.threadObjectAddress().equals(o) ||
              current.isLockOwned(o);
  }

  public Address owner() { return addr.getAddressAt(ownerFieldOffset); }
  // FIXME
  //  void      set_owner(void* owner);

  public int    waiters() { return waitersField.getValue(addr); }

  public Address nextOM() { return addr.getAddressAt(nextOMFieldOffset); }
  // FIXME
  //  void      set_queue(void* owner);

  public long recursions() { return recursionsField.getValue(addr); }

  public OopHandle object() {
    Address objAddr = addr.getAddressAt(objectFieldOffset);
    if (objAddr == null) {
      return null;
    }
    return objAddr.getOopHandleAt(0);
  }

  public int contentions() {
      return contentionsField.getValue(addr);
  }

  // The following four either aren't expressed as typed fields in
  // vmStructs.cpp because they aren't strongly typed in the VM, or
  // would confuse the SA's type system.
  private static ObjectHeap    heap;
  private static long          headerFieldOffset;
  private static long          objectFieldOffset;
  private static long          ownerFieldOffset;
  private static long          nextOMFieldOffset;
  private static JIntField     contentionsField;
  private static JIntField     waitersField;
  private static CIntegerField recursionsField;
  // FIXME: expose platform-dependent stuff
}
