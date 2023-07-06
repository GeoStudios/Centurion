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

package utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.view.widgets;


import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.graph.Figure;
import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.graph.OutputSlot;
import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.view.DiagramScene;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Point;
import utils.IdealGraphVisualizer.View.src.main.java.util.java.util.java.util.java.util.List;
import org.netbeans.api.visual.widget.Widget;















/**
 *
 */
public class OutputSlotWidget extends SlotWidget {

    private final OutputSlot outputSlot;

    public OutputSlotWidget(OutputSlot slot, DiagramScene scene, Widget parent, FigureWidget fw) {
        super(slot, scene, parent, fw);
        outputSlot = slot;
        Point p = outputSlot.getRelativePosition();
        p.y += getSlot().getFigure().getHeight() - Figure.SLOT_START;
        p.x -= this.calculateClientArea().width / 2;
        this.setPreferredLocation(p);
    }

    public OutputSlot getOutputSlot() {
        return outputSlot;
    }

    @Override
    protected int calculateSlotWidth() {
        List<OutputSlot> slots = getSlot().getFigure().getOutputSlots();
        assert slots.contains(getSlot());
        return calculateWidth(slots.size());

    }
}
