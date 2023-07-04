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

/**
  * This exception is thrown when there is a configuration problem.
  * This can arise when installation of a provider was
  * not done correctly, or if there are configuration problems with the
  * server, or if configuration information required to access
  * the provider or service is malformed or missing.
  * For example, a request to use SSL as the security protocol when
  * the service provider software was not configured with the SSL
  * component would cause such an exception. Another example is
  * if the provider requires that a URL be specified as one of the
  * environment properties but the client failed to provide it.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  *
  */
public class ConfigurationException extends NamingException {
    /**
     * Constructs a new instance of ConfigurationException using an
     * explanation. All other fields default to null.
     *
     * @param   explanation     A possibly null string containing
     *                          additional detail about this exception.
     * @see java.lang.Throwable#getMessage
     */
    public ConfigurationException(String explanation) {
        super(explanation);
    }

    /**
      * Constructs a new instance of ConfigurationException with
      * all name resolution fields and explanation initialized to null.
      */
    public ConfigurationException() {
        super();
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = -2535156726228855704L;
}
