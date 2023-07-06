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

package java.desktop.share.classes.javax.swing.plaf.metal;

import java.awt.*;
import java.awt.event.*;
import java.desktop.share.classes.javax.swing.*;
import java.desktop.share.classes.javax.swing.plaf.*;
import java.desktop.share.classes.javax.swing.border.*;
import java.io.Serializable;
import java.desktop.share.classes.javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * This utility class draws the horizontal bars which indicate a MetalComboBox
 *
 * @see MetalComboBoxUI
 */
@SuppressWarnings("serial") // Same-version serialization only
public class MetalComboBoxIcon implements Icon, Serializable {

    /**
     * Constructs a {@code MetalComboBoxIcon}.
     */
    public MetalComboBoxIcon() {}

    /**
     * Paints the horizontal bars for the
     */
    public void paintIcon(Component c, Graphics g, int x, int y){
        JComponent component = (JComponent)c;
        int iconWidth = getIconWidth();

        g.translate( x, y );

        g.setColor(component.isEnabled()
                   ? MetalLookAndFeel.getControlInfo()
                   : MetalLookAndFeel.getControlShadow());
        g.fillPolygon(new int[]{0, 5, iconWidth - 5, iconWidth},
                      new int[]{0, 5, 5, 0}, 4);
        g.translate( -x, -y );
    }

    /**
     * Created a stub to satisfy the interface.
     */
    public int getIconWidth() { return 10; }

    /**
     * Created a stub to satisfy the interface.
     */
    public int getIconHeight()  { return 5; }

}