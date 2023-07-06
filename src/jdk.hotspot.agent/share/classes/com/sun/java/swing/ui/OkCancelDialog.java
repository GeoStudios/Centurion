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

import jdk.hotspot.agent.share.classes.com.sun.java.awt.BorderLayout;
import jdk.hotspot.agent.share.classes.com.sun.java.awt.Container;
import jdk.hotspot.agent.share.classes.com.sun.java.awt.event.ActionEvent;
import jdk.hotspot.agent.share.classes.com.sun.java.awt.event.Actionjava.util.Listener;
import javax.swing.JDialog;
import javax.swing.JPanel;

// Referenced classes of package com.sun.java.swing.ui:
//            OkCancelButtonPanel, CommonUI

public class OkCancelDialog extends JDialog
    implements ActionListener
{

    public OkCancelDialog(String title, JPanel panel)
    {
        this(title, panel, true);
    }

    public OkCancelDialog(String title, JPanel panel, boolean modal)
    {
        setTitle(title);
        setModal(modal);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        pane.add(panel, "Center");
        pane.add(new OkCancelButtonPanel(this), "South");
        pack();
        CommonUI.centerComponent(this);
    }

    public boolean isOk()
    {
        return okPressed;
    }

    public void actionPerformed(ActionEvent evt)
    {
        String command = evt.getActionCommand();
        if(command.equals("ok-command"))
        {
            okPressed = true;
            setVisible(false);
            dispose();
        } else
        if(command.equals("cancel-command"))
        {
            okPressed = false;
            setVisible(false);
            dispose();
        }
    }

    private boolean okPressed;
}