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

package demo.share.jfc.J2Ddemo.java2d.demos.Mix;

import static java.awt.Color.LIGHT_GRAY;.extended
import static java.awt.Color.WHITE;.extended
import static java.lang.Math.random;.extended
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.Actionjava.util.Listener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import demo.share.jfc.J2Ddemo.java2d.AnimatingControlsSurface;
import demo.share.jfc.J2Ddemo.java2d.CustomControls;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * Animated Bezier Curve shape with images at the control points.
 * README.txt file scrolling up. Composited Image fading in and out.
 */
@SuppressWarnings("serial")
public class BezierScroller extends AnimatingControlsSurface {

    private static final String[] appletStrs = { " ", "J2Ddemo",
        "BezierScroller - Animated Bezier Curve shape with images",
        "For README.txt file scrolling run in application mode", " " };
    private static final int NUMPTS = 6;
    private static final Color greenBlend = new Color(0, 255, 0, 100);
    private static final Color blueBlend = new Color(0, 0, 255, 100);
    private static final Font font = new Font(Font.SERIF, Font.PLAIN, 12);
    private static final BasicStroke bs = new BasicStroke(3.0f);
    private static Image hotj_img;
    private static BufferedImage img;
    private static final int UP = 0;
    private static final int DOWN = 1;
    private final float[] animpts = new float[NUMPTS * 2];
    private final float[] deltas = new float[NUMPTS * 2];
    private BufferedReader reader;
    private int nStrs;
    private int strH;
    private int yy, ix, iy, imgX;
    private List<String> vector, appletVector;
    private float alpha = 0.2f;
    private int alphaDirection;
    protected boolean doImage, doShape, doText;
    protected boolean buttonToggle;

