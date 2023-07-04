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

/*
 * @test
 * @bug 8265917
 * @summary test the optimization of pow(x, 2.0)
 * @run main/othervm TestPow2Opt
 * @run main/othervm -Xint TestPow2Opt
 * @run main/othervm -Xbatch -XX:TieredStopAtLevel=1 TestPow2Opt
 * @run main/othervm -Xbatch -XX:-TieredCompilation  TestPow2Opt
 */

public class TestPow2Opt {

  static void test(double a) {
    double r1 = a * a;
    double r2 = Math.pow(a, 2.0);
    if (r1 != r2) {
      throw new RuntimeException("pow(" + a + ", 2.0), expected: " + r1 + ", actual: " + r2);
    }
  }

  public static void main(String[] args) throws Exception {
    for (int i = 0; i < 10; i++) {
      for (int j = 1; j < 100000; j++) {
        test(j * 1.0);
        test(1.0 / j);
      }
    }
  }

}
