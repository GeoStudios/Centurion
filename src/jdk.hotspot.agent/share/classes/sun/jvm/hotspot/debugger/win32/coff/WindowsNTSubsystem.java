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

















public interface WindowsNTSubsystem {
  /** Unknown subsystem. */
  short IMAGE_SUBSYSTEM_UNKNOWN = (short) 0;

  /** Used for device drivers and native Windows NT processes. */
  short IMAGE_SUBSYSTEM_NATIVE = (short) 1;

  /** Image runs in the Windows graphical user interface (GUI) subsystem. */
  short IMAGE_SUBSYSTEM_WINDOWS_GUI = (short) 2;

  /** Image runs in the Windows character subsystem. */
  short IMAGE_SUBSYSTEM_WINDOWS_CUI = (short) 3;

  /** Image runs in the Posix character subsystem. */
  short IMAGE_SUBSYSTEM_POSIX_CUI = (short) 7;

  /** Image runs on Windows CE. */
  short IMAGE_SUBSYSTEM_WINDOWS_CE_GUI = (short) 9;

  /** Image is an EFI application. */
  short IMAGE_SUBSYSTEM_EFI_APPLICATION = (short) 10;

  /** Image is an EFI driver that provides boot services. */
  short IMAGE_SUBSYSTEM_EFI_BOOT_SERVICE_DRIVER = (short) 11;

  /** Image is an EFI driver that provides runtime services. */
  short IMAGE_SUBSYSTEM_EFI_RUNTIME_DRIVER = (short) 12;
}
