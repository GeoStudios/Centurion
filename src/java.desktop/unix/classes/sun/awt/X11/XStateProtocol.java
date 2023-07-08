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

package java.desktop.unix.classes.sun.awt.X11;

public interface XStateProtocol {

    /**
     * Returns whether or not the protocol supports the transition to the state
     * represented by {@code state}. {@code State} contains encoded state
     * as a bit mask of state defined in java.awt.Frame
     */
    boolean supportsState(int state);

    /**
     * Moves window into the state.
     */
    void setState(XWindowPeer window, int state);

    /**
     * Returns current state of the window
     */
    int getState(XWindowPeer window);

    /**
     * Detects whether or not this event is indicates state change
     */
    boolean isStateChange(XPropertyEvent e);

    /**
     * Work around for 4775545.
     */
    void unshadeKludge(XWindowPeer window);
}
