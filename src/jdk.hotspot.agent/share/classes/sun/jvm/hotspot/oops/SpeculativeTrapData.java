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

// SpeculativeTrapData
//
// A SpeculativeTrapData is used to record traps due to type
// speculation. It records the root of the compilation.
public class SpeculativeTrapData<K, M> extends ProfileData {
  static final int speculativeTrapMethod = 0;
  static final int speculativeTrapCellCount = 1;
  final MethodDataInterface<K, M> methodData;

  public SpeculativeTrapData(MethodDataInterface<K,M> methodData, DataLayout layout) {
    super(layout);
    this.methodData = methodData;
  }

  static int staticCellCount() {
    return speculativeTrapCellCount;
  }

  public int cellCount() {
    return staticCellCount();
  }

  public M method() {
    return methodData.getMethodAtAddress(addressAt(speculativeTrapMethod));
  }

  static public int methodIndex() {
    return speculativeTrapMethod;
  }

  public void printDataOn(PrintStream st) {
    printShared(st, "SpeculativeTrapData");
    tab(st);
    methodData.printMethodValueOn(method(), st);
    st.println();
  }
}
