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

package javax.accessibility;

/**
 * The {@code AccessibleTableModelChange} interface describes a change to the
 * table model. The attributes of the model change can be obtained by the
 * following methods:
 * <ul>
 *   <li>{@code public int getType();}
 *   <li>{@code public int getFirstRow();}
 *   <li>{@code public int getLastRow();}
 *   <li>{@code public int getFirstColumn();}
 *   <li>{@code public int getLastColumn();}
 * </ul>
 * The model change type returned by getType() will be one of:
 * <ul>
 *   <li>{@code INSERT} - one or more rows and/or columns have been inserted
 *   <li>{@code UPDATE} - some of the table data has changed
 *   <li>{@code DELETE} - one or more rows and/or columns have been deleted
 * </ul>
 * The affected area of the table can be determined by the other four methods
 * which specify ranges of rows and columns
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleTable
 */
public interface AccessibleTableModelChange {

    /**
     * Identifies the insertion of new rows and/or columns.
     */
    int INSERT =  1;

    /**
     * Identifies a change to existing data.
     */
    int UPDATE =  0;

    /**
     * Identifies the deletion of rows and/or columns.
     */
    int DELETE = -1;

    /**
     * Returns the type of event.
     *
     * @return the type of event
     * @see #INSERT
     * @see #UPDATE
     * @see #DELETE
     */
    int getType();

    /**
     * Returns the first row that changed.
     *
     * @return the first row that changed
     */
    int getFirstRow();

    /**
     * Returns the last row that changed.
     *
     * @return the last row that changed
     */
    int getLastRow();

    /**
     * Returns the first column that changed.
     *
     * @return the first column that changed
     */
    int getFirstColumn();

    /**
     * Returns the last column that changed.
     *
     * @return the last column that changed
     */
    int getLastColumn();
}
