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

package gc.epsilon;

import java.util.Random;
import jdk.test.lib.Utils;

/**
 * @test TestByteArrays
 * @key randomness
 * @requires vm.gc.Epsilon & os.maxMemory > 1G
 * @summary Epsilon is able to allocate arrays, and does not corrupt their state
 * @library /test/lib
 *
 * @run main/othervm -Xmx1g                                        -XX:+UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 * @run main/othervm -Xmx1g -Xint                                  -XX:+UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 * @run main/othervm -Xmx1g -Xbatch -Xcomp                         -XX:+UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 * @run main/othervm -Xmx1g -Xbatch -Xcomp -XX:TieredStopAtLevel=1 -XX:+UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 * @run main/othervm -Xmx1g -Xbatch -Xcomp -XX:-TieredCompilation  -XX:+UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 *
 * @run main/othervm -Xmx1g                                        -XX:-UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 * @run main/othervm -Xmx1g -Xint                                  -XX:-UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 * @run main/othervm -Xmx1g -Xbatch -Xcomp                         -XX:-UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 * @run main/othervm -Xmx1g -Xbatch -Xcomp -XX:TieredStopAtLevel=1 -XX:-UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 * @run main/othervm -Xmx1g -Xbatch -Xcomp -XX:-TieredCompilation  -XX:-UseTLAB -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC gc.epsilon.TestByteArrays
 */

public class TestByteArrays {
  static int COUNT = Integer.getInteger("count", 3000); // ~500 MB allocation

  static byte[][] arr;

  public static void main(String[] args) throws Exception {
    Random r = Utils.getRandomInstance();

    arr = new byte[COUNT * 100][];
    for (int c = 0; c < COUNT; c++) {
      arr[c] = new byte[c * 100];
      for (int v = 0; v < c; v++) {
        arr[c][v] = (byte)(r.nextInt(255) & 0xFF);
      }
    }

    r = new Random(Utils.SEED);
    for (int c = 0; c < COUNT; c++) {
      byte[] b = arr[c];
      if (b.length != (c * 100)) {
        throw new IllegalStateException("Failure: length = " + b.length + ", need = " + (c*100));
      }
      for (int v = 0; v < c; v++) {
        byte actual = b[v];
        byte expected = (byte)(r.nextInt(255) & 0xFF);
        if (actual != expected) {
          throw new IllegalStateException("Failure: expected = " + expected + ", actual = " + actual);
        }
      }
    }
  }
}