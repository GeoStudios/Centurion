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

import jdk.hotspot.agent.share.classes.com.sun.java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class FileMenu extends AbstractAction
{

    public FileMenu()
    {
        super("File");
        putValue("ActionCommandKey", "file-menu-command");
        putValue("ShortDescription", "File operations");
        putValue("LongDescription", "File operations");
        putValue("MnemonicKey", VALUE_MNEMONIC);
    }

    public void actionPerformed(ActionEvent actionevent)
    {
    }

    public static final String VALUE_COMMAND = "file-menu-command";
    public static final String VALUE_NAME = "File";
    public static final Integer VALUE_MNEMONIC = 70;
    public static final String VALUE_SHORT_DESCRIPTION = "File operations";
    public static final String VALUE_LONG_DESCRIPTION = "File operations";

}
