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

import test.java.awt.regtesthelpers.Util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;

class SourceFrame extends Frame implements DragGestureListener {

    SourceFrame() {
        super("Source File List Frame");
        initGUI();
        new DragSource().createDefaultDragGestureRecognizer(this,
                DnDConstants.ACTION_COPY,this);
    }

    private void initGUI() {
        this.addWindowListener(Util.getClosingWindowAdapter());
        this.setLocation(300,250);
        this.setSize(200,200);
        this.setVisible(true);
    }

    int getNextLocationX() {
        return getX()+getWidth();
    }

    int getNextLocationY() {
        return getY();
    }

    int getDragSourcePointX() {
        return (int)getLocationOnScreen().getX()+(getWidth()/2);
    }

   int getDragSourcePointY() {
        return (int)getLocationOnScreen().getY()+ (getHeight()/2);
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        dge.startDrag(null, new StringSelection("A TEXT"));
    }
}
