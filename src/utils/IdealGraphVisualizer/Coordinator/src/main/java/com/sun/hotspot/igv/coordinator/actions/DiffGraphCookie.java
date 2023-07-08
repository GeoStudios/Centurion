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

package utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.coordinator.actions;

import utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.data.InputGraph;
import utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.data.services.GraphViewer;
import utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.data.services.InputGraphProvider;
import utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.difference.Difference;
import utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.util.LookupHistory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 */
public class DiffGraphCookie implements Node.Cookie {

    private final InputGraph graph;

    public DiffGraphCookie(InputGraph graph) {
        this.graph = graph;
    }

    private InputGraph getCurrentGraph() {
        InputGraphProvider graphProvider = LookupHistory.getLast(InputGraphProvider.class);
        if (graphProvider != null) {
            return graphProvider.getGraph();
        }
        return null;
    }

    public boolean isPossible() {
        return getCurrentGraph() != null;
    }

    public void openDiff() {
        InputGraph other = getCurrentGraph();
        final GraphViewer viewer = Lookup.getDefault().lookup(GraphViewer.class);
        if (viewer != null) {
            InputGraph diffGraph = Difference.createDiffGraph(other, graph);
            viewer.view(diffGraph, true);
        }
    }
}
