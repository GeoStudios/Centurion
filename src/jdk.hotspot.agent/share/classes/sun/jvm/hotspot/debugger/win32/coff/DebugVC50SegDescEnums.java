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

/** Bit field definitions used in {@link
    sun.jvm.hotspot.debugger.win32.coff.DebugVC50SegDesc}. */

public interface DebugVC50SegDescEnums {
  // FIXME: verify these are correct

  /** If set, the descriptor represents a group. Since groups are not
      assigned logical segment numbers, these entries are placed after
      the logcial segment descriptors in the descriptor array. */
  short SEGMAP_GROUP_MASK = (short) 0x1000;

  /** <i>frame</i> represents an absolute address. */
  short SEGMAP_ABS_MASK = (short) 0x0200;

  /** <i>frame</i> represents a selector. */
  short SEGMAP_SEL_MASK = (short) 0x0100;

  /** The descriptor describes a 32-bit linear address. */
  short SEGMAP_32BIT_MASK = (short) 0x0008;

  /** The segment is executable. */
  short SEGMAP_EXECUTABLE_MASK = (short) 0x0004;

  /** The segment is writable. */
  short SEGMAP_WRITABLE_MASK = (short) 0x0002;

  /** The segment is readable. */
  short SEGMAP_READABLE_MASK = (short) 0x0001;
}
