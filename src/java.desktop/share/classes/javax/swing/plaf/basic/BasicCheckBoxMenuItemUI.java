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

package java.desktop.share.classes.javax.swing.plaf.basic;


import java.awt.*;
import java.awt.event.*;
import java.desktop.share.classes.javax.swing.*;
import java.desktop.share.classes.javax.swing.plaf.*;















/**
 * BasicCheckboxMenuItem implementation
 *
 */
public class BasicCheckBoxMenuItemUI extends BasicMenuItemUI {

    /**
     * Constructs a {@code BasicCheckBoxMenuItemUI}.
     */
    public BasicCheckBoxMenuItemUI() {}

    /**
     * Constructs a new instance of {@code BasicCheckBoxMenuItemUI}.
     *
     * @param c a component
     * @return a new instance of {@code BasicCheckBoxMenuItemUI}
     */
    public static ComponentUI createUI(JComponent c) {
        return new BasicCheckBoxMenuItemUI();
    }

    protected String getPropertyPrefix() {
        return "CheckBoxMenuItem";
    }

    /**
     * Invoked when mouse event occurs.
     *
     * @param item a menu item
     * @param e a mouse event
     * @param path an array of {@code MenuElement}
     * @param manager an instance of {@code MenuSelectionManager}
     */
    public void processMouseEvent(JMenuItem item,MouseEvent e,MenuElement[] path,MenuSelectionManager manager) {
        Point p = e.getPoint();
        if(p.x >= 0 && p.x < item.getWidth() &&
           p.y >= 0 && p.y < item.getHeight()) {
            if(e.getID() == MouseEvent.MOUSE_RELEASED) {
                manager.clearSelectedPath();
                item.doClick(0);
            } else
                manager.setSelectedPath(path);
        } else if(item.getModel().isArmed()) {
            MenuElement[] newPath = new MenuElement[path.length-1];
            int i,c;
            for(i=0,c=path.length-1;i<c;i++)
                newPath[i] = path[i];
            manager.setSelectedPath(newPath);
        }
    }
}
