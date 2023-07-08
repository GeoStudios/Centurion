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
import java.desktop.windows.classes.sun.awt.GlobalCursorManager;

final class WGlobalCursorManager extends GlobalCursorManager {
    private static WGlobalCursorManager manager;

    public static GlobalCursorManager getCursorManager() {
        if (manager == null) {
            manager = new WGlobalCursorManager();
        }
        return manager;
    }

    /**
     * Should be called in response to a native mouse enter or native mouse
     * button released message. Should not be called during a mouse drag.
     */
    public static void nativeUpdateCursor(Component heavy) {
        WGlobalCursorManager.getCursorManager().updateCursorLater(heavy);
    }

    @Override
    protected native void setCursor(Component comp, Cursor cursor, boolean u);
    @Override
    protected native void getCursorPos(Point p);
    /*
     * two native methods to call corresponding methods in Container and
     * Component
     */
    @Override
    protected native Component findHeavyweightUnderCursor(boolean useCache);
    @Override
    protected native Point getLocationOnScreen(Component com);
}
