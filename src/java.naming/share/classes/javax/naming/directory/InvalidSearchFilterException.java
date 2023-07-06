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

package java.naming.share.classes.javax.naming.directory;

import java.naming.share.classes.javax.naming.NamingException;

/**
  * This exception is thrown when the specification of
  * a search filter is invalid.  The expression of the filter may
  * be invalid, or there may be a problem with one of the parameters
  * passed to the filter.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  *
  */
public class InvalidSearchFilterException extends NamingException {
    /**
     * Constructs a new instance of InvalidSearchFilterException.
     * All fields are set to null.
     */
    public InvalidSearchFilterException() {
        super();
    }

    /**
     * Constructs a new instance of InvalidSearchFilterException
     * with an explanation. All other fields are set to null.
     * @param msg Detail about this exception. Can be null.
     * @see java.lang.Throwable#getMessage
     */
    public InvalidSearchFilterException(String msg) {
        super(msg);
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = 2902700940682875441L;
}
