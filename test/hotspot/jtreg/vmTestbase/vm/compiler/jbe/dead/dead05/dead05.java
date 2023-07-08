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

package vm.compiler.jbe.dead.dead05;

/*
 * @test
 *
 * @summary converted from VM Testbase runtime/jbe/dead/dead05.
 * VM Testbase keywords: [quick, runtime]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm vm.compiler.jbe.dead.dead05.dead05
 */

/* -- Test the elimination of dead assignment to static class field
In the example below, all the values assigned to i in struct except of the last one are never used,thus can be eliminated.

 */

class struct {
    static int i = 6;
}

public class dead05 {

  public static void main(String args[]) {
    dead05 dce = new dead05();

    System.out.println("f()="+dce.f()+"; fopt()="+dce.fopt());
    if (dce.f() == dce.fopt()) {
      System.out.println("Test dead05 Passed.");
    } else {
      throw new Error("Test dead05 Failed: f()=" + dce.f() + " != fopt()=" + dce.fopt());
    }
  }

  int f() {

    struct.i = 1;
    struct.i = 2;
    struct.i = 3;
    struct.i = 4;
    struct.i = 5;
    struct.i = 6;
    struct.i = 7;
    struct.i = 8;

    struct.i = 1;
    struct.i = 2;
    struct.i = 3;
    struct.i = 4;
    struct.i = 5;
    struct.i = 6;
    struct.i = 7;
    struct.i = 8;

    struct.i = 1;
    struct.i = 2;
    struct.i = 3;
    struct.i = 4;
    struct.i = 5;
    struct.i = 6;
    struct.i = 7;
    struct.i = 8;

    return struct.i;
  }

  // Code fragment after dead code elimination
  int fopt() {

    struct.i = 8;
    return struct.i;
  }
}
