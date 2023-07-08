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


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.desktop.macosx.classes.sun.awt.CGraphicsDevice;
import java.desktop.macosx.classes.sun.awt.CGraphicsEnvironment;
import java.desktop.macosx.classes.sun.awt.LightweightFrame;
import java.desktop.macosx.classes.sun.java2d.SurfaceData;
import java.desktop.macosx.classes.sun.lwawt.LWLightweightFramePeer;
import java.desktop.macosx.classes.sun.lwawt.LWWindowPeer;
import java.desktop.macosx.classes.sun.lwawt.PlatformWindow;















public class CPlatformLWWindow extends CPlatformWindow {

    @Override
    public void initialize(Window target, LWWindowPeer peer, PlatformWindow owner) {
        initializeBase(target, peer, owner);
    }

    @Override
    CPlatformView createContentView() {
        return new CPlatformLWView();
    }

    @Override
    public void toggleFullScreen() {
    }

    @Override
    public void setMenuBar(MenuBar mb) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        return null;
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public Point getLocationOnScreen() {
        return null;
    }

    @Override
    public SurfaceData getScreenSurface() {
        return null;
    }

    @Override
    public SurfaceData replaceSurfaceData() {
        return null;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        if (getPeer() != null) {
            getPeer().notifyReshape(x, y, w, h);
        }
    }

    @Override
    public void setVisible(boolean visible) {
    }

    @Override
    public void setTitle(String title) {
    }

    @Override
    public void updateIconImages() {
    }

    @Override
    public SurfaceData getSurfaceData() {
        return null;
    }

    @Override
    public void toBack() {
    }

    @Override
    public void toFront() {
    }

    @Override
    public void setResizable(final boolean resizable) {
    }

    @Override
    public void setSizeConstraints(int minW, int minH, int maxW, int maxH) {
    }

    @Override
    public boolean rejectFocusRequest(FocusEvent.Cause cause) {
        return false;
    }

    @Override
    public boolean requestWindowFocus() {
        return true;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void updateFocusableWindowState() {
    }

    @Override
    public void setAlwaysOnTop(boolean isAlwaysOnTop) {
    }

    @Override
    public void setOpacity(float opacity) {
    }

    @Override
    public void setOpaque(boolean isOpaque) {
    }

    @Override
    public void enterFullScreenMode() {
    }

    @Override
    public void exitFullScreenMode() {
    }

    @Override
    public void setWindowState(int windowState) {
    }

    @Override
    public LWWindowPeer getPeer() {
        return super.getPeer();
    }

    @Override
    public CPlatformView getContentView() {
        return super.getContentView();
    }

    @Override
    public long getLayerPtr() {
        return 0;
    }

    @Override
    public GraphicsDevice getGraphicsDevice() {
        CGraphicsEnvironment ge = (CGraphicsEnvironment)GraphicsEnvironment.
                                  getLocalGraphicsEnvironment();

        LWLightweightFramePeer peer = (LWLightweightFramePeer)getPeer();
        int scale =(int) Math.round(((LightweightFrame)peer.getTarget())
                                                            .getScaleFactorX());

        Rectangle bounds = ((LightweightFrame)peer.getTarget()).getHostBounds();
        for (GraphicsDevice d : ge.getScreenDevices()) {
            if (d.getDefaultConfiguration().getBounds().intersects(bounds) &&
                ((CGraphicsDevice)d).getScaleFactor() == scale)
            {
                return d;
            }
        }
        // We shouldn't be here...
        return ge.getDefaultScreenDevice();
    }
}
