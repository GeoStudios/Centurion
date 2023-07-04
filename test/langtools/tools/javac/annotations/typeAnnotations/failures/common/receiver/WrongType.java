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

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)
@interface A {}

class WrongType {
  Object f;

  void good1(@A WrongType this) {}

  void good2(@A WrongType this) {
    this.f = null;
    Object o = this.f;
  }

  void bad1(@A Object this) {}

  void bad2(@A Object this) {
    this.f = null;
    Object o = this.f;
  }

  void wow(@A XYZ this) {
    this.f = null;
  }

  class Inner {
    void good1(@A Inner this) {}
    void good2(@A WrongType.Inner this) {}

    void outerOnly(@A WrongType this) {}
    void wrongInner(@A Object this) {}
    void badOuter(@A Outer.Inner this) {}
    void badInner(@A WrongType.XY this) {}
  }

  class Generics<X> {
    <Y> void m(Generics<Y> this) {}
  }
}
