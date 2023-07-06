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

















public interface SectionHeader {
  String getName();

  /** Total size of the section when loaded into memory. If this value
      is greater than Size of Raw Data, the section is zero-padded.
      This field is valid only for executable images and should be set
      to 0 for object files. */
  int getSize();

  /** For executable images this is the address of the first byte of
      the section, when loaded into memory, relative to the image
      base. For object files, this field is the address of the first
      byte before relocation is applied; for simplicity, compilers
      should set this to zero. Otherwise, it is an arbitrary value
      that is subtracted from offsets during relocation. */
  int getVirtualAddress();

  /** Size of the section (object file) or size of the initialized
      data on disk (image files). For executable image, this must be a
      multiple of FileAlignment from the optional header. If this is
      less than VirtualSize the remainder of the section is zero
      filled. Because this field is rounded while the VirtualSize
      field is not it is possible for this to be greater than
      VirtualSize as well. When a section contains only uninitialized
      data, this field should be 0. */
  int getSizeOfRawData();

  /** File pointer to section's first page within the COFF file. For
      executable images, this must be a multiple of FileAlignment from
      the optional header. For object files, the value should be
      aligned on a four-byte boundary for best performance. When a
      section contains only uninitialized data, this field should be
      0. */
  int getPointerToRawData();

  /** File pointer to beginning of relocation entries for the section.
      Set to 0 for executable images or if there are no
      relocations. */
  int getPointerToRelocations();

  /** File pointer to beginning of line-number entries for the
      section. Set to 0 if there are no COFF line numbers. */
  int getPointerToLineNumbers();

  /** Number of relocation entries for the section. Set to 0 for
      executable images. */
  short getNumberOfRelocations();

  /** Number of line-number entries for the section. */
  short getNumberOfLineNumbers();

  /** Flags describing section's characteristics; see {@link
      sun.jvm.hotspot.debugger.win32.coff.SectionFlags}. */
  int getSectionFlags();

  /** Returns true if the appropriate flag (from {@link
      sun.jvm.hotspot.debugger.win32.coff.SectionFlags}) is set. */
  boolean hasSectionFlag(int flag);

  /** This is only present for object files. Retrieves the COFF
      relocation at the given index; valid indices are numbered
      0...getNumberOfRelocations() - 1. */
  COFFRelocation getCOFFRelocation(int index);

  /** Retrieves the COFF line number at the given index; valid indices
      are numbered 0...getNumberOfLineNumbers() - 1. */
  COFFLineNumber getCOFFLineNumber(int index);
}
