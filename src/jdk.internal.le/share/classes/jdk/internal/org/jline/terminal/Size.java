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

package jdk.internal.le.share.classes.jdk.internal.org.jline.terminal;

















public class Size {

    private int rows;
    private int cols;

    public Size() {
    }

    public Size(int columns, int rows) {
        this();
        setColumns(columns);
        setRows(rows);
    }

    public int getColumns() {
        return cols;
    }

    public void setColumns(int columns) {
        cols = (short) columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = (short) rows;
    }

    /**
     * A cursor position combines a row number with a column position.
     * <p>
     * Note each row has {@code col+1} different column positions,
     * including the right margin.
     * </p>
     *
     * @param col the new column
     * @param row the new row
     * @return the cursor position
     */
    public int cursorPos(int row, int col) {
        return row * (cols+1) + col;
    }

    public void copy(Size size) {
        setColumns(size.getColumns());
        setRows(size.getRows());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Size size) {
            return rows == size.rows && cols == size.cols;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return rows * 31 + cols;
    }

    @Override
    public String toString() {
        return "Size[" + "cols=" + cols + ", rows=" + rows + ']';
    }
}
