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

package ThrowsIntersection_2;
















/*
 * @test
 * @bug 4042259
 * @summary Check that a class can inherit multiple methods with conflicting throws clauses.
 * @author maddox
 *
 * @compile ThrowsIntersection_2.java
 */


class Ex1 extends Exception {}
class Ex2 extends Exception {}
class Ex3 extends Exception {}

interface a {
  int m1() throws Ex1, Ex3;
}

interface b {
  int m1() throws Ex3, Ex2;
}

// Either order should work
abstract class c1 implements a, b {}
abstract class c2 implements b, a {}

class d extends c1  {
  public int m1() throws Ex3 {
    return 1;
  }
}

class e extends c2 {
  public int m1() throws Ex3 {
    return 1;
  }
}