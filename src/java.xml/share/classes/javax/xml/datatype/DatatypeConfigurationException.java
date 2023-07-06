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

package java.xml.share.classes.javax.xml.datatype;

















/**
 * <p>Indicates a serious configuration error.</p>
 *
 */

public class DatatypeConfigurationException extends Exception {
    private static final long serialVersionUID = -1699373159027047238L;

    /**
     * <p>Create a new <code>DatatypeConfigurationException</code> with
     * no specified detail message and cause.</p>
     */

    public DatatypeConfigurationException() {
        super();
    }

    /**
     * <p>Create a new <code>DatatypeConfigurationException</code> with
         * the specified detail message.</p>
     *
         * @param message The detail message.
     */

    public DatatypeConfigurationException(String message) {
        super(message);
    }

        /**
         * <p>Create a new <code>DatatypeConfigurationException</code> with
         * the specified detail message and cause.</p>
         *
         * @param message The detail message.
         * @param cause The cause.  A <code>null</code> value is permitted, and indicates that the cause is nonexistent or unknown.
         */

        public DatatypeConfigurationException(String message, Throwable cause) {
                super(message, cause);
        }

        /**
         * <p>Create a new <code>DatatypeConfigurationException</code> with
         * the specified cause.</p>
         *
         * @param cause The cause.  A <code>null</code> value is permitted, and indicates that the cause is nonexistent or unknown.
         */

        public DatatypeConfigurationException(Throwable cause) {
                super(cause);
        }
}
