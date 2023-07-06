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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.basic;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;

/** This interface is designed to allow a platform-specific
    implementation of the TypeDataBase.isOfType() method, while
    leaving the rest of the basic.* package platform independent. */

public interface VtblAccess {
  /** This is the necessarily platform-specific implementation.
      Attempt to return the address of the vtbl for the given
      polymorphic C++ type. This value will be used when searching
      nearby memory to implement isOfType() in as platform-independent
      a manner as possible. Returns null if this value was not
      available for the given type, which might indicate that the type
      was not polymorphic or that an error occurred while trying to
      find the symbol. Note that this method does not support multiple
      inheritance. */
  Address getVtblForType(Type type);

  /** Clear any cached values from symbol lookups in the target
      process. It is important that this mechanism be fast and for
      that reason the default implementation memoizes type-to-vtbl
      mappings. However, if the target process is resumed, these
      mappings may become invalid. */
  void clearCaches();
}
