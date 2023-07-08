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















// JumpData
//
// A JumpData is used to access profiling information for a direct
// branch.  It is a counter, used for counting the number of branches,
// plus a data displacement, used for realigning the data pointer to
// the corresponding target bci.
public class JumpData extends ProfileData {
  static final int   takenOffSet = 0;
  static final int     displacementOffSet = 1;
  static final int     jumpCellCount = 2;

  public JumpData(DataLayout layout) {
    super(layout);
    //assert(layout.tag() == DataLayout.jumpDataTag ||
    //       layout.tag() == DataLayout.branchDataTag, "wrong type");
  }

  static int staticCellCount() {
    return jumpCellCount;
  }

  public int cellCount() {
    return staticCellCount();
  }

  // Direct accessor
  int taken() {
    return uintAt(takenOffSet);
  }

  int displacement() {
    return intAt(displacementOffSet);
  }

  // Code generation support
  static int takenOffset() {
    return cellOffset(takenOffSet);
  }

  static int displacementOffset() {
    return cellOffset(displacementOffSet);
  }

  public void printDataOn(PrintStream st) {
    printShared(st, "JumpData");
    st.println("taken(" + taken() + ") displacement(" + displacement() + ")");
  }
}
