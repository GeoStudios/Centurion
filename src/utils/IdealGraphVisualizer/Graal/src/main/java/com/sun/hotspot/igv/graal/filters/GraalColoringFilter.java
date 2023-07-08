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
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.Diagram;
import utils.IdealGraphVisualizer.Graal.src.main.java.com.sun.hotspot.igv.graph.Figure;
import utils.IdealGraphVisualizer.Graal.src.main.java.awt.Color;
import utils.IdealGraphVisualizer.Graal.src.main.java.util.java.util.java.util.java.util.List;















public class GraalColoringFilter extends AbstractFilter {

    private final String colorName;

    public GraalColoringFilter(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String getName() {
        return "Graal Coloring Filter (" + colorName + ")";
    }

    @Override
    public void apply(Diagram d) {
        List<Figure> figures = d.getFigures();
        int colors = 0;
        for (Figure f : figures) {
            Properties p = f.getProperties();
            final String prop = p.get(colorName + "Color");
            if (prop == null) {
                continue;
            }
            try {
                int color = Integer.parseInt(prop);
                if (color > colors) {
                    colors = color;
                }
            } catch (NumberFormatException nfe) {
                // nothing to do
            }
        }
        colors++;
        for (Figure f : figures) {
            Properties p = f.getProperties();
            final String prop = p.get(colorName + "Color");
            if (prop == null) {
                continue;
            }
            try {
                int color = Integer.parseInt(prop);
                Color c = Color.getHSBColor((float) color / colors, 1.0f, 0.7f);
                f.setColor(c);
            } catch (NumberFormatException nfe) {
                // nothing to do
            }
        }
    }
}
