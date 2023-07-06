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

package compiler.intrinsics;

import java.lang.reflect.Constructor;

/*
 * @test
 * @bug 8237524
 * @summary String.compareTo() may return incorrect result in this case
 *
 * @modules java.base/java.lang:open
 *
 * @run main/othervm -XX:+CompactStrings compiler.intrinsics.Test8237524
 */

public class Test8237524 {

  private static int stringCompareTo(String s1, String s2) {
    return s1.compareTo(s2);
  }

  public static void main(String[] args) throws Exception {
    Constructor<String> c = String.class.getDeclaredConstructor(byte[].class, byte.class);
    c.setAccessible(true);

    byte[] bytes = new byte[] {
      'Y', 'm', '_', 'l', 'V', 'n', 'W', 'S', 'w', 'm', 'W', 'S'
    };

    String s1 = c.newInstance(bytes, (byte) 0); // a Latin string
    String s2 = c.newInstance(bytes, (byte) 1); // a Chinese string

    for (int i = 0; i < 50000; i++) {
      if (stringCompareTo(s1, s2) >= 0) {
        System.out.println("FAIL. s1 should be less than s2 according to Java API Spec");
        System.exit(1);
      }
    }
  }
}
