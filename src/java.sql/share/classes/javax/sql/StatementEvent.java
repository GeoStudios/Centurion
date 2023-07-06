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


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.EventObject;















/*
 * Created on Apr 28, 2005
 */



/**
 * A {@code StatementEvent} is sent to all {@code StatementEventListener}s which were
 * registered with a {@code PooledConnection}. This occurs when the driver determines that a
 * {@code PreparedStatement} that is associated with the {@code PooledConnection} has been closed or the driver determines
 * is invalid.
 *
 */
public class StatementEvent extends EventObject {

        static final long serialVersionUID = -8089573731826608315L;
        /**
         * The {@code SQLException} the driver is about to throw to the application.
         */
        private final SQLException            exception;

        /**
         * The {@code PreparedStatement} that is being closed or is invalid.
         */
        @SuppressWarnings("serial") // Not statically typed as Serializable
        private final PreparedStatement       statement;

        /**
         * Constructs a {@code StatementEvent} with the specified {@code PooledConnection} and
         * {@code PreparedStatement}.  The {@code SQLException} contained in the event defaults to
         * null.
         *
         * @param con                   The {@code PooledConnection} that the closed or invalid
         * {@code PreparedStatement}is associated with.
         * @param statement             The {@code PreparedStatement} that is being closed or is invalid
         *
         * @throws IllegalArgumentException if {@code con} is null.
         *
         */
        public StatementEvent(PooledConnection con,
                                                  PreparedStatement statement) {

                super(con);

                this.statement = statement;
                this.exception = null;
        }

        /**
         * Constructs a {@code StatementEvent} with the specified {@code PooledConnection},
         * {@code PreparedStatement} and {@code SQLException}
         *
         * @param con                   The {@code PooledConnection} that the closed or invalid {@code PreparedStatement}
         * is associated with.
         * @param statement             The {@code PreparedStatement} that is being closed or is invalid
         * @param exception             The {@code SQLException }the driver is about to throw to
         *                                              the application
         *
         * @throws IllegalArgumentException if {@code con} is null.
         *
         */
        public StatementEvent(PooledConnection con,
                                                  PreparedStatement statement,
                                                  SQLException exception) {

                super(con);

                this.statement = statement;
                this.exception = exception;
        }

        /**
         * Returns the {@code PreparedStatement} that is being closed or is invalid
         *
         * @return      The {@code PreparedStatement} that is being closed or is invalid
         *
         */
        public PreparedStatement getStatement() {

                return this.statement;
        }

        /**
         * Returns the {@code SQLException} the driver is about to throw
         *
         * @return      The {@code SQLException} the driver is about to throw
         *
         */
        public SQLException getSQLException() {

                return this.exception;
        }
}
