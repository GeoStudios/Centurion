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

import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.data.InputBlock;
import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.graph.Diagram;
import utils.IdealGraphVisualizer.View.src.main.java.awt.BasicStroke;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Color;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Font;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Graphics2D;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Rectangle;
import utils.IdealGraphVisualizer.View.src.main.java.awt.Stroke;
import utils.IdealGraphVisualizer.View.src.main.java.awt.geom.Rectangle2D;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 */
public class BlockWidget extends Widget {

    public static final int BORDER = 20;
    public static final Color BACKGROUND_COLOR = new Color(235, 235, 255);
    private static final Font titleFont = new Font("Serif", Font.PLAIN, 14).deriveFont(Font.BOLD);
    private final InputBlock blockNode;
    private final Diagram diagram;

    public BlockWidget(Scene scene, Diagram d, InputBlock blockNode) {
        super(scene);
        this.blockNode = blockNode;
        this.diagram = d;
        this.setBackground(BACKGROUND_COLOR);
        this.setOpaque(true);
        this.setCheckClipping(true);
    }

    @Override
    protected void paintWidget() {
        super.paintWidget();
        Graphics2D g = this.getGraphics();
        Stroke old = g.getStroke();
        g.setColor(Color.BLUE);
        Rectangle r = new Rectangle(this.getPreferredBounds());
        r.width--;
        r.height--;
        if (this.getBounds().width > 0 && this.getBounds().height > 0) {
            g.setStroke(new BasicStroke(2));
            g.drawRect(r.x, r.y, r.width, r.height);
        }

        Color titleColor = Color.BLACK;
        g.setColor(titleColor);
        g.setFont(titleFont);

        String s = "B" + blockNode.getName();
        Rectangle2D r1 = g.getFontMetrics().getStringBounds(s, g);
        g.drawString(s, r.x + 5, r.y + (int) r1.getHeight());
        g.setStroke(old);
    }
}
