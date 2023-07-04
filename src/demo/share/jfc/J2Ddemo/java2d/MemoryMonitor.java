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
package java2d;


import static java.awt.Color.BLACK;
import static java.awt.Color.GREEN;
import static java.awt.Color.YELLOW;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


/**
 * Tracks Memory allocated & used, displayed in graph form.
 */
@SuppressWarnings("serial")
public class MemoryMonitor extends JPanel {

    private final JCheckBox dateStampCB = new JCheckBox("Output Date Stamp");
    public Surface surf;
    JPanel controls;
    boolean doControls;
    JTextField tf;

    public MemoryMonitor() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(new EtchedBorder(), "Memory Monitor"));
        add(surf = new Surface());
        controls = new JPanel();
        controls.setPreferredSize(new Dimension(135, 80));
        Font font = new Font(Font.SERIF, Font.PLAIN, 10);
        JLabel label = new JLabel("Sample Rate");
        label.setFont(font);
        label.setForeground(BLACK);
        controls.add(label);
        tf = new JTextField("1000");
        tf.setPreferredSize(new Dimension(45, 20));
        controls.add(tf);
        controls.add(label = new JLabel("ms"));
        label.setFont(font);
        label.setForeground(BLACK);
        controls.add(dateStampCB);
        dateStampCB.setFont(font);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                removeAll();
                if ((doControls = !doControls)) {
                    surf.stop();
                    add(controls);
                } else {
                    try {
                        surf.sleepAmount = Long.parseLong(tf.getText().trim());
                    } catch (Exception ex) {
                    }
                    surf.start();
                    add(surf);
                }
                revalidate();
                repaint();
            }
        });
    }


    public class Surface extends JPanel implements Runnable {

        public Thread thread;
        public long sleepAmount = 1000;
        private int w, h;
        private BufferedImage bimg;
        private Graphics2D big;
        private final Font font = new Font(Font.SERIF, Font.PLAIN, 11);
        private final Runtime r = Runtime.getRuntime();
        private int columnInc;
        private int[] pts;
        private int ptNum;
        private int ascent, descent;
        private final Rectangle graphOutlineRect = new Rectangle();
        private final Rectangle2D mfRect = new Rectangle2D.Float();
        private final Rectangle2D muRect = new Rectangle2D.Float();
        private final Line2D graphLine = new Line2D.Float();
        private final Color graphColor = new Color(46, 139, 87);
        private final Color mfColor = new Color(0, 100, 0);
        private String usedStr;

        public Surface() {
            setBackground(BLACK);
            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (thread == null) {
                        start();
                    } else {
                        stop();
                    }
                }
            });
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
            return new Dimension(135, 80);
        }

        @Override
        public void paint(Graphics g) {

            if (big == null) {
                return;
            }

            big.setBackground(getBackground());
            big.clearRect(0, 0, w, h);

            float freeMemory = r.freeMemory();
            float totalMemory = r.totalMemory();

            // .. Draw allocated and used strings ..
            big.setColor(GREEN);
            big.drawString((int) totalMemory / 1024
                    + "K allocated", 4.0f, ascent + 0.5f);
            usedStr = ((int) (totalMemory - freeMemory)) / 1024
                    + "K used";
            big.drawString(usedStr, 4, h - descent);

            // Calculate remaining size
            float ssH = ascent + descent;
            float remainingHeight = (h - (ssH * 2) - 0.5f);
            float blockHeight = remainingHeight / 10;
            float blockWidth = 20.0f;

            // .. Memory Free ..
            big.setColor(mfColor);
            int MemUsage = (int) ((freeMemory / totalMemory) * 10);
            int i = 0;
            for (; i < MemUsage; i++) {
                mfRect.setRect(5, ssH + i * blockHeight,
                        blockWidth, blockHeight - 1);
                big.fill(mfRect);
            }

            // .. Memory Used ..
            big.setColor(GREEN);
            for (; i < 10; i++) {
                muRect.setRect(5, ssH + i * blockHeight,
                        blockWidth, blockHeight - 1);
                big.fill(muRect);
            }

            // .. Draw History Graph ..
            big.setColor(graphColor);
            int graphX = 30;
            int graphY = (int) ssH;
            int graphW = w - graphX - 5;
            int graphH = (int) remainingHeight;
            graphOutlineRect.setRect(graphX, graphY, graphW, graphH);
            big.draw(graphOutlineRect);

            int graphRow = graphH / 10;

            // .. Draw row ..
            for (int j = graphY; j <= graphH + graphY; j += graphRow) {
                graphLine.setLine(graphX, j, graphX + graphW, j);
                big.draw(graphLine);
            }

            // .. Draw animated column movement ..
            int graphColumn = graphW / 15;

            if (columnInc == 0) {
                columnInc = graphColumn;
            }

            for (int j = graphX + columnInc; j < graphW + graphX; j +=
                            graphColumn) {
                graphLine.setLine(j, graphY, j, graphY + graphH);
                big.draw(graphLine);
            }

            --columnInc;

            if (pts == null) {
                pts = new int[graphW];
                ptNum = 0;
            } else if (pts.length != graphW) {
                int[] tmp = null;
                if (ptNum < graphW) {
                    tmp = new int[ptNum];
                    System.arraycopy(pts, 0, tmp, 0, tmp.length);
                } else {
                    tmp = new int[graphW];
                    System.arraycopy(pts, pts.length - tmp.length, tmp, 0,
                            tmp.length);
                    ptNum = tmp.length - 2;
                }
                pts = new int[graphW];
                System.arraycopy(tmp, 0, pts, 0, tmp.length);
            } else {
                big.setColor(YELLOW);
                pts[ptNum] =
                        (int) (graphY + graphH * (freeMemory / totalMemory));
                for (int j = graphX + graphW - ptNum, k = 0; k < ptNum; k++, j++) {
                    if (k != 0) {
                        if (pts[k] != pts[k - 1]) {
                            big.drawLine(j - 1, pts[k - 1], j, pts[k]);
                        } else {
                            big.fillRect(j, pts[k], 1, 1);
                        }
                    }
                }
                if (ptNum + 2 == pts.length) {
                    // throw out oldest point
                    for (int j = 1; j < ptNum; j++) {
                        pts[j - 1] = pts[j];
                    }
                    --ptNum;
                } else {
                    ptNum++;
                }
            }
            g.drawImage(bimg, 0, 0, this);
        }

        public void start() {
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.setName("MemoryMonitor");
            thread.start();
        }

        public synchronized void stop() {
            thread = null;
            notify();
        }

        @Override
        @SuppressWarnings("SleepWhileHoldingLock")
        public void run() {

            Thread me = Thread.currentThread();

            while (thread == me && !isShowing() || getSize().width == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }
            }

            while (thread == me && isShowing()) {
                Dimension d = getSize();
                if (d.width != w || d.height != h) {
                    w = d.width;
                    h = d.height;
                    bimg = (BufferedImage) createImage(w, h);
                    big = bimg.createGraphics();
                    big.setFont(font);
                    FontMetrics fm = big.getFontMetrics(font);
                    ascent = fm.getAscent();
                    descent = fm.getDescent();
                }
                repaint();
                try {
                    Thread.sleep(sleepAmount);
                } catch (InterruptedException e) {
                    break;
                }
                if (dateStampCB.isSelected()) {
                    System.out.println(new Date() + " " + usedStr);
                }
            }
            thread = null;
        }
    }

    public static void main(String[] s) {
        final MemoryMonitor demo = new MemoryMonitor();
        WindowListener l = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                demo.surf.start();
            }

            @Override
            public void windowIconified(WindowEvent e) {
                demo.surf.stop();
            }
        };
        JFrame f = new JFrame("J2D Demo - MemoryMonitor");
        f.addWindowListener(l);
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(200, 200));
        f.setVisible(true);
        demo.surf.start();
    }
}
