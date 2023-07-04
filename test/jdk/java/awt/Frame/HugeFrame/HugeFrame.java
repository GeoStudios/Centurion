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
  @test
  @key headful
  @bug 7160609
  @summary A window with huge dimensions shouldn't crash JVM
  @author anthony.petrov@oracle.com: area=awt.toplevel
  @run main HugeFrame
*/

import java.awt.*;

public class HugeFrame {
    public static void main(String[] args) throws Exception {
        Frame f = new Frame("Huge");

        // 8193+ should already produce a crash, but let's go extreme...
        f.setBounds(10, 10, 30000, 500000);
        f.setVisible(true);

        // We would crash by now if the bug wasn't fixed
        Thread.sleep(1000);
        System.err.println(f.getBounds());

        // Cleanup
        f.dispose();
    }
}
