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

// FIXME: NOT FINISHED

public interface OptionalHeaderDataDirectories {
  /** Export Table address and size. */
  DataDirectory getExportTable() throws COFFException;

  /** Returns the Export Table, or null if none was present. */
  ExportDirectoryTable getExportDirectoryTable() throws COFFException;

  /** Import Table address and size */
  DataDirectory getImportTable() throws COFFException;

  /** Resource Table address and size. */
  DataDirectory getResourceTable() throws COFFException;

  /** Exception Table address and size. */
  DataDirectory getExceptionTable() throws COFFException;

  /** Attribute Certificate Table address and size. */
  DataDirectory getCertificateTable() throws COFFException;

  /** Base Relocation Table address and size. */
  DataDirectory getBaseRelocationTable() throws COFFException;

  /** Debug data starting address and size. */
  DataDirectory getDebug() throws COFFException;

  /** Returns the Debug Directory, or null if none was present. */
  DebugDirectory getDebugDirectory() throws COFFException;

  /** Architecture-specific data address and size. */
  DataDirectory getArchitecture() throws COFFException;

  /** Relative virtual address of the value to be stored in the global
      pointer register. Size member of this structure must be set to
      0. */
  DataDirectory getGlobalPtr() throws COFFException;

  /** Thread Local Storage (TLS) Table address and size. */
  DataDirectory getTLSTable() throws COFFException;

  /** Load Configuration Table address and size. */
  DataDirectory getLoadConfigTable() throws COFFException;

  /** Bound Import Table address and size. */
  DataDirectory getBoundImportTable() throws COFFException;

  /** Import Address Table address and size. */
  DataDirectory getImportAddressTable() throws COFFException;

  /** Address and size of the Delay Import Descriptor. */
  DataDirectory getDelayImportDescriptor() throws COFFException;

  /** COM+ Runtime Header address and size */
  DataDirectory getCOMPlusRuntimeHeader() throws COFFException;
}
