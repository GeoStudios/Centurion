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

package java.desktop.share.classes.sun.awt.dnd;

import java.awt.Component;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial") // JDK-implementation class
public final class SunDropTargetEvent extends MouseEvent {

    public static final int MOUSE_DROPPED = MouseEvent.MOUSE_RELEASED;

    private final SunDropTargetContextPeer.EventDispatcher dispatcher;

    public SunDropTargetEvent(Component source, int id, int x, int y,
                              SunDropTargetContextPeer.EventDispatcher d) {
        super(source, id, System.currentTimeMillis(), 0, x, y, 0, 0, 0,
              false,  MouseEvent.NOBUTTON);
        dispatcher = d;
        dispatcher.registerEvent(this);
    }

    public void dispatch() {
        try {
            dispatcher.dispatchEvent(this);
        } finally {
            dispose();
        }
    }

    public void consume() {
        boolean was_consumed = isConsumed();
        super.consume();
        if (!was_consumed && isConsumed()) {
            dispose();
        }
    }

    public void dispose() {
        dispatcher.unregisterEvent(this);
    }

    public SunDropTargetContextPeer.EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public String paramString() {
        String typeStr = null;

        if (id == MOUSE_DROPPED) {
            typeStr = "MOUSE_DROPPED";
        } else {
            return super.paramString();
        }
        return typeStr + ",(" + getX() + "," + getY() + ")";
    }
}
