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

/* This interface is needed for listening for StateChanged events (we are interested in iconify only )
 * fix for 6261352. We should detect if Window containing a Choice become iconified and hide pop-down menu with grab release.
 */
public interface ToplevelStateListener{
    /* two different methods for that case if ICCCM states
     * (WithdrawnState, IconicState, NormalState) has the same integer values as Java states
     * (Frame.ICONIFIED,  Frame.MAXIMIZED_BOTH, Frame.MAXIMIZED_HORIZ, Frame.MAXIMIZED_VERT)
     * They will be invoked from different peers in order not to mix different states having same codes.
     */
    void stateChangedICCCM(int oldState, int newState);
    void stateChangedJava(int oldState, int newState);
}
