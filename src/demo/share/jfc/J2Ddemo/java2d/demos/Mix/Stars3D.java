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

package demo.share.jfc.J2Ddemo.java2d.demos.Mix;

import static java.awt.Color.BLACK;.extended
import static java.awt.Color.BLUE;.extended
import static java.awt.Color.GREEN;.extended
import static java.awt.Color.RED;.extended
import static java.awt.Color.WHITE;.extended
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.Actionjava.util.Listener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import demo.share.jfc.J2Ddemo.java2d.ControlsSurface;
import demo.share.jfc.J2Ddemo.java2d.CustomControls;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Generate a 3D text shape with GeneralPath, render a number of small
 * multi-colored rectangles and then render the 3D text shape.
 */
@SuppressWarnings("serial")
public class Stars3D extends ControlsSurface {

    private static final Color[] colors = { RED, GREEN, WHITE };
    private static final AffineTransform at = AffineTransform.getTranslateInstance(-5,
            -5);
    private Shape shape, tshape;
    private Shape ribbon;
    protected int fontSize = 72;
    protected String text = "OpenJDK";
    protected int numStars = 300;

    public Stars3D() {
        setBackground(BLACK);
        setControls(new Component[] { new DemoControls(this) });
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        Rectangle2D rect = new Rectangle2D.Double();
        for (int i = 0; i < numStars; i++) {
            g2.setColor(colors[i % 3]);
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, (float) Math.random()));
            rect.setRect(w * Math.random(), h * Math.random(), 2, 2);
            g2.fill(rect);
        }

        FontRenderContext frc = g2.getFontRenderContext();
        Font font = new Font(Font.SERIF, Font.BOLD|Font.ITALIC, fontSize);
        shape = font.createGlyphVector(frc, text).getOutline();
        tshape = at.createTransformedShape(shape);
        PathIterator pi = shape.getPathIterator(null);

        float[] seg = new float[6];
        float[] tseg = new float[6];

        GeneralPath working = new GeneralPath(Path2D.WIND_NON_ZERO);
        float x = 0, y = 0; // Current point on the path
        float tx = 0, ty = 0; // Transformed path point
        float cx = 0, cy = 0; // Last moveTo point, for SEG_CLOSE
        float tcx = 0, tcy = 0; // Transformed last moveTo point

        //
        // Iterate through the Shape and build the ribbon
        // by adding general path objects.
        //
        while (!pi.isDone()) {
            int segType = pi.currentSegment(seg);
            switch (segType) {
                case PathIterator.SEG_MOVETO:
                    at.transform(seg, 0, tseg, 0, 1);
                    x = seg[0];
                    y = seg[1];
                    tx = tseg[0];
                    ty = tseg[1];
                    cx = x;
                    cy = y;
                    tcx = tx;
                    tcy = ty;
                    break;
                case PathIterator.SEG_LINETO:
                    at.transform(seg, 0, tseg, 0, 1);
                    if (Line2D.relativeCCW(x, y, tx, ty, seg[0], seg[1]) < 0) {
                        working.moveTo(x, y);
                        working.lineTo(seg[0], seg[1]);
                        working.lineTo(tseg[0], tseg[1]);
                        working.lineTo(tx, ty);
                        working.lineTo(x, y);
                    } else {
                        working.moveTo(x, y);
                        working.lineTo(tx, ty);
                        working.lineTo(tseg[0], tseg[1]);
                        working.lineTo(seg[0], seg[1]);
                        working.lineTo(x, y);
                    }

                    x = seg[0];
                    y = seg[1];
                    tx = tseg[0];
                    ty = tseg[1];
                    break;

                case PathIterator.SEG_QUADTO:
                    at.transform(seg, 0, tseg, 0, 2);
                    if (Line2D.relativeCCW(x, y, tx, ty, seg[2], seg[3]) < 0) {
                        working.moveTo(x, y);
                        working.quadTo(seg[0], seg[1],
                                seg[2], seg[3]);
                        working.lineTo(tseg[2], tseg[3]);
                        working.quadTo(tseg[0], tseg[1],
                                tx, ty);
                        working.lineTo(x, y);
                    } else {
                        working.moveTo(x, y);
                        working.lineTo(tx, ty);
                        working.quadTo(tseg[0], tseg[1],
                                tseg[2], tseg[3]);
                        working.lineTo(seg[2], seg[3]);
                        working.quadTo(seg[0], seg[1],
                                x, y);
                    }

                    x = seg[2];
                    y = seg[3];
                    tx = tseg[2];
                    ty = tseg[3];
                    break;

                case PathIterator.SEG_CUBICTO:
                    at.transform(seg, 0, tseg, 0, 3);
                    if (Line2D.relativeCCW(x, y, tx, ty, seg[4], seg[5]) < 0) {
                        working.moveTo(x, y);
                        working.curveTo(seg[0], seg[1],
                                seg[2], seg[3],
                                seg[4], seg[5]);
                        working.lineTo(tseg[4], tseg[5]);
                        working.curveTo(tseg[2], tseg[3],
                                tseg[0], tseg[1],
                                tx, ty);
                        working.lineTo(x, y);
                    } else {
                        working.moveTo(x, y);
                        working.lineTo(tx, ty);
                        working.curveTo(tseg[0], tseg[1],
                                tseg[2], tseg[3],
                                tseg[4], tseg[5]);
                        working.lineTo(seg[4], seg[5]);
                        working.curveTo(seg[2], seg[3],
                                seg[0], seg[1],
                                x, y);
                    }

                    x = seg[4];
                    y = seg[5];
                    tx = tseg[4];
                    ty = tseg[5];
                    break;

                case PathIterator.SEG_CLOSE:
                    if (Line2D.relativeCCW(x, y, tx, ty, cx, cy) < 0) {
                        working.moveTo(x, y);
                        working.lineTo(cx, cy);
                        working.lineTo(tcx, tcy);
                        working.lineTo(tx, ty);
                        working.lineTo(x, y);
                    } else {
                        working.moveTo(x, y);
                        working.lineTo(tx, ty);
                        working.lineTo(tcx, tcy);
                        working.lineTo(cx, cy);
                        working.lineTo(x, y);
                    }
                    x = cx;
                    y = cy;
                    tx = tcx;
                    ty = tcy;
            }
            pi.next();
        } // while
        ribbon = working;

        if (composite != null) {
            g2.setComposite(composite);
        } else {
            g2.setComposite(AlphaComposite.SrcOver);
        }
        Rectangle r = shape.getBounds();
        g2.translate(w * .5 - r.width * .5, h * .5 + r.height * .5);

        g2.setColor(BLUE);
        g2.fill(tshape);
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fill(ribbon);

        g2.setColor(WHITE);
        g2.fill(shape);

        g2.setColor(BLUE);
        g2.draw(shape);
    }

    public static void main(String[] argv) {
        createDemoFrame(new Stars3D());
    }

    static class DemoControls extends CustomControls implements ActionListener {

        Stars3D demo;
        JTextField tf1, tf2;

        @SuppressWarnings("LeakingThisInConstructor")
        public DemoControls(Stars3D demo) {
            super(demo.name);
            this.demo = demo;
            JLabel l = new JLabel("  Text:");
            l.setForeground(BLACK);
            add(l);
            add(tf1 = new JTextField(demo.text));
            tf1.setPreferredSize(new Dimension(60, 20));
            tf1.addActionListener(this);
            l = new JLabel("  Size:");
            l.setForeground(BLACK);
            add(l);
            add(tf2 = new JTextField(String.valueOf(demo.fontSize)));
            tf2.setPreferredSize(new Dimension(30, 20));
            tf2.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource().equals(tf1)) {
                    demo.text = tf1.getText().trim();
                } else if (e.getSource().equals(tf2)) {
                    demo.fontSize = Integer.parseInt(tf2.getText().trim());
                    if (demo.fontSize < 10) {
                        demo.fontSize = 10;
                    }
                }
                demo.repaint();
            } catch (Exception ignored) {
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 37);
        }

        @Override
        @SuppressWarnings("SleepWhileHoldingLock")
        public void run() {
            Thread me = Thread.currentThread();
            try {
                Thread.sleep(999);
            } catch (Exception e) {
                return;
            }
            int length = getSize().width / 4;
            int[] size = { length, length };
            String[] str = { "OpenJDK", "J2D" };
            while (thread == me) {
                for (int i = 0; i < str.length; i++) {
                    demo.fontSize = size[i];
                    tf2.setText(String.valueOf(demo.fontSize));
                    tf1.setText(demo.text = str[i]);
                    demo.repaint();
                    try {
                        Thread.sleep(5555);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
            thread = null;
        }
    } // End DemoControls
} // End Stars3D

