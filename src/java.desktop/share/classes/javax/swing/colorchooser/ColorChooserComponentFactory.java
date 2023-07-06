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

package java.desktop.share.classes.javax.swing.colorchooser;

import java.desktop.share.classes.javax.swing.JComponent;

/**
 * A class designed to produce preconfigured "accessory" objects to
 * insert into color choosers.
 *
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 */
@SuppressWarnings("serial") // Same-version serialization only
public class ColorChooserComponentFactory {

    private ColorChooserComponentFactory() { } // can't instantiate

    /**
     * Returns the default chooser panels.
     * @return the default chooser panels
     */
    public static AbstractColorChooserPanel[] getDefaultChooserPanels() {
        return new AbstractColorChooserPanel[] {
                new DefaultSwatchChooserPanel(),
                new ColorChooserPanel(new ColorModelHSV()),
                new ColorChooserPanel(new ColorModelHSL()),
                new ColorChooserPanel(new ColorModel()),
                new ColorChooserPanel(new ColorModelCMYK()),
        };
    }

    /**
     * Returns the preview panel.
     * @return the preview panel
     */
    public static JComponent getPreviewPanel() {
        return new DefaultPreviewPanel();
    }
}