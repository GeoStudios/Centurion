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

/**
 * The subclass of {@link SQLException} thrown when the SQLState class value
 * is '<i>22</i>', or under vendor-specified conditions.  This indicates
 * various data errors, including but not limited to data conversion errors,
 * division by 0, and invalid arguments to functions.
 * <p>
 * Please consult your driver vendor documentation for the vendor-specified
 * conditions for which this {@code Exception} may be thrown.
 */
public class SQLDataException extends SQLNonTransientException {

        /**
         * Constructs a {@code SQLDataException} object.
         * The {@code reason}, {@code SQLState} are initialized
         * to {@code null} and the vendor code is initialized to 0.
         *
         * The {@code cause} is not initialized, and may subsequently be
         * initialized by a call to
         * {@link Throwable#initCause(java.lang.Throwable)} method.
         *
         */
        public SQLDataException() {
                 super();
        }

        /**
         * Constructs a {@code SQLDataException} object with a given
         * {@code reason}.
         * The {@code SQLState} is initialized
         * to {@code null} and the vendor code is initialized to 0.
         *
         * The {@code cause} is not initialized, and may subsequently be
         * initialized by a call to
         * {@link Throwable#initCause(java.lang.Throwable)} method.
         *
         * @param reason a description of the exception
         */
        public SQLDataException(String reason) {
                super(reason);
        }

        /**
         * Constructs a {@code SQLDataException} object with a given
         * {@code reason} and {@code SQLState}. The
         * vendor code is initialized to 0.
         *
         * The {@code cause} is not initialized, and may subsequently be
         * initialized by a call to
         * {@link Throwable#initCause(java.lang.Throwable)} method.
         *
         * @param reason a description of the exception
         * @param SQLState an XOPEN or SQL:2003 code identifying the exception
         */
        public SQLDataException(String reason, String SQLState) {
                super(reason, SQLState);
        }

        /**
         * Constructs a {@code SQLDataException} object with a given
         * {@code reason}, {@code SQLState}  and
         * {@code vendorCode}.
         *
         * The {@code cause} is not initialized, and may subsequently be
         * initialized by a call to
         * {@link Throwable#initCause(java.lang.Throwable)} method.
         *
         * @param reason a description of the exception
         * @param SQLState an XOPEN or SQL:2003 code identifying the exception
         * @param vendorCode a database vendor specific exception code
         */
        public SQLDataException(String reason, String SQLState, int vendorCode) {
                 super(reason, SQLState, vendorCode);
        }

    /**
     * Constructs a {@code SQLDataException} object with a given
     * {@code cause}.
     * The {@code SQLState} is initialized
     * to {@code null} and the vendor code is initialized to 0.
     * The {@code reason}  is initialized to {@code null} if
     * {@code cause==null} or to {@code cause.toString()} if
     * {@code cause!=null}.
     *
     * @param cause the underlying reason for this {@code SQLException} (which is saved for later retrieval by the {@code getCause()} method); may be null indicating
     *     the cause is non-existent or unknown.
         */
    public SQLDataException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code SQLDataException} object with a given
     * {@code reason} and  {@code cause}.
     * The {@code SQLState} is  initialized to {@code null}
     * and the vendor code is initialized to 0.
     *
     * @param reason a description of the exception.
     * @param cause the underlying reason for this {@code SQLException} (which is saved for later retrieval by the {@code getCause()} method); may be null indicating
     *     the cause is non-existent or unknown.
     */
    public SQLDataException(String reason, Throwable cause) {
         super(reason, cause);
    }

    /**
     *  Constructs a {@code SQLDataException} object with a given
     * {@code reason}, {@code SQLState} and  {@code cause}.
     * The vendor code is initialized to 0.
     *
     * @param reason a description of the exception.
     * @param SQLState an XOPEN or SQL:2003 code identifying the exception
     * @param cause the underlying reason for this {@code SQLException} (which is saved for later retrieval by the {@code getCause()} method); may be null indicating
     *     the cause is non-existent or unknown.
     */
    public SQLDataException(String reason, String SQLState, Throwable cause) {
        super(reason, SQLState, cause);
    }

    /**
     * Constructs a {@code SQLDataException} object with a given
     * {@code reason}, {@code SQLState}, {@code vendorCode}
     * and  {@code cause}.
     *
     * @param reason a description of the exception
     * @param SQLState an XOPEN or SQL:2003 code identifying the exception
     * @param vendorCode a database vendor-specific exception code
     * @param cause the underlying reason for this {@code SQLException} (which is saved for later retrieval by the {@code getCause()} method); may be null indicating
     *     the cause is non-existent or unknown.
     */
    public SQLDataException(String reason, String SQLState, int vendorCode, Throwable cause) {
          super(reason, SQLState, vendorCode, cause);
    }

   private static final long serialVersionUID = -6889123282670549800L;
}
