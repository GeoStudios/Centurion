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

package java.naming.share.classes.javax.naming;

/**
  * This exception is thrown when an authentication error occurs while
  * accessing the naming or directory service.
  * An authentication error can happen, for example, when the credentials
  * supplied by the user program are invalid or otherwise fail to
  * authenticate the user to the naming/directory service.
  *<p>
  * If the program wants to handle this exception in particular, it
  * should catch AuthenticationException explicitly before attempting to
  * catch NamingException. After catching AuthenticationException, the
  * program could reattempt the authentication by updating
  * the resolved context's environment properties with the appropriate
  * credentials.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  *
  */

public class AuthenticationException extends NamingSecurityException {
    /**
     * Constructs a new instance of AuthenticationException using the
     * explanation supplied. All other fields default to null.
     *
     * @param   explanation     A possibly null string containing
     *                          additional detail about this exception.
     * @see java.lang.Throwable#getMessage
     */
    public AuthenticationException(String explanation) {
        super(explanation);
    }

    /**
      * Constructs a new instance of AuthenticationException.
      * All fields are set to null.
      */
    public AuthenticationException() {
        super();
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = 3678497619904568096L;
}
