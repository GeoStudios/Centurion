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

package jdk.hotspot.agent.share.classes.com.sun.java.swing.ui;

import jdk.hotspot.agent.share.classes.com.sun.java.swing.action.ActionManager;
import jdk.hotspot.agent.share.classes.com.sun.java.swing.action.StateChangeAction;
import javax.swing.*;

// Referenced classes of package com.sun.java.swing.ui:
//            ToggleActionPropertyChangeListener, StatusBar

public abstract class CommonMenuBar extends JMenuBar
{

    protected CommonMenuBar(ActionManager manager)
    {
        this(manager, StatusBar.getInstance());
    }

    protected CommonMenuBar(ActionManager manager, StatusBar status)
    {
        this.manager = manager;
        statusBar = status;
        configureMenu();
    }

    protected abstract void configureMenu();

    protected void configureToggleMenuItem(JMenuItem menuItem, Action action)
    {
        configureMenuItem(menuItem, action);
        action.addPropertyChangeListener(new ToggleActionPropertyChangeListener(menuItem));
    }

    protected void configureMenuItem(JMenuItem menuItem, Action action)
    {
        menuItem.addMouseListener(statusBar);
    }

    protected JMenu createMenu(String name, char mnemonic)
    {
        JMenu menu = new JMenu(name);
        menu.setMnemonic(mnemonic);
        return menu;
    }

    protected void addMenuItem(JMenu menu, Action action)
    {
        JMenuItem menuItem = menu.add(action);
        configureMenuItem(menuItem, action);
    }

    protected void addCheckBoxMenuItem(JMenu menu, StateChangeAction a)
    {
        addCheckBoxMenuItem(menu, a, false);
    }

    protected void addCheckBoxMenuItem(JMenu menu, StateChangeAction a, boolean selected)
    {
        JCheckBoxMenuItem mi = new JCheckBoxMenuItem(a);
        mi.addItemListener(a);
        mi.setSelected(selected);
        menu.add(mi);
        configureToggleMenuItem(mi, a);
    }

    protected void addRadioButtonMenuItem(JMenu menu, ButtonGroup group, StateChangeAction a)
    {
        addRadioButtonMenuItem(menu, group, a, false);
    }

    protected void addRadioButtonMenuItem(JMenu menu, ButtonGroup group, StateChangeAction a, boolean selected)
    {
        JRadioButtonMenuItem mi = new JRadioButtonMenuItem(a);
        mi.addItemListener(a);
        mi.setSelected(selected);
        menu.add(mi);
        if(group != null)
            group.add(mi);
        configureToggleMenuItem(mi, a);
    }

    protected ActionManager manager;
    private final StatusBar statusBar;
}
