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

















public interface DebugVC50SSModule extends DebugVC50Subsection {
  short getOverlayNumber();
  /** Index into sstLibraries subsection if this module was linked
      from a library. */
  short getLibrariesIndex();

  /** Count of the number of code segments this module contributes to.
      This is the length of the SegInfo array, below. */
  short getNumCodeSegments();

  /** Debugging style for this module. Currently only "CV" is defined.
      A module can have only one debugging style. If a module contains
      debugging information in an unrecognized style, the information
      will be discarded. */
  short getDebuggingStyle();

  /** Fetch description of segment to which this module contributes
      code (0..getNumCodeSegments - 1) */
  DebugVC50SegInfo getSegInfo(int i);

  /** Name of the module */
  String getName();
}
