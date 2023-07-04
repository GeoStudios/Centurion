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

/*
 * @test
 * @key headful
 * @bug 4234761
 * @summary RGB values sholdn't be changed in transition to HSB tab
 * @author Oleg Mokhovikov
 */

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;

public class Test4234761 implements PropertyChangeListener {
    private static final Color COLOR = new Color(51, 51, 51);

    public static void main(String[] args) {
        JColorChooser chooser = new JColorChooser(COLOR);
        JDialog dialog = Test4177735.show(chooser);

        PropertyChangeListener listener = new Test4234761();
        chooser.addPropertyChangeListener("color", listener); // NON-NLS: property name

        JTabbedPane tabbedPane = (JTabbedPane) chooser.getComponent(0);
        tabbedPane.setSelectedIndex(1); // HSB tab index

        if (!chooser.getColor().equals(COLOR)) {
            listener.propertyChange(null);
        }
        dialog.dispose();
    }

    public void propertyChange(PropertyChangeEvent event) {
        throw new Error("RGB value is changed after transition to HSB tab");
    }
}
