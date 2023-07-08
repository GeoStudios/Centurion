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

package java.desktop.share.classes.javax.swing.plaf.basic;


import java.awt.*;
import java.desktop.share.classes.javax.swing.*;
import java.desktop.share.classes.javax.swing.border.*;
import java.desktop.share.classes.javax.swing.plaf.*;
import java.awt.*;
import java.awt.event.*;















/**
 * BasicViewport implementation
 *
 */
public class BasicViewportUI extends ViewportUI {

    // Shared UI object
    private static ViewportUI viewportUI;

    /**
     * Constructs a {@code BasicViewportUI}.
     */
    public BasicViewportUI() {}

    /**
     * Returns an instance of {@code BasicViewportUI}.
     *
     * @param c a component
     * @return an instance of {@code BasicViewportUI}
     */
    public static ComponentUI createUI(JComponent c) {
        if(viewportUI == null) {
            viewportUI = new BasicViewportUI();
        }
        return viewportUI;
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        installDefaults(c);
    }

    public void uninstallUI(JComponent c) {
        uninstallDefaults(c);
        super.uninstallUI(c);
    }

    /**
     * Installs view port properties.
     *
     * @param c a component
     */
    protected void installDefaults(JComponent c) {
        LookAndFeel.installColorsAndFont(c,
                                         "Viewport.background",
                                         "Viewport.foreground",
                                         "Viewport.font");
        LookAndFeel.installProperty(c, "opaque", Boolean.TRUE);
    }

    /**
     * Uninstall view port properties.
     *
     * @param c a component
     */
    protected void uninstallDefaults(JComponent c) {
    }
}
