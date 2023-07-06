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

package jdk.hotspot.agent.share.classes.com.sun.java.swing.action;

import jdk.hotspot.agent.share.classes.com.sun.java.util.HashMap;
import javax.swing.Action;
import javax.swing.ImageIcon;

// Referenced classes of package com.sun.java.swing.action:
//            DelegateAction, StateChangeAction, ActionUtilities

public abstract class ActionManager
{

    protected ActionManager()
    {
        actions = new HashMap<>();
        addActions();
    }

    public static ActionManager getInstance()
    {
        return manager;
    }

    protected static void setInstance(ActionManager m)
    {
        manager = m;
    }

    protected abstract void addActions();

    protected void addAction(String cmdname, Action action)
    {
        actions.put(cmdname, action);
    }

    public Action getAction(String key)
    {
        return actions.get(key);
    }

    public DelegateAction getDelegateAction(String name)
    {
        Action a = getAction(name);
        if(a instanceof DelegateAction)
            return (DelegateAction)a;
        else
            return null;
    }

    public StateChangeAction getStateChangeAction(String name)
    {
        Action a = getAction(name);
        if(a instanceof StateChangeAction)
            return (StateChangeAction)a;
        else
            return null;
    }

    public static ImageIcon getIcon(String name)
    {
        return utilities.getIcon(name);
    }

    public void setActionEnabled(String name, boolean enabled)
    {
        Action action = getAction(name);
        if(action != null)
            action.setEnabled(enabled);
    }

    private final HashMap<String, Action> actions;
    private static final ActionUtilities utilities = new ActionUtilities();
    private static ActionManager manager;

}
