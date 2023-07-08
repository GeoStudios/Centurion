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

package jit.Arrays.ArrayStoreCheck;


import nsk.share.TestFailure;














// A simple program checking whether ArrayStoreExceptions are thrown


/*
 * @test
 *
 * @summary converted from VM Testbase jit/Arrays/ArrayStoreCheck.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.Arrays.ArrayStoreCheck.ArrayStoreCheck
 */



class A {}

class B extends A {}

public class ArrayStoreCheck {

  static void doit(A list[], A element) {
    boolean caught = false;
    try {
      list[0] = element;
    } catch (Exception ex) {
      caught = true;
    }
    if (caught) {
        System.out.println("Array store check test passed");
    } else {
        throw new TestFailure("Array store check test failed");
    }
  }

  public static void main(String args[]) {
    doit(new B[1], new A());
  }

}
