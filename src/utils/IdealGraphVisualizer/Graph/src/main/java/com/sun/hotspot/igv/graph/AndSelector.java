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
public class AndSelector implements Selector {

    private final Selector selector1;
    private final Selector selector2;

    public AndSelector(Selector s1, Selector s2) {
        this.selector1 = s1;
        this.selector2 = s2;
    }

    @Override
    public List<Figure> selected(Diagram d) {
        List<Figure> l1 = selector1.selected(d);
        List<Figure> l2 = selector2.selected(d);
        List<Figure> result = new ArrayList<>();
        for (Figure f : l2) {
            if (l1.contains(f)) {
                result.add(f);
            }
        }
        return result;
    }
}
