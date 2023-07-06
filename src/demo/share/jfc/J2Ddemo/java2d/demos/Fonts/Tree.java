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

import static java.awt.Color.BLUE;.extended
import static java.awt.Color.GREEN;.extended
import static java.awt.Color.RED;.extended
import static java.awt.Color.WHITE;.extended
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import demo.share.jfc.J2Ddemo.java2d.AnimatingSurface;

/**
 * Transformation of characters.
 */
@SuppressWarnings("serial")
public class Tree extends AnimatingSurface {

    private char theC = 'A';
    private Character theT = Character.valueOf(theC);
    private Character theR = Character.valueOf((char) (theC + 1));

    public Tree() {
        setBackground(WHITE);
    }

    @Override
    public void reset(int w, int h) {
    }

    @Override
    public void step(int w, int h) {
        setSleepAmount(4000);
        theT = Character.valueOf(theC = ((char) (theC + 1)));
        theR = Character.valueOf((char) (theC + 1));
        if (theR.compareTo(Character.valueOf('z')) == 0) {
            theC = 'A';
        }
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        int mindim = Math.min(w, h);
        AffineTransform at = new AffineTransform();
        at.translate((w - mindim) / 2.0,
                (h - mindim) / 2.0);
        at.scale(mindim, mindim);
        at.translate(0.5, 0.5);
        at.scale(0.3, 0.3);
        at.translate(-(Twidth + Rwidth), FontHeight / 4.0);
        g2.transform(at);
        tree(g2, mindim * 0.3, 0);

    }
    static Font theFont = new Font(Font.SERIF, Font.PLAIN, 1);
    static double Twidth = 0.6;
    static double Rwidth = 0.6;
    static double FontHeight = 0.75;
    static Color[] colors = { BLUE,
        RED.darker(),
        GREEN.darker() };

    public void tree(Graphics2D g2d, double size, int phase) {
        g2d.setColor(colors[phase % 3]);
        new TextLayout(theT.toString(), theFont, g2d.getFontRenderContext()).
                draw(g2d, 0.0f, 0.0f);
        if (size > 10.0) {
            AffineTransform at = new AffineTransform();
            at.setToTranslation(Twidth, -0.1);
            at.scale(0.6, 0.6);
            g2d.transform(at);
            size *= 0.6;
            new TextLayout(theR.toString(), theFont, g2d.getFontRenderContext()).
                    draw(g2d, 0.0f, 0.0f);
            at.setToTranslation(Rwidth + 0.75, 0);
            g2d.transform(at);
            Graphics2D g2dt = (Graphics2D) g2d.create();
            at.setToRotation(-Math.PI / 2.0);
            g2dt.transform(at);
            tree(g2dt, size, phase + 1);
            g2dt.dispose();
            at.setToTranslation(.75, 0);
            at.rotate(-Math.PI / 2.0);
            at.scale(-1.0, 1.0);
            at.translate(-Twidth, 0);
            g2d.transform(at);
            tree(g2d, size, phase);
        }
        g2d.setTransform(new AffineTransform());
    }

    public static void main(String[] argv) {
        createDemoFrame(new Tree());
    }
}
