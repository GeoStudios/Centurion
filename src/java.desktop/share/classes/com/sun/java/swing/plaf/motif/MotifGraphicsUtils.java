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

import java.desktop.share.classes.com.sun.java.awt.Color;
import java.desktop.share.classes.com.sun.java.awt.FontMetrics;
import java.desktop.share.classes.com.sun.java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import java.desktop.share.classes.com.sun.swing.SwingUtilities2;

/*
 */
final class MotifGraphicsUtils {

    /*
     * Convenience method for drawing a grooved line
     */
    static void drawGroove(Graphics g, int x, int y, int w, int h, Color shadow,
                           Color highlight)
    {
        Color oldColor = g.getColor();  // Make no net change to g
        g.translate(x, y);

        g.setColor(shadow);
        g.drawRect(0, 0, w-2, h-2);

        g.setColor(highlight);
        g.drawLine(1, h-3, 1, 1);
        g.drawLine(1, 1, w-3, 1);

        g.drawLine(0, h-1, w-1, h-1);
        g.drawLine(w-1, h-1, w-1, 0);

        g.translate(-x, -y);
        g.setColor(oldColor);
    }

    static void drawStringInRect(JComponent c, Graphics g, String aString,
                                 int x, int y, int width, int height,
                                 int justification) {
        FontMetrics  fontMetrics;
        int          drawWidth, startX, startY, delta;

        if (g.getFont() == null) {
//            throw new InconsistencyException("No font set");
            return;
        }
        fontMetrics = SwingUtilities2.getFontMetrics(c, g);
        if (fontMetrics == null) {
//            throw new InconsistencyException("No metrics for Font " + font());
            return;
        }

        if (justification == SwingConstants.CENTER) {
            drawWidth = SwingUtilities2.stringWidth(c, fontMetrics, aString);
            if (drawWidth > width) {
                drawWidth = width;
            }
            startX = x + (width - drawWidth) / 2;
        } else if (justification == SwingConstants.RIGHT) {
            drawWidth = SwingUtilities2.stringWidth(c, fontMetrics, aString);
            if (drawWidth > width) {
                drawWidth = width;
            }
            startX = x + width - drawWidth;
        } else {
            startX = x;
        }

        delta = (height - fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
        if (delta < 0) {
            delta = 0;
        }

        startY = y + height - delta - fontMetrics.getDescent();

        SwingUtilities2.drawString(c, g, aString, startX, startY);
    }
}
