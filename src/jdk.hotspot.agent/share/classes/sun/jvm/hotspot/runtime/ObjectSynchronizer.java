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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class ObjectSynchronizer {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type = db.lookupType("ObjectSynchronizer");
    inUseList = type.getAddressField("_in_use_list").getValue();
  }

  public long identityHashValueFor(Oop obj) {
    Mark mark = obj.getMark();
    if (mark.isUnlocked()) {
      // FIXME: can not generate marks in debugging system
      return mark.hash();
    } else if (mark.hasMonitor()) {
      ObjectMonitor monitor = mark.monitor();
      Mark temp = monitor.header();
      return temp.hash();
    } else {
      if (Assert.ASSERTS_ENABLED) {
        Assert.that(VM.getVM().isDebugging(), "Can not access displaced header otherwise");
      }
      if (mark.hasDisplacedMarkHelper()) {
        Mark temp = mark.displacedMarkHelper();
        return temp.hash();
      }
      // FIXME: can not do anything else here in debugging system
      return 0;
    }
  }

  public static Iterator objectMonitorIterator() {
    if (inUseList != null) {
      return new ObjectMonitorIterator();
    } else {
      return null;
    }
  }

  private static class ObjectMonitorIterator implements Iterator {

    // JVMTI raw monitors are not included in _in_use_list and
    // are not returned by this Iterator.

    ObjectMonitorIterator() {
      mon = new ObjectMonitor(inUseList);
    }

    public boolean hasNext() {
      return (mon.nextOM() != null);
    }

    public Object next() {
      // advance to next entry
      Address monAddr = mon.nextOM();
      if (monAddr == null) {
        throw new NoSuchElementException();
      }
      mon = new ObjectMonitor(monAddr);
      return mon;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    private ObjectMonitor mon;
  }

  private static Address inUseList;

}
