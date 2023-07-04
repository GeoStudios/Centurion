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

package sun.lwawt;

import java.awt.*;
import java.awt.event.FocusEvent;

import sun.java2d.SurfaceData;

// TODO Is it worth to generify this interface, like that:
//
// public interface PlatformWindow<WindowType extends Window>
//
// ?

public interface PlatformWindow {

    /*
     * Delegate initialization (create native window and all the
     * related resources).
     */
    void initialize(Window target, LWWindowPeer peer, PlatformWindow owner);

    /*
     * Delegate shutdown (dispose native window and all the
     * related resources).
     */
    void dispose();

    /*
     * Shows or hides the window.
     */
    void setVisible(boolean visible);

    /*
     * Sets the window title
     */
    void setTitle(String title);

    /*
     * Sets the window bounds. Called when user changes window bounds
     * with setSize/setLocation/setBounds/reshape methods.
     */
    void setBounds(int x, int y, int w, int h);

    /*
     * Sets the maximized bounds.
     */
    default void setMaximizedBounds(int x, int y, int w, int h){}

    /*
     * Returns the graphics device where the window is.
     */
    GraphicsDevice getGraphicsDevice();

    /*
     * Returns the location of the window.
     */
    Point getLocationOnScreen();

    /*
     * Returns the window insets.
     */
    Insets getInsets();

    /*
     * Returns the metrics for a given font.
     */
    FontMetrics getFontMetrics(Font f);

    /*
     * Get the SurfaceData for the window.
     */
    SurfaceData getScreenSurface();

    /*
     * Revalidates the window's current SurfaceData and returns
     * the newly created one.
     */
    SurfaceData replaceSurfaceData();

    void setModalBlocked(boolean blocked);

    void toFront();

    void toBack();

    void setMenuBar(MenuBar mb);

    void setAlwaysOnTop(boolean value);

    void updateFocusableWindowState();

    boolean rejectFocusRequest(FocusEvent.Cause cause);

    boolean requestWindowFocus();

    /*
     * Returns true only when called on a frame/dialog when it's natively focused.
     */
    boolean isActive();

    void setResizable(boolean resizable);

    /**
     * Applies the minimum and maximum size to the platform window.
     */
    void setSizeConstraints(int minW, int minH, int maxW, int maxH);

    /*
     * Installs the images for particular window.
     */
    void updateIconImages();

    void setOpacity(float opacity);

    void setOpaque(boolean isOpaque);

    void enterFullScreenMode();

    void exitFullScreenMode();

    boolean isFullScreenMode();

    void setWindowState(int windowState);

    long getLayerPtr();

    LWWindowPeer getPeer();

    boolean isUnderMouse();
}
