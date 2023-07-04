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

// 71 errors expected.

import pkg.SuperClass;

class ProtectedMemberAccess2 {

  // Since this class is not a subclass of the class in which the
  // protected members are declared, all of the accesses are illegal.

  pkg.SuperClass x = new pkg.SuperClass();

  static pkg.SuperClass sx = new pkg.SuperClass();

  int i = x.pi;                                 // illegal
  int j = x.spi;                                // illegal

  int n = sx.pi;                                // illegal
  int m = sx.spi;                               // illegal

  static int sn = sx.pi;                        // illegal
  static int sm = sx.spi;                       // illegal

  int w = x.pm();                               // illegal
  int y = x.spm();                              // illegal

  int u = sx.pm();                              // illegal
  int v = sx.spm();                             // illegal

  pkg.SuperClass.pC  obj1;                      // illegal
  pkg.SuperClass.spC obj2;                      // illegal

  pkg.SuperClass.pI  obj3;                      // illegal
  pkg.SuperClass.spI obj4;                      // illegal

  Object o1 = (pkg.SuperClass.pC) null;         // illegal
  Object o2 = (pkg.SuperClass.spC) null;        // illegal

  Object o3 = (pkg.SuperClass.pI) null;         // illegal
  Object o4 = (pkg.SuperClass.spI) null;        // illegal

  class C1 extends pkg.SuperClass.pC {}         // illegal
  class C2 extends pkg.SuperClass.spC {}        // illegal

  interface I1 extends pkg.SuperClass.pI {}     // illegal
  interface I2 extends pkg.SuperClass.spI {}    // illegal

  static {

    pkg.SuperClass lx = new pkg.SuperClass();

    sx.pi  = 1;                                 // illegal
    sx.spi = 2;                                 // illegal

    lx.pi  = 1;                                 // illegal
    lx.spi = 2;                                 // illegal

    int n = sx.pi;                              // illegal
    int m = sx.spi;                             // illegal

    int k = lx.pi;                              // illegal
    int l = lx.spi;                             // illegal

    int u = sx.pm();                            // illegal
    int v = sx.spm();                           // illegal

    int w = lx.pm();                            // illegal
    int z = lx.spm();                           // illegal

    pkg.SuperClass.pC  obj1;                    // illegal
    pkg.SuperClass.spC obj2;                    // illegal

    pkg.SuperClass.pI  obj3;                    // illegal
    pkg.SuperClass.spI obj4;                    // illegal

    Object o1 = (pkg.SuperClass.pC) null;       // illegal
    Object o2 = (pkg.SuperClass.spC) null;      // illegal

    Object o3 = (pkg.SuperClass.pI) null;       // illegal
    Object o4 = (pkg.SuperClass.spI) null;      // illegal

    //class C1 extends pkg.SuperClass.pC {}
    class C2 extends pkg.SuperClass.spC {}      // illegal

    //interface I1 extends pkg.SuperClass.pI {}
    //interface I2 extends pkg.SuperClass.spI {}

  }

  void m() {

    pkg.SuperClass lx = new pkg.SuperClass();

    x.pi  = 1;                                  // illegal
    x.spi = 2;                                  // illegal

    sx.pi  = 1;                                 // illegal
    sx.spi = 2;                                 // illegal

    lx.pi  = 1;                                 // illegal
    lx.spi = 2;                                 // illegal

    int t = x.pm();                             // illegal
    int y = x.spm();                            // illegal

    int u = sx.pm();                            // illegal
    int v = sx.spm();                           // illegal

    int w = lx.pm();                            // illegal
    int z = lx.spm();                           // illegal

    int i = x.pi;                               // illegal
    int j = x.spi;                              // illegal

    int n = sx.pi;                              // illegal
    int m = sx.spi;                             // illegal

    int k = lx.pi;                              // illegal
    int l = lx.spi;                             // illegal

    pkg.SuperClass.pC  obj1;                    // illegal
    pkg.SuperClass.spC obj2;                    // illegal

    pkg.SuperClass.pI  obj3;                    // illegal
    pkg.SuperClass.spI obj4;                    // illegal

    Object o1 = (pkg.SuperClass.pC) null;       // illegal
    Object o2 = (pkg.SuperClass.spC) null;      // illegal

    Object o3 = (pkg.SuperClass.pI) null;       // illegal
    Object o4 = (pkg.SuperClass.spI) null;      // illegal

    class C1 extends pkg.SuperClass.pC {}       // illegal
    class C2 extends pkg.SuperClass.spC {}      // illegal

    //interface I1 extends pkg.SuperClass.pI {}
    //interface I2 extends pkg.SuperClass.spI {}

  }

}
