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

import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.util.Collections;
import utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.util.java.util.java.util.java.util.List;

/**
 *
 */
public class Node<N, E> {

    private N data;
    private final List<Edge<N, E>> inEdges;
    private final List<Edge<N, E>> outEdges;
    private boolean visited;
    private boolean active;
    private boolean reachable;
    private final Graph<N, E> graph;

    protected boolean isVisited() {
        return visited;
    }

    protected void setVisited(boolean b) {
        visited = b;
    }

    protected boolean isReachable() {
        return reachable;
    }

    protected void setReachable(boolean b) {
        reachable = b;
    }

    protected boolean isActive() {
        return active;
    }

    protected void setActive(boolean b) {
        active = b;
    }

    public int getInDegree() {
        return getInDegree(true);
    }

    public int getInDegree(boolean countSelfLoops) {
        if (countSelfLoops) {
            return inEdges.size();
        } else {
            int cnt = 0;
            for (Edge<N, E> e : inEdges) {
                if (e.getSource() != this) {
                    cnt++;
                }
            }
            return cnt;
        }
    }

    public int getOutDegree() {
        return outEdges.size();
    }

    protected Node(Graph<N, E> graph, N data) {
        setData(data);
        this.graph = graph;
        inEdges = new ArrayList<>();
        outEdges = new ArrayList<>();
    }

    protected void addInEdge(Edge<N, E> e) {
        inEdges.add(e);
    }

    public Graph<N, E> getGraph() {
        return graph;
    }

    protected void addOutEdge(Edge<N, E> e) {
        outEdges.add(e);
    }

    protected void removeInEdge(Edge<N, E> e) {
        //assert inEdges.contains(e);
        inEdges.remove(e);
    }

    protected void removeOutEdge(Edge<N, E> e) {
        //assert outEdges.contains(e);
        outEdges.remove(e);
    }

    public List<Edge<N, E>> getInEdges() {
        return Collections.unmodifiableList(inEdges);
    }

    public List<Edge<N, E>> getOutEdges() {
        return Collections.unmodifiableList(outEdges);
    }

    public List<Node<N, E>> getSuccessors() {
        ArrayList<Node<N, E>> succ = new ArrayList<>();
        for (Edge<N, E> e : getOutEdges()) {
            Node<N, E> n = e.getDest();
            if (!succ.contains(n)) {
                succ.add(n);
            }
        }
        return succ;
    }

    public List<Node<N, E>> getPredecessors() {
        ArrayList<Node<N, E>> pred = new ArrayList<>();
        for (Edge<N, E> e : getInEdges()) {
            Node<N, E> n = e.getSource();
            if (!pred.contains(n)) {
                pred.add(n);
            }
        }
        return pred;
    }

    public N getData() {
        return data;
    }

    public void setData(N d) {
        data = d;
    }

    @Override
    public String toString() {
        return "Node: " + data;
    }
}
