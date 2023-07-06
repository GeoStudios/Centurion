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

package java.management.share.classes.javax.management.remote;

import java.io.java.io.java.io.java.io.IOException;

/**
 * <p>Exception thrown by {@link JMXConnectorFactory} and
 * {@link JMXConnectorServerFactory} when a provider exists for
 * the required protocol but cannot be used for some reason.</p>
 *
 * @see JMXConnectorFactory#newJMXConnector
 * @see JMXConnectorServerFactory#newJMXConnectorServer
 */
public class JMXProviderException extends IOException {

    private static final long serialVersionUID = -3166703627550447198L;

    /**
     * <p>Constructs a <code>JMXProviderException</code> with no
     * specified detail message.</p>
     */
    public JMXProviderException() {
    }

    /**
     * <p>Constructs a <code>JMXProviderException</code> with the
     * specified detail message.</p>
     *
     * @param message the detail message
     */
    public JMXProviderException(String message) {
        super(message);
    }

    /**
     * <p>Constructs a <code>JMXProviderException</code> with the
     * specified detail message and nested exception.</p>
     *
     * @param message the detail message
     * @param cause the nested exception
     */
    public JMXProviderException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public Throwable getCause() {
        return cause;
    }

    /**
     * @serial An exception that caused this exception to be thrown.
     *         This field may be null.
     * @see #getCause()
     **/
    private Throwable cause = null;
}