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
import java.awt.geom.Arc2D;
import java.awt.geom.AffineTransform;
import demo.share.jfc.J2Ddemo.java2d.AnimatingSurface;
import static java.awt.Color.*;.extended















/**
 * Arc2D Open, Chord & Pie arcs; Animated Pie Arc.
 */
@SuppressWarnings("serial")
public class Arcs extends AnimatingSurface {

    private static final String[] types = { "Arc2D.OPEN", "Arc2D.CHORD", "Arc2D.PIE" };
    private static final int CLOSE = 0;
    private static final int OPEN = 1;
    private static final int FORWARD = 0;
    private static final int BACKWARD = 1;
    private static final int DOWN = 2;
    private static final int UP = 3;
    private int aw, ah; // animated arc width & height
    private int x, y;
    private int angleStart = 45;
    private int angleExtent = 270;
    private int mouth = CLOSE;
    private int direction = FORWARD;

    public Arcs() {
        setBackground(WHITE);
    }

    @Override
    public void reset(int w, int h) {
        x = 0;
        y = 0;
        aw = w / 12;
        ah = h / 12;
    }

    @Override
    public void step(int w, int h) {
        // Compute direction
        if (x + aw >= w - 5 && direction == FORWARD) {
            direction = DOWN;
        }
        if (y + ah >= h - 5 && direction == DOWN) {
            direction = BACKWARD;
        }
        if (x - aw <= 5 && direction == BACKWARD) {
            direction = UP;
        }
        if (y - ah <= 5 && direction == UP) {
            direction = FORWARD;
        }

        // compute angle start & extent
        if (mouth == CLOSE) {
            angleStart -= 5;
            angleExtent += 10;
        }
        if (mouth == OPEN) {
            angleStart += 5;
            angleExtent -= 10;
        }
        if (direction == FORWARD) {
            x += 5;
            y = 0;
        }
        if (direction == DOWN) {
            x = w;
            y += 5;
        }
        if (direction == BACKWARD) {
            x -= 5;
            y = h;
        }
        if (direction == UP) {
            x = 0;
            y -= 5;
        }
        if (angleStart == 0) {
            mouth = OPEN;
        }
        if (angleStart > 45) {
            mouth = CLOSE;
        }
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        // Draw Arcs
        g2.setStroke(new BasicStroke(5.0f));
        for (int i = 0; i < types.length; i++) {
            Arc2D arc = new Arc2D.Float(i);
            arc.setFrame((i + 1) * w * .2, (i + 1) * h * .2, w * .17, h * .17);
            arc.setAngleStart(45);
            arc.setAngleExtent(270);
            g2.setColor(BLUE);
            g2.draw(arc);
            g2.setColor(GRAY);
            g2.fill(arc);
            g2.setColor(BLACK);
            g2.drawString(types[i], (int) ((i + 1) * w * .2), (int) ((i + 1) * h
                    * .2 - 3));
        }

        // Draw Animated Pie Arc
        Arc2D pieArc = new Arc2D.Float(Arc2D.PIE);
        pieArc.setFrame(0, 0, aw, ah);
        pieArc.setAngleStart(angleStart);
        pieArc.setAngleExtent(angleExtent);
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        switch (direction) {
            case DOWN:
                at.rotate(Math.toRadians(90));
                break;
            case BACKWARD:
                at.rotate(Math.toRadians(180));
                break;
            case UP:
                at.rotate(Math.toRadians(270));
        }
        g2.setColor(BLUE);
        g2.fill(at.createTransformedShape(pieArc));
    }

    public static void main(String[] argv) {
        createDemoFrame(new Arcs());
    }
}
