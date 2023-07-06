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

package java.sql.share.classes.javax.sql;


import java.sql.*;















/**
 * An object that provides support for distributed transactions. An
 * {@code XAConnection} object may be enlisted in a distributed transaction
 * by means of an {@code XAResource} object. A transaction manager, usually
 * part of a middle tier server, manages an {@code XAConnection} object
 * through the {@code XAResource} object.
 * <P>
 * An application programmer does not use this interface directly; rather, it is
 * used by a transaction manager working in the middle tier server.
 *
 */
public interface XAConnection extends PooledConnection {

    /**
     * Retrieves an {@code XAResource} object that the transaction manager
     * will use to manage this {@code XAConnection} object's participation
     * in a distributed transaction.
     *
     * @return the {@code XAResource} object
     * @throws SQLException if a database access error occurs
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not
     * support this method
     */
    javax.transaction.xa.XAResource getXAResource() throws SQLException;
}
