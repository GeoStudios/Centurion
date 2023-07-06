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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared;


import java.io.*;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















public class ContiguousSpace extends CompactibleSpace implements LiveRegionsProvider {
  private static AddressField topField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("ContiguousSpace");

    topField = type.getAddressField("_top");
  }

  public ContiguousSpace(Address addr) {
    super(addr);
  }

  public Address top() {
    return topField.getValue(addr);
  }

  /** In bytes */
  public long capacity() {
    return end().minus(bottom());
  }

  /** In bytes */
  public long used() {
    return top().minus(bottom());
  }

  /** In bytes */
  public long free() {
    return end().minus(top());
  }

  /** In a contiguous space we have a more obvious bound on what parts
      contain objects. */
  public MemRegion usedRegion() {
    return new MemRegion(bottom(), top());
  }

  /** Returns regions of Space where live objects live */
  public List<MemRegion> getLiveRegions() {
    List<MemRegion> res = new ArrayList<>();
    res.add(new MemRegion(bottom(), top()));
    return res;
  }

  /** Testers */
  public boolean contains(Address p) {
    return (bottom().lessThanOrEqual(p) && top().greaterThan(p));
  }

  public void printOn(PrintStream tty) {
    tty.print(" [" + bottom() + "," +
                top() + "," + end() + ")");
    super.printOn(tty);
  }
}
