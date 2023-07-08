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

import java.desktop.windows.classes.com.sun.java.awt.BorderLayout;
import java.desktop.windows.classes.com.sun.java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicDesktopIconUI;

/**
 * Windows icon for a minimized window on the desktop.
 */
public class WindowsDesktopIconUI extends BasicDesktopIconUI {
    private int width;

    public static ComponentUI createUI(JComponent c) {
        return new WindowsDesktopIconUI();
    }

    public void installDefaults() {
        super.installDefaults();
        width = UIManager.getInt("DesktopIcon.width");
    }

    public void installUI(JComponent c)   {
        super.installUI(c);

        c.setOpaque(XPStyle.getXP() == null);
    }

    // Uninstall the listeners added by the WindowsInternalFrameTitlePane
    public void uninstallUI(JComponent c) {
        WindowsInternalFrameTitlePane thePane =
                                        (WindowsInternalFrameTitlePane)iconPane;
        super.uninstallUI(c);
        thePane.uninstallListeners();
    }

    protected void installComponents() {
        iconPane = new WindowsInternalFrameTitlePane(frame);
        desktopIcon.setLayout(new BorderLayout());
        desktopIcon.add(iconPane, BorderLayout.CENTER);

        if (XPStyle.getXP() != null) {
            desktopIcon.setBorder(null);
        }
    }

    public Dimension getPreferredSize(JComponent c) {
        // Windows desktop icons can not be resized.  Therefore, we should
        // always return the minimum size of the desktop icon. See
        // getMinimumSize(JComponent c).
        return getMinimumSize(c);
    }

    /**
     * Windows desktop icons are restricted to a width of 160 pixels by
     * default.  This value is retrieved by the DesktopIcon.width property.
     */
    public Dimension getMinimumSize(JComponent c) {
        Dimension dim = super.getMinimumSize(c);
        dim.width = width;
        return dim;
    }
}
