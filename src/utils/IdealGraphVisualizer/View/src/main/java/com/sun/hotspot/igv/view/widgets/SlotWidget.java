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

package utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.view.widgets;

import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.graph.Figure;
import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.graph.OutputSlot;
import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.graph.Slot;
import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.util.DoubleClickHandler;
import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.view.DiagramScene;
import utils.IdealGraphVisualizer.View.src.main.java.awt.BasicStroke;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Color;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Font;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Graphics2D;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Rectangle;
import utils.IdealGraphVisualizer.View.src.main.java.awt.geom.Rectangle2D;
import utils.IdealGraphVisualizer.View.src.main.java.util.HashSet;
import utils.IdealGraphVisualizer.View.src.main.java.util.Set;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 */
public abstract class SlotWidget extends Widget implements DoubleClickHandler {

    private final Slot slot;
    private final FigureWidget figureWidget;
    private static final double TEXT_ZOOM_FACTOR = 0.9;
    private static final double ZOOM_FACTOR = 0.6;
    private final DiagramScene diagramScene;

    public SlotWidget(Slot slot, DiagramScene scene, Widget parent, FigureWidget fw) {
        super(scene);
        this.diagramScene = scene;
        this.slot = slot;
        figureWidget = fw;
        if (!slot.getSource().getSourceNodes().isEmpty()) {
            this.setToolTipText("<HTML>" + slot.getToolTipText() + "</HTML>");
        }
        this.setCheckClipping(true);
        parent.addChild(this);

        //this.setPreferredBounds(this.calculateClientArea());
    }

    @Override
    protected void notifyStateChanged(ObjectState previousState, ObjectState state) {
        super.notifyStateChanged(previousState, state);
        repaint();
    }

    public Slot getSlot() {
        return slot;
    }

    public FigureWidget getFigureWidget() {
        return figureWidget;
    }

    @Override
    protected void paintWidget() {

        if (getScene().getZoomFactor() < ZOOM_FACTOR) {
            return;
        }

        Graphics2D g = this.getGraphics();
        // g.setColor(Color.DARK_GRAY);
        int w = this.getBounds().width;
        int h = this.getBounds().height;

        if (getSlot().getSource().getSourceNodes().size() > 0) {
            final int SMALLER = 0;
            g.setColor(getSlot().getColor());

            int FONT_OFFSET = 2;

            int s = h - SMALLER;
            int rectW = s;

            Font font = this.getSlot().getFigure().getDiagram().getSlotFont();
            if (this.getState().isSelected()) {
                font = font.deriveFont(Font.BOLD);
                g.setStroke(new BasicStroke(1.5f));
            } else {
                g.setStroke(new BasicStroke(1f));
            }

            if (getSlot().getShortName() != null && getSlot().getShortName().length() > 0) {
                g.setFont(font);
                Rectangle2D r1 = g.getFontMetrics().getStringBounds(getSlot().getShortName(), g);
                rectW = (int) r1.getWidth() + FONT_OFFSET * 2;
            }
            g.fillRect(w / 2 - rectW / 2, 0, rectW - 1, s - 1);

            if (this.getState().isHighlighted()) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawRect(w / 2 - rectW / 2, 0, rectW - 1, s - 1);

            if (getSlot().getShortName() != null && getSlot().getShortName().length() > 0 && getScene().getZoomFactor() >= TEXT_ZOOM_FACTOR) {
                Rectangle2D r1 = g.getFontMetrics().getStringBounds(getSlot().getShortName(), g);
                g.drawString(getSlot().getShortName(), (int) (w - r1.getWidth()) / 2, g.getFontMetrics().getAscent() - 1);//(int) (r1.getHeight()));
            }

        } else {

            if (this.getSlot().getConnections().isEmpty()) {
                if (this.getState().isHighlighted()) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.BLACK);
                }
                int r = 2;
                if (slot instanceof OutputSlot) {
                    g.fillOval(w / 2 - r, Figure.SLOT_WIDTH - Figure.SLOT_START - r, 2 * r, 2 * r);
                } else {
                    g.fillOval(w / 2 - r, Figure.SLOT_START - r, 2 * r, 2 * r);
                }
            } else {
                // Do not paint a slot with connections.
            }
        }
    }

    @Override
    protected Rectangle calculateClientArea() {
        return new Rectangle(0, 0, slot.getWidth(), Figure.SLOT_WIDTH);
    }

    protected abstract int calculateSlotWidth();

    protected int calculateWidth(int count) {
        return getFigureWidget().getFigure().getWidth() / count;
    }

    @Override
    public void handleDoubleClick(Widget w, WidgetAction.WidgetMouseEvent e) {
        Set<Integer> hiddenNodes = new HashSet<>(diagramScene.getModel().getHiddenNodes());
        if (diagramScene.isAllVisible()) {
            hiddenNodes = new HashSet<>(diagramScene.getModel().getGraphToView().getGroup().getAllNodes());
        }

        boolean progress = false;
        for (Figure f : diagramScene.getModel().getDiagramToView().getFigures()) {
            for (Slot s : f.getSlots()) {
                if (DiagramScene.doesIntersect(s.getSource().getSourceNodesAsSet(), slot.getSource().getSourceNodesAsSet())) {
                    progress = true;
                    hiddenNodes.removeAll(f.getSource().getSourceNodesAsSet());
                }
            }
        }

        if (progress) {
            this.diagramScene.getModel().showNot(hiddenNodes);
        }
    }
}