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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ui.action;

import javax.swing.Action;
import com.sun.java.swing.action.ActionManager;
import com.sun.java.swing.action.StateChangeAction;

/**
 * Callback action for ThreadInfoing the Object Type
 */
public class ThreadInfoAction extends StateChangeAction {

    public static final String VALUE_COMMAND = "thread-info-command";
    public static final String VALUE_NAME = "Show Thread Information...";
    public static final String VALUE_SMALL_ICON = "general/Information16.gif";
    public static final String VALUE_LARGE_ICON = "general/Information24.gif";
    public static final Integer VALUE_MNEMONIC = (int) 'I';
    public static final String VALUE_SHORT_DESCRIPTION = "Show Thread Informaion";
    public static final String VALUE_LONG_DESCRIPTION = "Show information about the current thread";

    public ThreadInfoAction() {
        super(VALUE_NAME, ActionManager.getIcon(VALUE_SMALL_ICON));

        putValue(Action.ACTION_COMMAND_KEY, VALUE_COMMAND);
        putValue(Action.SHORT_DESCRIPTION, VALUE_SHORT_DESCRIPTION);
        putValue(Action.LONG_DESCRIPTION, VALUE_LONG_DESCRIPTION);
        putValue(Action.MNEMONIC_KEY, VALUE_MNEMONIC);
    }
}
