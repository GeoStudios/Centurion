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


import utils.IdealGraphVisualizer.Filter.src.main.java.com.sun.hotspot.igv.graph.*;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.java.util.java.util.java.util.List;















/**
 *
 */
public class RemoveInputsFilter extends AbstractFilter {

    private final List<RemoveInputsRule> rules;
    private final String name;

    public RemoveInputsFilter(String name) {
        this.name = name;
        rules = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void apply(Diagram diagram) {

        for (RemoveInputsRule r : rules) {

            List<Figure> list = r.getSelector().selected(diagram);
            for (Figure f : list) {
                int z = 0;
                List<InputSlot> last = new ArrayList<>();
                for (InputSlot is : f.getInputSlots()) {
                    if (z >= r.getStartingIndex() && z <= r.getEndIndex() && is.getConnections().size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        List<Connection> conns = is.getConnections();
                        for (int i = 0; i < conns.size(); i++) {
                            Connection c = conns.get(i);
                            OutputSlot os = c.getOutputSlot();
                            Figure pred = os.getFigure();
                            if (i != 0) {
                                sb.append("<BR>");
                            }
                            sb.append(pred.getLines()[0]);
                        }
                        is.removeAllConnections();
                        is.setShortName("X");
                        is.setText(sb.toString());
                        last.add(is);
                    } else {
                        last.clear();
                    }
                    z++;
                }

                if (last.size() > 3) {
                    InputSlot first = last.get(0);
                    first.setShortName("XX");

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < last.size(); i++) {
                        InputSlot is2 = last.get(i);
                        if (i != 0) {
                            sb.append("<BR>");
                        }
                        sb.append(is2.getText());
                    }

                    first.setText(sb.toString());

                    for (int i = 1; i < last.size(); i++) {
                        f.removeSlot(last.get(i));
                    }
                }
            }
        }
    }

    public void addRule(RemoveInputsRule rule) {
        rules.add(rule);
    }

    public static class RemoveInputsRule {

        private final Selector selector;
        private final int startingIndex;
        private final int endIndex;

        public RemoveInputsRule(Selector selector) {
            this(selector, 0);
        }

        public RemoveInputsRule(Selector selector, int startIndex) {
            this(selector, startIndex, Integer.MAX_VALUE);
        }

        public RemoveInputsRule(Selector selector, int startIndex, int endIndex) {
            this.startingIndex = startIndex;
            this.endIndex = endIndex;
            this.selector = selector;
        }

        public int getStartingIndex() {
            return startingIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public Selector getSelector() {
            return selector;
        }
    }
}
