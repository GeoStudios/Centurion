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

package java.sql.share.classes.java.sql;

import java.sql.share.classes.java.util.*;

/**
 * Enumeration for RowId life-time values.
 *
 */

public enum RowIdLifetime {

    /**
     * Indicates that this data source does not support the ROWID type.
     */
    ROWID_UNSUPPORTED,

    /**
     * Indicates that the lifetime of a RowId from this data source is indeterminate;
     * but not one of ROWID_VALID_TRANSACTION, ROWID_VALID_SESSION, or,
     * ROWID_VALID_FOREVER.
     */
    ROWID_VALID_OTHER,

    /**
     * Indicates that the lifetime of a RowId from this data source is at least the
     * containing session.
     */
    ROWID_VALID_SESSION,

    /**
     * Indicates that the lifetime of a RowId from this data source is at least the
     * containing transaction.
     */
    ROWID_VALID_TRANSACTION,

    /**
     * Indicates that the lifetime of a RowId from this data source is, effectively,
     * unlimited.
     */
    ROWID_VALID_FOREVER
}