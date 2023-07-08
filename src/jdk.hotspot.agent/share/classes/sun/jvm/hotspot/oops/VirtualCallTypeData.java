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

// VirtualCallTypeData
//
// A VirtualCallTypeData is used to access profiling information about
// a virtual call for which we collect type information about
// arguments and return value.
public class VirtualCallTypeData<K,M> extends VirtualCallData<K,M> implements CallTypeDataInterface<K> {
  final TypeStackSlotEntries<K,M> args;
  final ReturnTypeEntry<K,M> ret;

  int cellCountGlobalOffset() {
    return VirtualCallData.staticCellCount() + TypeEntriesAtCall.cellCountLocalOffset();
  }

  int cellCountNoHeader() {
    return uintAt(cellCountGlobalOffset());
  }

  public VirtualCallTypeData(MethodDataInterface<K,M> methodData, DataLayout layout) {
    super(methodData, layout);
    args = new TypeStackSlotEntries<K,M>(methodData, this, VirtualCallData.staticCellCount()+TypeEntriesAtCall.headerCellCount(), numberOfArguments());
    ret = new ReturnTypeEntry<K,M>(methodData, this, cellCount() - ReturnTypeEntry.staticCellCount());
  }

  static int staticCellCount() {
    return -1;
  }

  public int cellCount() {
    return VirtualCallData.staticCellCount() +
      TypeEntriesAtCall.headerCellCount() +
      intAt(cellCountGlobalOffset());
  }

  public int numberOfArguments() {
    return cellCountNoHeader() / TypeStackSlotEntries.perArgCount();
  }

  public boolean hasArguments() {
    return cellCountNoHeader() >= TypeStackSlotEntries.perArgCount();
  }

  public K argumentType(int i) {
    return args.type(i);
  }

  public boolean hasReturn() {
    return (cellCountNoHeader() % TypeStackSlotEntries.perArgCount()) != 0;
  }

  public K returnType() {
    return ret.type();
  }

  public int argumentTypeIndex(int i) {
    return args.typeIndex(i);
  }

  public int returnTypeIndex() {
    return ret.typeIndex();
  }

  public void printDataOn(PrintStream st) {
    super.printDataOn(st);
    if (hasArguments()) {
      tab(st);
      st.print("argument types");
      args.printDataOn(st);
    }
    if (hasReturn()) {
      tab(st);
      st.print("return type");
      ret.printDataOn(st);
    }
  }
}
