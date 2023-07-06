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

package demo.share.jfc.J2Ddemo.java2d.demos.Composite;

import static java.awt.Color.BLACK;.extended
import static java.awt.Color.BLUE;.extended
import static java.awt.Color.CYAN;.extended
import static java.awt.Color.GREEN;.extended
import static java.awt.Color.LIGHT_GRAY;.extended
import static java.awt.Color.MAGENTA;.extended
import static java.awt.Color.ORANGE;.extended
import static java.awt.Color.PINK;.extended
import static java.awt.Color.RED;.extended
import static java.awt.Color.WHITE;.extended
import static java.awt.Color.YELLOW;.extended
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import demo.share.jfc.J2Ddemo.java2d.Surface;

/**
 * Compositing shapes on images.
 */
@SuppressWarnings("serial")
public class ACimages extends Surface {

    private static final String[] s = { "box", "fight", "magnify",
        "boxwave", "globe", "snooze",
        "tip", "thumbsup", "dukeplug" };
    private static final Image[] imgs = new Image[s.length];
    private static final Color[] colors = { BLUE, CYAN, GREEN,
        MAGENTA, ORANGE, PINK, RED, YELLOW, LIGHT_GRAY };

    public ACimages() {
        setBackground(WHITE);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = getImage(s[i] + ".png");
        }
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        float alpha = 0.0f;
        int iw = w / 3;
        int ih = (h - 45) / 3;
        float xx = 0, yy = 15;

        for (int i = 0; i < imgs.length; i++) {

            xx = (i % 3 == 0) ? 0 : xx + w / 3;
            switch (i) {
                case 3:
                    yy = h / 3 + 15;
                    break;
                case 6:
                    yy = h / 3 * 2 + 15;
            }

            g2.setComposite(AlphaComposite.SrcOver);
            g2.setColor(BLACK);
            AlphaComposite ac = AlphaComposite.SrcOver.derive(alpha += .1f);
            String str = "a=" + Float.toString(alpha).substring(0, 3);
            new TextLayout(str, g2.getFont(), g2.getFontRenderContext()).draw(g2, xx
                    + 3, yy - 2);

            Shape shape = null;

            switch (i % 3) {
                case 0:
                    shape = new Ellipse2D.Float(xx, yy, iw, ih);
                    break;
                case 1:
                    shape = new RoundRectangle2D.Float(xx, yy, iw, ih, 25, 25);
                    break;
                case 2:
                    shape = new Rectangle2D.Float(xx, yy, iw, ih);
                    break;
            }
            g2.setColor(colors[i]);
            g2.setComposite(ac);
            g2.fill(shape);
            g2.drawImage(imgs[i], (int) xx, (int) yy, iw, ih, null);
        }
    }

    public static void main(String[] s) {
        createDemoFrame(new ACimages());
    }
}
