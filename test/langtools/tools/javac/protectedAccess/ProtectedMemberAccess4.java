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

// 33 errors expected.

import pkg.SuperClass;

class ProtectedMemberAccess4 extends pkg.SuperClass {

  // Access to a protected instance (non-static) field, instance method,
  // or member type by a qualified name is always legal in a subclass of
  // the class in which the member is declared.  Such access to a protected
  // instance field or instance method is allowed only if the qualifying type
  // or the type of the qualifying expression is (a subclass of) the class
  // in which the reference occurs.  In this test case, the qualifying type
  // is a superclass, thus all of the qualified references to instance fields
  // and instance methods are illegal.

  pkg.SuperClass x = new pkg.SuperClass();

  static pkg.SuperClass sx = new pkg.SuperClass();

  int i = x.pi;                                 // illegal
  int j = x.spi;                                // ok

  int n = sx.pi;                                // illegal
  int m = sx.spi;                               // ok

  static int sn = sx.pi;                        // illegal
  static int sm = sx.spi;                       // ok

  int w = x.pm();                               // illegal
  int y = x.spm();                              // ok

  int u = sx.pm();                              // illegal
  int v = sx.spm();                             // ok

  pkg.SuperClass.pC  obj1;                      // ok
  pkg.SuperClass.spC obj2;                      // ok

  pkg.SuperClass.pI  obj3;                      // ok
  pkg.SuperClass.spI obj4;                      // ok

  Object o1 = (pkg.SuperClass.pC) null;         // ok
  Object o2 = (pkg.SuperClass.spC) null;        // ok

  Object o3 = (pkg.SuperClass.pI) null;         // ok
  Object o4 = (pkg.SuperClass.spI) null;        // ok

  class C1 extends pkg.SuperClass.pC {}         // ok
  class C2 extends pkg.SuperClass.spC {}        // ok

  interface I1 extends pkg.SuperClass.pI {}     // ok
  interface I2 extends pkg.SuperClass.spI {}    // ok

  static {

    pkg.SuperClass lx = new pkg.SuperClass();

    sx.pi  = 1;                                 // illegal
    sx.spi = 2;                                 // ok

    lx.pi  = 1;                                 // illegal
    lx.spi = 2;                                 // ok

    int n = sx.pi;                              // illegal
    int m = sx.spi;                             // ok

    int k = lx.pi;                              // illegal
    int l = lx.spi;                             // ok

    int u = sx.pm();                            // illegal
    int v = sx.spm();                           // ok

    int w = lx.pm();                            // illegal
    int z = lx.spm();                           // ok

    pkg.SuperClass.pC  obj1;                    // ok
    pkg.SuperClass.spC obj2;                    // ok

    pkg.SuperClass.pI  obj3;                    // ok
    pkg.SuperClass.spI obj4;                    // ok

    Object o1 = (pkg.SuperClass.pC) null;       // ok
    Object o2 = (pkg.SuperClass.spC) null;      // ok

    Object o3 = (pkg.SuperClass.pI) null;       // ok
    Object o4 = (pkg.SuperClass.spI) null;      // ok

    //class C1 extends pkg.SuperClass.pC {}
    class C2 extends pkg.SuperClass.spC {}      // ok

    //interface I1 extends pkg.SuperClass.pI {}
    //interface I2 extends pkg.SuperClass.spI {}

  }

