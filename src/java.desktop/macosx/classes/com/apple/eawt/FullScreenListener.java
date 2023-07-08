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

package java.desktop.macosx.classes.com.apple.eawt;


import java.desktop.macosx.classes.com.apple.eawt.event.FullScreenEvent;
import java.util.Eventjava.util.Listener;















/**
 *
 *
 */
public interface FullScreenListener extends EventListener {
        /**
     * Invoked when a window has started to enter full screen.
     * @param e containing the specific window entering full screen.
     */
        void windowEnteringFullScreen(final FullScreenEvent e);

        /**
     * Invoked when a window has fully entered full screen.
     * @param e containing the specific window which has entered full screen.
     */
        void windowEnteredFullScreen(final FullScreenEvent e);

        /**
     * Invoked when a window has started to exit full screen.
     * @param e containing the specific window exiting full screen.
     */
        void windowExitingFullScreen(final FullScreenEvent e);

        /**
     * Invoked when a window has fully exited full screen.
     * @param e containing the specific window which has exited full screen.
     */
        void windowExitedFullScreen(final FullScreenEvent e);
}
