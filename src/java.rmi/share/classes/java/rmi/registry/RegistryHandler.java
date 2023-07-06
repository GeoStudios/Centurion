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

package java.rmi.share.classes.java.rmi.registry;


import java.rmi.share.classes.java.rmi.RemoteException;
import java.rmi.share.classes.java.rmi.UnknownHostException;















/**
 * <code>RegistryHandler</code> is an interface used internally by the RMI
 * runtime in previous implementation versions.  It should never be accessed
 * by application code.
 *
 * @deprecated no replacement
 */
@Deprecated
public interface RegistryHandler {

    /**
     * Returns a "stub" for contacting a remote registry
     * on the specified host and port.
     *
     * @deprecated no replacement.  As of the Java 2 platform v1.2, RMI no
     * longer uses the <code>RegistryHandler</code> to obtain the registry's
     * stub.
     * @param host name of remote registry host
     * @param port remote registry port
     * @return remote registry stub
     * @throws RemoteException if a remote error occurs
     * @throws UnknownHostException if unable to resolve given hostname
     */
    @Deprecated
    Registry registryStub(String host, int port)
        throws RemoteException, UnknownHostException;

    /**
     * Constructs and exports a Registry on the specified port.
     * The port must be non-zero.
     *
     * @deprecated no replacement.  As of the Java 2 platform v1.2, RMI no
     * longer uses the <code>RegistryHandler</code> to obtain the registry's
     * implementation.
     * @param port port to export registry on
     * @return registry stub
     * @throws RemoteException if a remote error occurs
     */
    @Deprecated
    Registry registryImpl(int port) throws RemoteException;
}
