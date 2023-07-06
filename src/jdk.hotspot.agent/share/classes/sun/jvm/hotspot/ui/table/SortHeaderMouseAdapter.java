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


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;
import com.sun.java.swing.ui.CommonUI;















/**
 * A mouse adapater which is attached to the header of a JTable. It listens
 * for mouse clicks on a column and sorts that column.
 */
public class SortHeaderMouseAdapter extends MouseAdapter {

    private final SortableTableModel model;
    private final JTable table;

    public SortHeaderMouseAdapter(JTable table, SortableTableModel model) {
        this.model = model;
        this.table = table;
    }

    public void mouseClicked(MouseEvent evt) {
        // XXX Benchmark sort performance
        //long start = System.currentTimeMillis();
        CommonUI.setWaitCursor(SwingUtilities.getRoot(table));

        TableColumnModel columnModel = table.getColumnModel();
        int viewColumn = columnModel.getColumnIndexAtX(evt.getX());
        int column = table.convertColumnIndexToModel(viewColumn);
        if (evt.getClickCount() == 1 && column != -1) {
            // Reverse the sorting direction.
            model.sortByColumn(column, !model.isAscending());
        }

        // XXX Benchmark performance
        //      System.out.println("Sort time: " +
        //         (System.currentTimeMillis() - start));
        CommonUI.setDefaultCursor(SwingUtilities.getRoot(table));
    }
}
