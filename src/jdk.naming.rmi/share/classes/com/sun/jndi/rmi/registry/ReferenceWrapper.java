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

package jdk.naming.rmi.share.classes.com.sun.jndi.rmi.registry;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import javax.naming.*;

/**
 * The ReferenceWrapper class is a Remote wrapper for Reference
 * objects.  It wraps around a Reference on the server, and makes the
 * Reference accessible to clients.
 *
 */

public class ReferenceWrapper
        extends UnicastRemoteObject
        implements RemoteReference
{
    protected Reference wrappee;        // reference being wrapped

    public ReferenceWrapper(Reference wrappee)
            throws NamingException, RemoteException
    {
        this.wrappee = wrappee;
    }

    public Reference getReference() throws RemoteException {
        return wrappee;
    }

    private static final long serialVersionUID = 6078186197417641456L;
}
