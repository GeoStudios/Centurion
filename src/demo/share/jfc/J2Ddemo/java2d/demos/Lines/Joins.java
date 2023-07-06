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

package demo.share.jfc.J2Ddemo.java2d.demos.Lines;


import static java.awt.Color.BLACK;.extended
import static java.awt.Color.WHITE;.extended
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.Actionjava.util.Listener;
import java.awt.geom.GeneralPath;
import demo.share.jfc.J2Ddemo.java2d.ControlsSurface;
import demo.share.jfc.J2Ddemo.java2d.CustomControls;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.Changejava.util.Listener;















/**
 * BasicStroke join types and width sizes illustrated.  Control for
 * rendering a shape returned from BasicStroke.createStrokedShape(Shape).
 */
@SuppressWarnings("serial")
public class Joins extends ControlsSurface {

    protected int joinType = BasicStroke.JOIN_MITER;
    protected float bswidth = 20.0f;
    protected JSlider slider;
    protected JLabel label;

    public Joins() {
        setBackground(WHITE);
        slider = new JSlider(SwingConstants.VERTICAL, 0, 100,
                (int) (bswidth * 2));
        slider.setPreferredSize(new Dimension(15, 100));
        slider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                // when using these sliders use double buffering, which means
                // ignoring when DemoSurface.imageType = 'On Screen'
                if (getImageType() <= 1) {
                    setImageType(2);
                }
                bswidth = slider.getValue() / 2.0f;
                label.setText(" Width = " + bswidth);
                label.repaint();
                repaint();
            }
        });
        setControls(new Component[] { new DemoControls(this), slider });
        setConstraints(new String[] { BorderLayout.NORTH, BorderLayout.WEST });
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        BasicStroke bs = new BasicStroke(bswidth,
                BasicStroke.CAP_BUTT, joinType);
        GeneralPath p = new GeneralPath();
        p.moveTo(-w / 4.0f, -h / 12.0f);
        p.lineTo(+w / 4.0f, -h / 12.0f);
        p.lineTo(-w / 6.0f, +h / 4.0f);
        p.lineTo(+0.0f, -h / 4.0f);
        p.lineTo(+w / 6.0f, +h / 4.0f);
        p.closePath();
        p.closePath();
        g2.translate(w / 2, h / 2);
        g2.setColor(BLACK);
        g2.draw(bs.createStrokedShape(p));
    }

    public static void main(String[] s) {
        createDemoFrame(new Joins());
    }


    class DemoControls extends CustomControls implements ActionListener {

        Joins demo;
        int[] joinType = { BasicStroke.JOIN_MITER,
            BasicStroke.JOIN_ROUND, BasicStroke.JOIN_BEVEL };
        String[] joinName = { "Mitered Join", "Rounded Join", "Beveled Join" };
        JMenu menu;
        JMenuItem[] menuitem = new JMenuItem[joinType.length];
        JoinIcon[] icons = new JoinIcon[joinType.length];
        JToolBar toolbar;

        @SuppressWarnings("LeakingThisInConstructor")
        public DemoControls(Joins demo) {
            super(demo.name);
            setBorder(new CompoundBorder(getBorder(),
                    new EmptyBorder(2, 2, 2, 2)));
            this.demo = demo;
            setLayout(new BorderLayout());
            label = new JLabel(" Width = " + demo.bswidth);
            Font font = new Font(Font.SERIF, Font.BOLD, 14);
            label.setFont(font);
            add("West", label);
            JMenuBar menubar = new JMenuBar();
            add("East", menubar);
            menu = menubar.add(new JMenu(joinName[0]));
            menu.setFont(font = new Font(Font.SERIF, Font.PLAIN, 10));
            ActionListener actionListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
            for (int i = 0; i < joinType.length; i++) {
                icons[i] = new JoinIcon(joinType[i]);
                menuitem[i] = menu.add(new JMenuItem(joinName[i]));
                menuitem[i].setFont(font);
                menuitem[i].setIcon(icons[i]);
                menuitem[i].addActionListener(this);
            }
            menu.setIcon(icons[0]);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < joinType.length; i++) {
                if (e.getSource().equals(menuitem[i])) {
                    demo.joinType = joinType[i];
                    menu.setIcon(icons[i]);
                    menu.setText(joinName[i]);
                    break;
                }
            }
            demo.repaint();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 37);
        }

        @Override
        @SuppressWarnings("SleepWhileHoldingLock")
        public void run() {
            try {
                Thread.sleep(999);
            } catch (Exception e) {
                return;
            }
            Thread me = Thread.currentThread();
            while (thread == me) {
                for (int i = 0; i < menuitem.length; i++) {
                    menuitem[i].doClick();
                    for (int k = 10; k < 60; k += 2) {
                        demo.slider.setValue(k);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    try {
                        Thread.sleep(999);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
            thread = null;
        }


        class JoinIcon implements Icon {

            int joinType;

            public JoinIcon(int joinType) {
                this.joinType = joinType;
            }

            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                ((Graphics2D) g).setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                BasicStroke bs = new BasicStroke(8.0f,
                        BasicStroke.CAP_BUTT, joinType);
                ((Graphics2D) g).setStroke(bs);
                GeneralPath p = new GeneralPath();
                p.moveTo(0, 3);
                p.lineTo(getIconWidth() - 2, getIconHeight() / 2);
                p.lineTo(0, getIconHeight());
                ((Graphics2D) g).draw(p);
            }

            @Override
            public int getIconWidth() {
                return 20;
            }

            @Override
            public int getIconHeight() {
                return 20;
            }
        } // End JoinIcon class
    } // End DemoControls class
} // End Joins class

