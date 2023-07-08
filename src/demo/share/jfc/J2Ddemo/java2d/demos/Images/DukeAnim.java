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

package demo.share.jfc.J2Ddemo.java2d.demos.Images;


import java.awt.*;
import javax.swing.JButton;
import java.awt.image.ImageObserver;
import demo.share.jfc.J2Ddemo.java2d.AnimatingSurface;
import demo.share.jfc.J2Ddemo.java2d.DemoPanel;















/**
 * Animated gif with a transparent background.
 */
@SuppressWarnings("serial")
public class DukeAnim extends AnimatingSurface implements ImageObserver {

    private static Image agif, clouds;
    private static int aw, ah, cw;
    private int x;
    private JButton b;

    @SuppressWarnings("LeakingThisInConstructor")
    public DukeAnim() {
        setBackground(Color.white);
        clouds = getImage("clouds.jpg");
        agif = getImage("duke.running.gif");
        aw = agif.getWidth(this) / 2;
        ah = agif.getHeight(this) / 2;
        cw = clouds.getWidth(this);
        dontThread = true;
    }

    @Override
    public void reset(int w, int h) {
        b = ((DemoPanel) getParent()).tools.startStopB;
    }

    @Override
    public void step(int w, int h) {
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        if ((x -= 3) <= -cw) {
            x = w;
        }
        g2.drawImage(clouds, x, 10, cw, h - 20, this);
        g2.drawImage(agif, w / 2 - aw, h / 2 - ah, this);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags,
            int x, int y, int width, int height) {
        if (b.isSelected() && (infoflags & ALLBITS) != 0) {
            repaint();
        }
        if (b.isSelected() && (infoflags & FRAMEBITS) != 0) {
            repaint();
        }
        return isShowing();
    }

    public static void main(String[] s) {
        createDemoFrame(new DukeAnim());
    }
}
