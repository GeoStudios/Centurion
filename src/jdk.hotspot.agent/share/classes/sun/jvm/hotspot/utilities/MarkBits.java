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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;















/** Helper class which covers the reserved area of the heap with an
    (object-external) set of mark bits, used for GC-like scans through
    the heap like liveness analysis. */

public class MarkBits {
  public MarkBits(CollectedHeap heap) {
    MemRegion reserved = heap.reservedRegion();
    // Must cover "reserved" with one bit for each OopHandle
    start = reserved.start();
    end   = reserved.end();
    long numOopHandles = end.minus(start) / VM.getVM().getOopSize();
    bits = heap.createBitMap(numOopHandles);
  }

  public void clear() {
    bits.clear();
  }

  /** Returns true if a mark was newly placed for the given Oop, or
      false if the Oop was already marked. If the Oop happens to lie
      outside the heap (should not happen), prints a warning and
      returns false. */
  public boolean mark(Oop obj) {
    if (obj == null) {
      System.err.println("MarkBits: WARNING: null object, ignoring");
      return false;
    }

    OopHandle handle = obj.getHandle();
    long idx = handle.minus(start) / VM.getVM().getOopSize();
    if (bits.at(idx)) {
      return false; // already marked
    }
    bits.atPut(idx, true);
    return true;
  }

  /** Forces clearing of a given mark bit. */
  public void clear(Oop obj) {
    OopHandle handle = obj.getHandle();
    long idx = handle.minus(start) / VM.getVM().getOopSize();
    bits.atPut(idx, false);
  }

  private final BitMapInterface bits;
  private final Address start;
  private final Address end;
}
