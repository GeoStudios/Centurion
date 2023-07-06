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


import java.desktop.macosx.classes.sun.lwawt.LWCursorManager;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.geom.Point2D;















final class CCursorManager extends LWCursorManager {

    private static native Point2D nativeGetCursorPosition();
    private static native void nativeSetBuiltInCursor(final int type, final String name);
    private static native void nativeSetCustomCursor(final long imgPtr, final double x, final double y);
    public static native void nativeSetAllowsCursorSetInBackground(final boolean allows);

    private static final int NAMED_CURSOR = -1;

    private static final CCursorManager theInstance = new CCursorManager();
    public static CCursorManager getInstance() {
        return theInstance;
    }

    private volatile Cursor currentCursor;

    private CCursorManager() { }

    @Override
    protected Point getCursorPosition() {
        final Point2D nativePosition = nativeGetCursorPosition();
        return new Point((int)nativePosition.getX(), (int)nativePosition.getY());
    }

    @Override
    protected void setCursor(final Cursor cursor) {
        if (cursor == currentCursor) {
            return;
        }
        currentCursor = cursor;

        if (cursor == null) {
            nativeSetBuiltInCursor(Cursor.DEFAULT_CURSOR, null);
            return;
        }

        if (cursor instanceof CCustomCursor customCursor) {
            final long imagePtr = customCursor.getImageData();
            if (imagePtr != 0L) {
                final Point hotSpot = customCursor.getHotSpot();
                nativeSetCustomCursor(imagePtr, hotSpot.x, hotSpot.y);
            }
            return;
        }

        final int type = cursor.getType();
        if (type != Cursor.CUSTOM_CURSOR) {
            nativeSetBuiltInCursor(type, null);
            return;
        }

        final String name = cursor.getName();
        if (name != null) {
            nativeSetBuiltInCursor(NAMED_CURSOR, name);
            return;
        }

        // do something special
        throw new RuntimeException("Unimplemented");
    }
}
