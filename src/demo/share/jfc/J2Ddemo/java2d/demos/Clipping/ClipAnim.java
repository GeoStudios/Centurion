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

package demo.share.jfc.J2Ddemo.java2d.demos.Clipping;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import demo.share.jfc.J2Ddemo.java2d.AnimatingControlsSurface;
import demo.share.jfc.J2Ddemo.java2d.CustomControls;
import static java.lang.Math.random;.extended
import static java.awt.Color.*;.extended















/**
 * Animated clipping of an image & composited shapes.
 */
@SuppressWarnings("serial")
public class ClipAnim extends AnimatingControlsSurface {

    private static Image dimg, cimg;
    static TexturePaint texturePaint;

    static {
        BufferedImage bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
        Graphics2D big = bi.createGraphics();
        big.setBackground(YELLOW);
        big.clearRect(0, 0, 5, 5);
        big.setColor(RED);
        big.fillRect(0, 0, 3, 3);
        texturePaint = new TexturePaint(bi, new Rectangle(0, 0, 5, 5));
    }
    private final AnimVal[] animval = new AnimVal[3];
    protected boolean doObjects = true;
    private final Font originalFont = new Font(Font.SERIF, Font.PLAIN, 12);
    private Font font;
    private GradientPaint gradient;
    private int strX, strY;
    private int dukeX, dukeY, dukeWidth, dukeHeight;

    public ClipAnim() {
        cimg = getImage("clouds.jpg");
        dimg = getImage("duke.png");
        setBackground(WHITE);
        animval[0] = new AnimVal(true);
        animval[1] = new AnimVal(false);
        animval[2] = new AnimVal(false);
        setControls(new Component[] { new DemoControls(this) });
    }

    @Override
    public void reset(int w, int h) {
        for (AnimVal a : animval) {
            a.reset(w, h);
        }
        gradient = new GradientPaint(0, h / 2, RED, w * .4f, h * .9f, YELLOW);
        double scale = 0.4;
        dukeHeight = (int) (scale * h);
        dukeWidth = (int) (dimg.getWidth(this) * scale * h / dimg.getHeight(this));
        dukeX = (int) (w * .25 - dukeWidth / 2);
        dukeY = (int) (h * .25 - dukeHeight / 2);
        FontMetrics fm = getFontMetrics(originalFont);
        double sw = fm.stringWidth("CLIPPING");
        double sh = fm.getAscent() + fm.getDescent();
        double sx = (w / 2 - 30) / sw;
        double sy = (h / 2 - 30) / sh;
        AffineTransform Tx = AffineTransform.getScaleInstance(sx, sy);
        font = originalFont.deriveFont(Tx);
        fm = getFontMetrics(font);
        strX = (int) (w * .75 - fm.stringWidth("CLIPPING") / 2);
        strY = (int) (h * .72 + fm.getAscent() / 2);
    }

    @Override
    public void step(int w, int h) {
        for (AnimVal a : animval) {
            if (a.isSelected) {
                a.step(w, h);
            }
        }
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        GeneralPath p1 = new GeneralPath();
        GeneralPath p2 = new GeneralPath();

        for (AnimVal a : animval) {
            if (a.isSelected) {
                double x = a.x;
                double y = a.y;
                double ew = a.ew;
                double eh = a.eh;
                p1.append(new Ellipse2D.Double(x, y, ew, eh), false);
                p2.append(new Rectangle2D.Double(x + 5, y + 5, ew - 10, eh - 10),
                        false);
            }
        }
        if (animval[0].isSelected || animval[1].isSelected
                || animval[2].isSelected) {
            g2.setClip(p1);
            g2.clip(p2);
        }

        if (doObjects) {
            int w2 = w / 2;
            int h2 = h / 2;
            g2.drawImage(cimg, 0, 0, w2, h2, null);
            g2.drawImage(dimg, dukeX, dukeY, dukeWidth, dukeHeight, null);

            g2.setPaint(texturePaint);
            g2.fillRect(w2, 0, w2, h2);

            g2.setPaint(gradient);
            g2.fillRect(0, h2, w2, h2);

            g2.setColor(LIGHT_GRAY);
            g2.fillRect(w2, h2, w2, h2);
            g2.setColor(RED);
            g2.drawOval(w2, h2, w2 - 1, h2 - 1);
            g2.setFont(font);
            g2.drawString("CLIPPING", strX, strY);
        } else {
            g2.setColor(LIGHT_GRAY);
            g2.fillRect(0, 0, w, h);
        }
    }

