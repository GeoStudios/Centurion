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

















/** Models the "sstGlobalTypes" subsection in Visual C++ 5.0 debug
    information. This class provides access to the types via
    iterators; it does not instantiate objects to represent types
    because of the expected high volume of types. The caller is
    expected to traverse this table and convert the platform-dependent
    types into a platform-independent format at run time. */

public interface DebugVC50SSGlobalTypes extends DebugVC50Subsection {
  /** Number of types in the table. */
  int getNumTypes();

  /** Absolute file offset of the <i>i</i>th (0..getNumTypes() - 1)
      type in the table. */
  int getTypeOffset(int i);

  /** Create a new type iterator pointing to the first type in the
      subsection. */
  DebugVC50TypeIterator getTypeIterator();
}
