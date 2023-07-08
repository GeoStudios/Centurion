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
public class InputBlock {

    private final List<InputNode> nodes;
    private final String name;
    private final InputGraph graph;
    private final Set<InputBlock> successors;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (o == null || (!(o instanceof InputBlock b))) {
            return false;
        }

        final boolean result = b.nodes.equals(nodes) && b.name.equals(name) && b.successors.size() == successors.size();
        if (!result) {
            return false;
        }

        final HashSet<String> s = new HashSet<>();
        for (InputBlock succ : successors) {
            s.add(succ.name);
        }

        for (InputBlock succ : b.successors) {
            if (!s.contains(succ.name)) {
                return false;
            }
        }

        return true;
    }

    InputBlock(InputGraph graph, String name) {
        this.graph = graph;
        this.name = name;
        nodes = new ArrayList<>();
        successors = new LinkedHashSet<>(2);
    }

    public String getName() {
        return name;
    }

    public List<InputNode> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public void addNode(int id) {
        InputNode node = graph.getNode(id);
        assert node != null;
        // nodes.contains(node) is too expensive for large graphs so
        // just make sure the Graph doesn't know it yet.
        assert graph.getBlock(id) == null : "duplicate : " + node;
        graph.setBlock(node, this);
        nodes.add(node);
    }

    public Set<InputBlock> getSuccessors() {
        return Collections.unmodifiableSet(successors);
    }

    @Override
    public String toString() {
        return "Block " + this.getName();
    }

    void addSuccessor(InputBlock b) {
        successors.add(b);
    }
}
