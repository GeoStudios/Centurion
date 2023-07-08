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

package jdk.jconsole.share.classes.sun.tools.jconsole;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

@SuppressWarnings("serial")
public class HTMLPane extends JEditorPane {
    private boolean hasSelection = false;

    public HTMLPane() {
        setContentType("text/html");
        setEditable(false);
        ((DefaultCaret)getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        addCaretListener(new CaretListener() {
            // Listen for selection changes
            public void caretUpdate(CaretEvent e) {
                setHasSelection(e.getDot() != e.getMark());
            }
        });
    }

    public synchronized void setHasSelection(boolean b) {
        hasSelection = b;
    }

    public synchronized boolean getHasSelection() {
        return hasSelection;
    }

    public void setText(String text) {
        // Apply update only if a selection is not active
        if (!getHasSelection()) {
            // JEditorPane does not automatically pick up fg color
            String textColor =
                String.format("%06x", getForeground().getRGB() & 0xFFFFFF);
            super.setText("<html><body text=#"+textColor+">" + text + "</body></html>");
        }
    }
}
