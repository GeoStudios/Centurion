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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.basic;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;















public class BasicIntType extends BasicType implements IntType {
  private final boolean unsigned;

  public BasicIntType(String name, int size, boolean unsigned) {
    this(name, size, unsigned, 0);
  }

  protected BasicIntType(String name, int size, boolean unsigned, int cvAttributes) {
    super(name, size, cvAttributes);
    this.unsigned = unsigned;
  }

  public IntType asInt()      { return this; }

  public int     getIntSize() { return getSize(); }
  public boolean isUnsigned() { return unsigned; }

  public void iterateObject(Address a, ObjectVisitor v, FieldIdentifier f) {
    v.doInt(f, a.getCIntegerAt(0, getSize(), isUnsigned()));
  }

  protected Type createCVVariant(int cvAttributes) {
    return new BasicIntType(getName(), getSize(), isUnsigned(), cvAttributes);
  }

  public void visit(TypeVisitor v) {
    v.doIntType(this);
  }
}
