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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ui.table;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;

/**
 * A cell renderer for the JTableHeader which understands the sorted
 * column state and renders arrow buttons to indicated the sorted column
 * and order
 */
public class SortHeaderCellRenderer extends DefaultTableCellRenderer {

    private final Icon descendingIcon;
    private final Icon ascendingIcon;

    private final SortableTableModel model;

    public SortHeaderCellRenderer(JTableHeader header, SortableTableModel model) {
        this.model = model;

        descendingIcon = getIcon("navigation/Down16.gif");
        ascendingIcon = getIcon("navigation/Up16.gif");

        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());

        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(JLabel.CENTER);

    }

    /**
     *  Retrieves an Image Icon from the JLF graphics repository.
     */
    public ImageIcon getIcon(String name) {
        String imagePath = "/toolbarButtonGraphics/" + name;
        java.net.URL url = this.getClass().getResource(imagePath);
        if (url != null) {
            return new ImageIcon(url);
        }
        return null;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column)  {
        setText((value == null) ? "" : value.toString());

        Icon icon = null;
        if (column == model.getColumn()) {
            if (model.isAscending()) {
                icon = ascendingIcon;
            } else {
                icon = descendingIcon;
            }
        }
        setIcon(icon);

        return this;
    }
}
