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

package java.desktop.windows.classes.sun.awt.windows;


import java.awt.*;
import java.awt.peer.*;
import java.awt.im.InputMethodRequests;















final class WTextAreaPeer extends WTextComponentPeer implements TextAreaPeer {

    // WComponentPeer overrides

    @Override
    public Dimension getMinimumSize() {
        return getMinimumSize(10, 60);
    }

    // TextAreaPeer implementation

    @Override
    public void insert(String text, int pos) {
        replaceRange(text, pos, pos);
    }

    @Override
    public native void replaceRange(String text, int start, int end);

    @Override
    public Dimension getPreferredSize(int rows, int cols) {
        return getMinimumSize(rows, cols);
    }

    @Override
    public Dimension getMinimumSize(int rows, int cols) {
        FontMetrics fm = getFontMetrics(((TextArea)target).getFont());
        return new Dimension(fm.charWidth('0') * cols + 20, fm.getHeight() * rows + 20);
    }

    @Override
    public InputMethodRequests getInputMethodRequests() {
           return null;
    }

    // Toolkit & peer internals

    WTextAreaPeer(TextArea target) {
        super(target);
    }

    @Override
    native void create(WComponentPeer parent);
}
