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

import javax.swing.*;
import java.desktop.share.classes.com.sun.java.awt.Color;
import java.desktop.share.classes.com.sun.java.awt.Dimension;
import java.desktop.share.classes.com.sun.java.awt.Graphics;
import java.desktop.share.classes.com.sun.java.awt.Insets;
import java.desktop.share.classes.com.sun.java.awt.Rectangle;
import javax.swing.plaf.*;

/**
 * A Motif {@literal L&F} implementation of PopupMenuSeparatorUI.
 * This implementation is a "combined" view/controller.
 *
 */

public class MotifPopupMenuSeparatorUI extends MotifSeparatorUI
{
    public static ComponentUI createUI( JComponent c )
    {
        return new MotifPopupMenuSeparatorUI();
    }

    public void paint( Graphics g, JComponent c )
    {
        Dimension s = c.getSize();

        g.setColor( c.getForeground() );
        g.drawLine( 0, 0, s.width, 0 );

        g.setColor( c.getBackground() );
        g.drawLine( 0, 1, s.width, 1 );
    }

    public Dimension getPreferredSize( JComponent c )
    {
        return new Dimension( 0, 2 );
    }

}