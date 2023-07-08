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

import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.code.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class CodeHeap extends VMObject {
  private static Field         memoryField;
  private static Field         segmapField;
  //  private static CIntegerField numberOfCommittedSegmentsField;
  //  private static CIntegerField numberOfReservedSegmentsField;
  //  private static CIntegerField segmentSizeField;
  private static CIntegerField log2SegmentSizeField;
  //  private static CIntegerField nextSegmentField;
  //  private static AddressField  freelistField;
  //  private static CIntegerField freeSegmentsField;

  private final VirtualSpace memory;
  private final VirtualSpace segmentMap;
  private final int log2SegmentSize;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static void initialize(TypeDataBase db) {
    Type type = db.lookupType("CodeHeap");

    memoryField          = type.getField("_memory");
    segmapField          = type.getField("_segmap");
    log2SegmentSizeField = type.getCIntegerField("_log2_segment_size");

  }

  public CodeHeap(Address addr) {
    super(addr);
    log2SegmentSize = (int) log2SegmentSizeField.getValue(addr);
    segmentMap = new VirtualSpace(addr.addOffsetTo(segmapField.getOffset()));
    memory = new VirtualSpace(addr.addOffsetTo(memoryField.getOffset()));
  }

  public Address begin() {
    return getMemory().low();
  }

  public Address end() {
    return getMemory().high();
  }

  public boolean contains(Address p) {
    return (begin().lessThanOrEqual(p) && end().greaterThan(p));
  }

  /** Returns the start of the block containing p or null */
  public Address findStart(Address p) {
    if (!contains(p)) return null;
    HeapBlock h = blockStart(p);
    if (h == null || h.isFree()) {
      return null;
    }
    return h.getAllocatedSpace();
  }

  private Address nextBlock(Address ptr) {
    Address base = blockBase(ptr);
    if (base == null) {
      return null;
    }
    HeapBlock block = getBlockAt(base);
    return base.addOffsetTo(block.getLength() * (1L << getLog2SegmentSize()));
  }

  public void iterate(CodeCacheVisitor visitor, CodeCache cache) {
    CodeBlob lastBlob = null;
    Address ptr = begin();
    while (ptr != null && ptr.lessThan(end())) {
      try {
        // Use findStart to get a pointer inside blob other findBlob asserts
        CodeBlob blob = cache.createCodeBlobWrapper(findStart(ptr));
        if (blob != null) {
          visitor.visit(blob);
          if (blob == lastBlob) {
            throw new InternalError("saw same blob twice");
          }
          lastBlob = blob;
        }
      } catch (RuntimeException e) {
        e.printStackTrace();
      }
      Address next = nextBlock(ptr);
      if (next != null && next.lessThan(ptr)) {
        throw new InternalError("pointer moved backwards");
      }
      ptr = next;
    }
  }

  //--------------------------------------------------------------------------------
  // Internals only below this point
  //

  private VirtualSpace getMemory() {
    return memory;
  }

  private VirtualSpace getSegmentMap() {
    return segmentMap;
  }

  private long segmentFor(Address p) {
    return p.minus(getMemory().low()) >> getLog2SegmentSize();
  }

  private int getLog2SegmentSize() {
    return log2SegmentSize;
  }

  private HeapBlock getBlockAt(Address addr) {
    return VMObjectFactory.newObject(HeapBlock.class, addr);
  }

  private HeapBlock blockStart(Address p) {
    Address base = blockBase(p);
    if (base == null) return null;
    return getBlockAt(base);
  }

  private Address blockBase(Address p) {
    long i = segmentFor(p);
    Address b = getSegmentMap().low();
    if (b.getCIntegerAt(i, 1, true) == 0xFF) {
      return null;
    }
    while (b.getCIntegerAt(i, 1, true) > 0) {
      i -= b.getCIntegerAt(i, 1, true);
    }
    return getMemory().low().addOffsetTo(i << getLog2SegmentSize());
  }

}
