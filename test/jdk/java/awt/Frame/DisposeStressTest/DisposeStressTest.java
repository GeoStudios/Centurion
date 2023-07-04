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
  @bug 4051487 4145670
  @summary Tests that disposing of an empty Frame or a Frame with a MenuBar
           while it is being created does not crash the VM.
  @run main/timeout=7200 DisposeStressTest
*/

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class DisposeStressTest {

    public static void main(final String[] args) {
        for (int i = 0; i < 1000; i++) {
            Frame f = new Frame();
            f.setBounds(10, 10, 10, 10);
            f.show();
            f.dispose();

            Frame f2 = new Frame();
            f2.setBounds(10, 10, 100, 100);
            MenuBar bar = new MenuBar();
            Menu menu = new Menu();
            menu.add(new MenuItem("foo"));
            bar.add(menu);
            f2.setMenuBar(bar);
            f2.show();
            f2.dispose();
        }
    }
}
