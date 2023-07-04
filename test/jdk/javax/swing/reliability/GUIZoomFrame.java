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

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * @author Aruna Samji
 */

public class GUIZoomFrame extends Frame {

    JFrame jframe1, jframe2;
    JButton jbutton;
    JTextArea jtextarea;
    volatile boolean maxHor, maxVer, maxBoth, normal, iconify;

    public GUIZoomFrame() {
        //GUI for ZoomJFrameChangeState
        jframe1 = new JFrame("ZoomJFrameChangeState");
        jframe1.getContentPane().setBackground(Color.red);
        jframe1.getContentPane().setLayout(null);
        jframe1.setSize(500,270);

        //GUI for ZoomJFrameRepaint
        jframe2 = new JFrame("ZoomJFrameRepaint");
        jframe2.getContentPane().setBackground(Color.red);
        jframe2.setSize(500, 270);
        jframe2.getContentPane().setLayout(null);
        jbutton = new JButton("TestButton");
        jtextarea = new JTextArea("TextArea");
        jbutton.setBounds(20, 100, 80, 60);
        jtextarea.setBounds(120, 100, 80, 60);

        //Listeners for ZoomJFrameChangeState
        jframe1.addWindowStateListener(new WindowAdapter() {
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == Frame.MAXIMIZED_BOTH)
                    maxBoth = true;

                if (e.getNewState() == Frame.NORMAL)
                    normal = true;

                if (e.getNewState() == Frame.ICONIFIED)
                    iconify = true;

                if (e.getNewState() == Frame.MAXIMIZED_HORIZ)
                    maxHor = true;

                if (e.getNewState() == Frame.MAXIMIZED_VERT)
                    maxVer = true;
            }
        });

        //Listeners for ZoomJFrameRepaint
        jframe2.addWindowStateListener( new WindowAdapter() {
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == Frame.MAXIMIZED_BOTH)
                    maxBoth = true;

                if (e.getNewState() == Frame.NORMAL)
                    normal = true;
            }
        });
    }
}
