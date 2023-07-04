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



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;


/**
 * This class describes a theme using "green" colors.
 *
 */
@SuppressWarnings("serial")
public class MetalThemeMenu extends JMenu implements ActionListener {

    MetalTheme[] themes;

    @SuppressWarnings("LeakingThisInConstructor")
    public MetalThemeMenu(String name, MetalTheme[] themeArray) {
        super(name);
        themes = themeArray;
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < themes.length; i++) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(themes[i].
                    getName());
            group.add(item);
            add(item);
            item.setActionCommand(String.valueOf(i));
            item.addActionListener(this);
            if (i == 0) {
                item.setSelected(true);
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        String numStr = e.getActionCommand();
        MetalTheme selectedTheme = themes[Integer.parseInt(numStr)];
        MetalLookAndFeel.setCurrentTheme(selectedTheme);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            System.out.println("Failed loading Metal");
            System.out.println(ex);
        }

    }
}