  void m() {

    pkg.SuperClass lx = new pkg.SuperClass();

    x.pi  = 1;                                  // illegal
    x.spi = 2;                                  // ok

    sx.pi  = 1;                                 // illegal
    sx.spi = 2;                                 // ok

    lx.pi  = 1;                                 // illegal
    lx.spi = 2;                                 // ok

    int t = x.pm();                             // illegal
    int y = x.spm();                            // ok

    int u = sx.pm();                            // illegal
    int v = sx.spm();                           // ok

    int w = lx.pm();                            // illegal
    int z = lx.spm();                           // ok

    int i = x.pi;                               // illegal
    int j = x.spi;                              // ok

    int n = sx.pi;                              // illegal
    int m = sx.spi;                             // ok

    int k = lx.pi;                              // illegal
    int l = lx.spi;                             // ok

    pkg.SuperClass.pC  obj1;                    // ok
    pkg.SuperClass.spC obj2;                    // ok

    pkg.SuperClass.pI  obj3;                    // ok
    pkg.SuperClass.spI obj4;                    // ok

    Object o1 = (pkg.SuperClass.pC) null;       // ok
    Object o2 = (pkg.SuperClass.spC) null;      // ok

    Object o3 = (pkg.SuperClass.pI) null;       // ok
    Object o4 = (pkg.SuperClass.spI) null;      // ok

    class C1 extends pkg.SuperClass.pC {}       // ok
    class C2 extends pkg.SuperClass.spC {}      // ok

    //interface I1 extends pkg.SuperClass.pI {}
    //interface I2 extends pkg.SuperClass.spI {}

  }

  class Inner {

    int i = x.pi;                               // illegal
    int j = x.spi;                              // ok

    int n = sx.pi;                              // illegal
    int m = sx.spi;                             // ok

    //static int sn = sx.pi;
    //static int sm = sx.spi;

    int w = x.pm();                             // illegal
    int y = x.spm();                            // ok

    int u = sx.pm();                            // illegal
    int v = sx.spm();                           // ok

    pkg.SuperClass.pC  obj1;                    // ok
    pkg.SuperClass.spC obj2;                    // ok

    pkg.SuperClass.pI  obj3;                    // ok
    pkg.SuperClass.spI obj4;                    // ok

    Object o1 = (pkg.SuperClass.pC) null;       // ok
    Object o2 = (pkg.SuperClass.spC) null;      // ok

    Object o3 = (pkg.SuperClass.pI) null;       // ok
    Object o4 = (pkg.SuperClass.spI) null;      // ok

    class C1 extends pkg.SuperClass.pC {}       // ok
    class C2 extends pkg.SuperClass.spC {}      // ok

    //interface I1 extends pkg.SuperClass.pI {}
    //interface I2 extends pkg.SuperClass.spI {}

    // Not allowed in inner classes.
    // static { ... }

    void m() {

      pkg.SuperClass lx = new pkg.SuperClass();

      x.pi  = 1;                                // illegal
      x.spi = 2;                                // ok

      sx.pi  = 1;                               // illegal
      sx.spi = 2;                               // ok

      lx.pi  = 1;                               // illegal
      lx.spi = 2;                               // ok

      int t = x.pm();                           // illegal
      int y = x.spm();                          // ok

      int u = sx.pm();                          // illegal
      int v = sx.spm();                         // ok

      int w = lx.pm();                          // illegal
      int z = lx.spm();                         // ok

      int i = x.pi;                             // illegal
      int j = x.spi;                            // ok

      int n = sx.pi;                            // illegal
      int m = sx.spi;                           // ok

      int k = lx.pi;                            // illegal
      int l = lx.spi;                           // ok

      pkg.SuperClass.pC  obj1;                  // ok
      pkg.SuperClass.spC obj2;                  // ok

      pkg.SuperClass.pI  obj3;                  // ok
      pkg.SuperClass.spI obj4;                  // ok

      Object o1 = (pkg.SuperClass.pC) null;     // ok
      Object o2 = (pkg.SuperClass.spC) null;    // ok

      Object o3 = (pkg.SuperClass.pI) null;     // ok
      Object o4 = (pkg.SuperClass.spI) null;    // ok

      class C1 extends pkg.SuperClass.pC {}     // ok
      class C2 extends pkg.SuperClass.spC {}    // ok

      //interface I1 extends pkg.SuperClass.pI {}
      //interface I2 extends pkg.SuperClass.spI {}

    }

  }

}
