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


import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Assert;















public class BasicEnumType extends BasicIntType implements EnumType {
  // Integer type or lazy type
  private Type underlyingType;

  private static class Enum {
    String name;
    long   value;
    Enum(String name, long value) {
      this.name = name;
      this.value = value;
    }

    String getName()  { return name; }
    long   getValue() { return value; }
  }
  private List<Enum> enums;

  /** Underlying type of enum must be an integer type (or as yet
      unresolved) */

  public BasicEnumType(String name, Type underlyingType) {
    this(name, underlyingType, 0);
  }

  private BasicEnumType(String name, Type underlyingType, int cvAttributes) {
    super(name, 0, false, cvAttributes);
    this.underlyingType = underlyingType;
  }

  public EnumType asEnum()    { return this; }

  public int     getSize() { return underlyingType.getSize(); }
  public boolean isUnsigned() {
    if (underlyingType.isInt()) {
      return ((IntType) underlyingType).isUnsigned();
    }
    return false;
  }

  public void addEnum(String name, long val) {
    if (enums == null) {
      enums = new ArrayList<>();
    }
    enums.add(new Enum(name, val));
  }

  public int    getNumEnumerates()  { return enums.size(); }
  public String getEnumName(int i)  { return enums.get(i).getName();  }
  public long   getEnumValue(int i) { return enums.get(i).getValue(); }

  public String enumNameForValue(long val) {
    if (enums == null) {
      return null;
    }

    for (Iterator iter = enums.iterator(); iter.hasNext(); ) {
      Enum e = (Enum) iter.next();
      if (e.getValue() == val) {
        return e.getName();
      }
    }

    return null;
  }

  Type resolveTypes(BasicCDebugInfoDataBase db, ResolveListener listener) {
    super.resolveTypes(db, listener);
    underlyingType = db.resolveType(this, underlyingType, listener, "resolving enum type");
    if (Assert.ASSERTS_ENABLED) {
      BasicType b = (BasicType) underlyingType;
      Assert.that(b.isLazy() || b.isInt(),
                  "Underlying type of enum must be integer type (or unresolved due to error)");
    }
    return this;
  }

  public void iterateObject(Address a, ObjectVisitor v, FieldIdentifier f) {
    long val = a.getCIntegerAt(0, getSize(), isUnsigned());
    v.doEnum(f, val, enumNameForValue(val));
  }

  protected Type createCVVariant(int cvAttributes) {
    BasicEnumType t = new BasicEnumType(getName(), underlyingType, cvAttributes);
    t.enums = enums;
    return t;
  }

  public void visit(TypeVisitor v) {
    v.doEnumType(this);
  }
}
