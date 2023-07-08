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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.Itemjava.util.Listener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.Changejava.util.Listener;

/**
 * Global Controls panel for changing graphic attributes of
 * the demo surface.
 */
@SuppressWarnings("serial")
public class GlobalControls extends JPanel implements ItemListener,
        ChangeListener {
    private final DemoInstVarsAccessor demoInstVars;
    public static final String[] screenNames = {
        "Auto Screen", "On Screen", "Off Screen",
        "INT_xRGB", "INT_ARGB", "INT_ARGB_PRE", "INT_BGR",
        "3BYTE_BGR", "4BYTE_ABGR", "4BYTE_ABGR_PRE", "USHORT_565_RGB",
        "USHORT_x555_RGB", "BYTE_GRAY", "USHORT_GRAY",
        "BYTE_BINARY", "BYTE_INDEXED", "BYTE_BINARY 2 bit", "BYTE_BINARY 4 bit",
        "INT_RGBx", "USHORT_555x_RGB" };
    public final JComboBox<String> screenCombo;
    public TextureChooser texturechooser;
    public JCheckBox aliasCB, renderCB, toolBarCB;
    public JCheckBox compositeCB, textureCB;
    public JSlider slider;
    public Object obj;
    private final Font font = new Font(Font.SERIF, Font.PLAIN, 12);

    @SuppressWarnings("LeakingThisInConstructor")
    public GlobalControls(DemoInstVarsAccessor demoInstVars) {
        this.demoInstVars = demoInstVars;

        setLayout(new GridBagLayout());
        setBorder(new TitledBorder(new EtchedBorder(), "Global Controls"));

        aliasCB = createCheckBox("Anti-Aliasing", true, 0);
        renderCB = createCheckBox("Rendering Quality", false, 1);
        textureCB = createCheckBox("Texture", false, 2);
        compositeCB = createCheckBox("AlphaComposite", false, 3);

        screenCombo = new JComboBox<>();
        screenCombo.setPreferredSize(new Dimension(120, 18));
        screenCombo.setLightWeightPopupEnabled(true);
        screenCombo.setFont(font);
        for (int i = 0; i < screenNames.length; i++) {
            screenCombo.addItem(screenNames[i]);
        }
        screenCombo.addItemListener(this);
        J2Ddemo.addToGridBag(this, screenCombo, 0, 4, 1, 1, 0.0, 0.0);

        toolBarCB = createCheckBox("Tools", false, 5);

        slider = new JSlider(SwingConstants.HORIZONTAL, 0, 200, 30);
        slider.addChangeListener(this);
        TitledBorder tb = new TitledBorder(new EtchedBorder());
        tb.setTitleFont(font);
        tb.setTitle("Anim delay = 30 ms");
        slider.setBorder(tb);
        slider.setMinimumSize(new Dimension(80, 46));
        J2Ddemo.addToGridBag(this, slider, 0, 6, 1, 1, 1.0, 1.0);

        texturechooser = new TextureChooser(0, demoInstVars);
        J2Ddemo.addToGridBag(this, texturechooser, 0, 7, 1, 1, 1.0, 1.0);
    }

    private JCheckBox createCheckBox(String s, boolean b, int y) {
        JCheckBox cb = new JCheckBox(s, b);
        cb.setFont(font);
        cb.setHorizontalAlignment(SwingConstants.LEFT);
        cb.addItemListener(this);
        J2Ddemo.addToGridBag(this, cb, 0, y, 1, 1, 1.0, 1.0);
        return cb;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int value = slider.getValue();
        TitledBorder tb = (TitledBorder) slider.getBorder();
        tb.setTitle("Anim delay = " + value + " ms");
        int index = demoInstVars.getTabbedPane().getSelectedIndex() - 1;
        DemoGroup dg = demoInstVars.getGroup()[index];
        JPanel p = dg.getPanel();
        for (int i = 0; i < p.getComponentCount(); i++) {
            DemoPanel dp = (DemoPanel) p.getComponent(i);
            if (dp.tools != null && dp.tools.slider != null) {
                dp.tools.slider.setValue(value);
            }
        }
        slider.repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (demoInstVars.getTabbedPane().getSelectedIndex() != 0) {
            obj = e.getSource();
            int index = demoInstVars.getTabbedPane().getSelectedIndex() - 1;
            demoInstVars.getGroup()[index].setup(true);
            obj = null;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(135, 260);
    }
}
