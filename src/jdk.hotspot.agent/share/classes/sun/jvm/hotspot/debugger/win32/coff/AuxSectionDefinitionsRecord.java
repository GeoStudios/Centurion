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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.win32.coff;

public interface AuxSectionDefinitionsRecord extends AuxSymbolRecord {
  /** Size of section data; same as Size of Raw Data in the section
      header. */
  int getLength();

  /** Number of relocation entries for the section. */
  short getNumberOfRelocations();

  /** Number of line-number entries for the section. */
  short getNumberOfLineNumbers();

  /** Checksum for communal data. Applicable if the
      IMAGE_SCN_LNK_COMDAT flag is set in the section header. */
  int getCheckSum();

  /** One-based index into the Section Table for the associated
      section; used when the COMDAT Selection setting is 5. */
  short getNumber();

  /** COMDAT selection number. Applicable if the section is a COMDAT
      section. See {@link
      sun.jvm.hotspot.debugger.win32.coff.COMDATSelectionTypes}. */
  byte getSelection();
}
