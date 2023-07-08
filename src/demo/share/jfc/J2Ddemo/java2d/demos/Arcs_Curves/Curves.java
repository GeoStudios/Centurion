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

package demo.share.jfc.J2Ddemo.java2d.demos.Arcs_Curves;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.FlatteningPathIterator;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import demo.share.jfc.J2Ddemo.java2d.Surface;
import static java.awt.Color.*;.extended
import static java.awt.geom.PathIterator.*;.extended

/**
 * CubicCurve2D & QuadCurve2D curves includes FlattenPathIterator example.
 */
@SuppressWarnings("serial")
public class Curves extends Surface {

    private static final Color[] colors = { BLUE, GREEN, RED };

    public Curves() {
        setBackground(WHITE);
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        g2.setColor(BLACK);
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout tl = new TextLayout("QuadCurve2D", g2.getFont(), frc);
        float xx = (float) (w * .5 - tl.getBounds().getWidth() / 2);
        tl.draw(g2, xx, tl.getAscent());

        tl = new TextLayout("CubicCurve2D", g2.getFont(), frc);
        xx = (float) (w * .5 - tl.getBounds().getWidth() / 2);
        tl.draw(g2, xx, h * .5f);
        g2.setStroke(new BasicStroke(5.0f));

        float yy = 20;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                Shape shape = null;

                if (i == 0) {
                    shape = new QuadCurve2D.Float(w * .1f, yy, w * .5f, 50, w
                            * .9f, yy);
                } else {
                    shape = new CubicCurve2D.Float(w * .1f, yy, w * .4f, yy - 15,
                            w * .6f, yy + 15, w * .9f, yy);
                }
                g2.setColor(colors[j]);
                if (j != 2) {
                    g2.draw(shape);
                }

                if (j == 1) {
                    g2.setColor(LIGHT_GRAY);
                    PathIterator f = shape.getPathIterator(null);
                    while (!f.isDone()) {
                        float[] pts = new float[6];
                        switch (f.currentSegment(pts)) {
                            case SEG_MOVETO:
                            case SEG_LINETO:
                                g2.fill(new Rectangle2D.Float(pts[0], pts[1], 5,
                                        5));
                                break;
                            case SEG_CUBICTO:
                            case SEG_QUADTO:
                                g2.fill(new Rectangle2D.Float(pts[0], pts[1], 5,
                                        5));
                                if (pts[2] != 0) {
                                    g2.fill(new Rectangle2D.Float(pts[2], pts[3],
                                            5, 5));
                                }
                                if (pts[4] != 0) {
                                    g2.fill(new Rectangle2D.Float(pts[4], pts[5],
                                            5, 5));
                                }
                        }
                        f.next();
                    }
                } else if (j == 2) {
                    PathIterator p = shape.getPathIterator(null);
                    FlatteningPathIterator f =
                            new FlatteningPathIterator(p, 0.1);
                    while (!f.isDone()) {
                        float[] pts = new float[6];
                        switch (f.currentSegment(pts)) {
                            case SEG_MOVETO:
                            case SEG_LINETO:
                                g2.fill(new Ellipse2D.Float(pts[0], pts[1], 3, 3));
                        }
                        f.next();
                    }
                }
                yy += h / 6;
            }
            yy = h / 2 + 15;
        }
    }

    public static void main(String[] argv) {
        createDemoFrame(new Curves());
    }
}
