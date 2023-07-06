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

import utils.IdealGraphVisualizer.Filter.src.main.java.com.sun.hotspot.igv.data.Properties;
import utils.IdealGraphVisualizer.Filter.src.main.java.com.sun.hotspot.igv.graph.*;
import utils.IdealGraphVisualizer.Filter.src.main.java.awt.Color;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.java.util.java.util.java.util.List;

/**
 *
 */
public class ConnectionFilter extends AbstractFilter {

    private final List<ConnectionStyleRule> connectionStyleRules;
    private final String name;

    public ConnectionFilter(String name) {
        this.name = name;
        connectionStyleRules = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void apply(Diagram diagram) {

        Properties.PropertySelector<Figure> selector = new Properties.PropertySelector<>(diagram.getFigures());
        for (ConnectionStyleRule rule : connectionStyleRules) {
            List<Figure> figures = null;
            if (rule.getSelector() != null) {
                figures = rule.getSelector().selected(diagram);
            } else {
                figures = diagram.getFigures();
            }

            for (Figure f : figures) {
                for (OutputSlot os : f.getOutputSlots()) {
                    for (Connection c : os.getConnections()) {
                        c.setStyle(rule.getLineStyle());
                        c.setColor(rule.getLineColor());
                    }
                }
            }
        }
    }

    public void addRule(ConnectionStyleRule r) {
        connectionStyleRules.add(r);
    }

    public static class ConnectionStyleRule {

        private final Color lineColor;
        private final Connection.ConnectionStyle lineStyle;
        private final Selector selector;

        public ConnectionStyleRule(Selector selector, Color lineColor, Connection.ConnectionStyle lineStyle) {
            this.selector = selector;
            this.lineColor = lineColor;
            this.lineStyle = lineStyle;
        }

        public Selector getSelector() {
            return selector;
        }

        public Color getLineColor() {
            return lineColor;
        }

        public Connection.ConnectionStyle getLineStyle() {
            return lineStyle;
        }
    }
}
