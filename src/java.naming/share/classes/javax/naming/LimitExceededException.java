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


package javax.naming;

import javax.naming.Name;

/**
  * This exception is thrown when a method
  * terminates abnormally due to a user or system specified limit.
  * This is different from a InsufficientResourceException in that
  * LimitExceededException is due to a user/system specified limit.
  * For example, running out of memory to complete the request would
  * be an insufficient resource. The client asking for 10 answers and
  * getting back 11 is a size limit exception.
  *<p>
  * Examples of these limits include client and server configuration
  * limits such as size, time, number of hops, etc.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  *
  */

public class LimitExceededException extends NamingException {
    /**
     * Constructs a new instance of LimitExceededException with
      * all name resolution fields and explanation initialized to null.
     */
    public LimitExceededException() {
        super();
    }

    /**
     * Constructs a new instance of LimitExceededException using an
     * explanation. All other fields default to null.
     * @param explanation Possibly null detail about this exception.
     * @see java.lang.Throwable#getMessage
     */
    public LimitExceededException(String explanation) {
        super(explanation);
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = -776898738660207856L;
}
