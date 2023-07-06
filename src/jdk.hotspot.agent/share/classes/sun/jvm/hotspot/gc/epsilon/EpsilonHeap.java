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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.epsilon;

import java.io.*;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.code.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class EpsilonHeap extends CollectedHeap {

  private static AddressField spaceField;
  private static Field virtualSpaceField;
  private final ContiguousSpace space;
  private final VirtualSpace virtualSpace;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static void initialize(TypeDataBase db) {
    Type type = db.lookupType("EpsilonHeap");
    spaceField = type.getAddressField("_space");
    virtualSpaceField = type.getField("_virtual_space");
  }

  public EpsilonHeap(Address addr) {
    super(addr);
    space = new ContiguousSpace(spaceField.getValue(addr));
    virtualSpace = new VirtualSpace(addr.addOffsetTo(virtualSpaceField.getOffset()));
  }

  @Override
  public CollectedHeapName kind() {
    return CollectedHeapName.EPSILON;
  }

  @Override
  public long capacity() {
    return space.capacity();
  }

  @Override
  public long used() {
    return space.used();
  }

  public ContiguousSpace space() {
    return space;
  }

  @Override
  public void liveRegionsIterate(LiveRegionsClosure closure) {
    closure.doLiveRegions(space());
  }

  @Override
  public void printOn(PrintStream tty) {
     MemRegion mr = reservedRegion();
     tty.println("Epsilon heap");
     tty.println(" reserved:  [" + mr.start() + ", " + mr.end() + "]");
     tty.println(" committed: [" + virtualSpace.low() + ", " + virtualSpace.high() + "]");
     tty.println(" used:      [" + space.bottom() + ", " + space.top() + "]");
  }

}
