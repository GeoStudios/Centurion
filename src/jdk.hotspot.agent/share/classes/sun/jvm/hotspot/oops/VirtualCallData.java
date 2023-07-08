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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops;


import java.io.*;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;















// VirtualCallData
//
// A VirtualCallData is used to access profiling information about a
// call.  For now, it has nothing more than a ReceiverTypeData.
public class VirtualCallData<K,M> extends ReceiverTypeData<K,M> {
  public VirtualCallData(MethodDataInterface<K,M> methodData, DataLayout layout) {
    super(methodData, layout);
    //assert(layout.tag() == DataLayout.virtualCallDataTag, "wrong type");
  }

  static int staticCellCount() {
    // At this point we could add more profile state, e.g., for arguments.
    // But for now it's the same size as the base record type.
    int cellCount = ReceiverTypeData.staticCellCount();
    return cellCount;
  }

  public int cellCount() {
    return staticCellCount();
  }

  // Direct accessors
  static int virtualCallDataSize() {
    return cellOffset(staticCellCount());
  }

  public void printDataOn(PrintStream st) {
    printShared(st, "VirtualCallData");
    printReceiverDataOn(st);
  }
}
