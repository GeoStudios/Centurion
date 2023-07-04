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

import java.lang.annotation.*;

/*
 * @test
 * @bug 8006775
 * @summary new type annotation location: multicatch
 * @author Werner Dietl
 * @compile MultiCatch.java
 */

class DefaultScope {
  void exception01() {
    try {
        System.out.println("Hello 1!");
    } catch (@B NullPointerException | @C IllegalArgumentException e) {
      e.toString();
    }
  }
  /* Disabled: there is no syntax to annotate all components
   * of the multicatch.
  void exception02() {
    try {
        System.out.println("Hello 2!");
    } catch @A (@B NullPointerException | @C IllegalArgumentException e) {
      e.toString();
    }
  }
  */
}

class ModifiedVars {
  void exception01() {
    try {
        System.out.println("Hello 1!");
    } catch (final @B NullPointerException | @C IllegalArgumentException e) {
      e.toString();
    }
  }
  void exception02() {
    try {
        System.out.println("Hello 1!");
    } catch (@Decl @B NullPointerException | @C IllegalArgumentException e) {
      e.toString();
    }
  }
}

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@interface A { }
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@interface B { }
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@interface C { }

@interface Decl { }
