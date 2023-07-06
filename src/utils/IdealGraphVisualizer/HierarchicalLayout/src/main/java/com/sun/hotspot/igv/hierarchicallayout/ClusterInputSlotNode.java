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


import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.com.sun.hotspot.igv.layout.Cluster;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.com.sun.hotspot.igv.layout.Port;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.com.sun.hotspot.igv.layout.Vertex;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.awt.Dimension;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.awt.Point;















/**
 *
 */
public class ClusterInputSlotNode implements Vertex {

    private final int SIZE = 0;
    private Point position;
    private final Port inputSlot;
    private final Port outputSlot;
    private final ClusterNode blockNode;
    private InterClusterConnection interBlockConnection;
    private Cluster cluster;
    private ClusterIngoingConnection conn;

    public void setIngoingConnection(ClusterIngoingConnection c) {
        conn = c;
    }

    public ClusterIngoingConnection getIngoingConnection() {
        return conn;
    }
    private final String id;

    @Override
    public String toString() {
        return id;
    }

    public ClusterInputSlotNode(ClusterNode n, String id) {
        this.blockNode = n;
        this.id = id;

        n.addSubNode(this);

        final Vertex thisNode = this;
        final ClusterNode thisBlockNode = blockNode;

        outputSlot = new Port() {

            public Point getRelativePosition() {
                return new Point(0, 0);
            }

            public Vertex getVertex() {
                return thisNode;
            }

            @Override
            public String toString() {
                return "OutPort of " + thisNode;
            }
        };

        inputSlot = new Port() {

            public Point getRelativePosition() {
                Point p = new Point(thisNode.getPosition());
                p.x += ClusterNode.BORDER;
                p.y = 0;
                return p;
            }

            public Vertex getVertex() {
                return thisBlockNode;
            }

            @Override
            public String toString() {
                return "InPort of " + thisNode;
            }
        };
    }

    public Port getInputSlot() {
        return inputSlot;
    }

    public InterClusterConnection getInterBlockConnection() {
        return interBlockConnection;
    }

    public Port getOutputSlot() {
        return outputSlot;
    }

    public Dimension getSize() {
        return new Dimension(SIZE, SIZE);
    }

    public void setPosition(Point p) {
        this.position = p;
    }

    public Point getPosition() {
        return position;
    }

    public void setInterBlockConnection(InterClusterConnection interBlockConnection) {
        this.interBlockConnection = interBlockConnection;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public boolean isRoot() {
        return true;
    }

    public int compareTo(Vertex o) {
        return toString().compareTo(o.toString());
    }
}
