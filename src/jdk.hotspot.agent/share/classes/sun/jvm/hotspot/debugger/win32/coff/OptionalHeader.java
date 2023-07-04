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

package sun.jvm.hotspot.debugger.win32.coff;

/** Models the information stored in the optional header of a Portable
    Executable file. */

public interface OptionalHeader {
  /** Magic number for a PE32 file */
  short MAGIC_PE32 = (short) 0x10B;

  /** Magic number for a PE32+ file */
  short MAGIC_PE32_PLUS = (short) 0x20B;

  /** Magic number for a "ROM image" */
  short MAGIC_ROM_IMAGE = (short) 0x107;

  /** Returns the magic number of the Optional Header ({@link
      #MAGIC_PE32}, {@link #MAGIC_PE32_PLUS}, or {@link
      #MAGIC_ROM_IMAGE}) */
  short getMagicNumber();

  /** These are defined for all implementations of COFF, including
      UNIX. */
  OptionalHeaderStandardFields getStandardFields();

  /** These include additional fields to support specific features of
      Windows (for example, subsystem). */
  OptionalHeaderWindowsSpecificFields getWindowsSpecificFields();

  /** Gets the data directories portion of the optional header. */
  OptionalHeaderDataDirectories getDataDirectories();
}
