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

import utils.IdealGraphVisualizer.Graph.src.main.java.com.sun.hotspot.igv.data.Source;
import utils.IdealGraphVisualizer.Graph.src.main.java.com.sun.hotspot.igv.layout.Link;
import utils.IdealGraphVisualizer.Graph.src.main.java.com.sun.hotspot.igv.layout.Port;
import utils.IdealGraphVisualizer.Graph.src.main.java.awt.Color;
import utils.IdealGraphVisualizer.Graph.src.main.java.awt.Point;
import utils.IdealGraphVisualizer.Graph.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Graph.src.main.java.util.java.util.java.util.java.util.List;

/**
 *
 */
public class Connection implements Source.Provider, Link {

    @Override
    public boolean isVIP() {
        return style == ConnectionStyle.BOLD;
    }

    public enum ConnectionStyle {

        NORMAL,
        DASHED,
        BOLD,
        INVISIBLE
    }
    private final InputSlot inputSlot;
    private final OutputSlot outputSlot;
    private final Source source;
    private Color color;
    private ConnectionStyle style;
    private List<Point> controlPoints;
    private final String label;
    private final String type;

    protected Connection(InputSlot inputSlot, OutputSlot outputSlot, String label, String type) {
        this.inputSlot = inputSlot;
        this.outputSlot = outputSlot;
        this.label = label;
        this.type = type;
        this.inputSlot.connections.add(this);
        this.outputSlot.connections.add(this);
        controlPoints = new ArrayList<>();
        Figure sourceFigure = this.outputSlot.getFigure();
        Figure destFigure = this.inputSlot.getFigure();
        sourceFigure.addSuccessor(destFigure);
        destFigure.addPredecessor(sourceFigure);
        source = new Source();

        this.color = Color.BLACK;
        this.style = ConnectionStyle.NORMAL;
    }

    public InputSlot getInputSlot() {
        return inputSlot;
    }

    public OutputSlot getOutputSlot() {
        return outputSlot;
    }

    public Color getColor() {
        return color;
    }

    public ConnectionStyle getStyle() {
        return style;
    }

    public void setColor(Color c) {
        color = c;
    }

    public void setStyle(ConnectionStyle s) {
        style = s;
    }

    @Override
    public Source getSource() {
        return source;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public void remove() {
        inputSlot.getFigure().removePredecessor(outputSlot.getFigure());
        inputSlot.connections.remove(this);
        outputSlot.getFigure().removeSuccessor(inputSlot.getFigure());
        outputSlot.connections.remove(this);
    }

    public String getToolTipText() {
        StringBuilder builder = new StringBuilder();
        if (label != null) {
            builder.append(label).append(": ");
        }
        if (type != null) {
            builder.append(type).append(" ");
        }
        // Resolve strings lazily every time the tooltip is shown, instead of
        // eagerly as for node labels, for efficiency.
        String shortNodeText = getInputSlot().getFigure().getDiagram().getShortNodeText();
        builder.append(getOutputSlot().getFigure().getProperties().resolveString(shortNodeText));
        builder.append(" → ");
        builder.append(getInputSlot().getFigure().getProperties().resolveString(shortNodeText));
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Connection('" + label + "', " + getFrom().getVertex() + " to " + getTo().getVertex() + ")";
    }

    @Override
    public Port getFrom() {
        return outputSlot;
    }

    @Override
    public Port getTo() {
        return inputSlot;
    }

    @Override
    public List<Point> getControlPoints() {
        return controlPoints;
    }

    @Override
    public void setControlPoints(List<Point> list) {
        controlPoints = list;
    }
}

