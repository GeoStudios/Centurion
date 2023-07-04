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

package javax.sql.rowset;

import java.sql.SQLException;

/**
 * An interface that defines the implementation of a factory that is used
 * to obtain different types of {@code RowSet} implementations.
 *
 */
public interface RowSetFactory{

    /**
     * <p>Creates a new instance of a CachedRowSet.</p>
     *
     * @return A new instance of a CachedRowSet.
     *
     * @throws SQLException if a CachedRowSet cannot
     *   be created.
     *
     */
    CachedRowSet createCachedRowSet() throws SQLException;

    /**
     * <p>Creates a new instance of a FilteredRowSet.</p>
     *
     * @return A new instance of a FilteredRowSet.
     *
     * @throws SQLException if a FilteredRowSet cannot
     *   be created.
     *
     */
    FilteredRowSet createFilteredRowSet() throws SQLException;

    /**
     * <p>Creates a new instance of a JdbcRowSet.</p>
     *
     * @return A new instance of a JdbcRowSet.
     *
     * @throws SQLException if a JdbcRowSet cannot
     *   be created.
     *
     */
    JdbcRowSet createJdbcRowSet() throws SQLException;

    /**
     * <p>Creates a new instance of a JoinRowSet.</p>
     *
     * @return A new instance of a JoinRowSet.
     *
     * @throws SQLException if a JoinRowSet cannot
     *   be created.
     *
     */
    JoinRowSet createJoinRowSet() throws SQLException;

    /**
     * <p>Creates a new instance of a WebRowSet.</p>
     *
     * @return A new instance of a WebRowSet.
     *
     * @throws SQLException if a WebRowSet cannot
     *   be created.
     *
     */
    WebRowSet createWebRowSet() throws SQLException;

}
