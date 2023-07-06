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

package java.desktop.share.classes.java.awt.dnd.peer;


import java.desktop.share.classes.java.awt.Cursor;
import java.desktop.share.classes.java.awt.Image;
import java.desktop.share.classes.java.awt.Point;
import java.desktop.share.classes.java.awt.dnd.DragSourceContext;
import java.desktop.share.classes.java.awt.dnd.InvalidDnDOperationException;















/**
 * <p>
 * This interface is supplied by the underlying window system platform to
 * expose the behaviors of the Drag and Drop system to an originator of
 * the same
 * </p>
 *
 *
 */

public interface DragSourceContextPeer {

    /**
     * start a drag
     * @param dsc the DragSourceContext
     * @param c the cursor
     * @param dragImage the image to be dragged
     * @param imageOffset the offset
     */

    void startDrag(DragSourceContext dsc, Cursor c, Image dragImage, Point imageOffset) throws InvalidDnDOperationException;

    /**
     * return the current drag cursor
     * @return the current drag cursor
     */

    Cursor getCursor();

    /**
     * set the current drag cursor
     * @param c the cursor
     */

    void setCursor(Cursor c) throws InvalidDnDOperationException;

    /**
     * notify the peer that the Transferables DataFlavors have changed
     */

    void transferablesFlavorsChanged();
}
