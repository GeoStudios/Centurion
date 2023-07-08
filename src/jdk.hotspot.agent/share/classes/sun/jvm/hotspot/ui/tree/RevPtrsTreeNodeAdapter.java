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


import java.util.*;
import java.io.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;















/** Who directly points to this node. */

public class RevPtrsTreeNodeAdapter extends FieldTreeNodeAdapter {
  private static final FieldIdentifier fid = new NamedFieldIdentifier("_revPtrs");

  private final List<LivenessPathElement> children;

  public RevPtrsTreeNodeAdapter(Oop oop) {
    this(oop, false);
  }

  public RevPtrsTreeNodeAdapter(Oop oop, boolean treeTableMode) {
    super(fid, treeTableMode);
    children = VM.getVM().getRevPtrs().get(oop);
  }

  public int getChildCount() {
    return children != null ? children.size() : 0;
  }

  public SimpleTreeNode getChild(int index) {
    LivenessPathElement lpe = children.get(index);
    IndexableFieldIdentifier ifid = new IndexableFieldIdentifier(index);
    Oop oop = lpe.getObj();
    if (oop != null) {
      return new OopTreeNodeAdapter(oop, ifid, getTreeTableMode());
    } else {
      NamedFieldIdentifier nfi = (NamedFieldIdentifier)lpe.getField();
      return new RootTreeNodeAdapter(nfi.getName(), ifid, getTreeTableMode());
    }
  }

  public boolean isLeaf() {
    return false;
  }

  public int getIndexOfChild(SimpleTreeNode child) {
    FieldIdentifier id = ((FieldTreeNodeAdapter) child).getID();
    IndexableFieldIdentifier ifid = (IndexableFieldIdentifier)id;
    return ifid.getIndex();
  }

  public String getName()  { return "<<Reverse pointers>>"; }
  public String getValue() { return ""; }
}
