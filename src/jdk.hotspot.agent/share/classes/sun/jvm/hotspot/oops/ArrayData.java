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















// ArrayData
//
// A ArrayData is a base class for accessing profiling data which does
// not have a statically known size.  It consists of an array length
// and an array start.
abstract class ArrayData extends ProfileData {

  static final int arrayLenOffSet = 0;
  static final int arrayStartOffSet = 1;

  int arrayUintAt(int index) {
    int aindex = index + arrayStartOffSet;
    return uintAt(aindex);
  }
  int arrayIntAt(int index) {
    int aindex = index + arrayStartOffSet;
    return intAt(aindex);
  }

  // Code generation support for subclasses.
  static int arrayElementOffset(int index) {
    return cellOffset(arrayStartOffSet + index);
  }

  ArrayData(DataLayout layout) {
    super(layout);
  }

  static int staticCellCount() {
    return -1;
  }

  int arrayLen() {
    return intAt(arrayLenOffSet);
  }

  public int cellCount() {
    return arrayLen() + 1;
  }

  // Code generation support
  static int arrayLenOffset() {
    return cellOffset(arrayLenOffSet);
  }
  static int arrayStartOffset() {
    return cellOffset(arrayStartOffSet);
  }

}
