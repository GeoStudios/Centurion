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
 * @key headful
 * @bug 8001470
 * @summary JTextField's size is computed incorrectly when it contains Indic or Thai characters
 * @author Semyon Sadetsky
 */

import javax.swing.*;
import java.awt.*;

public class bug8001470 {

    private static JFrame frame;
    private static JTextField textField1;
    private static JTextField textField2;

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("JTextField Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel container = (JPanel) frame.getContentPane();
                container.setLayout(new GridLayout(2,1));

                textField1 = new JTextField("\u0e01");
                textField2 = new JTextField("\u0c01");

                container.add(textField1);
                container.add(textField2);
                frame.setVisible(true);
                frame.pack();
            }
        });
        if( textField1.getHeight() < 10 || textField2.getHeight() < 10 )
            throw new Exception("Wrong field height");
        System.out.println("ok");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.dispose();
            }
        });
    }
}
