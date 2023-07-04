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

package com.apple.eawt.event;

/**
 * Listener interface for receiving swipe events. A single swipe event
 * may be both vertical and horizontal simultaneously, invoking both
 * a vertical and horizontal method.
 *
 * @see SwipeEvent
 * @see GestureUtilities
 *
 */
public interface SwipeListener extends GestureListener {
    /**
     * Invoked when an upwards swipe gesture is performed by the user.
     * @param e representing the occurrence of a swipe.
     */
    void swipedUp(final SwipeEvent e);

    /**
     * Invoked when a downward swipe gesture is performed by the user.
     * @param e representing the occurrence of a swipe.
     */
    void swipedDown(final SwipeEvent e);

    /**
     * Invoked when a leftward swipe gesture is performed by the user.
     * @param e representing the occurrence of a swipe.
     */
    void swipedLeft(final SwipeEvent e);

    /**
     * Invoked when a rightward swipe gesture is performed by the user.
     * @param e representing the occurrence of a swipe.
     */
    void swipedRight(final SwipeEvent e);
}
