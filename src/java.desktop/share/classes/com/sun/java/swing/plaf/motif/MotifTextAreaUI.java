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

package java.desktop.share.classes.com.sun.java.swing.plaf.motif;


import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.Caret;















/**
 * Provides the look and feel for a plain text editor.  In this
 * implementation the default UI is extended to act as a simple
 * view factory.
 *
 */
public class MotifTextAreaUI extends BasicTextAreaUI {

    /**
     * Creates a UI for a JTextArea.
     *
     * @param ta a text area
     * @return the UI
     */
    public static ComponentUI createUI(JComponent ta) {
        return new MotifTextAreaUI();
    }

    /**
     * Creates the object to use for a caret.  By default an
     * instance of MotifTextUI.MotifCaret is created.  This method
     * can be redefined to provide something else that implements
     * the Caret interface.
     *
     * @return the caret object
     */
    protected Caret createCaret() {
        return MotifTextUI.createCaret();
    }

}
