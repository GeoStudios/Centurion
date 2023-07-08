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

package demo.share.jfc.J2Ddemo.java2d.demos.Paths;


import static java.awt.Color.BLACK;.extended
import static java.awt.Color.LIGHT_GRAY;.extended
import static java.awt.Color.WHITE;.extended
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import demo.share.jfc.J2Ddemo.java2d.Surface;















/**
 * Rectangles filled to illustrate the GenerPath winding rule, determining
 * the interior of a path.
 */
@SuppressWarnings("serial")
public class WindingRule extends Surface {

    public WindingRule() {
        setBackground(WHITE);
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        g2.translate(w * .2, h * .2);

        GeneralPath p = new GeneralPath(Path2D.WIND_NON_ZERO);
        p.moveTo(0.0f, 0.0f);
        p.lineTo(w * .5f, 0.0f);
        p.lineTo(w * .5f, h * .2f);
        p.lineTo(0.0f, h * .2f);
        p.closePath();

        p.moveTo(w * .05f, h * .05f);
        p.lineTo(w * .55f, h * .05f);
        p.lineTo(w * .55f, h * .25f);
        p.lineTo(w * .05f, h * .25f);
        p.closePath();

        g2.setColor(LIGHT_GRAY);
        g2.fill(p);
        g2.setColor(BLACK);
        g2.draw(p);
        g2.drawString("NON_ZERO rule", 0, -5);

        g2.translate(0.0f, h * .45);

        p.setWindingRule(Path2D.WIND_EVEN_ODD);
        g2.setColor(LIGHT_GRAY);
        g2.fill(p);
        g2.setColor(BLACK);
        g2.draw(p);
        g2.drawString("EVEN_ODD rule", 0, -5);
    }

    public static void main(String[] s) {
        createDemoFrame(new WindingRule());
    }
}
