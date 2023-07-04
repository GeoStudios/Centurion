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

package sun.lwawt.macosx;

import sun.lwawt.LWComponentPeer;
import sun.lwawt.PlatformDropTarget;

import java.awt.*;
import java.awt.dnd.DropTarget;


final class CDropTarget implements PlatformDropTarget {
    private long fNativeDropTarget;

    CDropTarget(DropTarget dropTarget, Component component, LWComponentPeer<?, ?> peer) {
        long nativePeer = CPlatformWindow.getNativeViewPtr(peer.getPlatformWindow());
        if (nativePeer == 0L) return; // Unsupported for a window without a native view (plugin)

        // Create native dragging destination:
        fNativeDropTarget = createNativeDropTarget(dropTarget, component, nativePeer);
        if (fNativeDropTarget == 0) {
            throw new IllegalStateException("CDropTarget.createNativeDropTarget() failed.");
        }
    }

    @Override
    public void dispose() {
        if (fNativeDropTarget != 0) {
            releaseNativeDropTarget(fNativeDropTarget);
            fNativeDropTarget = 0;
        }
    }

    protected native long createNativeDropTarget(DropTarget dropTarget,
                                                 Component component,
                                                 long nativePeer);
    protected native void releaseNativeDropTarget(long nativeDropTarget);
}
