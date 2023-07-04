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

package sun.jvm.hotspot.compiler;

import sun.jvm.hotspot.code.*;

public class OopMapStream {
  private final CompressedReadStream stream;
  private ImmutableOopMap oopMap;
  private int mask;
  private final int size;
  private int position;
  private final OopMapValue omv;
  private boolean omvValid;

  public OopMapStream(ImmutableOopMap oopMap) {
    this(oopMap, (OopMapValue.OopTypes[]) null);
  }

  public OopMapStream(ImmutableOopMap oopMap, OopMapValue.OopTypes type) {
    this(oopMap, (OopMapValue.OopTypes[]) null);
    mask = type.getValue();
  }

  public OopMapStream(ImmutableOopMap oopMap, OopMapValue.OopTypes[] types) {
    stream = new CompressedReadStream(oopMap.getData());
    mask = computeMask(types);
    size = (int) oopMap.getCount();
    position = 0;
    omv = new OopMapValue();
    omvValid = false;
  }

  public boolean isDone() {
    if (!omvValid) {
      findNext();
    }
    return !omvValid;
  }

  public void next() {
    findNext();
  }

  public OopMapValue getCurrent() {
    return omv;
  }

  //--------------------------------------------------------------------------------
  // Internals only below this point
  //

  private int computeMask(OopMapValue.OopTypes[] types) {
    mask = 0;
    if (types != null) {
      for (int i = 0; i < types.length; i++) {
        mask |= types[i].getValue();
      }
    }
    return mask;
  }

  private void findNext() {
    while (position++ < size) {
      omv.readFrom(stream);
      if ((omv.getType().getValue() & mask) > 0) {
        omvValid = true;
        return;
      }
    }
    omvValid = false;
  }
}