    public static void main(String[] argv) {
        createDemoFrame(new ClipAnim());
    }


    public class AnimVal {

        double ix = 5.0;
        double iy = 3.0;
        double iw = 5.0;
        double ih = 3.0;
        double x, y;
        double ew, eh;   // ellipse width & height
        boolean isSelected;

        public AnimVal(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public void step(int w, int h) {
            x += ix;
            y += iy;
            ew += iw;
            eh += ih;

            if (ew > w / 2) {
                ew = w / 2;
                iw = random() * -w / 16 - 1;
            }
            if (ew < w / 8) {
                ew = w / 8;
                iw = random() * w / 16 + 1;
            }
            if (eh > h / 2) {
                eh = h / 2;
                ih = random() * -h / 16 - 1;
            }
            if (eh < h / 8) {
                eh = h / 8;
                ih = random() * h / 16 + 1;
            }

            if ((x + ew) > w) {
                x = (w - ew) - 1;
                ix = random() * -w / 32 - 1;
            }
            if ((y + eh) > h) {
                y = (h - eh) - 2;
                iy = random() * -h / 32 - 1;
            }
            if (x < 0) {
                x = 2;
                ix = random() * w / 32 + 1;
            }
            if (y < 0) {
                y = 2;
                iy = random() * h / 32 + 1;
            }
        }

        public void reset(int w, int h) {
            x = random() * w;
            y = random() * h;
            ew = (random() * w) / 2;
            eh = (random() * h) / 2;
        }
    }


    static final class DemoControls extends CustomControls implements
            ActionListener {

        ClipAnim demo;
        JToolBar toolbar;

        public DemoControls(ClipAnim demo) {
            super(demo.name);
            this.demo = demo;
            add(toolbar = new JToolBar());
            toolbar.setFloatable(false);
            addTool("Objects", true);
            addTool("Clip1", true);
            addTool("Clip2", false);
            addTool("Clip3", false);
        }

        public void addTool(String str, boolean state) {
            JToggleButton b =
                    (JToggleButton) toolbar.add(new JToggleButton(str));
            b.setFocusPainted(false);
            b.setSelected(state);
            b.addActionListener(this);
            int width = b.getPreferredSize().width;
            Dimension prefSize = new Dimension(width, 21);
            b.setPreferredSize(prefSize);
            b.setMaximumSize(prefSize);
            b.setMinimumSize(prefSize);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JToggleButton b = (JToggleButton) e.getSource();
            if (b.getText().equals("Objects")) {
                demo.doObjects = b.isSelected();
            } else if (b.getText().equals("Clip1")) {
                demo.animval[0].isSelected = b.isSelected();
            } else if (b.getText().equals("Clip2")) {
                demo.animval[1].isSelected = b.isSelected();
            } else if (b.getText().equals("Clip3")) {
                demo.animval[2].isSelected = b.isSelected();
            }
            if (!demo.animating.running()) {
                demo.repaint();
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 40);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                return;
            }
            ((AbstractButton) toolbar.getComponentAtIndex(2)).doClick();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                return;
            }
            if (getSize().width > 400) {
                ((AbstractButton) toolbar.getComponentAtIndex(3)).doClick();
            }
            thread = null;
        }
    } // End DemoControls
} // End ClipAnim

