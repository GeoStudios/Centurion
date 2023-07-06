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

// ParametersTypeData
//
// A ParametersTypeData is used to access profiling information about
// types of parameters to a method
public class ParametersTypeData<K,M> extends ArrayData {
  final TypeStackSlotEntries<K,M> parameters;

  static int stackSlotLocalOffset(int i) {
    return arrayStartOffSet + TypeStackSlotEntries.stackSlotLocalOffset(i);
  }

  static int typeLocalOffset(int i) {
    return arrayStartOffSet + TypeStackSlotEntries.typeLocalOffset(i);
  }

  public ParametersTypeData(MethodDataInterface<K,M> methodData, DataLayout layout) {
    super(layout);
    parameters = new TypeStackSlotEntries<K,M>(methodData, this, 1, numberOfParameters());
  }

  public int numberOfParameters() {
    return arrayLen() / TypeStackSlotEntries.perArgCount();
  }

  int stackSlot(int i) {
    return parameters.stackSlot(i);
  }

  public K type(int i) {
    return parameters.type(i);
  }

  static public int typeIndex(int i) {
    return typeLocalOffset(i);
  }

  public void printDataOn(PrintStream st) {
    st.print("parameter types");
    parameters.printDataOn(st);
  }
}