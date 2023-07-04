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

package sun.jvm.hotspot.ui.tree;

import sun.jvm.hotspot.oops.*;

/** Abstract base class for all adapters for fields of oops */

public abstract class FieldTreeNodeAdapter implements SimpleTreeNode {
  private final FieldIdentifier id;
  private final boolean         treeTableMode;

  /** The identifier may be null, i.e., for the root of the tree */
  public FieldTreeNodeAdapter(FieldIdentifier id, boolean treeTableMode) {
    this.id = id;
    this.treeTableMode = treeTableMode;
  }

  public FieldIdentifier getID() {
    return id;
  }

  /** Defaults to false in subclasses */
  public boolean getTreeTableMode() {
    return treeTableMode;
  }

  public String getName() {
    if (getID() != null) {
      return getID().getName();
    }
    return "";
  }

  public String toString() {
    if (treeTableMode) {
      return getName();
    } else {
      if (getID() != null) {
        return getName() + ": " + getValue();
      } else {
        return getValue();
      }
    }
  }
}
