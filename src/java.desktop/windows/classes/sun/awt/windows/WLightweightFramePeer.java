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

package java.desktop.windows.classes.sun.awt.windows;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.dnd.DropTarget;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.desktop.windows.classes.sun.awt.LightweightFrame;
import java.desktop.windows.classes.sun.awt.OverrideNativeWindowHandle;
import java.desktop.windows.classes.sun.swing.JLightweightFrame;
import java.desktop.windows.classes.sun.swing.SwingAccessor;

public class WLightweightFramePeer extends WFramePeer implements OverrideNativeWindowHandle {

    public WLightweightFramePeer(LightweightFrame target) {
        super(target);
    }

    private LightweightFrame getLwTarget() {
        return (LightweightFrame)target;
    }

    @Override
    public Graphics getGraphics() {
        return getLwTarget().getGraphics();
    }

    private native void overrideNativeHandle(long hwnd);

    @Override
    public void overrideWindowHandle(final long handle) {
        overrideNativeHandle(handle);
    }

    @Override
    public void show() {
        super.show();
        postEvent(new ComponentEvent((Component)getTarget(), ComponentEvent.COMPONENT_SHOWN));
    }

    @Override
    public void hide() {
        super.hide();
        postEvent(new ComponentEvent((Component)getTarget(), ComponentEvent.COMPONENT_HIDDEN));
    }

    @Override
    public void reshape(int x, int y, int width, int height) {
        super.reshape(x, y, width, height);
        postEvent(new ComponentEvent((Component) getTarget(), ComponentEvent.COMPONENT_MOVED));
        postEvent(new ComponentEvent((Component) getTarget(), ComponentEvent.COMPONENT_RESIZED));
    }

    @Override
    public void handleEvent(java.awt.AWTEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            emulateActivation(true);
        }
        super.handleEvent(e);
    }

    @Override
    public void grab() {
        getLwTarget().grabFocus();
    }

    @Override
    public void ungrab() {
        getLwTarget().ungrabFocus();
    }

    @Override
    public void updateCursorImmediately() {
        SwingAccessor.getJLightweightFrameAccessor().updateCursor((JLightweightFrame)getLwTarget());
    }

    public boolean isLightweightFramePeer() {
        return true;
    }

    @Override
    public void addDropTarget(DropTarget dt) {
        getLwTarget().addDropTarget(dt);
    }

    @Override
    public void removeDropTarget(DropTarget dt) {
        getLwTarget().removeDropTarget(dt);
    }
}