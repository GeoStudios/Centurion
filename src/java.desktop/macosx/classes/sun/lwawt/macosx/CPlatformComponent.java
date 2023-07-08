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


import java.awt.Insets;
import java.desktop.macosx.classes.sun.lwawt.PlatformComponent;
import java.desktop.macosx.classes.sun.lwawt.PlatformWindow;















/**
 * On OSX {@code CPlatformComponent} stores pointer to the native CAlayer which
 * can be used from JAWT.
 */
class CPlatformComponent extends CFRetainedResource
        implements PlatformComponent {

    private volatile PlatformWindow platformWindow;

    CPlatformComponent() {
        super(0, true);
    }

    /**
     * Used by JAWT.
     */
    public long getPointer() {
        return ptr;
    }

    @Override
    public void initialize(final PlatformWindow platformWindow) {
        this.platformWindow = platformWindow;
        setPtr(nativeCreateComponent(platformWindow.getLayerPtr()));
    }

    // TODO: visibility, z-order

    @Override
    public void setBounds(final int x, final int y, final int w, final int h) {
        // translates values from the coordinate system of the top-level window
        // to the coordinate system of the content view
        final Insets insets = platformWindow.getPeer().getInsets();
        execute(ptr->nativeSetBounds(ptr, x - insets.left, y - insets.top, w, h));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private native long nativeCreateComponent(long windowLayer);

    private native void nativeSetBounds(long ptr, int x, int y, int w, int h);
}
