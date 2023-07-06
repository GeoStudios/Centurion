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
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.Connection.ConnectionStyle;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.Diagram;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.Figure;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.InputSlot;
import utils.IdealGraphVisualizer.Graal.src.main.java.awt.Color;
import utils.IdealGraphVisualizer.Graal.src.main.java.util.HashMap;
import utils.IdealGraphVisualizer.Graal.src.main.java.util.java.util.java.util.java.util.List;

/**
 * Filter that colors usage and successor edges differently.
 *
 */
public class GraalEdgeColorFilter extends AbstractFilter {

    private final HashMap<String,Color> usageColor = new HashMap<>();
    private Color otherUsageColor = Color.BLACK;

    public GraalEdgeColorFilter() {
    }

    @Override
    public String getName() {
        return "Graal Edge Color Filter";
    }

    @Override
    public void apply(Diagram d) {
        List<Figure> figures = d.getFigures();
        for (Figure f : figures) {
            for (InputSlot is : f.getInputSlots()) {
                for (Connection c : is.getConnections()) {
                    String type = c.getType();
                    if (type == "Association" && "EndNode".equals(c.getOutputSlot().getFigure().getProperties().get("class"))) {
                        type = "Successor";
                    }

                    if (type != null) {
                        Color typeColor = usageColor.get(type);
                        if (typeColor == null) {
                            c.setColor(otherUsageColor);
                        } else {
                            c.setColor(typeColor);
                        }
                        if (c.getStyle() != ConnectionStyle.DASHED && type == "Successor") {
                            c.setStyle(ConnectionStyle.BOLD);
                        }
                    }
                }
            }
        }
    }

    public Color getUsageColor(String type) {
        return usageColor.get(type);
    }

    public void setUsageColor(String type, Color usageColor) {
        this.usageColor.put(type, usageColor);
    }

    public Color getOtherUsageColor() {
        return otherUsageColor;
    }

    public void setOtherUsageColor(Color otherUsageColor) {
        this.otherUsageColor = otherUsageColor;
    }
}