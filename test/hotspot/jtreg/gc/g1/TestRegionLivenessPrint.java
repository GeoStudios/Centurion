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

package gc.g1;

import sun.hotspot.WhiteBox;

/*
 * @test TestRegionLivenessPrint.java
 * @bug 8151920
 * @requires vm.gc.G1
 * @summary Make sure that G1 does not assert when printing region liveness data on a humongous continues region.
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run main/othervm -Xbootclasspath/a:. -XX:+UnlockDiagnosticVMOptions -XX:+WhiteBoxAPI -XX:+UseG1GC -Xmx128M -XX:G1HeapRegionSize=1m -Xlog:gc+liveness=trace gc.g1.TestRegionLivenessPrint
 */

public class TestRegionLivenessPrint {

  static byte[] bigobj = new byte[1024* 1024 * 2];

  public static void main(String[] args) throws InterruptedException {
      WhiteBox wb = WhiteBox.getWhiteBox();
      // Run a concurrent mark cycle to trigger the liveness accounting log messages.
      wb.g1StartConcMarkCycle();
      while (wb.g1InConcurrentMark()) {
          Thread.sleep(100);
      }
  }

}
