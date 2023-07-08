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

import static java.awt.Font.BOLD;.extended
import static java.awt.Font.ITALIC;.extended
import static java.awt.Font.PLAIN;.extended
import static java.awt.font.TextAttribute.BACKGROUND;.extended
import static java.awt.font.TextAttribute.CHAR_REPLACEMENT;.extended
import static java.awt.font.TextAttribute.FONT;.extended
import static java.awt.font.TextAttribute.FOREGROUND;.extended
import static java.awt.font.TextAttribute.UNDERLINE;.extended
import static java.awt.font.TextAttribute.UNDERLINE_ON;.extended
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.font.FontRenderContext;
import java.awt.font.GraphicAttribute;
import java.awt.font.ImageGraphicAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.ShapeGraphicAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import demo.share.jfc.J2Ddemo.java2d.Surface;

/**
 * Demonstrates how to build an AttributedString and then render the
 * string broken over lines.
 */
@SuppressWarnings("serial")
public class AttributedStr extends Surface {

    static final Color black = new Color(20, 20, 20);
    static final Color blue = new Color(94, 105, 176);
    static final Color yellow = new Color(255, 255, 140);
    static final Color red = new Color(149, 43, 42);
    static final Color white = new Color(240, 240, 255);
    static final String text =
            "  A quick brown  fox  jumped  over the lazy duke  ";
    static final AttributedString as = new AttributedString(text);
    static AttributedCharacterIterator aci;

    static {
        Shape shape = new Ellipse2D.Double(0, 25, 12, 12);
        ShapeGraphicAttribute sga = new ShapeGraphicAttribute(shape,
                GraphicAttribute.TOP_ALIGNMENT, false);
        as.addAttribute(CHAR_REPLACEMENT, sga, 0, 1);

        Font font = new Font("sanserif", BOLD | ITALIC, 20);
        int index = text.indexOf("quick");
        as.addAttribute(FONT, font, index, index + 5);

        index = text.indexOf("brown");
        font = new Font(Font.SERIF, BOLD, 20);
        as.addAttribute(FONT, font, index, index + 5);
        as.addAttribute(FOREGROUND, red, index, index + 5);

        index = text.indexOf("fox");
        AffineTransform fontAT = new AffineTransform();
        fontAT.rotate(Math.toRadians(10));
        Font fx = new Font(Font.SERIF, BOLD, 30).deriveFont(fontAT);
        as.addAttribute(FONT, fx, index, index + 1);
        as.addAttribute(FONT, fx, index + 1, index + 2);
        as.addAttribute(FONT, fx, index + 2, index + 3);

        fontAT.setToRotation(Math.toRadians(-4));
        fx = font.deriveFont(fontAT);
        index = text.indexOf("jumped");
        as.addAttribute(FONT, fx, index, index + 6);

        font = new Font(Font.SERIF, BOLD | ITALIC, 30);
        index = text.indexOf("over");
        as.addAttribute(UNDERLINE, UNDERLINE_ON, index, index + 4);
        as.addAttribute(FOREGROUND, white, index, index + 4);
        as.addAttribute(FONT, font, index, text.length());

        font = new Font(Font.DIALOG, PLAIN, 20);
        int i = text.indexOf("duke");
        as.addAttribute(FONT, font, index, i - 1);

        BufferedImage bi = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        bi.setRGB(0, 0, 0xffffffff);
        TexturePaint tp = new TexturePaint(bi, new Rectangle(0, 0, 4, 4));
        as.addAttribute(BACKGROUND, tp, i, i + 4);
        font = new Font(Font.SERIF, BOLD, 40);
        as.addAttribute(FONT, font, i, i + 4);
    }

    public AttributedStr() {
        setBackground(Color.white);

        Font font = getFont("A.ttf");
        if (font != null) {
            font = font.deriveFont(PLAIN, 70);
        } else {
            font = new Font(Font.SERIF, PLAIN, 50);
        }
        int index = text.indexOf("A") + 1;
        as.addAttribute(FONT, font, 0, index);
        as.addAttribute(FOREGROUND, white, 0, index);

        font = new Font(Font.DIALOG, PLAIN, 40);
        int size = getFontMetrics(font).getHeight();
        BufferedImage bi =
                new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D big = bi.createGraphics();
        big.drawImage(getImage("snooze.png"), 0, 0, size, size, null);
        ImageGraphicAttribute iga =
                new ImageGraphicAttribute(bi, GraphicAttribute.TOP_ALIGNMENT);
        as.addAttribute(CHAR_REPLACEMENT, iga, text.length() - 1, text.length());

        aci = as.getIterator();
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        float x = 5, y = 0;
        FontRenderContext frc = g2.getFontRenderContext();
        LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);

        g2.setPaint(new GradientPaint(0, h, blue, w, 0, black));
        g2.fillRect(0, 0, w, h);

        g2.setColor(white);
        String s = "AttributedString LineBreakMeasurer";
        Font font = new Font(Font.SERIF, PLAIN, 12);
        TextLayout tl = new TextLayout(s, font, frc);

        tl.draw(g2, 5, y += (float) tl.getBounds().getHeight());

        g2.setColor(yellow);

        while (y < h - tl.getAscent()) {
            lbm.setPosition(0);
            while (lbm.getPosition() < text.length()) {
                tl = lbm.nextLayout(w - x);
                if (!tl.isLeftToRight()) {
                    x = w - tl.getAdvance();
                }
                tl.draw(g2, x, y += tl.getAscent());
                y += tl.getDescent() + tl.getLeading();
            }
        }
    }

    public static void main(String[] s) {
        createDemoFrame(new AttributedStr());
    }
}
