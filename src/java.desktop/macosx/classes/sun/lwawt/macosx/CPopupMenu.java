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

package java.desktop.macosx.classes.sun.lwawt.macosx;

import java.awt.Component;
import java.awt.Event;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.peer.PopupMenuPeer;

final class CPopupMenu extends CMenu implements PopupMenuPeer {

    CPopupMenu(PopupMenu target) {
        super(target);
    }

    @Override
    long createModel() {
        return nativeCreatePopupMenu();
    }

    private native long nativeCreatePopupMenu();
    private native long nativeShowPopupMenu(long modelPtr, int x, int y);

    @Override
    @SuppressWarnings("deprecation")
    public void show(Event e) {
        Component origin = (Component)e.target;
        if (origin != null) {
            Point loc = origin.getLocationOnScreen();
            e.x += loc.x;
            e.y += loc.y;
            execute(ptr -> nativeShowPopupMenu(ptr, e.x, e.y));
        }
    }
}