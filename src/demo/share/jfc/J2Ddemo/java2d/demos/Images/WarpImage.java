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

package demo.share.jfc.J2Ddemo.java2d.demos.Images;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import demo.share.jfc.J2Ddemo.java2d.AnimatingSurface;















/**
 * Warps a image on a CubicCurve2D flattened path.
 */
@SuppressWarnings("serial")
public class WarpImage extends AnimatingSurface {

    private static int iw, ih, iw2, ih2;
    private static Image img;
    private static final int FORWARD = 0;
    private static final int BACK = 1;
    private Point2D[] pts;
    private int direction = FORWARD;
    private int pNum;
    private int x, y;

    @SuppressWarnings("LeakingThisInConstructor")
    public WarpImage() {
        setBackground(Color.white);
        img = getImage("surfing.png");
        iw = img.getWidth(this);
        ih = img.getHeight(this);
        iw2 = iw / 2;
        ih2 = ih / 2;
    }

    @Override
    public void reset(int w, int h) {
        pNum = 0;
        direction = FORWARD;
        CubicCurve2D cc = new CubicCurve2D.Float(
                w * .2f, h * .5f, w * .4f, 0, w * .6f, h, w * .8f, h * .5f);
        PathIterator pi = cc.getPathIterator(null, 0.1);
        Point2D[] tmp = new Point2D[200];
        int i = 0;
        while (!pi.isDone()) {
            float[] coords = new float[6];
            switch (pi.currentSegment(coords)) {
                case PathIterator.SEG_MOVETO:
                case PathIterator.SEG_LINETO:
                    tmp[i] = new Point2D.Float(coords[0], coords[1]);
            }
            i++;
            pi.next();
        }
        pts = new Point2D[i];
        System.arraycopy(tmp, 0, pts, 0, i);
    }

    @Override
    public void step(int w, int h) {
        if (pts == null) {
            return;
        }
        x = (int) pts[pNum].getX();
        y = (int) pts[pNum].getY();
        if (direction == FORWARD) {
            if (++pNum == pts.length) {
                direction = BACK;
            }
        }
        if (direction == BACK) {
            if (--pNum == 0) {
                direction = FORWARD;
            }
        }
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        g2.drawImage(img,
                0, 0, x, y,
                0, 0, iw2, ih2,
                this);
        g2.drawImage(img,
                x, 0, w, y,
                iw2, 0, iw, ih2,
                this);
        g2.drawImage(img,
                0, y, x, h,
                0, ih2, iw2, ih,
                this);
        g2.drawImage(img,
                x, y, w, h,
                iw2, ih2, iw, ih,
                this);
    }

    public static void main(String[] argv) {
        createDemoFrame(new WarpImage());
    }
}
