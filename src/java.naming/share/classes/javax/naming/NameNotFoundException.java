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
  * This exception is thrown when a component of the name cannot be resolved
  * because it is not bound.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  *
  */

public class NameNotFoundException extends NamingException {
    /**
     * Constructs a new instance of NameNotFoundException using the
     * explanation supplied. All other fields default to null.
     *
     * @param   explanation     Possibly null additional detail about
     *                          this exception.
     * @see java.lang.Throwable#getMessage
     */
    public NameNotFoundException(String explanation) {
        super(explanation);
    }

    /**
      * Constructs a new instance of NameNotFoundException.
      * All name resolution fields and explanation are initialized to null.
      */
    public NameNotFoundException() {
        super();
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = -8007156725367842053L;
}