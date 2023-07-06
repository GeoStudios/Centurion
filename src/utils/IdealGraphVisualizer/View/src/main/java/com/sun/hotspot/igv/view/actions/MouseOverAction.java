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

package utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.view.actions;


import org.netbeans.api.visual.action.HoverProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.action.WidgetAction.State;
import org.netbeans.api.visual.widget.Widget;















/**
 *
 */
public class MouseOverAction extends WidgetAction.Adapter {

    private long eventID = Integer.MIN_VALUE;
    private final HoverProvider provider;

    public MouseOverAction(HoverProvider provider) {
        this.provider = provider;
    }

    @Override
    public State mouseMoved(Widget widget, WidgetMouseEvent event) {
        long id = event.getEventID();
        if (id != eventID) {
            eventID = id;
            provider.widgetHovered(widget);
        }
        return State.REJECTED;
    }

    @Override
    public State mouseExited(Widget widget, WidgetMouseEvent event) {
        provider.widgetHovered(null);
        return State.REJECTED;
    }
}
