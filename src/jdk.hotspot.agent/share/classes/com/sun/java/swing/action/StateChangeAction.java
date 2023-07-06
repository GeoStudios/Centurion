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


import jdk.hotspot.agent.share.classes.com.sun.java.awt.event.ItemEvent;
import jdk.hotspot.agent.share.classes.com.sun.java.awt.event.Itemjava.util.Listener;
import javax.swing.Icon;















// Referenced classes of package com.sun.java.swing.action:
//            DelegateAction

public abstract class StateChangeAction extends DelegateAction
    implements ItemListener
{

    public StateChangeAction(String name)
    {
        super(name, null);
        selected = false;
    }

    public StateChangeAction(String name, Icon icon)
    {
        super(name, icon);
        selected = false;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public synchronized void setSelected(boolean newValue)
    {
        boolean oldValue = selected;
        if(oldValue != newValue)
        {
            selected = newValue;
            firePropertyChange("selected", Boolean.valueOf(oldValue), Boolean.valueOf(newValue));
        }
    }

    public void setItemListener(ItemListener listener)
    {
        this.listener = listener;
    }

    public ItemListener getItemListener()
    {
        return listener;
    }

    public void itemStateChanged(ItemEvent evt)
    {
        setSelected(evt.getStateChange() == 1);
        if(listener != null)
            listener.itemStateChanged(evt);
    }

    protected boolean selected;
    private ItemListener listener;
}
