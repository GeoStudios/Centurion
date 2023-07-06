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

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import java.io.*;

// An IndexableFieldIdentifier describes a field in an Oop accessed by an index

public class IndexableFieldIdentifier extends FieldIdentifier {

  public IndexableFieldIdentifier(int index) {
    this.index = index;
  }

  private final int index;

  public int getIndex() { return index; }

  public String getName() { return Integer.toString(getIndex()); }

  public void printOn(PrintStream tty) {
    tty.print(" - " + getIndex() + ":\t");
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (!(obj instanceof IndexableFieldIdentifier)) {
      return false;
    }

    return (((IndexableFieldIdentifier) obj).getIndex() == index);
  }

  public int hashCode() {
    return index;
  }
}
