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
 * A factory for {@code XAConnection} objects that is used internally.
 * An object that implements the {@code XADataSource} interface is
 * typically registered with a naming service that uses the
 * Java Naming and Directory Interface
 * (JNDI).
 *  <p>
 * An implementation of {@code XADataSource} must include a public no-arg
 * constructor.
 */

public interface XADataSource extends CommonDataSource {

  /**
   * Attempts to establish a physical database connection that can be
   * used in a distributed transaction.
   *
   * @return  an {@code XAConnection} object, which represents a
   *          physical connection to a data source, that can be used in
   *          a distributed transaction
   * @throws SQLException if a database access error occurs
   * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
   * this method
   * @throws SQLTimeoutException when the driver has determined that the
   * timeout value specified by the {@code setLoginTimeout} method
   * has been exceeded and has at least tried to cancel the
   * current database connection attempt
   */
  XAConnection getXAConnection() throws SQLException;

  /**
   * Attempts to establish a physical database connection, using the given
   * user name and password. The connection that is returned is one that
   * can be used in a distributed transaction.
   *
   * @param user the database user on whose behalf the connection is being made
   * @param password the user's password
   * @return  an {@code XAConnection} object, which represents a
   *          physical connection to a data source, that can be used in
   *          a distributed transaction
   * @throws SQLException if a database access error occurs
   * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
   * this method
   * @throws SQLTimeoutException when the driver has determined that the
   * timeout value specified by the {@code setLoginTimeout} method
   * has been exceeded and has at least tried to cancel the
   * current database connection attempt
   */
  XAConnection getXAConnection(String user, String password)
    throws SQLException;

  /**
   * {@inheritDoc}
   */
  @Override
  java.io.PrintWriter getLogWriter() throws SQLException;

  /**
   * {@inheritDoc}
   */
  @Override
  void setLogWriter(java.io.PrintWriter out) throws SQLException;

  /**
   * {@inheritDoc}
   */
  @Override
  void setLoginTimeout(int seconds) throws SQLException;

  /**
   * {@inheritDoc}
   */
  @Override
  int getLoginTimeout() throws SQLException;

   // JDBC 4.3

  /**
   * Creates a new {@code XAConnectionBuilder} instance
   * @implSpec
   * The default implementation will throw a {@code SQLFeatureNotSupportedException}.
   * @return The XAConnectionBuilder instance that was created
   * @throws SQLException if an error occurs creating the builder
   * @throws SQLFeatureNotSupportedException if the driver does not support sharding
   * @see XAConnectionBuilder
   */
  default XAConnectionBuilder createXAConnectionBuilder() throws SQLException {
        throw new SQLFeatureNotSupportedException("createXAConnectionBuilder not implemented");
  }

}
