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

package utils.IdealGraphVisualizer.Data.src.main.java.com.sun.hotspot.igv.data;

import utils.IdealGraphVisualizer.Data.src.main.java.util.*;

/**
 *
 */
public class Source {

    private final List<InputNode> sourceNodes;
    private final Set<Integer> set;

    public Source() {
        sourceNodes = new ArrayList<>(1);
        set = new LinkedHashSet<>(1);
    }

    public List<InputNode> getSourceNodes() {
        return Collections.unmodifiableList(sourceNodes);
    }

    public Set<Integer> getSourceNodesAsSet() {
        return Collections.unmodifiableSet(set);
    }

    public void addSourceNode(InputNode n) {
        if (!set.contains(n.getId())) {
            sourceNodes.add(n);
            set.add(n.getId());
        }
    }

    public interface Provider {

        Source getSource();
    }

    public void addSourceNodes(Source s) {
        for (InputNode n : s.getSourceNodes()) {
            addSourceNode(n);
        }
    }
}