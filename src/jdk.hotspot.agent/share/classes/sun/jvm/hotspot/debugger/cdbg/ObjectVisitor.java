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

package sun.jvm.hotspot.debugger.cdbg;

import sun.jvm.hotspot.debugger.*;

/** Provides uniform visitation to primitive and compound objects.
    Typically one will have an Address of an "object" (in the
    idealistic C++ definition, including "primitive objects" like
    ints) and a Type for that object. This visitor interface allows
    one to either get the value of the object (if of primitive type)
    or iterate through its fields, getting the value of each, in a
    consistent fashion. Also supports iteration through arrays of
    known length. */

public interface ObjectVisitor {
  /** This is called before beginning iterating through either the
      fields declared in this compound type (not its superclasses) or
      the elements of this array */
  void enterType(Type type, Address objectAddress);

  /** This is called after finishing iterating through this compound
      type */
  void exitType();

  /** Primitive field or object of integer bitfield
      type. FieldIdentifier is null if not a field of an enclosing
      object. */
  void doBit(FieldIdentifier f, long val);

  /** Primitive field or object of integer type. FieldIdentifier is
      null if not a field of an enclosing object. */
  void doInt(FieldIdentifier f, long val);

  /** Primitive field or object of enumerated type type.
      FieldIdentifier is null if not a field of an enclosing
      object. */
  void doEnum(FieldIdentifier f, long val, String enumName);

  /** Primitive field or object of single-precision floating-point
      type. FieldIdentifier is null if not a field of an enclosing
      object. */
  void doFloat(FieldIdentifier f, float val);

  /** Primitive field or object of double-precision floating-point
      type. FieldIdentifier is null if not a field of an enclosing
      object. */
  void doDouble(FieldIdentifier f, double val);

  /** Primitive field or object of pointer type. FieldIdentifier is
      null if not a field of an enclosing object. */
  void doPointer(FieldIdentifier f, Address val);

  /** Primitive field or object of array type. FieldIdentifier is null
      if not a field of an enclosing object. */
  void doArray(FieldIdentifier f, Address val);

  /** Primitive field or object of (C++) reference
      type. FieldIdentifier is null if not a field of an enclosing
      object. */
  void doRef(FieldIdentifier f, Address val);

  /** Identifies embedded objects in compound objects. FieldIdentifier
      is null if not a field of an enclosing object. */
  void doCompound(FieldIdentifier f, Address addressOfEmbeddedCompoundObject);
}
