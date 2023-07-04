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

public interface OptionalHeaderWindowsSpecificFields {
  /** Preferred address of first byte of image when loaded into
      memory; must be a multiple of 64K. The default for DLLs is
      0x10000000. The default for Windows CE EXEs is 0x00010000. The
      default for Windows NT, Windows 95, and Windows 98 is
      0x00400000. */
  long getImageBase();

  /** Alignment (in bytes) of sections when loaded into memory. Must
      be greater or equal to File Alignment. Default is the page size
      for the architecture. */
  int getSectionAlignment();

  /** Alignment factor (in bytes) used to align the raw data of
      sections in the image file. The value should be a power of 2
      between 512 and 64K inclusive. The default is 512. If the
      SectionAlignment is less than the architecture's page size than
      this must match the SectionAlignment. */
  int getFileAlignment();

  /** Major version number of required OS. */
  short getMajorOperatingSystemVersion();

  /** Minor version number of required OS. */
  short getMinorOperatingSystemVersion();

  /** Major version number of image. */
  short getMajorImageVersion();

  /** Minor version number of image. */
  short getMinorImageVersion();

  /** Major version number of subsystem. */
  short getMajorSubsystemVersion();

  /** Minor version number of subsystem. */
  short getMinorSubsystemVersion();

  /** Size, in bytes, of image, including all headers; must be a
      multiple of Section Alignment. */
  int getSizeOfImage();

  /** Combined size of MS-DOS stub, PE Header, and section headers
      rounded up to a multiple of FileAlignment. */
  int getSizeOfHeaders();

  /** Image file checksum. The algorithm for computing is incorporated
      into IMAGHELP.DLL. The following are checked for validation at
      load time: all drivers, any DLL loaded at boot time, and any DLL
      that ends up in the server. */
  int getCheckSum();

  /** Subsystem required to run this image; returns one of the
      constants defined in {@link
      sun.jvm.hotspot.debugger.win32.coff.WindowsNTSubsystem}. */
  short getSubsystem();

  /** Indicates characteristics of a DLL; see {@link
      sun.jvm.hotspot.debugger.win32.coff.DLLCharacteristics}. */
  short getDLLCharacteristics();

  /** Size of stack to reserve. Only the Stack Commit Size is
      committed; the rest is made available one page at a time, until
      reserve size is reached. */
  long getSizeOfStackReserve();

  /** Size of stack to commit. */
  long getSizeOfStackCommit();

  /** Size of local heap space to reserve. Only the Heap Commit Size
      is committed; the rest is made available one page at a time,
      until reserve size is reached. */
  long getSizeOfHeapReserve();

  /** Size of local heap space to commit. */
  long getSizeOfHeapCommit();

  /** Obsolete. */
  int getLoaderFlags();

  /** Number of data-dictionary entries in the remainder of the
      Optional Header. Each describes a location and size. */
  int getNumberOfRvaAndSizes();
}
