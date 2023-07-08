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
import jdk.hotspot.agent.share.classes.com.sun.java.awt.Dimension;
import jdk.hotspot.agent.share.classes.com.sun.java.awt.Insets;
import javax.swing.*;















// Referenced classes of package com.sun.java.swing.ui:
//            ToggleActionPropertyChangeListener, StatusBar, CommonUI

public abstract class CommonToolBar extends JToolBar
{

    protected CommonToolBar(ActionManager manager)
    {
        this(manager, StatusBar.getInstance());
    }

    protected CommonToolBar(ActionManager manager, StatusBar status)
    {
        this.manager = manager;
        statusBar = status;
        buttonSize = new Dimension(CommonUI.getButtconPrefSize());
        buttonInsets = new Insets(0, 0, 0, 0);
        addComponents();
    }

    protected abstract void addComponents();

    protected void addButton(Action action)
    {
        javax.swing.JButton button = add(action);
        configureButton(button, action);
    }

    protected void addToggleButton(StateChangeAction a)
    {
        addToggleButton(a, null);
    }

    protected void addToggleButton(StateChangeAction a, ButtonGroup group)
    {
        JToggleButton button = new JToggleButton(a);
        button.addItemListener(a);
        button.setSelected(a.isSelected());
        if(group != null)
            group.add(button);
        add(button);
        configureToggleButton(button, a);
    }

    protected void configureToggleButton(JToggleButton button, Action action)
    {
        configureButton(button, action);
        action.addPropertyChangeListener(new ToggleActionPropertyChangeListener(button));
    }

    protected void configureButton(AbstractButton button, Action action)
    {
        button.setToolTipText((String)action.getValue("Name"));
        button.setText("");
        button.addMouseListener(statusBar);
    }

    protected ActionManager manager;
    private final Dimension buttonSize;
    private final Insets buttonInsets;
    private final StatusBar statusBar;
}
