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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;















/** <P> This is the basic interface which describes a field in a C/C++
    data structure or a Java object. </P>

    <P> The accessors in this class are designed to allow manual
    coercion of the data within the field, which is often necessary
    when interfacing with C programs. Therefore, the accessors here do
    not perform any type checking. Specializations of the Field
    interface, such as JByteField, provide getValue() methods which
    both perform type checking and return the appropriate specialized
    type. </P>

    <P> See @see CIntegerType for a description of why all C integer
    types are bundled into the category "CIntegerType". </P>

    <P> As an example, coercing a pointer field into an int can be
    done in the following fashion (assuming the application has
    registered an integer type in the type database called
    "intptr_t"): </P>

    <PRE>
    {
      ...
      Address myObject = ...;
      CIntegerType intptr_tType = (CIntegerType) db.lookupType("intptr_t");
      long addrVal = field.getCInteger(myObject, intptr_tType);
      ...
    }
    </PRE>

    FIXME: among other things, this interface is not sufficient to
    describe fields which are themselves arrays (like Symbol's
    u1 _body[1]).  */
public interface Field {
  /** Get the name of this field */
  String getName();

  /** Get the type of this field */
  Type getType();

  /** Get the size, in bytes, of this field. Used for manual data
      structure traversal where necessary. */
  long getSize();

  /** Is this a static field? */
  boolean isStatic();

  /** The offset of this field, in bytes, in its containing data
      structure, if nonstatic. If this is a static field, throws a
      WrongTypeException. */
  long getOffset() throws WrongTypeException;

  /** The address of this field, if it is a static field. If this is a
      nonstatic field, throws a WrongTypeException. */
  Address getStaticFieldAddress() throws WrongTypeException;

  /** <P> These accessors require that the field be nonstatic;
      otherwise, a WrongTypeException will be thrown. Note that type
      checking is not performed by these accessors in order to allow
      manual type coercion of field data. For better protection when
      accessing primitive fields, use the get(Type)Field accessors in
      Type.java. </P>

      <P> NOTE that the Address passed in to these routines may, in
      fact, be an OopHandle. Specifically, in a reflective system,
      dereferencing operations applied to the OopHandle must be
      performed atomically with respect to GC. </P>

      <P> See @see CIntegerType for a description of why all C integer
      types are bundled into the category "CIntegerType". </P>
  */
  boolean   getJBoolean (Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  byte      getJByte    (Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  char      getJChar    (Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  short     getJShort   (Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  int       getJInt     (Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  long      getJLong    (Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  float     getJFloat   (Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  double    getJDouble  (Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  long      getCInteger (Address addr, CIntegerType type)
    throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  Address   getAddress  (Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  OopHandle getOopHandle(Address addr)
    throws UnmappedAddressException, UnalignedAddressException, WrongTypeException, NotInHeapException;
  OopHandle getNarrowOopHandle(Address addr)
    throws UnmappedAddressException, UnalignedAddressException, WrongTypeException, NotInHeapException;

  /** <P> These accessors require that the field be static; otherwise,
      a WrongTypeException will be thrown. Note that type checking is
      not performed by these accessors in order to allow manual type
      coercion of field data. For better protection when accessing
      primitive fields, use the get(Type)Field accessors in
      Type.java. </P>

      <P> NOTE that the Address passed in to these routines may, in
      fact, be an OopHandle. Specifically, in a reflective system,
      dereferencing operations applied to the OopHandle must be
      performed atomically with respect to GC. </P>

      <P> See @see CIntegerType for a description of why all C integer
      types are bundled into the category "CIntegerType". </P>
  */
  boolean   getJBoolean () throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  byte      getJByte    () throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  char      getJChar    () throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  float     getJFloat   () throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  double    getJDouble  () throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  int       getJInt     () throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  long      getJLong    () throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  short     getJShort   () throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  long      getCInteger (CIntegerType type)
    throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
  Address   getAddress  () throws UnmappedAddressException, UnalignedAddressException;
  OopHandle getOopHandle()
    throws UnmappedAddressException, UnalignedAddressException, NotInHeapException;
  OopHandle getNarrowOopHandle()
    throws UnmappedAddressException, UnalignedAddressException, NotInHeapException;
}
