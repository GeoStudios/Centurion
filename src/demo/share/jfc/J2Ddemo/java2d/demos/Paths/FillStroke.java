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
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import demo.share.jfc.J2Ddemo.java2d.Surface;

/**
 * Basic implementation of GeneralPath, filling & drawing a path w/o closing it.
 */
@SuppressWarnings("serial")
public class FillStroke extends Surface {

    public FillStroke() {
        setBackground(WHITE);
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        GeneralPath p = new GeneralPath(Path2D.WIND_EVEN_ODD);
        p.moveTo(w * .5f, h * .15f);
        p.lineTo(w * .8f, h * .75f);
        p.lineTo(w * .2f, h * .75f);
        g2.setColor(LIGHT_GRAY);
        g2.fill(p);
        g2.setColor(BLACK);
        g2.setStroke(new BasicStroke(10));
        g2.draw(p);
        TextLayout tl = new TextLayout("Fill, Stroke w/o closePath",
                g2.getFont(), g2.getFontRenderContext());
        tl.draw(g2, (float) (w / 2 - tl.getBounds().getWidth() / 2), h * .85f);
    }

    public static void main(String[] s) {
        createDemoFrame(new FillStroke());
    }
}