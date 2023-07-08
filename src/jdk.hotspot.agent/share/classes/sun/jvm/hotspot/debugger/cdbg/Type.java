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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;

/** Models a C or C++ type. Symbols have an associated Type. */

public interface Type {
  String       getName();
  /** Size of the type in bytes */
  int          getSize();

  BitType      asBit();
  IntType      asInt();
  EnumType     asEnum();
  FloatType    asFloat();
  DoubleType   asDouble();
  PointerType  asPointer();
  ArrayType    asArray();
  RefType      asRef();
  CompoundType asCompound();
  FunctionType asFunction();
  MemberFunctionType asMemberFunction();
  VoidType     asVoid();

  boolean      isBit();
  boolean      isInt();
  boolean      isEnum();
  boolean      isFloat();
  boolean      isDouble();
  boolean      isPointer();
  boolean      isArray();
  boolean      isRef();
  boolean      isCompound();
  boolean      isFunction();
  boolean      isMemberFunction();
  boolean      isVoid();

  boolean      isConst();
  boolean      isVolatile();

  /** Visit an object of this type at the given address with the
      specified visitor */
  void iterateObject(Address a, ObjectVisitor v);

  /** Alternate visitor which allows end user to specify the
      FieldIdentifier associated with this type (typically for
      visiting locals in a frame) */
  void iterateObject(Address a, ObjectVisitor v, FieldIdentifier f);

  /** Returns getName() unless a subclass can return something more
      appropriate */
  String toString();

  /*
  // Kinds of types

  // Primitive types
  private static final int BIT;    // Specialized integer type with bit offset and size
  private static final int INT;    // Integer type of any size and signedness
  private static final int FLOAT;  // Single-precision floating-point
  private static final int DOUBLE; // Double-precision floating-point

  // Pointer and related types
  private static final int PTR;    // Any pointer type
  private static final int ARRAY;  // Array type with known size
  private static final int REF;    // C++ references

  // Compound types
  private static final int COMPOUND;

  // Function type
  private static final int FUNC;

  // Template types
  private static final int TEMPLATE_CLASS;
  private static final int TEMPLATE_STRUCT;
  private static final int TEMPLATE_UNION;
  private static final int TEMPLATE_FUNCTION;
  */
}
