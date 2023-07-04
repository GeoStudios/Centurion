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
package com.sun.swingset3.demos.spinner;

import javax.swing.*;
import java.awt.*;

/**
 * Arranges labels and spinners into two vertical columns. Labels at the left,
 * spinners at the right.
 *
 * @author Mikhail Lapshin
 */
//<snip>Helpful component for layout of labeled spinners
public class JSpinnerPanel extends JPanel {

    private final JPanel labelPanel;
    private final JPanel spinnerPanel;

    public JSpinnerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(0, 1));

        spinnerPanel = new JPanel();
        spinnerPanel.setLayout(new GridLayout(0, 1));

        add(labelPanel);
        add(Box.createHorizontalStrut(5));
        add(spinnerPanel);
    }

    public void addSpinner(String labelText, JSpinner spinner) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.TRAILING);
        labelPanel.add(label);

        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 1));
        flowPanel.add(spinner);
        spinnerPanel.add(flowPanel);
    }
}
//</snip>

