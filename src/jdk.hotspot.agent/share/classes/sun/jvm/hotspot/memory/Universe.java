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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory;


import java.io.PrintStream;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.OopHandle;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.epsilon.EpsilonHeap;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.g1.G1CollectedHeap;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.parallel.ParallelScavengeHeap;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.serial.SerialHeap;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared.CollectedHeap;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shenandoah.ShenandoahHeap;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.z.ZCollectedHeap;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.Oop;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.BasicType;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObject;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VirtualConstructor;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.AddressField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.CIntegerField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.Type;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.TypeDataBase;















public class Universe {
  private static AddressField collectedHeapField;
  private static VirtualConstructor heapConstructor;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static boolean typeExists(TypeDataBase db, String type) {
      try {
          db.lookupType(type);
      } catch (RuntimeException e) {
          return false;
      }
      return true;
  }

  private static void addHeapTypeIfInDB(TypeDataBase db, Class<? extends VMObject> heapClass) {
      String heapName = heapClass.getSimpleName();
      if (typeExists(db, heapName)) {
          heapConstructor.addMapping(heapName, heapClass);
      }
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("Universe");

    collectedHeapField = type.getAddressField("_collectedHeap");

    heapConstructor = new VirtualConstructor(db);
    addHeapTypeIfInDB(db, SerialHeap.class);
    addHeapTypeIfInDB(db, ParallelScavengeHeap.class);
    addHeapTypeIfInDB(db, G1CollectedHeap.class);
    addHeapTypeIfInDB(db, EpsilonHeap.class);
    addHeapTypeIfInDB(db, ZCollectedHeap.class);
    addHeapTypeIfInDB(db, ShenandoahHeap.class);

    UniverseExt.initialize(heapConstructor);
  }

  public Universe() {
  }
  public CollectedHeap heap() {
    return (CollectedHeap) heapConstructor.instantiateWrapperFor(collectedHeapField.getValue());
  }


  /** Returns "TRUE" iff "p" points into the allocated area of the heap. */
  public boolean isIn(Address p) {
    return heap().isIn(p);
  }

  /** Returns "TRUE" iff "p" points into the reserved area of the heap. */
  public boolean isInReserved(Address p) {
    return heap().isInReserved(p);
  }

  public void print() { printOn(System.out); }
  public void printOn(PrintStream tty) {
    heap().printOn(tty);
  }

  // Check whether an element of a typeArrayOop with the given type must be
  // aligned 0 mod 8.  The typeArrayOop itself must be aligned at least this
  // strongly.
  public static boolean elementTypeShouldBeAligned(BasicType type) {
    return type == BasicType.T_DOUBLE || type == BasicType.T_LONG;
  }

  // Check whether an object field (static/non-static) of the given type must be
  // aligned 0 mod 8.
  public static boolean fieldTypeShouldBeAligned(BasicType type) {
    return type == BasicType.T_DOUBLE || type == BasicType.T_LONG;
  }
}
