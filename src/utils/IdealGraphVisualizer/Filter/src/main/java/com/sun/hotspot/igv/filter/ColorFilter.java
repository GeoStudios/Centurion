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
package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.data.Properties;
import com.sun.hotspot.igv.graph.Connection.ConnectionStyle;
import com.sun.hotspot.igv.graph.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ColorFilter extends AbstractFilter {

    private final List<ColorRule> colorRules;
    private final String name;

    public ColorFilter(String name) {
        this.name = name;
        colorRules = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void apply(Diagram diagram) {

        Properties.PropertySelector<Figure> selector = new Properties.PropertySelector<>(diagram.getFigures());
        for (ColorRule rule : colorRules) {
            if (rule.getSelector() != null) {
                List<Figure> figures = rule.getSelector().selected(diagram);
                for (Figure f : figures) {
                    applyRule(rule, f);
                    if (rule.getColor() != null) {
                        f.setColor(rule.getColor());
                    }
                }
            } else {
                for (Figure f : diagram.getFigures()) {
                    applyRule(rule, f);
                }
            }
        }
    }

    private void applyRule(ColorRule rule, Figure f) {
        if (rule.getColor() != null) {
            f.setColor(rule.getColor());
        }
        Color color = rule.getLineColor();
        ConnectionStyle style = rule.getLineStyle();

        for (OutputSlot s : f.getOutputSlots()) {
            for (Connection c : s.getConnections()) {
                if (color != null) {
                    c.setColor(color);
                }

                if (style != null) {
                    c.setStyle(style);
                }
            }
        }
    }

    public void addRule(ColorRule r) {
        colorRules.add(r);
    }

    public static class ColorRule {

        private final Color color;
        private final Color lineColor;
        private final Connection.ConnectionStyle lineStyle;
        private final Selector selector;

        public ColorRule(Selector selector, Color c) {
            this(selector, c, null, null);
        }

        public ColorRule(Selector selector, Color c, Color lineColor, Connection.ConnectionStyle lineStyle) {
            this.selector = selector;
            this.color = c;
            this.lineColor = lineColor;
            this.lineStyle = lineStyle;

        }

        public ColorRule(Color c) {
            this(null, c);
        }

        public Color getColor() {
            return color;
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
