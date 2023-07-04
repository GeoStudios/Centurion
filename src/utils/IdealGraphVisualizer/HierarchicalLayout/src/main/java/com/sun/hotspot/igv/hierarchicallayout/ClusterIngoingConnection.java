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
package com.sun.hotspot.igv.hierarchicallayout;

import com.sun.hotspot.igv.layout.Link;
import com.sun.hotspot.igv.layout.Port;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ClusterIngoingConnection implements Link {

    private List<Point> controlPoints;
    private final ClusterInputSlotNode inputSlotNode;
    private final Link connection;
    private final Port inputSlot;
    private final Port outputSlot;

    public ClusterIngoingConnection(ClusterInputSlotNode inputSlotNode, Link c) {
        this.inputSlotNode = inputSlotNode;
        this.connection = c;
        this.controlPoints = new ArrayList<Point>();

        inputSlot = c.getTo();
        outputSlot = inputSlotNode.getOutputSlot();
    }

    public Link getConnection() {
        return connection;
    }

    public ClusterInputSlotNode getInputSlotNode() {
        return inputSlotNode;
    }

    public Port getTo() {
        return inputSlot;
    }

    public Port getFrom() {
        return outputSlot;
    }

    public void setControlPoints(List<Point> p) {
        this.controlPoints = p;
    }

    public List<Point> getControlPoints() {
        return controlPoints;
    }

    public boolean isVIP() {
        return false;
    }
}
