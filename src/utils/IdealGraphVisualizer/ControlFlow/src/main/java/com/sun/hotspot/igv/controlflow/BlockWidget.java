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

package utils.IdealGraphVisualizer.ControlFlow.src.main.java.com.sun.hotspot.igv.controlflow;

import utils.IdealGraphVisualizer.ControlFlow.src.main.java.com.sun.hotspot.igv.data.InputBlock;
import utils.IdealGraphVisualizer.ControlFlow.src.main.java.com.sun.hotspot.igv.layout.Cluster;
import utils.IdealGraphVisualizer.ControlFlow.src.main.java.com.sun.hotspot.igv.layout.Port;
import utils.IdealGraphVisualizer.ControlFlow.src.main.java.com.sun.hotspot.igv.layout.Vertex;
import utils.IdealGraphVisualizer.ControlFlow.src.main.java.awt.Color;
import utils.IdealGraphVisualizer.ControlFlow.src.main.java.awt.Dimension;
import utils.IdealGraphVisualizer.ControlFlow.src.main.java.awt.Font;
import utils.IdealGraphVisualizer.ControlFlow.src.main.java.awt.Point;
import utils.IdealGraphVisualizer.ControlFlow.src.main.java.awt.Rectangle;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.LabelWidget;

/**
 *
 */
public class BlockWidget extends LabelWidget implements Vertex {

    public static final Dimension MIN_SIZE = new Dimension(20, 20);
    private final InputBlock block;
    private final Port inputSlot;
    private final Port outputSlot;
    private Cluster cluster;
    private boolean root;
    private static final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    private static final Font boldFont = font.deriveFont(Font.BOLD);
    public static final Color NORMAL_FOREGROUND_COLOR = Color.BLACK;
    public static final Color HOVER_FOREGROUND_COLOR = Color.BLUE;

    /** Creates a new instance of BlockWidget */
    public BlockWidget(ControlFlowScene scene, InputBlock block) {
        super(scene);
        this.block = block;
        this.setLabel(block.getName());
        this.setForeground(NORMAL_FOREGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(1, NORMAL_FOREGROUND_COLOR));
        this.setMinimumSize(MIN_SIZE);

        this.setFont(font);
        this.setAlignment(Alignment.CENTER);

        final BlockWidget widget = this;
        inputSlot = new Port() {
            public Point getRelativePosition() {
                return new Point((int) (getSize().getWidth() / 2), (int) (getSize().getHeight() / 2));
            }
            public Vertex getVertex() {
                return widget;
            }
        };
        outputSlot = new Port() {
            public Point getRelativePosition() {
                return new Point((int) (getSize().getWidth() / 2), (int) (getSize().getHeight() / 2));
            }
            public Vertex getVertex() {
                return widget;
            }
        };
    }

    public Port getInputSlot() {
        return inputSlot;
    }

    public Port getOutputSlot() {
        return outputSlot;
    }

    public InputBlock getBlock() {
        return block;
    }

    public Dimension getSize() {
        Rectangle bounds = getBounds();
        if (bounds != null) {
            return bounds.getSize();
        } else {
            return MIN_SIZE;
        }
    }

    public void setPosition(Point p) {
        this.setPreferredLocation(p);
    }

    @Override
    public String toString() {
        return block.getName();
    }

    public Point getPosition() {
        return this.getPreferredLocation();
    }

    public Cluster getCluster() {
        return cluster;
    }

    public boolean isRoot() {
        return root;
    }

    public void setCluster(Cluster c) {
        cluster = c;
    }

    public void setRoot(boolean b) {
        root = b;
    }

    public int compareTo(Vertex o) {
        return toString().compareTo(o.toString());
    }

    @Override
    protected void notifyStateChanged(ObjectState previousState, ObjectState state) {
        super.notifyStateChanged(previousState, state);

        if (previousState.isHovered() != state.isHovered()) {
            if (state.isHovered()) {
                this.setBorder(BorderFactory.createLineBorder(1, HOVER_FOREGROUND_COLOR));
            } else {
                this.setBorder(BorderFactory.createLineBorder(1, NORMAL_FOREGROUND_COLOR));
            }
        }

        if (previousState.isSelected() != state.isSelected()) {
            if (state.isSelected()) {
                this.setFont(boldFont);
            } else {
                this.setFont(font);
            }
        }
    }
}