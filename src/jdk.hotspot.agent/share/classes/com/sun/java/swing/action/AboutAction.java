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

// Referenced classes of package com.sun.java.swing.action:
//            DelegateAction, ActionManager

public class AboutAction extends DelegateAction
{

    public AboutAction()
    {
        this("general/About16.gif");
    }

    public AboutAction(String iconPath)
    {
        super("About...", ActionManager.getIcon(iconPath));
        putValue("ActionCommandKey", "about-command");
        putValue("ShortDescription", "About...");
        putValue("LongDescription", "System information and version of the application.");
        putValue("MnemonicKey", VALUE_MNEMONIC);
    }

    public static final String VALUE_COMMAND = "about-command";
    public static final String VALUE_NAME = "About...";
    public static final String VALUE_SMALL_ICON = "general/About16.gif";
    public static final String VALUE_LARGE_ICON = "general/About24.gif";
    public static final Integer VALUE_MNEMONIC = 65;
    public static final String VALUE_SHORT_DESCRIPTION = "About...";
    public static final String VALUE_LONG_DESCRIPTION = "System information and version of the application.";

}
