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

package java.desktop.windows.classes.com.sun.java.swing.plaf.windows;


import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPasswordFieldUI;
import javax.swing.text.Caret;















/**
 * Windows rendition of the component.
 */
public class WindowsPasswordFieldUI extends BasicPasswordFieldUI {

    /**
     * Creates a UI for a JPasswordField
     *
     * @param c the password field
     * @return the UI
     */
    public static ComponentUI createUI(JComponent c) {
        return new WindowsPasswordFieldUI();
    }


    /**
     * Creates the object to use for a caret.  By default an
     * instance of WindowsCaret is created.  This method
     * can be redefined to provide something else that implements
     * the InputPosition interface or a subclass of DefaultCaret.
     *
     * @return the caret object
     */
    protected Caret createCaret() {
        return new WindowsTextUI.WindowsCaret();
    }
}
