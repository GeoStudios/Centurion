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
  @bug 8000423 7197619 8025649
  @summary Check if diacritical signs could be typed for TextArea and TextField
  @run applet/manual=yesno DiacriticsTest.html
*/


import java.applet.Applet;
import java.awt.*;
import javax.swing.JPanel;


public class DiacriticsTest extends Applet {

    public void init() {
        this.setLayout(new BorderLayout());
    }

    public void start() {

        setSize(350, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        TextArea txtArea = new TextArea();
        panel.add(txtArea);

        TextField txtField = new TextField();
        panel.add(txtField);

        add(panel, BorderLayout.CENTER);

        validate();
        setVisible(true);
    }
}

