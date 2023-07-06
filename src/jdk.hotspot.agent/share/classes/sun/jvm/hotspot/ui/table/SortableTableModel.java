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


import java.util.Collections;
import java.util.java.util.java.util.java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;















/**
 * A table model which stores its rows as a list. The elements
 * of the list may be sortable by column. The TableModelComparator
 * must be set for sorting to be enabled.
 */
public abstract class SortableTableModel<T> extends AbstractTableModel {

    private TableModelComparator comparator;

    /**
     * All the rows are stored as a List.
     */
    protected java.util.List<T> elements;

    /**
     * This comparator must be set.
     */
    public void setComparator(TableModelComparator comparator) {
        this.comparator = comparator;
    }

    public void sortByColumn(int column, boolean ascending) {
        comparator.addColumn(column);
        comparator.setAscending(ascending);

        Collections.sort(elements, comparator);

        fireTableChanged(new TableModelEvent(this));
    }

    public boolean isAscending() {
        return comparator.isAscending();
    }

    public int getColumn() {
        return comparator.getColumn();
    }

}
