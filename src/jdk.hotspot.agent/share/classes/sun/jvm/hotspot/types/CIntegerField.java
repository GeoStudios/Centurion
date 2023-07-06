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

/** A specialization of Field which represents a field referring to a
    C integer value. See CIntegerType for a discussion of C integer
    types and why this class is not specialized for various int sizes
    or signed/unsigned. */

public interface CIntegerField extends Field {
  boolean isUnsigned();

  /** The field must be nonstatic and of integer type, or a
      WrongTypeException will be thrown. */
  long getValue(Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;

  /** The field must be static and of integer type, or a
      WrongTypeException will be thrown. */
  long getValue() throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
}