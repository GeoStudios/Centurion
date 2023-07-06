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

package utils.IdealGraphVisualizer.Graph.src.main.java.com.sun.hotspot.igv.graph;

import utils.IdealGraphVisualizer.Graph.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Graph.src.main.java.util.java.util.java.util.java.util.List;

/**
 *
 */
public class PredecessorSelector implements Selector {

    private final Selector innerSelector;

    public PredecessorSelector(Selector innerSelector) {
        this.innerSelector = innerSelector;
    }

    @Override
    public List<Figure> selected(Diagram d) {
        List<Figure> inner = innerSelector.selected(d);
        List<Figure> result = new ArrayList<>();
        for (Figure f : d.getFigures()) {
            boolean saved = false;
            for (Figure f2 : f.getSuccessors()) {
                if (inner.contains(f2)) {
                    saved = true;
                    break;
                }
            }

            if (saved) {
                result.add(f);
            }
        }

        return result;
    }
}
