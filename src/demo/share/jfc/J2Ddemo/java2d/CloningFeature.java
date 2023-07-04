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


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;


/**
 * Illustration of how to use the clone feature of the demo.
 */
@SuppressWarnings("serial")
public final class CloningFeature extends JPanel implements Runnable {

    private final DemoInstVarsAccessor demoInstVars;
    private Thread thread;
    private final JTextArea ta;

    public CloningFeature(DemoInstVarsAccessor demoInstVars) {
        this.demoInstVars = demoInstVars;

        setLayout(new BorderLayout());
        EmptyBorder eb = new EmptyBorder(5, 5, 5, 5);
        SoftBevelBorder sbb = new SoftBevelBorder(BevelBorder.RAISED);
        setBorder(new CompoundBorder(eb, sbb));

        ta = new JTextArea("Cloning Demonstrated\n\nClicking once on a demo\n");
        ta.setMinimumSize(new Dimension(300, 500));
        JScrollPane scroller = new JScrollPane();
        scroller.getViewport().add(ta);
        ta.setFont(new Font("Dialog", Font.PLAIN, 14));
        ta.setForeground(Color.black);
        ta.setBackground(Color.lightGray);
        ta.setEditable(false);

        add("Center", scroller);

        start();
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setName("CloningFeature");
        thread.start();
    }

    public void stop() {
        if (thread != null) {
            thread.interrupt();
        }
        thread = null;
    }

    @Override
    @SuppressWarnings("SleepWhileHoldingLock")
    public void run() {


        int index = demoInstVars.getTabbedPane().getSelectedIndex();
        if (index == 0) {
            demoInstVars.getTabbedPane().setSelectedIndex(1);
            try {
                Thread.sleep(3333);
            } catch (Exception e) {
                return;
            }
        }

        if (!demoInstVars.getControls().toolBarCB.isSelected()) {
            demoInstVars.getControls().toolBarCB.setSelected(true);
            try {
                Thread.sleep(2222);
            } catch (Exception e) {
                return;
            }
        }

        index = demoInstVars.getTabbedPane().getSelectedIndex() - 1;
        DemoGroup dg = demoInstVars.getGroup()[index];
        DemoPanel dp = (DemoPanel) dg.getPanel().getComponent(0);
        if (dp.surface == null) {
            ta.append("Sorry your zeroth component is not a Surface.");
            return;
        }

        dg.mouseClicked(dp.surface);

        try {
            Thread.sleep(3333);
        } catch (Exception e) {
            return;
        }

        ta.append("Clicking the ToolBar double document button\n");
        try {
            Thread.sleep(3333);
        } catch (Exception e) {
            return;
        }

        dp = (DemoPanel) dg.clonePanels[0].getComponent(0);

        if (dp.tools != null) {
            for (int i = 0; i < 3 && thread != null; i++) {
                ta.append("   Cloning\n");
                dp.tools.cloneB.doClick();
                try {
                    Thread.sleep(3333);
                } catch (Exception e) {
                    return;
                }
            }
        }

        ta.append("Changing attributes \n");

        try {
            Thread.sleep(3333);
        } catch (Exception e) {
            return;
        }

        Component[] cmps = dg.clonePanels[0].getComponents();
        for (int i = 0; i < cmps.length && thread != null; i++) {
            if ((dp = (DemoPanel) cmps[i]).tools == null) {
                continue;
            }
            switch (i) {
                case 0:
                    ta.append("   Changing AntiAliasing\n");
                    dp.tools.aliasB.doClick();
                    break;
                case 1:
                    ta.append("   Changing Composite & Texture\n");
                    dp.tools.compositeB.doClick();
                    dp.tools.textureB.doClick();
                    break;
                case 2:
                    ta.append("   Changing Screen\n");
                    dp.tools.screenCombo.setSelectedIndex(4);
                    break;
                case 3:
                    ta.append("   Removing a clone\n");
                    dp.tools.cloneB.doClick();
            }
            try {
                Thread.sleep(3333);
            } catch (Exception e) {
                return;
            }
        }

        ta.append("\nAll Done!");
    }
}
