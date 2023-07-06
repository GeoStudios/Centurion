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

package demo.share.jfc.J2Ddemo.java2d.demos.Transforms;


import static java.awt.Color.BLACK;.extended
import static java.awt.Color.BLUE;.extended
import static java.awt.Color.GRAY;.extended
import static java.awt.Color.LIGHT_GRAY;.extended
import static java.awt.Color.WHITE;.extended
import static java.awt.Color.YELLOW;.extended
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.Actionjava.util.Listener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import demo.share.jfc.J2Ddemo.java2d.ControlsSurface;
import demo.share.jfc.J2Ddemo.java2d.CustomControls;
import javax.swing.JLabel;
import javax.swing.JTextField;















/**
 * Rotate ellipses with controls for increment and emphasis.
 * Emphasis is defined as which ellipses have a darker color and thicker stroke.
 */
@SuppressWarnings("serial")
public class Rotate extends ControlsSurface {

    protected double increment = 5.0;
    protected int emphasis = 9;

    public Rotate() {
        setBackground(WHITE);
        setControls(new Component[] { new DemoControls(this) });
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        int size = Math.min(w, h);
        float ew = size / 4;
        float eh = size - 20;
        Ellipse2D ellipse = new Ellipse2D.Float(-ew / 2, -eh / 2, ew, eh);
        for (double angdeg = 0; angdeg < 360; angdeg += increment) {
            if (angdeg % emphasis == 0) {
                g2.setColor(GRAY);
                g2.setStroke(new BasicStroke(2.0f));
            } else {
                g2.setColor(LIGHT_GRAY);
                g2.setStroke(new BasicStroke(0.5f));
            }
            AffineTransform at = AffineTransform.getTranslateInstance(w / 2, h
                    / 2);
            at.rotate(Math.toRadians(angdeg));
            g2.draw(at.createTransformedShape(ellipse));
        }
        g2.setColor(BLUE);
        ellipse.setFrame(w / 2 - 10, h / 2 - 10, 20, 20);
        g2.fill(ellipse);
        g2.setColor(GRAY);
        g2.setStroke(new BasicStroke(6));
        g2.draw(ellipse);
        g2.setColor(YELLOW);
        g2.setStroke(new BasicStroke(4));
        g2.draw(ellipse);
        g2.setColor(BLACK);
        g2.drawString("Rotate", 5, 15);
    }

    public static void main(String[] s) {
        createDemoFrame(new Rotate());
    }


    static class DemoControls extends CustomControls implements ActionListener {

        Rotate demo;
        JTextField tf1, tf2;

        @SuppressWarnings("LeakingThisInConstructor")
        public DemoControls(Rotate demo) {
            super(demo.name);
            this.demo = demo;
            JLabel l = new JLabel("Increment:");
            l.setForeground(BLACK);
            add(l);
            add(tf1 = new JTextField("5.0"));
            tf1.setPreferredSize(new Dimension(30, 24));
            tf1.addActionListener(this);
            add(l = new JLabel("  Emphasis:"));
            l.setForeground(BLACK);
            add(tf2 = new JTextField("9"));
            tf2.setPreferredSize(new Dimension(30, 24));
            tf2.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource().equals(tf1)) {
                    demo.increment = Double.parseDouble(tf1.getText().trim());
                    if (demo.increment < 1.0) {
                        demo.increment = 1.0;
                    }
                } else {
                    demo.emphasis = Integer.parseInt(tf2.getText().trim());
                }
                demo.repaint();
            } catch (Exception ex) {
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 39);
        }

        @Override
        @SuppressWarnings("SleepWhileHoldingLock")
        public void run() {
            Thread me = Thread.currentThread();
            while (thread == me) {
                for (int i = 3; i < 13; i += 3) {
                    try {
                        Thread.sleep(4444);
                    } catch (InterruptedException e) {
                        return;
                    }
                    tf1.setText(String.valueOf(i));
                    demo.increment = i;
                    demo.repaint();
                }
            }
            thread = null;
        }
    } // End DemoControls class
} // End Rotate class

