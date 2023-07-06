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

package demo.share.jfc.J2Ddemo.java2d.demos.Lines;


import static java.awt.Color.BLACK;.extended
import static java.awt.Color.GRAY;.extended
import static java.awt.Color.LIGHT_GRAY;.extended
import static java.awt.Color.PINK;.extended
import static java.awt.Color.WHITE;.extended
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import demo.share.jfc.J2Ddemo.java2d.AnimatingSurface;















/**
 * Lines & Paths animation illustrating BasicStroke attributes.
 */
@SuppressWarnings("serial")
public class LineAnim extends AnimatingSurface {

    private static final int[] caps = { BasicStroke.CAP_BUTT,
        BasicStroke.CAP_SQUARE, BasicStroke.CAP_ROUND };
    private static final int[] joins = { BasicStroke.JOIN_MITER,
        BasicStroke.JOIN_BEVEL, BasicStroke.JOIN_ROUND };
    private static final Color[] colors = { GRAY, PINK, LIGHT_GRAY };
    private static final BasicStroke bs1 = new BasicStroke(1.0f);
    private static final int CLOCKWISE = 0;
    private final Line2D[] lines = new Line2D[3];
    private final int[] rAmt = new int[lines.length];
    private final int[] direction = new int[lines.length];
    private final int[] speed = new int[lines.length];
    private final BasicStroke[] strokes = new BasicStroke[lines.length];
    private GeneralPath path;
    private Point2D[] pts;
    private float size;
    private final Ellipse2D ellipse = new Ellipse2D.Double();

    public LineAnim() {
        setBackground(WHITE);
    }

    @Override
    public void reset(int w, int h) {
        size = (w > h) ? h / 6f : w / 6f;
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line2D.Float(0, 0, size, 0);
            strokes[i] = new BasicStroke(size / 3, caps[i], joins[i]);
            rAmt[i] = i * 360 / lines.length;
            direction[i] = i % 2;
            speed[i] = i + 1;
        }

        path = new GeneralPath();
        path.moveTo(size, -size / 2);
        path.lineTo(size + size / 2, 0);
        path.lineTo(size, +size / 2);

        ellipse.setFrame(w / 2 - size * 2 - 4.5f, h / 2 - size * 2 - 4.5f, size
                * 4, size * 4);
        PathIterator pi = ellipse.getPathIterator(null, 0.9);
        Point2D[] points = new Point2D[100];
        int num_pts = 0;
        while (!pi.isDone()) {
            float[] pt = new float[6];
            switch (pi.currentSegment(pt)) {
                case PathIterator.SEG_MOVETO:
                case PathIterator.SEG_LINETO:
                    points[num_pts] = new Point2D.Float(pt[0], pt[1]);
                    num_pts++;
            }
            pi.next();
        }
        pts = new Point2D[num_pts];
        System.arraycopy(points, 0, pts, 0, num_pts);
    }

    @Override
    public void step(int w, int h) {
        for (int i = 0; i < lines.length; i++) {
            if (direction[i] == CLOCKWISE) {
                rAmt[i] += speed[i];
                if (rAmt[i] == 360) {
                    rAmt[i] = 0;
                }
            } else {
                rAmt[i] -= speed[i];
                if (rAmt[i] == 0) {
                    rAmt[i] = 360;
                }
            }
        }
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        ellipse.setFrame(w / 2 - size, h / 2 - size, size * 2, size * 2);
        g2.setColor(BLACK);
        g2.draw(ellipse);

        for (int i = 0; i < lines.length; i++) {
            AffineTransform at = AffineTransform.getTranslateInstance(w / 2, h
                    / 2);
            at.rotate(Math.toRadians(rAmt[i]));
            g2.setStroke(strokes[i]);
            g2.setColor(colors[i]);
            g2.draw(at.createTransformedShape(lines[i]));
            g2.draw(at.createTransformedShape(path));

            int j = (int) ((double) rAmt[i] / 360 * pts.length);
            j = (j == pts.length) ? pts.length - 1 : j;
            ellipse.setFrame(pts[j].getX(), pts[j].getY(), 9, 9);
            g2.fill(ellipse);
        }

        g2.setStroke(bs1);
        g2.setColor(BLACK);
        for (int i = 0; i < pts.length; i++) {
            ellipse.setFrame(pts[i].getX(), pts[i].getY(), 9, 9);
            g2.draw(ellipse);
        }
    }

    public static void main(String[] argv) {
        createDemoFrame(new LineAnim());
    }
}
