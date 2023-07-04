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

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

package transparentruler;


import java.awt.*;
import java.awt.GraphicsDevice.WindowTranslucency;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D.Float;
import java.lang.reflect.InvocationTargetException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
 * This sample demonstrates shaped and translucent window feature.
 */
@SuppressWarnings("serial")
public class Ruler extends JFrame {

    private static final Color BACKGROUND = Color.RED;
    private static final Color FOREGROUND = Color.WHITE;
    private static final int OPACITY = 180;
    private static final int W = 70;
    private static final int F_HEIGHT = 400;
    private static final int F_WIDTH = (int) (F_HEIGHT * 1.618 + 0.5);

    private static boolean translucencySupported;
    private static boolean transparencySupported;

    private static boolean checkTranslucencyMode(WindowTranslucency arg) {
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.isWindowTranslucencySupported(arg);
    }

    public Shape buildShape() {
        int h = getHeight();
        int w = getWidth();
        float a = (float) Math.hypot(h, w);
        Float path = new java.awt.geom.Path2D.Float();
        path.moveTo(0, 0);
        path.lineTo(w, 0);
        path.lineTo(0, h);
        path.closePath();
        path.moveTo(W, W);
        path.lineTo(W, h - W * (a + h) / w);
        path.lineTo(w - W * (a + w) / h, W);
        path.closePath();
        return path;
    }

    private final ComponentAdapter componentListener = new ComponentAdapter() {

        /**
         * Applies the shape to window. It is recommended to apply shape in
         * componentResized() method
         */
        @Override
        public void componentResized(ComponentEvent e) {

            // We do apply shape only if PERPIXEL_TRANSPARENT is supported
            if (transparencySupported) {
                setShape(buildShape());
            }
        }
    };

    private final Action exitAction = new AbstractAction("Exit") {

        {
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    private final JPopupMenu jPopupMenu = new JPopupMenu();

    {
        jPopupMenu.add(new JMenuItem(exitAction));

        // To avoid popup cutting by main window shape forbid light-weight popups
        jPopupMenu.setLightWeightPopupEnabled(false);
    }

    /**
     * Implements mouse-related behavior: window dragging and popup menu
     * invocation
     */
    private final MouseAdapter mouseListener = new MouseAdapter() {

        int x, y;

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                x = e.getX();
                y = e.getY();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0) {
                setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                jPopupMenu.show(getContentPane(), e.getX(), e.getY());
            }
        }
    };

    /**
     * Implements keyboard navigation. Arrows move by 5 pixels, Ctrl + arrows
     * move by 50 pixels, Alt + arrows move by 1 pixel.
     * Esc exits the application.
     */
    private final KeyAdapter keyboardListener = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            int step = e.isControlDown() ? 50 : e.isAltDown() ? 1 : 5;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    setLocation(getX() - step, getY());
                    break;
                case KeyEvent.VK_RIGHT:
                    setLocation(getX() + step, getY());
                    break;
                case KeyEvent.VK_UP:
                    setLocation(getX(), getY() - step);
                    break;
                case KeyEvent.VK_DOWN:
                    setLocation(getX(), getY() + step);
                    break;
                case KeyEvent.VK_ESCAPE:
                    exitAction.actionPerformed(null);
            }
        }
    };

    public Ruler() {
        setUndecorated(true);

        // Enables perpixel translucency
        setBackground(new Color(BACKGROUND.getRed(), BACKGROUND.getGreen(),
                BACKGROUND.getBlue(), OPACITY));

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addComponentListener(componentListener);
        addKeyListener(keyboardListener);
        setContentPane(new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D gg = (Graphics2D) g.create();
                int w = getWidth();
                int h = getHeight();
                int hh = gg.getFontMetrics().getAscent();

                // This is an approach to apply shape when PERPIXEL_TRANSPARENT
                // isn't supported
                if (!transparencySupported) {
                    gg.setBackground(new Color(0, 0, 0, 0));
                    gg.clearRect(0, 0, w, h);
                    gg.clip(buildShape());

                    gg.setBackground(Ruler.this.getBackground());
                    gg.clearRect(0, 0, w, h);
                }

                gg.setColor(FOREGROUND);
                for (int x = 0; x < w * (h - 8) / h - 5; x += 5) {
                    boolean hi = x % 50 == 0;
                    gg.drawLine(x + 5, 0, x + 5,
                            hi ? 20 : (x % 25 == 0 ? 13 : 8));
                    if (hi) {
                        String number = Integer.toString(x);
                        int ww = gg.getFontMetrics().stringWidth(number);
                        gg.drawString(number, x + 5 - ww / 2, 20 + hh);
                    }
                }

                gg.dispose();
            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(F_WIDTH, F_HEIGHT);
        setLocationByPlatform(true);
    }

    /**
     * @param args the command line arguments are ignored
     */
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {

        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                translucencySupported = checkTranslucencyMode(PERPIXEL_TRANSLUCENT);
                transparencySupported = checkTranslucencyMode(PERPIXEL_TRANSPARENT);

                if (!translucencySupported) {
                    System.err.println("This application requires "
                            + "'PERPIXEL_TRANSLUCENT' translucency mode to "
                            + "be supported.");
                    System.exit(-1);
                }

                Ruler ruler = new Ruler();
                ruler.setVisible(true);
            }
        });
    }
}
