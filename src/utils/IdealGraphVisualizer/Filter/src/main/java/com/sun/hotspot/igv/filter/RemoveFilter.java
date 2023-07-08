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

package utils.IdealGraphVisualizer.Filter.src.main.java.com.sun.hotspot.igv.filter;

import utils.IdealGraphVisualizer.Filter.src.main.java.com.sun.hotspot.igv.graph.Diagram;
import utils.IdealGraphVisualizer.Filter.src.main.java.com.sun.hotspot.igv.graph.Figure;
import utils.IdealGraphVisualizer.Filter.src.main.java.com.sun.hotspot.igv.graph.Selector;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.HashSet;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.java.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.Set;

/**
 *
 */
public class RemoveFilter extends AbstractFilter {

    private final List<RemoveRule> rules;
    private final String name;

    public RemoveFilter(String name) {
        this.name = name;
        rules = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void apply(Diagram diagram) {
        for (RemoveRule r : rules) {
            List<Figure> selected = r.getSelector().selected(diagram);
            Set<Figure> toRemove = new HashSet<>(selected);

            if (r.getRemoveOrphans()) {
                boolean changed;
                do {
                    changed = false;
                    for (Figure f : diagram.getFigures()) {
                        if (!toRemove.contains(f)) {
                            if (toRemove.containsAll(f.getPredecessors()) && toRemove.containsAll(f.getSuccessors())) {
                                toRemove.add(f);
                                changed = true;
                            }
                        }
                    }
                } while (changed);
            }

            diagram.removeAllFigures(toRemove);
        }
    }

    public void addRule(RemoveRule rule) {
        rules.add(rule);
    }

    public static class RemoveRule {

        private final Selector selector;
        private final boolean removeOrphans;

        public RemoveRule(Selector selector) {
            this(selector, false);
        }

        public RemoveRule(Selector selector, boolean removeOrphans) {
            this.selector = selector;
            this.removeOrphans = removeOrphans;
        }

        public Selector getSelector() {
            return selector;
        }

        public boolean getRemoveOrphans() {
            return removeOrphans;
        }
    }
}
