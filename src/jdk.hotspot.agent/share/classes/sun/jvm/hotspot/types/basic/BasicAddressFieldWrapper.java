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

/** A wrapper for a Field which adds getValue() methods returning
    Addresses. Must be a wrapper since the concrete type of the Field
    implementation will not be, for example, a BasicAddressField
    because, for simplicity, we don't currently understand pointer
    types in the Type system. */

public class BasicAddressFieldWrapper extends BasicFieldWrapper implements AddressField {
  public BasicAddressFieldWrapper(Field field) {
    super(field);
  }

  public Address getValue(Address addr)
    throws UnmappedAddressException, UnalignedAddressException, WrongTypeException {
    return field.getAddress(addr);
  }

  public Address getValue()
    throws UnmappedAddressException, UnalignedAddressException, WrongTypeException {
    return field.getAddress();
  }
}
