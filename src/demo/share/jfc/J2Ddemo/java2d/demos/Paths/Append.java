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
package java2d.demos.Paths;


import static java.awt.Color.BLACK;
import static java.awt.Color.GRAY;
import static java.awt.Color.WHITE;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java2d.Surface;


/**
 * Simple append of rectangle to path with & without the connect.
 */
@SuppressWarnings("serial")
public class Append extends Surface {

    public Append() {
        setBackground(WHITE);
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        GeneralPath p = new GeneralPath(Path2D.WIND_NON_ZERO);
        p.moveTo(w * 0.25f, h * 0.2f);
        p.lineTo(w * 0.75f, h * 0.2f);
        p.closePath();
        p.append(new Rectangle2D.Double(w * .4, h * .3, w * .2, h * .1), false);
        g2.setColor(GRAY);
        g2.fill(p);
        g2.setColor(BLACK);
        g2.draw(p);
        g2.drawString("Append rect to path", (int) (w * .25), (int) (h * .2) - 5);

        p.reset();
        p.moveTo(w * 0.25f, h * 0.6f);
        p.lineTo(w * 0.75f, h * 0.6f);
        p.closePath();
        p.append(new Rectangle2D.Double(w * .4, h * .7, w * .2, h * .1), true);
        g2.setColor(GRAY);
        g2.fill(p);
        g2.setColor(BLACK);
        g2.draw(p);
        g2.drawString("Append, connect", (int) (w * .25), (int) (h * .6) - 5);
    }

    public static void main(String[] s) {
        createDemoFrame(new Append());
    }
}