    /*
     * Using this to scale down globe.png since we want a smaller version,
     * I know it is 100 x 160 and has a transparent pixel.
     */
    private Image scaled(Image src) {
        int sw = src.getWidth(null);
        int sh = src.getHeight(null);
        int dw = sw/5;
        int dh = sh/5;
        BufferedImage bi =
            new BufferedImage(dw, dh, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                             RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(src, 0, 0, dw, dh, 0, 0, sw, sh, null);
        g2d.dispose();
        return bi;
    }

    @SuppressWarnings("LeakingThisInConstructor")
    public BezierScroller() {
        setBackground(WHITE);
        doShape = doText = true;
        hotj_img = scaled(getImage("globe.png"));
        Image image = getImage("jumptojavastrip.png");
        int iw = image.getWidth(this);
        int ih = image.getHeight(this);
        img = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
        img.createGraphics().drawImage(image, 0, 0, this);
        setControls(new Component[] { new DemoControls(this) });
    }

    public void animate(float[] pts, float[] deltas, int index, int limit) {
        float newpt = pts[index] + deltas[index];
        if (newpt <= 0) {
            newpt = -newpt;
            deltas[index] = (float) (random() * 4.0 + 2.0);
        } else if (newpt >= limit) {
            newpt = 2.0f * limit - newpt;
            deltas[index] = -(float) (random() * 4.0 + 2.0);
        }
        pts[index] = newpt;
    }

    public void getFile() {
        try {
            String fName = "README.txt";
            if ((reader = new BufferedReader(new FileReader(fName))) != null) {
                getLine();
            }
        } catch (Exception e) {
            reader = null;
        }
        if (reader == null) {
            appletVector = new ArrayList<String>(100);
            for (int i = 0; i < 100; i++) {
                appletVector.add(appletStrs[i % appletStrs.length]);
            }
            getLine();
        }
        buttonToggle = true;
    }

    public String getLine() {
        String str = null;
        if (reader != null) {
            try {
                if ((str = reader.readLine()) != null) {
                    if (str.length() == 0) {
                        str = " ";
                    }
                    vector.add(str);
                }
            } catch (Exception e) {
                Logger.getLogger(BezierScroller.class.getName()).log(
                        Level.SEVERE,
                        null, e);
                reader = null;
            }
        } else {
            if (!appletVector.isEmpty()) {
                vector.add(str = appletVector.remove(0));
            }
        }
        return str;
    }

    @Override
    public void reset(int w, int h) {
        for (int i = 0; i < animpts.length; i += 2) {
            animpts[i] = (float) (random() * w);
            animpts[i + 1] = (float) (random() * h);
            deltas[i] = (float) (random() * 6.0 + 4.0);
            deltas[i + 1] = (float) (random() * 6.0 + 4.0);
            if (animpts[i] > w / 2.0f) {
                deltas[i] = -deltas[i];
            }
            if (animpts[i + 1] > h / 2.0f) {
                deltas[i + 1] = -deltas[i + 1];
            }
        }
        FontMetrics fm = getFontMetrics(font);
        strH = fm.getAscent() + fm.getDescent();
        nStrs = h / strH + 2;
        vector = new ArrayList<String>(nStrs);
        ix = (int) (random() * (w - 80));
        iy = (int) (random() * (h - 80));
    }

    @Override
    public void step(int w, int h) {
        if (doText && vector.isEmpty()) {
            getFile();
        }
        if (doText) {
            String s = getLine();
            if (s == null || vector.size() == nStrs && !vector.isEmpty()) {
                vector.remove(0);
            }
            yy = (s == null) ? 0 : h - vector.size() * strH;
        }

        for (int i = 0; i < animpts.length && doShape; i += 2) {
            animate(animpts, deltas, i, w);
            animate(animpts, deltas, i + 1, h);
        }
        if (doImage && alphaDirection == UP) {
            if ((alpha += 0.025) > .99) {
                alphaDirection = DOWN;
                alpha = 1.0f;
            }
        } else if (doImage && alphaDirection == DOWN) {
            if ((alpha -= .02) < 0.01) {
                alphaDirection = UP;
                alpha = 0;
                ix = (int) (random() * (w - 80));
                iy = (int) (random() * (h - 80));
            }
        }
        if (doImage) {
            if ((imgX += 80) == 800) {
                imgX = 0;
            }
        }
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        if (doText) {
            g2.setColor(LIGHT_GRAY);
            g2.setFont(font);
            float y = yy;
            //for (int i = 0; i < vector.size(); i++) {
            for (String string : vector) {
                g2.drawString(string, 1, y += strH);
            }
        }

        if (doShape) {
            float[] ctrlpts = animpts;
            int len = ctrlpts.length;
            float prevx = ctrlpts[len - 2];
            float prevy = ctrlpts[len - 1];
            float curx = ctrlpts[0];
            float cury = ctrlpts[1];
            float midx = (curx + prevx) / 2.0f;
            float midy = (cury + prevy) / 2.0f;
            GeneralPath gp = new GeneralPath(Path2D.WIND_NON_ZERO);
            gp.moveTo(midx, midy);
            for (int i = 2; i <= ctrlpts.length; i += 2) {
                float x1 = (midx + curx) / 2.0f;
                float y1 = (midy + cury) / 2.0f;
                prevx = curx;
                prevy = cury;
                if (i < ctrlpts.length) {
                    curx = ctrlpts[i];
                    cury = ctrlpts[i + 1];
                } else {
                    curx = ctrlpts[0];
                    cury = ctrlpts[1];
                }
                midx = (curx + prevx) / 2.0f;
                midy = (cury + prevy) / 2.0f;
                float x2 = (prevx + midx) / 2.0f;
                float y2 = (prevy + midy) / 2.0f;
                gp.curveTo(x1, y1, x2, y2, midx, midy);
            }
            gp.closePath();

            g2.setColor(blueBlend);
            g2.setStroke(bs);
            g2.draw(gp);
            g2.setColor(greenBlend);
            g2.fill(gp);

            PathIterator pi = gp.getPathIterator(null);
            float[] pts = new float[6];
            while (!pi.isDone()) {
                if (pi.currentSegment(pts) == PathIterator.SEG_CUBICTO) {
                    g2.drawImage(hotj_img, (int) pts[0], (int) pts[1], this);
                }
                pi.next();
            }
        }

        if (doImage) {
            AlphaComposite ac = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);
            g2.setComposite(ac);
            g2.drawImage(img.getSubimage(imgX, 0, 80, 80), ix, iy, this);
        }
    }

    public static void main(String[] argv) {
        createDemoFrame(new BezierScroller());
    }

    static final class DemoControls extends CustomControls implements
            ActionListener {

        BezierScroller demo;
        JToolBar toolbar;

        public DemoControls(BezierScroller demo) {
            super(demo.name);
            this.demo = demo;
            add(toolbar = new JToolBar());
            toolbar.setFloatable(false);
            addTool("Image", false);
            addTool("Shape", true);
            addTool("Text", true);
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
            if (b.getText().equals("Image")) {
                demo.doImage = b.isSelected();
            } else if (b.getText().equals("Shape")) {
                demo.doShape = b.isSelected();
            } else {
                demo.doText = b.isSelected();
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
        @SuppressWarnings("SleepWhileHoldingLock")
        public void run() {
            Thread me = Thread.currentThread();
            int i = 0;
            while (thread == me) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    return;
                }
                if (demo.buttonToggle) {
                    ((AbstractButton) toolbar.getComponentAtIndex(i++ % 2)).
                            doClick();
                    demo.buttonToggle = false;
                }
            }
            thread = null;
        }
    } // End DemoControls
} // End BezierScroller

