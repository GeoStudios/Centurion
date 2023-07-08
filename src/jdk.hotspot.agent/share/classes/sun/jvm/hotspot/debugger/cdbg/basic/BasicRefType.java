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















public class BasicRefType extends BasicType implements RefType {
  private Type targetType;

  public BasicRefType(String name, int size, Type targetType) {
    this(name, size, targetType, 0);
  }

  private BasicRefType(String name, int size, Type targetType, int cvAttributes) {
    super(name, size, cvAttributes);
    this.targetType = targetType;
    if (!((BasicType) targetType).isLazy()) {
      computeName();
    }
  }

  public RefType asRef() { return this; }

  public Type getTargetType() { return targetType; }

  Type resolveTypes(BasicCDebugInfoDataBase db, ResolveListener listener) {
    super.resolveTypes(db, listener);
    targetType = db.resolveType(this, targetType, listener, "resolving ref type");
    computeName();
    return this;
  }

  public void iterateObject(Address a, ObjectVisitor v, FieldIdentifier f) {
    v.doRef(f, a.getAddressAt(0));
  }

  protected Type createCVVariant(int cvAttributes) {
    return new BasicRefType(getName(), getSize(), getTargetType(), cvAttributes);
  }

  public void visit(TypeVisitor v) {
    v.doRefType(this);
  }

  private void computeName() {
    setName(targetType.getName() + " &");
  }
}
