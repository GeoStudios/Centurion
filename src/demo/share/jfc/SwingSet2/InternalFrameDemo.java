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

package demo.share.jfc.SwingSet2;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import javax.swing.filechooser.*;
import javax.accessibility.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;

/**
 * Internal Frames Demo
 *
 */
public class InternalFrameDemo extends DemoModule {
    int windowCount = 0;
    JDesktopPane desktop = null;

    ImageIcon icon1, icon2, icon3, icon4;
    ImageIcon smIcon1, smIcon2, smIcon3, smIcon4;

    public Integer FIRST_FRAME_LAYER  = Integer.valueOf(1);
    public Integer DEMO_FRAME_LAYER   = Integer.valueOf(2);
    public Integer PALETTE_LAYER     = Integer.valueOf(3);

    public int FRAME0_X        = 15;
    public int FRAME0_Y        = 280;

    public int FRAME0_WIDTH    = 320;
    public int FRAME0_HEIGHT   = 230;

    public int FRAME_WIDTH     = 225;
    public int FRAME_HEIGHT    = 150;

    public int PALETTE_X      = 375;
    public int PALETTE_Y      = 20;

    public int PALETTE_WIDTH  = 260;
    public int PALETTE_HEIGHT = 260;

    JCheckBox windowResizable   = null;
    JCheckBox windowClosable    = null;
    JCheckBox windowIconifiable = null;
    JCheckBox windowMaximizable = null;

    JTextField windowTitleField = null;
    JLabel windowTitleLabel = null;

    /**
     * main method allows us to run as a standalone demo.
     */
    public static void main(String[] args) {
        InternalFrameDemo demo = new InternalFrameDemo(null);
        demo.mainImpl();
    }

    /**
     * InternalFrameDemo Constructor
     */
    public InternalFrameDemo(SwingSet2 swingset) {
        super(swingset, "InternalFrameDemo", "toolbar/JDesktop.gif");

        // preload all the icons we need for this demo
        icon1 = createImageIcon("misc/toast.gif", getString("InternalFrameDemo.toast"));
        icon2 = createImageIcon("misc/duke.gif", getString("InternalFrameDemo.duke"));
        icon3 = createImageIcon("misc/duchess.gif",  getString("InternalFrameDemo.duchess"));
        icon4 = createImageIcon("misc/cab.gif",  getString("InternalFrameDemo.cab"));

        smIcon1 = createImageIcon("misc/toast_small.gif", getString("InternalFrameDemo.toast"));
        smIcon2 = createImageIcon("misc/duke_small.gif", getString("InternalFrameDemo.duke"));
        smIcon3 = createImageIcon("misc/duchess_small.gif",  getString("InternalFrameDemo.duchess"));
        smIcon4 = createImageIcon("misc/cab_small.gif",  getString("InternalFrameDemo.cab"));

        // Create the desktop pane
        desktop = new JDesktopPane();
        getDemoPanel().add(desktop, BorderLayout.CENTER);

        // Create the "frame maker" palette
        createInternalFramePalette();

        // Create an initial internal frame to show
        JInternalFrame frame1 = createInternalFrame(icon1, FIRST_FRAME_LAYER, 1, 1);
        frame1.setBounds(FRAME0_X, FRAME0_Y, FRAME0_WIDTH, FRAME0_HEIGHT);

        // Create four more starter windows
        createInternalFrame(icon1, DEMO_FRAME_LAYER, FRAME_WIDTH, FRAME_HEIGHT);
        createInternalFrame(icon3, DEMO_FRAME_LAYER, FRAME_WIDTH, FRAME_HEIGHT);
        createInternalFrame(icon4, DEMO_FRAME_LAYER, FRAME_WIDTH, FRAME_HEIGHT);
        createInternalFrame(icon2, DEMO_FRAME_LAYER, FRAME_WIDTH, FRAME_HEIGHT);
    }

    /**
     * Create an internal frame and add a scrollable imageicon to it
     */
    public JInternalFrame createInternalFrame(Icon icon, Integer layer, int width, int height) {
        JInternalFrame jif = new JInternalFrame();

        if(!windowTitleField.getText().equals(getString("InternalFrameDemo.frame_label"))) {
            jif.setTitle(windowTitleField.getText() + "  ");
        } else {
            jif = new JInternalFrame(getString("InternalFrameDemo.frame_label") + " " + windowCount + "  ");
        }

        // set properties
        jif.setClosable(windowClosable.isSelected());
        jif.setMaximizable(windowMaximizable.isSelected());
        jif.setIconifiable(windowIconifiable.isSelected());
        jif.setResizable(windowResizable.isSelected());

        jif.setBounds(20*(windowCount%10), 20*(windowCount%10), width, height);
        jif.setContentPane(new ImageScroller(this, icon, 0, windowCount));

        windowCount++;

        desktop.add(jif, layer);

        // Set this internal frame to be selected

        try {
            jif.setSelected(true);
        } catch (java.beans.PropertyVetoException e2) {
        }

        jif.show();

        return jif;
    }

