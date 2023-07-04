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
package java2d.demos.Lines;


import static java.awt.BasicStroke.CAP_BUTT;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.CAP_SQUARE;
import static java.awt.BasicStroke.JOIN_MITER;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java2d.Surface;


/**
 * Shows the three different styles of stroke ending.
 */
@SuppressWarnings("serial")
public class Caps extends Surface {

    private static final int[] cap = { CAP_BUTT, CAP_ROUND, CAP_SQUARE };
    private static final String[] desc = { "Butt Cap", "Round Cap", "Square Cap" };

    public Caps() {
        setBackground(WHITE);
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        FontRenderContext frc = g2.getFontRenderContext();
        Font font = g2.getFont();
        g2.setColor(BLACK);
        for (int i = 0; i < cap.length; i++) {
            g2.setStroke(new BasicStroke(15, cap[i], JOIN_MITER));
            g2.draw(new Line2D.Float(w / 4, (i + 1) * h / 4, w - w / 4, (i + 1)
                    * h / 4));
            TextLayout tl = new TextLayout(desc[i], font, frc);
            tl.draw(g2, (float) (w / 2 - tl.getBounds().getWidth() / 2), (i + 1)
                    * h / 4 - 10);
        }
    }

    public static void main(String[] s) {
        createDemoFrame(new Caps());
    }
}
