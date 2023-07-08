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

package demo.share.jfc.J2Ddemo.java2d.demos.Fonts;


import static java.awt.Color.BLACK;.extended
import static java.awt.Color.CYAN;.extended
import static java.awt.Color.LIGHT_GRAY;.extended
import static java.awt.Color.WHITE;.extended
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextHitInfo;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import demo.share.jfc.J2Ddemo.java2d.AnimatingSurface;















/**
 * Highlighting of text showing the caret, the highlight & the character
 * advances.
 */
@SuppressWarnings("serial")
public class Highlighting extends AnimatingSurface {

    private static final String[] text = { "HIGHLIGHTING", "OpenJDK" };
    private static final Color[] colors = { CYAN, LIGHT_GRAY };
    private static final Font smallF = new Font("Monospaced", Font.PLAIN, 8);
    private final int[] curPos;
    private final TextLayout[] layouts;
    private final Font[] fonts;

    public Highlighting() {
        setBackground(WHITE);
        fonts = new Font[2];
        layouts = new TextLayout[fonts.length];
        curPos = new int[fonts.length];
    }

    @Override
    public void reset(int w, int h) {
        fonts[0] = new Font("Monospaced", Font.PLAIN, w / text[0].length() + 8);
        fonts[1] = new Font("Serif", Font.BOLD, w / text[1].length());
        for (int i = 0; i < layouts.length; i++) {
            curPos[i] = 0;
        }
    }

    @Override
    public void step(int w, int h) {
        setSleepAmount(900);
        for (int i = 0; i < 2; i++) {
            if (layouts[i] == null) {
                continue;
            }
            if (curPos[i]++ == layouts[i].getCharacterCount()) {
                curPos[i] = 0;
            }
        }
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        FontRenderContext frc = g2.getFontRenderContext();
        for (int i = 0; i < 2; i++) {
            layouts[i] = new TextLayout(text[i], fonts[i], frc);
            float rw = layouts[i].getAdvance();
            float rx = ((w - rw) / 2);
            float ry = ((i == 0) ? h / 3 : h * 0.75f);

            // draw highlighted shape
            Shape hilite = layouts[i].getLogicalHighlightShape(0, curPos[i]);
            AffineTransform at = AffineTransform.getTranslateInstance(rx, ry);
            hilite = at.createTransformedShape(hilite);
            float hy = (float) hilite.getBounds2D().getY();
            float hh = (float) hilite.getBounds2D().getHeight();
            g2.setColor(colors[i]);
            g2.fill(hilite);

            // get caret shape
            Shape[] shapes = layouts[i].getCaretShapes(curPos[i]);
            Shape caret = at.createTransformedShape(shapes[0]);

            g2.setColor(BLACK);
            layouts[i].draw(g2, rx, ry);
            g2.draw(caret);
            g2.draw(new Rectangle2D.Float(rx, hy, rw, hh));

            // Display character advances.
            for (int j = 0; j <= layouts[i].getCharacterCount(); j++) {
                float[] cInfo = layouts[i].getCaretInfo(TextHitInfo.leading(j));
                String str = String.valueOf((int) cInfo[0]);
                TextLayout tl = new TextLayout(str, smallF, frc);
                tl.draw(g2, rx + cInfo[0] - tl.getAdvance() / 2, hy + hh + tl.
                        getAscent() + 1.0f);
            }
        }
    }

    public static void main(String[] argv) {
        createDemoFrame(new Highlighting());
    }
}
