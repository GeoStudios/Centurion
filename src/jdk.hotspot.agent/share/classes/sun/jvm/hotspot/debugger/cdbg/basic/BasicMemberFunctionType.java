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















public class BasicMemberFunctionType extends BasicFunctionType implements MemberFunctionType {
  private Type containingClass;
  private Type thisType;
  private final long thisAdjust;

  public BasicMemberFunctionType(String name,
                                 int size,
                                 Type returnType,
                                 Type containingClass,
                                 Type thisType,
                                 long thisAdjust) {
    this(name, size, returnType, containingClass, thisType, thisAdjust, 0);
  }

  private BasicMemberFunctionType(String name,
                                  int size,
                                  Type returnType,
                                  Type containingClass,
                                  Type thisType,
                                  long thisAdjust,
                                  int cvAttributes) {
    super(name, size, returnType, cvAttributes);
    this.containingClass = containingClass;
    this.thisType        = thisType;
    this.thisAdjust      = thisAdjust;
  }

  public MemberFunctionType asMemberFunction() { return this; }

  public Type getContainingClass()       { return containingClass; }
  public Type getThisType()              { return thisType; }
  public long getThisAdjust()            { return thisAdjust; }

  Type resolveTypes(BasicCDebugInfoDataBase db, ResolveListener listener) {
    super.resolveTypes(db, listener);
    containingClass = db.resolveType(this, containingClass, listener, "resolving member function class");
    thisType        = db.resolveType(this, thisType,        listener, "resolving member function \"this\" type");
    return this;
  }

  public void iterateObject(Address a, ObjectVisitor v, FieldIdentifier f) {
    // FIXME: nothing to do here? Are we going to provide iteration
    // mechanisms through member functions, and if so what are the
    // types of those functions going to be?
  }

  protected Type createCVVariant(int cvAttributes) {
    return new BasicMemberFunctionType(getName(),
                                       getSize(),
                                       getReturnType(),
                                       getContainingClass(),
                                       getThisType(),
                                       getThisAdjust(),
                                       cvAttributes);
  }

  public void visit(TypeVisitor v) {
    v.doMemberFunctionType(this);
  }
}
