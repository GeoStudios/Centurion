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

package java.desktop.share.classes.java.awt.event;

import java.desktop.share.classes.java.util.Eventjava.util.Listener;

/**
 * The listener interface for receiving window state events.
 * <p>
 * The class that is interested in processing a window state event
 * either implements this interface (and all the methods it contains)
 * or extends the abstract {@code WindowAdapter} class
 * (overriding only the methods of interest).
 * <p>
 * The listener object created from that class is then registered with
 * a window using the {@code Window}'s
 * {@code addWindowStateListener} method.  When the window's
 * state changes by virtue of being iconified, maximized etc., the
 * {@code windowStateChanged} method in the listener object is
 * invoked, and the {@code WindowEvent} is passed to it.
 *
 * @see java.awt.event.WindowAdapter
 * @see java.awt.event.WindowEvent
 *
 */
public interface WindowStateListener extends EventListener {
    /**
     * Invoked when window state is changed.
     * @param e the event to be processed
     */
    void windowStateChanged(WindowEvent e);
}
