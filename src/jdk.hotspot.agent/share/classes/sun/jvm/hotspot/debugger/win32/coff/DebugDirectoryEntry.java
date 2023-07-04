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

public interface DebugDirectoryEntry {
  /** A reserved field intended to be used for flags, set to zero for
      now. */
  int getCharacteristics();

  /** Time and date the debug data was created. */
  int getTimeDateStamp();

  /** Major version number of the debug data format. */
  short getMajorVersion();

  /** Minor version number of the debug data format. */
  short getMinorVersion();

  /** Format of debugging information: this field enables support of
      multiple debuggers. See
      @link{sun.jvm.hotspot.debugger.win32.coff.DebugTypes}. */
  int getType();

  /** Size of the debug data (not including the debug directory itself). */
  int getSizeOfData();

  /** Address of the debug data when loaded, relative to the image base. */
  int getAddressOfRawData();

  /** File pointer to the debug data. */
  int getPointerToRawData();

  /** If this debug directory entry is of type
      IMAGE_DEBUG_TYPE_CODEVIEW (see
      @link{sun.jvm.hotspot.debugger.win32.coff.DebugTypes}), returns
      the contents as a DebugVC50 object; otherwise, returns null. */
  DebugVC50 getDebugVC50();

  /** Placeholder */
  byte getRawDataByte(int i);
}
