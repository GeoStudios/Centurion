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

package utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.com.sun.hotspot.igv.hierarchicallayout;

import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.com.sun.hotspot.igv.layout.Link;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.com.sun.hotspot.igv.layout.Port;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.awt.Point;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.util.java.util.java.util.java.util.List;

/**
 *
 */
public class InterClusterConnection implements Link {

    private final Port inputSlot;
    private final Port outputSlot;
    private List<Point> intermediatePoints;
    private final ClusterInputSlotNode inputSlotNode;
    private final ClusterOutputSlotNode outputSlotNode;

    public InterClusterConnection(ClusterOutputSlotNode outputSlotNode, ClusterInputSlotNode inputSlotNode) {
        this.outputSlotNode = outputSlotNode;
        this.inputSlotNode = inputSlotNode;
        this.inputSlot = inputSlotNode.getInputSlot();
        this.outputSlot = outputSlotNode.getOutputSlot();
        intermediatePoints = new ArrayList<Point>();
    }

    public ClusterOutputSlotNode getOutputSlotNode() {
        return outputSlotNode;
    }

    public Port getTo() {
        return inputSlot;
    }

    public Port getFrom() {
        return outputSlot;
    }

    public void setControlPoints(List<Point> p) {
        this.intermediatePoints = p;
    }

    public List<Point> getControlPoints() {
        return intermediatePoints;
    }

    @Override
    public String toString() {
        return "InterClusterConnection[from=" + getFrom() + ", to=" + getTo() + "]";
    }

    public boolean isVIP() {
        return false;
    }
}
