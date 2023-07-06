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

package com.sun.swingset3.demos.table;


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import javax.swing.table.AbstractTableModel;














/**
 * Data model for oscar candidate data: a list of OscarCandidate beans.
 *
 * @author aim
 */

public class OscarTableModel extends AbstractTableModel {
    public static final int CATEGORY_COLUMN = 0;
    public static final int YEAR_COLUMN = 1;
    public static final int WINNER_COLUMN = 2;
    public static final int MOVIE_COLUMN = 3;
    public static final int PERSONS_COLUMN = 4;
    public static final int COLUMN_COUNT = 5;

    private final List<OscarCandidate> candidates = new ArrayList<OscarCandidate>();

    public void add(List<OscarCandidate> newCandidates) {
        int first = candidates.size();
        int last = first + newCandidates.size() - 1;
        candidates.addAll(newCandidates);
        fireTableRowsInserted(first, last);
    }

    public void add(OscarCandidate candidate) {
        int index = candidates.size();
        candidates.add(candidate);
        fireTableRowsInserted(index, index);
    }

    public int getRowCount() {
        return candidates.size();
    }

    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    public OscarCandidate getCandidate(int row) {
        return candidates.get(row);
    }

    public Object getValueAt(int row, int column) {
        OscarCandidate oscarCandidate = candidates.get(row);
        switch (column) {
            case CATEGORY_COLUMN:
                return oscarCandidate.getCategory();
            case YEAR_COLUMN:
                return oscarCandidate.getYear();
            case MOVIE_COLUMN:
                return oscarCandidate.getMovieTitle();
            case WINNER_COLUMN:
                return oscarCandidate.isWinner() ? Boolean.TRUE : Boolean.FALSE;
            case PERSONS_COLUMN:
                return oscarCandidate.getPersons();
        }
        return null;
    }

}
