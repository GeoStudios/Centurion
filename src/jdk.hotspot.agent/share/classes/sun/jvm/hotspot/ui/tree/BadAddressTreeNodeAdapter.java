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


import java.io.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;















/** Simple wrapper for displaying bad addresses in the Inspector */

public class BadAddressTreeNodeAdapter extends FieldTreeNodeAdapter {
  private final String message;

  private static String generateMessage(long addr, String kind) {
    return "** BAD " + kind + " " + Long.toHexString(addr) + " **";
  }

  public BadAddressTreeNodeAdapter(Address addr, MetadataField field, boolean treeTableMode) {
    super(field.getID(), treeTableMode);
    message = generateMessage(addr.minus(null), "METADATA");
  }

  public BadAddressTreeNodeAdapter(Address addr, OopField field, boolean treeTableMode) {
    super(field.getID(), treeTableMode);
    message = generateMessage(addr.minus(null), "OOP");
  }

  public BadAddressTreeNodeAdapter(OopHandle addr, FieldIdentifier id, boolean treeTableMode) {
    super(id, treeTableMode);
    message = generateMessage(addr.minus(null), "OOP");
  }

  /** The address may be null (for address fields of structures which
      are null); the FieldIdentifier may also be null (for the root
      node). */
  public BadAddressTreeNodeAdapter(long addr, FieldIdentifier id, boolean treeTableMode) {
    super(id, treeTableMode);
    message = generateMessage(addr, "ADDRESS");
  }

  public int getChildCount() {
    return 0;
  }

  public SimpleTreeNode getChild(int index) {
    throw new RuntimeException("Should not call this");
  }

  public boolean isLeaf() {
    return true;
  }

  public int getIndexOfChild(SimpleTreeNode child) {
    throw new RuntimeException("Should not call this");
  }

  public String getValue() {
    return message;
      }
    }
