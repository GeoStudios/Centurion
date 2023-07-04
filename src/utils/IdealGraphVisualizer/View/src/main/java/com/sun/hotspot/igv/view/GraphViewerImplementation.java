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
package com.sun.hotspot.igv.view;

import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.services.GraphViewer;
import com.sun.hotspot.igv.graph.Diagram;
import com.sun.hotspot.igv.settings.Settings;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 */
@ServiceProvider(service=GraphViewer.class)
public class GraphViewerImplementation implements GraphViewer {

    @Override
    public void view(InputGraph graph, boolean clone) {

        if (!clone) {
            WindowManager manager = WindowManager.getDefault();
            for (Mode m : manager.getModes()) {
                for (TopComponent t : manager.getOpenedTopComponents(m)) {
                    if (t instanceof EditorTopComponent etc) {
                        if (etc.getModel().getGroup().getGraphs().contains(graph)) {
                            etc.getModel().selectGraph(graph);
                            t.requestActive();
                            return;
                        }
                    }
                }
            }
        }

        Diagram diagram = Diagram.createDiagram(graph,
                                                Settings.get().get(Settings.NODE_TEXT, Settings.NODE_TEXT_DEFAULT),
                                                Settings.get().get(Settings.NODE_SHORT_TEXT, Settings.NODE_SHORT_TEXT_DEFAULT));
        EditorTopComponent tc = new EditorTopComponent(diagram);
        tc.open();
        tc.requestActive();
    }
}
