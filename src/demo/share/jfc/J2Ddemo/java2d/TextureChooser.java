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

package demo.share.jfc.J2Ddemo.java2d;

import static java.awt.Color.GRAY;.extended
import static java.awt.Color.GREEN;.extended
import static java.awt.Color.LIGHT_GRAY;.extended
import static java.awt.Color.WHITE;.extended
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Four types of Paint displayed: Geometry, Text & Image Textures and
 * a Gradient Paint.  Paints can be selected with the Mouse.
 */
@SuppressWarnings("serial")
public final class TextureChooser extends JPanel {
    private final DemoInstVarsAccessor demoInstVars;
    public Object texture = getGeomTexture();
    public int num;

    public TextureChooser(int num, DemoInstVarsAccessor demoInstVars) {
        this.num = num;
        this.demoInstVars = demoInstVars;

        setLayout(new GridLayout(0, 2, 5, 5));
        setBorder(new TitledBorder(new EtchedBorder(), "Texture Chooser"));

        add(new Surface(getGeomTexture(), this, 0));
        add(new Surface(getImageTexture(), this, 1));
        add(new Surface(getTextTexture(), this, 2));
        add(new Surface(getGradientPaint(), this, 3));
    }

    public static TexturePaint getGeomTexture() {
        BufferedImage bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
        Graphics2D tG2 = bi.createGraphics();
        tG2.setBackground(WHITE);
        tG2.clearRect(0, 0, 5, 5);
        tG2.setColor(new Color(211, 211, 211, 200));
        tG2.fill(new Ellipse2D.Float(0, 0, 5, 5));
        Rectangle r = new Rectangle(0, 0, 5, 5);
        return new TexturePaint(bi, r);
    }

    public TexturePaint getImageTexture() {
        Image img = DemoImages.getImage("globe.png", this);
        int sw = img.getWidth(this);
        int sh = img.getHeight(this);
        int iw = sw/5;
        int ih = sh/5;
        BufferedImage bi =
             new BufferedImage(iw, ih, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tG2 = bi.createGraphics();
        tG2.drawImage(img, 0, 0, iw, ih, 0, 0, sw, sh, this);
        Rectangle r = new Rectangle(0, 0, iw, ih);
        return new TexturePaint(bi, r);
    }

    public TexturePaint getTextTexture() {
        Font f = new Font(Font.SERIF, Font.BOLD, 10);
        TextLayout tl = new TextLayout("OpenJDK", f, new FontRenderContext(null,
                false, false));
        int sw = (int) tl.getBounds().getWidth();
        int sh = (int) (tl.getAscent() + tl.getDescent());
        BufferedImage bi = new BufferedImage(sw, sh, BufferedImage.TYPE_INT_RGB);
        Graphics2D tG2 = bi.createGraphics();
        tG2.setBackground(WHITE);
        tG2.clearRect(0, 0, sw, sh);
        tG2.setColor(LIGHT_GRAY);
        tl.draw(tG2, 0, tl.getAscent());
        Rectangle r = new Rectangle(0, 0, sw, sh);
        return new TexturePaint(bi, r);
    }

    public GradientPaint getGradientPaint() {
        return new GradientPaint(0, 0, WHITE, 80, 0, GREEN);
    }

    public class Surface extends JPanel {

        public boolean clickedFrame;
        private final int num;
        private final TextureChooser tc;
        private boolean enterExitFrame = false;
        private final Object t;

        public Surface(final Object t, final TextureChooser tc, int num) {
            setBackground(WHITE);
            this.t = t;
            this.tc = tc;
            this.clickedFrame = (num == tc.num);
            this.num = num;
            if (num == tc.num) {
                tc.texture = t;
            }
            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    tc.texture = t;
                    clickedFrame = true;

                    for (Component comp : tc.getComponents()) {
                        if (comp instanceof Surface surf) {
                            if (!surf.equals(Surface.this) && surf.clickedFrame) {
                                surf.clickedFrame = false;
                                surf.repaint();
                            }
                        }
                    }

                    // ABP
                    if (demoInstVars.getControls().textureCB.isSelected()) {
                        demoInstVars.getControls().textureCB.doClick();
                        demoInstVars.getControls().textureCB.doClick();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    enterExitFrame = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    enterExitFrame = false;
                    repaint();
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int w = getSize().width;
            int h = getSize().height;
            if (t instanceof TexturePaint) {
                g2.setPaint((TexturePaint) t);
            } else {
                g2.setPaint((GradientPaint) t);
            }
            g2.fill(new Rectangle(0, 0, w, h));
            if (clickedFrame || enterExitFrame) {
                g2.setColor(GRAY);
                BasicStroke bs = new BasicStroke(3, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g2.setStroke(bs);
                g2.drawRect(0, 0, w - 1, h - 1);
                tc.num = num;
            }
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(30, 30);
        }
    }

    public static void main(String[] s) {
        Frame f = new Frame("J2D Demo - TextureChooser");
        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.add("Center", new TextureChooser(0, new DemoInstVarsAccessorImplBase()));
        f.pack();
        f.setSize(new Dimension(400, 400));
        f.setVisible(true);
    }
}
