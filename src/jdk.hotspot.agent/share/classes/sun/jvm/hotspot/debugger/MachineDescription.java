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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger;

import java.io.Serializable;

/** Encapsulates machine-specific information that currently must be
    exported up to the Java level. Implementations must be
    serializable. */

public interface MachineDescription extends Serializable {
  /** Returns the size of an address in bytes. Currently needed to be
      able to traverse arrays of pointers or oops. */
  long getAddressSize();

  /** Returns the maximum value of the C integer type with the given
      size in bytes and signedness. Throws IllegalArgumentException if
      the size in bytes is not legal for a C type (or can not be
      handled by this system). Note that the current implementation
      does not currently handle unsigned 8-byte longs properly. */
  long cIntegerTypeMaxValue(long sizeInBytes, boolean isUnsigned);

  /** Returns the minimum value of the C integer type with the given
      size in bytes and signedness. Throws IllegalArgumentException if
      the size in bytes is not legal for a C type (or can not be
      handled by this system). */
  long cIntegerTypeMinValue(long sizeInBytes, boolean isUnsigned);

  /** Indicates whether the CPU is big- or little-endian. This
      information is typically only needed by the Debugger
      implementation. */
  boolean isBigEndian();

  /** Indicates whether the underlying machine supports the LP64 data
      model (currently only SPARC/64). */
  boolean isLP64();
}
