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

package java.desktop.windows.classes.com.sun.java.swing.plaf.windows;


import java.desktop.windows.classes.com.sun.java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicPopupMenuSeparatorUI;
import javax.swing.plaf.ComponentUI;
import java.desktop.windows.classes.com.sun.java.swing.plaf.windows.TMSchema.Part;
import java.desktop.windows.classes.com.sun.java.swing.plaf.windows.TMSchema.State;
import java.desktop.windows.classes.com.sun.java.swing.plaf.windows.XPStyle.Skin;















/**
 * Windows {@literal L&F} implementation of PopupMenuSeparatorUI.
 *
 */

public class WindowsPopupMenuSeparatorUI extends BasicPopupMenuSeparatorUI {

    public static ComponentUI createUI(JComponent c) {
        return new WindowsPopupMenuSeparatorUI();
    }

    public void paint(Graphics g, JComponent c) {
        Dimension s = c.getSize();
        XPStyle xp = XPStyle.getXP();
        if (WindowsMenuItemUI.isVistaPainting(xp)) {
            int x = 1;
            Component parent = c.getParent();
            if (parent instanceof JComponent) {
                Object gutterOffsetObject =
                    ((JComponent) parent).getClientProperty(
                        WindowsPopupMenuUI.GUTTER_OFFSET_KEY);
                if (gutterOffsetObject instanceof Integer) {
                    /*
                     * gutter offset is in parent's coordinates.
                     * See comment in
                     * WindowsPopupMenuUI.getTextOffset(JComponent)
                     */
                    x = ((Integer) gutterOffsetObject).intValue() - c.getX();
                    x += WindowsPopupMenuUI.getGutterWidth();
                }
            }
            Skin skin = xp.getSkin(c, Part.MP_POPUPSEPARATOR);
            int skinHeight = skin.getHeight();
            int y = (s.height - skinHeight) / 2;
            skin.paintSkin(g, x, y, s.width - x - 1, skinHeight, State.NORMAL);
        } else {
            int y = s.height / 2;
            g.setColor(c.getForeground());
            g.drawLine(1, y - 1, s.width - 2, y - 1);

            g.setColor(c.getBackground());
            g.drawLine(1, y,     s.width - 2, y);
        }
    }

    public Dimension getPreferredSize(JComponent c) {
        int fontHeight = 0;
        Font font = c.getFont();
        if (font != null) {
            fontHeight = c.getFontMetrics(font).getHeight();
        }

        return new Dimension(0, fontHeight/2 + 2);
    }

}
