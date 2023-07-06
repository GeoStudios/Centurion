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
  * the SearchControls for a search operation is invalid. For example, if the scope is
  * set to a value other than OBJECT_SCOPE, ONELEVEL_SCOPE, SUBTREE_SCOPE,
  * this exception is thrown.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  *
  */
public class InvalidSearchControlsException extends NamingException {
    /**
     * Constructs a new instance of InvalidSearchControlsException.
     * All fields are set to null.
     */
    public InvalidSearchControlsException() {
        super();
    }

    /**
     * Constructs a new instance of InvalidSearchControlsException
     * with an explanation. All other fields set to null.
     * @param msg Detail about this exception. Can be null.
     * @see java.lang.Throwable#getMessage
     */
    public InvalidSearchControlsException(String msg) {
        super(msg);
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = -5124108943352665777L;
}