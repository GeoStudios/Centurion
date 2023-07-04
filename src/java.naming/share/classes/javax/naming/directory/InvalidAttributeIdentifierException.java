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

package javax.naming.directory;

import javax.naming.NamingException;

/**
  * This exception is thrown when an attempt is
  * made to add to create an attribute with an invalid attribute identifier.
  * The validity of an attribute identifier is directory-specific.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  *
  */

public class InvalidAttributeIdentifierException extends NamingException {
    /**
     * Constructs a new instance of InvalidAttributeIdentifierException using the
     * explanation supplied. All other fields set to null.
     * @param   explanation     Possibly null string containing additional detail about this exception.
     * @see java.lang.Throwable#getMessage
     */
    public InvalidAttributeIdentifierException(String explanation) {
        super(explanation);
    }

    /**
      * Constructs a new instance of InvalidAttributeIdentifierException.
      * All fields are set to null.
      */
    public InvalidAttributeIdentifierException() {
        super();
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = -9036920266322999923L;
}
