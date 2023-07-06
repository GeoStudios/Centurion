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

package com.sun.swingset3.demos.frame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.JPanel;














/**
 * GlassPane component which can be set on toplevel
 * containers to makes those containers "busy" be disabling input.
 *
 * Example usage:
 * <pre><code>
 *    // Install glasspane
 *    frame.setGlassPane(new BusyGlass());
 *
 *    // Make frame busy
 *    frame.getGlassPane().setVisible(true);
 * </code></pre>
 *
 * Caution: A well-written client should rarely need to make
 * a window "busy" because the app should be as responsive as possible;
 * long-winded operations should be off-loaded to non-GUI threads
 * whenever possible.
 *
 * @author aim
 */
//<snip>Make toplevel "busy"
public class BusyGlass extends JPanel {

    /**
     * Create GlassPane component to block input on toplevel
     */
    public BusyGlass() {
        setLayout(new BorderLayout());
        setVisible(false); //initially invisible
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    protected void paintComponent(Graphics g) {
        // Render partially opaque to 'veil' the frame's contents so
        // that the user has visual feedback that the components
        // arn't responsive.
        Color bgColor = getBackground();
        g.setColor(new Color(bgColor.getRed(),
                bgColor.getGreen(),
                bgColor.getBlue(), 150));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
//</snip>