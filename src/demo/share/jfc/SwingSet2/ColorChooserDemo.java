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
 * JColorChooserDemo
 *
 */
public class ColorChooserDemo extends DemoModule {

    BezierAnimationPanel bezAnim;
    JButton outerColorButton = null;
    JButton backgroundColorButton = null;
    JButton gradientAButton = null;
    JButton gradientBButton = null;

    // to store the color chosen from the JColorChooser
    private Color chosen;

    /**
     * main method allows us to run as a standalone demo.
     */
    public static void main(String[] args) {
        ColorChooserDemo demo = new ColorChooserDemo(null);
        demo.mainImpl();
    }


    /**
     * ColorChooserDemo Constructor
     */
    public ColorChooserDemo(SwingSet2 swingset) {
        // Set the title for this demo, and an icon used to represent this
        // demo inside the SwingSet2 app.
        super(swingset, "ColorChooserDemo", "toolbar/JColorChooser.gif");

        // Create the bezier animation panel to put in the center of the panel.
        bezAnim = new BezierAnimationPanel();

        outerColorButton = new JButton(getString("ColorChooserDemo.outer_line"));
        outerColorButton.setIcon(new ColorSwatch("OuterLine", bezAnim));

        backgroundColorButton = new JButton(getString("ColorChooserDemo.background"));
        backgroundColorButton.setIcon(new ColorSwatch("Background", bezAnim));

        gradientAButton = new JButton(getString("ColorChooserDemo.grad_a"));
        gradientAButton.setIcon(new ColorSwatch("GradientA", bezAnim));

        gradientBButton = new JButton(getString("ColorChooserDemo.grad_b"));
        gradientBButton.setIcon(new ColorSwatch("GradientB", bezAnim));

        ActionListener l = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color current = bezAnim.getOuterColor();

                if(e.getSource() == backgroundColorButton) {
                    current = bezAnim.getBackgroundColor();
                } else if(e.getSource() == gradientAButton) {
                    current = bezAnim.getGradientColorA();
                } else if(e.getSource() == gradientBButton) {
                    current = bezAnim.getGradientColorB();
                }

                final JColorChooser chooser = new JColorChooser(current != null ?
                                                                current :
                                                                Color.WHITE);
                if (getSwingSet2() != null && getSwingSet2().isDragEnabled()) {
                    chooser.setDragEnabled(true);
                }

                chosen = null;
                ActionListener okListener = new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        chosen = chooser.getColor();
                    }
                };

                JDialog dialog = JColorChooser.createDialog(getDemoPanel(),
                                                            getString("ColorChooserDemo.chooser_title"),
                                                            true,
                                                            chooser,
                                                            okListener,
                                                            null);

                dialog.setVisible(true);

                if(e.getSource() == outerColorButton) {
                    bezAnim.setOuterColor(chosen);
                } else if(e.getSource() == backgroundColorButton) {
                    bezAnim.setBackgroundColor(chosen);
                } else if(e.getSource() == gradientAButton) {
                    bezAnim.setGradientColorA(chosen);
                } else {
                    bezAnim.setGradientColorB(chosen);
                }
            }
        };

        outerColorButton.addActionListener(l);
        backgroundColorButton.addActionListener(l);
        gradientAButton.addActionListener(l);
        gradientBButton.addActionListener(l);

        // Add everything to the panel
        JPanel p = getDemoPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        // Add control buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(backgroundColorButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(15, 1)));

        buttonPanel.add(gradientAButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(15, 1)));

        buttonPanel.add(gradientBButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(15, 1)));

        buttonPanel.add(outerColorButton);

        // Add the panel midway down the panel
        p.add(Box.createRigidArea(new Dimension(1, 10)));
        p.add(buttonPanel);
        p.add(Box.createRigidArea(new Dimension(1, 5)));
        p.add(bezAnim);
    }

    class ColorSwatch implements Icon {
        String gradient;
        BezierAnimationPanel bez;

        public ColorSwatch(String g, BezierAnimationPanel b) {
            bez = b;
            gradient = g;
        }

        public int getIconWidth() {
            return 11;
        }

        public int getIconHeight() {
            return 11;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(Color.black);
            g.fillRect(x, y, getIconWidth(), getIconHeight());
            if(gradient.equals("GradientA")) {
                g.setColor(bez.getGradientColorA());
            } else if(gradient.equals("GradientB")) {
                g.setColor(bez.getGradientColorB());
            } else if(gradient.equals("Background")) {
                g.setColor(bez.getBackgroundColor());
            } else if(gradient.equals("OuterLine")) {
                g.setColor(bez.getOuterColor());
            }
            g.fillRect(x+2, y+2, getIconWidth()-4, getIconHeight()-4);
        }
    }

}
