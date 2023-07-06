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

package utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graal.filters;

import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.data.Properties;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.filter.AbstractFilter;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.Connection;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.Diagram;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.Figure;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.InputSlot;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.OutputSlot;
import utils.IdealGraphVisualizer.Graal.src.main.java.util.HashSet;
import utils.IdealGraphVisualizer.Graal.src.main.java.util.Set;

public class GraalCFGFilter extends AbstractFilter {

    @Override
    public String getName() {
        return "Graal CFG Filter";
    }

    @Override
    public void apply(Diagram d) {
        Set<Connection> connectionsToRemove = new HashSet<>();

        for (Figure f : d.getFigures()) {
            Properties p = f.getProperties();
            int predCount;
            String predCountString = p.get("predecessorCount");
            if (predCountString != null) {
                predCount = Integer.parseInt(predCountString);
            } else if (Boolean.parseBoolean(p.get("hasPredecessor"))) {
                predCount = 1;
            } else {
                predCount = 0;
            }
            for (InputSlot is : f.getInputSlots()) {
                if (is.getPosition() >= predCount && !"EndNode".equals(is.getProperties().get("class"))) {
                    for (Connection c : is.getConnections()) {
                        if (!"EndNode".equals(c.getOutputSlot().getFigure().getProperties().get("class"))) {
                            connectionsToRemove.add(c);
                        }
                    }
                }
            }
        }

        for (Connection c : connectionsToRemove) {
            c.remove();
        }

        Set<Figure> figuresToRemove = new HashSet<>();
        next: for (Figure f : d.getFigures()) {
            for (InputSlot is : f.getInputSlots()) {
                if (!is.getConnections().isEmpty()) {
                    continue next;
                }
            }
            for (OutputSlot os : f.getOutputSlots()) {
                if (!os.getConnections().isEmpty()) {
                    continue next;
                }
            }
            figuresToRemove.add(f);
        }
        d.removeAllFigures(figuresToRemove);
    }
}
