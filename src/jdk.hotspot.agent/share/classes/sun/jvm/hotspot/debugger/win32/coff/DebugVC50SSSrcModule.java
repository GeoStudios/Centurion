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

public interface DebugVC50SSSrcModule extends DebugVC50Subsection {
  /** The number of source files contributing code to segments. */
  int getNumSourceFiles();

  /** The number of code segments receiving code from this module. */
  int getNumCodeSegments();

  /** Get description of <i>i</i>th source file
      (0..getNumSourceFiles() - 1) contributing code to this
      module. */
  DebugVC50SrcModFileDesc getSourceFileDesc(int i);

  /** Get <i>i</i>th (0..getNumCodeSegments() - 1) start offset,
      within a segment, of code contributed to that segment. If this
      and the end offset are both 0, the start/end offsets are not
      known and the file and line tables must be examined to find
      information about the program counter of interest. */
  int getSegmentStartOffset(int i);

  /** Get <i>i</i>th (0..getNumCodeSegments() - 1) end offset, within
      a segment, of code contributed to that segment. If this and the
      start offset are both 0, the start/end offsets are not known and
      the file and line tables must be examined to find information
      about the program counter of interest. */
  int getSegmentEndOffset(int i);

  /** Get <i>i</i>th (0..getNumCodeSegments() - 1) segment to which
      this module contributes code. See {@link
      sun.jvm.hotspot.debugger.win32.coff.COFFHeader.getSectionHeader}. */
  int getSegment(int i);
}
