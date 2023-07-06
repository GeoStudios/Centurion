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
import static java.awt.Color.BLUE;.extended
import static java.awt.Color.GREEN;.extended
import static java.awt.Color.MAGENTA;.extended
import static java.awt.Color.RED;.extended
import static java.awt.Color.WHITE;.extended
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import demo.share.jfc.J2Ddemo.java2d.Surface;















/**
 * Rendering text as an outline shape.
 */
@SuppressWarnings("serial")
public class Outline extends Surface {

    public Outline() {
        setBackground(WHITE);
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        FontRenderContext frc = g2.getFontRenderContext();
        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, w / 8);
        Font f1 = new Font(Font.SANS_SERIF, Font.ITALIC, w / 8);
        String s = "AttributedString";
        AttributedString as = new AttributedString(s);
        as.addAttribute(TextAttribute.FONT, f, 0, 10);
        as.addAttribute(TextAttribute.FONT, f1, 10, s.length());
        AttributedCharacterIterator aci = as.getIterator();
        TextLayout tl = new TextLayout(aci, frc);
        float sw = (float) tl.getBounds().getWidth();
        float sh = (float) tl.getBounds().getHeight();
        Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(w / 2 - sw
                / 2, h * 0.2 + sh / 2));
        g2.setColor(BLUE);
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(sha);
        g2.setColor(MAGENTA);
        g2.fill(sha);

        f = new Font(Font.SERIF, Font.BOLD, w / 6);
        tl = new TextLayout("Outline", f, frc);
        sw = (float) tl.getBounds().getWidth();
        sh = (float) tl.getBounds().getHeight();
        sha = tl.getOutline(AffineTransform.getTranslateInstance(w / 2 - sw / 2, h
                * 0.5 + sh / 2));
        g2.setColor(BLACK);
        g2.draw(sha);
        g2.setColor(RED);
        g2.fill(sha);

        f = new Font(Font.SANS_SERIF, Font.ITALIC, w / 8);
        AffineTransform fontAT = new AffineTransform();
        fontAT.shear(-0.2, 0.0);
        Font derivedFont = f.deriveFont(fontAT);
        tl = new TextLayout("Italic-Shear", derivedFont, frc);
        sw = (float) tl.getBounds().getWidth();
        sh = (float) tl.getBounds().getHeight();
        sha = tl.getOutline(AffineTransform.getTranslateInstance(w / 2 - sw / 2, h
                * 0.80f + sh / 2));
        g2.setColor(GREEN);
        g2.draw(sha);
        g2.setColor(BLACK);
        g2.fill(sha);
    }

    public static void main(String[] s) {
        createDemoFrame(new Outline());
    }
}
