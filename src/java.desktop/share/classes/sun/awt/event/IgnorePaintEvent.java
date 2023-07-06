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

package java.desktop.share.classes.sun.awt.event;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.PaintEvent;

/**
 * PaintEvents that are effectively ignored.  This class is used only for
 * tagging.  If a heavy weight peer is asked to handle an event of this
 * class it'll ignore it.  This class is used by Swing.
 * Look at {@code javax.swing.SwingPaintEventDispatcher} for more.
 *
 */
@SuppressWarnings("serial") // JDK-implementation class
public class IgnorePaintEvent extends PaintEvent {
    public IgnorePaintEvent(Component source, int id, Rectangle updateRect) {
        super(source, id, updateRect);
    }
}