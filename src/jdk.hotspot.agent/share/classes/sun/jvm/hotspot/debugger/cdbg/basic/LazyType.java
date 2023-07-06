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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Assert;

public class LazyType extends BasicType {
  private final Object key;
  private final int    cvAttributes;

  public LazyType(Object key) {
    this(key, 0);
  }

  private LazyType(Object key, int cvAttributes) {
    super(null, 0, cvAttributes);
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(key != null, "key must not be null");
    }
    this.key = key;
    this.cvAttributes = cvAttributes;
  }

  public boolean isLazy() { return true; }
  public Object getKey()  { return key; }

  Type resolveTypes(BasicCDebugInfoDataBase db, ResolveListener listener) {
    BasicType t = (BasicType) db.resolveType(this, this, listener, "resolving lazy type");
    // Returned type might be lazy if there was an error
    if (t.isLazy()) {
      return this;
    }
    if (cvAttributes != 0) {
      return t.getCVVariant(cvAttributes);
    }
    return t;
  }

  public void iterateObject(Address a, ObjectVisitor v, FieldIdentifier f) {}
  protected Type createCVVariant(int cvAttributes) {
    return new LazyType(key, cvAttributes);
  }

  public void visit(TypeVisitor v) {}
}