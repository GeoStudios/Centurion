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

package utils.IdealGraphVisualizer.ControlFlow.src.main.java.com.sun.hotspot.igv.controlflow;

import utils.IdealGraphVisualizer.ControlFlow.src.main.java.awt.Point;
import utils.IdealGraphVisualizer.ControlFlow.src.main.java.awt.event.MouseEvent;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Widget;

/**
 * Selection action that acts on double-click only. Does not support aiming.
 *
 */
public class DoubleClickSelectAction extends WidgetAction.LockedAdapter {

    private final SelectProvider provider;

    public DoubleClickSelectAction(SelectProvider provider) {
        this.provider = provider;
    }

    protected boolean isLocked() {
        return false;
    }

    @Override
    public State mousePressed(Widget widget, WidgetMouseEvent event) {
        if (event.getClickCount() >= 2 && (event.getButton() == MouseEvent.BUTTON1 || event.getButton() == MouseEvent.BUTTON2)) {
            boolean invert = (event.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) != 0;
            Point point = event.getPoint();
            if (provider.isSelectionAllowed(widget, point, invert)) {
                provider.select(widget, point, invert);
                return State.CHAIN_ONLY;
            }
        }
        return State.REJECTED;
    }
}