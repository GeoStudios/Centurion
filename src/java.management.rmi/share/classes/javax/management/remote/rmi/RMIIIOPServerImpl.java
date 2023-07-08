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

package java.management.rmi.share.classes.javax.management.remote.rmi;

import java.io.java.io.java.io.java.io.IOException;
import java.rmi.Remote;
import java.util.Map;
import java.management.rmi.share.classes.javax.security.auth.Subject;

/**
 * <p>An {@link RMIServerImpl} that is exported through IIOP and that
 * creates client connections as RMI objects exported through IIOP.
 * User code does not usually reference this class directly.</p>
 *
 * @see RMIServerImpl
 *
 * @deprecated This transport is no longer supported.
 */
@Deprecated
public class RMIIIOPServerImpl extends RMIServerImpl {
    /**
     * Throws {@linkplain UnsupportedOperationException}
     *
     * @param env the environment containing attributes for the new
     * <code>RMIServerImpl</code>.  Can be null, which is equivalent
     * to an empty Map.
     *
     * @throws IOException if the RMI object cannot be created.
     */
    public RMIIIOPServerImpl(Map<String,?> env)
            throws IOException {
        super(env);

        throw new UnsupportedOperationException();
    }

    @Override
    protected void export() throws IOException {
        throw new UnsupportedOperationException("Method not supported. JMX RMI-IIOP is deprecated");
    }

    @Override
    protected String getProtocol() {
        return "iiop";
    }

    @Override
    public Remote toStub() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected RMIConnection makeClient(String connectionId, Subject subject)
            throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void closeClient(RMIConnection client) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void closeServer() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    RMIConnection doNewClient(final Object credentials) throws IOException {
        throw new UnsupportedOperationException();
    }
}
