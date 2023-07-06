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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ui.tree;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;

/** Encapsulates a long value in a tree handled by SimpleTreeModel */

public class LongTreeNodeAdapter extends FieldTreeNodeAdapter {
  private final long val;
  private boolean hexFormat = false;

  public LongTreeNodeAdapter(long val, FieldIdentifier id) {
    this(val, id, false);
  }

  public LongTreeNodeAdapter(long val, FieldIdentifier id, boolean treeTableMode) {
    super(id, treeTableMode);
    this.val = val;
  }

  public void setHexFormat(boolean hexFormat) {
    this.hexFormat = hexFormat;
  }

  public boolean getHexFormat() {
    return hexFormat;
  }

  public int getChildCount() {
    return 0;
  }

  public SimpleTreeNode getChild(int index) {
    return null;
  }

  public boolean isLeaf() {
    return true;
  }

  public int getIndexOfChild(SimpleTreeNode child) {
    return 0;
  }

  public String getValue() {
    if (hexFormat) {
      return "0x" + Long.toHexString(val);
    } else {
      return Long.toString(val);
    }
  }
}
