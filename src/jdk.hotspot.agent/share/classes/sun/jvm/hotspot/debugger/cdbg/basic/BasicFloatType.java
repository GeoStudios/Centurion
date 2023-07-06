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

public class BasicFloatType extends BasicType implements FloatType {
  public BasicFloatType(String name, int size) {
    this(name, size, 0);
  }

  private BasicFloatType(String name, int size, int cvAttributes) {
    super(name, size, cvAttributes);
  }

  public FloatType asFloat() { return this; }

  public void iterateObject(Address a, ObjectVisitor v, FieldIdentifier f) {
    v.doFloat(f, a.getJFloatAt(0));
  }

  protected Type createCVVariant(int cvAttributes) {
    return new BasicFloatType(getName(), getSize(), cvAttributes);
  }

  public void visit(TypeVisitor v) {
    v.doFloatType(this);
  }
}
