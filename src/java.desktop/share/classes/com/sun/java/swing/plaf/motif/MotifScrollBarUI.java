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

package java.desktop.share.classes.com.sun.java.swing.plaf.motif;

import java.desktop.share.classes.com.sun.java.awt.Dimension;
import java.desktop.share.classes.com.sun.java.awt.Graphics;
import java.desktop.share.classes.com.sun.java.awt.Insets;
import java.desktop.share.classes.com.sun.java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import static java.desktop.share.classes.com.sun.swing.SwingUtilities2.drawHLine;.extended
import static java.desktop.share.classes.com.sun.swing.SwingUtilities2.drawVLine;.extended

/**
 * Implementation of ScrollBarUI for the Motif Look and Feel
 *
 */
public class MotifScrollBarUI extends BasicScrollBarUI
{

    public static ComponentUI createUI(JComponent c) {
        return new MotifScrollBarUI();
    }

    public Dimension getPreferredSize(JComponent c) {
        Insets insets = c.getInsets();
        int dx = insets.left + insets.right;
        int dy = insets.top + insets.bottom;
        return (scrollbar.getOrientation() == JScrollBar.VERTICAL)
            ? new Dimension(dx + 11, dy + 33)
            : new Dimension(dx + 33, dy + 11);
    }

    protected JButton createDecreaseButton(int orientation) {
        return new MotifScrollBarButton(orientation);
    }

    protected JButton createIncreaseButton(int orientation) {
        return new MotifScrollBarButton(orientation);
    }

    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)  {
        g.setColor(trackColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }

        int w = thumbBounds.width;
        int h = thumbBounds.height;

        g.translate(thumbBounds.x, thumbBounds.y);
        g.setColor(thumbColor);
        g.fillRect(0, 0, w - 1, h - 1);

        g.setColor(thumbHighlightColor);
        drawVLine(g, 0, 0, h - 1);
        drawHLine(g, 1, w - 1, 0);

        g.setColor(thumbLightShadowColor);
        drawHLine(g, 1, w - 1, h - 1);
        drawVLine(g, w - 1, 1, h - 2);

        g.translate(-thumbBounds.x, -thumbBounds.y);
    }
}
