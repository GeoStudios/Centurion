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


import java.util.*;















/** <P> This subinterface of Type provides accessors to deal with all
    C integer types. The observation is that, according to the C
    specification, there is no guarantee that the integer types of
    char, short, int, and long will not all be of the same size,
    meaning that it is incorrect to use any Java primitive type that
    is too small to hold all of these values when talking about C
    integer types, signed or unsigned. </P>

    <P> Therefore we use long, the largest Java primitive type,
    universally to hold C integer field values (deciding that a
    ubiquitous change to BigInteger is not currently advantageous).
    Routines which read C integers from fields know the fields' sizes
    and signedness and read the appropriate number of bytes and handle
    sign- or zero- extension for signed and unsigned types,
    respectively. Unfortunately, since long is a signed 64-bit
    integer, there will be problems handling C's unsigned 64-bit
    integers, but these problems must be dealt with by the user. </P> */
public interface CIntegerType extends Type {
  /** Is this integer type unsigned? */
  boolean isUnsigned();

  /** What is the maximum value of this type? Note that this will not
      work properly for unsigned long longs. */
  long maxValue();

  /** What is the minimum value of this type? Note that this will not
      work properly for unsigned long longs. */
  long minValue();
}
