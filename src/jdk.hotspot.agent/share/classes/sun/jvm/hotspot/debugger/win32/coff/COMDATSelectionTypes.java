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

/** Enumerates the COMDAT selection types. One of these is returned
    from {@link
    sun.jvm.hotspot.debugger.win32.coff.AuxSectionDefinitionsRecord#getSelection()}. */

public interface COMDATSelectionTypes {
  /** The linker issues a multiply defined symbol error if this symbol
      is already defined. */
  byte IMAGE_COMDAT_SELECT_NODUPLICATES = 1;
  /** Any section defining the same COMDAT symbol may be linked; the
      rest are removed. */
  byte IMAGE_COMDAT_SELECT_ANY = 2;
  /** The linker chooses an arbitrary section among the definitions
      for this symbol. A multiply defined symbol error is issued if
      all definitions don't have the same size. */
  byte IMAGE_COMDAT_SELECT_SAME_SIZE = 3;
  /** The linker chooses an arbitrary section among the definitions
      for this symbol. A multiply defined symbol error is issued if
      all definitions don't match exactly. */
  byte IMAGE_COMDAT_SELECT_EXACT_MATCH = 4;
  /** The section is linked if a certain other COMDAT section is
      linked. This other section is indicated by the Number field of
      the auxiliary symbol record for the section definition. Use of
      this setting is useful for definitions that have components in
      multiple sections (for example, code in one and data in
      another), but where all must be linked or discarded as a set. */
  byte IMAGE_COMDAT_SELECT_ASSOCIATIVE = 5;
  /** The linker chooses the largest from the definitions for this
      symbol. If multiple definitions have this size the choice
      between them is arbitrary. */
  byte IMAGE_COMDAT_SELECT_LARGEST = 6;
}