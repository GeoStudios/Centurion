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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.basic;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;

/** A specialization of Field which represents a field containing a
    Java short value (in either a C/C++ data structure or a Java
    object) and which adds typechecked getValue() routines returning
    shorts. */

public class BasicJShortField extends BasicField implements JShortField {
  public BasicJShortField(BasicTypeDataBase db, Type containingType, String name, Type type,
                          boolean isStatic, long offset, Address staticFieldAddress) {
    super(db, containingType, name, type, isStatic, offset, staticFieldAddress);

    if (!type.equals(db.getJShortType())) {
      throw new WrongTypeException("Type of a BasicJShortField must be equal to TypeDataBase.getJShortType()");
    }
  }

  /** The field must be nonstatic and the type of the field must be a
      Java short, or a WrongTypeException will be thrown. */
  public short getValue(Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException {
    return getJShort(addr);
  }

  /** The field must be static and the type of the field must be a
      Java short, or a WrongTypeException will be thrown. */
  public short getValue() throws UnmappedAddressException, UnalignedAddressException, WrongTypeException {
    return getJShort();
  }
}