    public JInternalFrame createInternalFramePalette() {
        JInternalFrame palette = new JInternalFrame(
            getString("InternalFrameDemo.palette_label")
        );
        palette.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        palette.getContentPane().setLayout(new BorderLayout());
        palette.setBounds(PALETTE_X, PALETTE_Y, PALETTE_WIDTH, PALETTE_HEIGHT);
        palette.setResizable(true);
        palette.setIconifiable(true);
        desktop.add(palette, PALETTE_LAYER);

        // *************************************
        // * Create create frame maker buttons *
        // *************************************
        JButton b1 = new JButton(smIcon1);
        JButton b2 = new JButton(smIcon2);
        JButton b3 = new JButton(smIcon3);
        JButton b4 = new JButton(smIcon4);

        // add frame maker actions
        b1.addActionListener(new ShowFrameAction(this, icon1));
        b2.addActionListener(new ShowFrameAction(this, icon2));
        b3.addActionListener(new ShowFrameAction(this, icon3));
        b4.addActionListener(new ShowFrameAction(this, icon4));

        // add frame maker buttons to panel
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JPanel buttons1 = new JPanel();
        buttons1.setLayout(new BoxLayout(buttons1, BoxLayout.X_AXIS));

        JPanel buttons2 = new JPanel();
        buttons2.setLayout(new BoxLayout(buttons2, BoxLayout.X_AXIS));

        buttons1.add(b1);
        buttons1.add(Box.createRigidArea(HGAP15));
        buttons1.add(b2);

        buttons2.add(b3);
        buttons2.add(Box.createRigidArea(HGAP15));
        buttons2.add(b4);

        p.add(Box.createRigidArea(VGAP10));
        p.add(buttons1);
        p.add(Box.createRigidArea(VGAP15));
        p.add(buttons2);
        p.add(Box.createRigidArea(VGAP10));

        palette.getContentPane().add(p, BorderLayout.NORTH);

        // ************************************
        // * Create frame property checkboxes *
        // ************************************
        p = new JPanel() {
            final Insets insets = new Insets(10,15,10,5);
            public Insets getInsets() {
                return insets;
            }
        };
        p.setLayout(new GridLayout(1,2));

        Box box = new Box(BoxLayout.Y_AXIS);
        windowResizable   = new JCheckBox(getString("InternalFrameDemo.resizable_label"), true);
        windowIconifiable = new JCheckBox(getString("InternalFrameDemo.iconifiable_label"), true);

        box.add(Box.createGlue());
        box.add(windowResizable);
        box.add(windowIconifiable);
        box.add(Box.createGlue());
        p.add(box);

        box = new Box(BoxLayout.Y_AXIS);
        windowClosable    = new JCheckBox(getString("InternalFrameDemo.closable_label"), true);
        windowMaximizable = new JCheckBox(getString("InternalFrameDemo.maximizable_label"), true);

        box.add(Box.createGlue());
        box.add(windowClosable);
        box.add(windowMaximizable);
        box.add(Box.createGlue());
        p.add(box);

        palette.getContentPane().add(p, BorderLayout.CENTER);

        // ************************************
        // *   Create Frame title textfield   *
        // ************************************
        p = new JPanel() {
            final Insets insets = new Insets(0,0,10,0);
            public Insets getInsets() {
                return insets;
            }
        };

        windowTitleField = new JTextField(getString("InternalFrameDemo.frame_label"));
        windowTitleLabel = new JLabel(getString("InternalFrameDemo.title_text_field_label"));

        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(Box.createRigidArea(HGAP5));
        p.add(windowTitleLabel, BorderLayout.WEST);
        p.add(Box.createRigidArea(HGAP5));
        p.add(windowTitleField, BorderLayout.CENTER);
        p.add(Box.createRigidArea(HGAP5));

        palette.getContentPane().add(p, BorderLayout.SOUTH);

        palette.show();

        return palette;
    }

    class ShowFrameAction extends AbstractAction {
        InternalFrameDemo demo;
        Icon icon;

        public ShowFrameAction(InternalFrameDemo demo, Icon icon) {
            this.demo = demo;
            this.icon = icon;
        }

        public void actionPerformed(ActionEvent e) {
            demo.createInternalFrame(icon,
                                     getDemoFrameLayer(),
                                     getFrameWidth(),
                                     getFrameHeight()
            );
        }
    }

    public int getFrameWidth() {
        return FRAME_WIDTH;
    }

    public int getFrameHeight() {
        return FRAME_HEIGHT;
    }

    public Integer getDemoFrameLayer() {
        return DEMO_FRAME_LAYER;
    }

    class ImageScroller extends JScrollPane {

        public ImageScroller(InternalFrameDemo demo, Icon icon, int layer, int count) {
            super();
            JPanel p = new JPanel();
            p.setBackground(Color.white);
            p.setLayout(new BorderLayout() );

            p.add(new JLabel(icon), BorderLayout.CENTER);

            getViewport().add(p);
            getHorizontalScrollBar().setUnitIncrement(10);
            getVerticalScrollBar().setUnitIncrement(10);
        }

        public Dimension getMinimumSize() {
            return new Dimension(25, 25);
        }

    }

    void updateDragEnabled(boolean dragEnabled) {
        windowTitleField.setDragEnabled(dragEnabled);
    }

}