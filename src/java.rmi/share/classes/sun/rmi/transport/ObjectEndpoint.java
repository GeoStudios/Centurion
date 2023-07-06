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

package java.rmi.share.classes.sun.rmi.transport;


import java.rmi.server.ObjID;















/**
 * An object used as a key to the object table that maps an
 * instance of this class to a Target.
 *
 **/
class ObjectEndpoint {

    private final ObjID id;
    private final Transport transport;

    /**
     * Constructs a new ObjectEndpoint instance with the specified id and
     * transport.  The specified id must be non-null, and the specified
     * transport must either be non-null or the specified id must be
     * equivalent to an ObjID constructed with ObjID.DGC_ID.
     *
     * @param id the object identifier
     * @param transport the transport
     * @throws NullPointerException if id is null
     **/
    ObjectEndpoint(ObjID id, Transport transport) {
        if (id == null) {
            throw new NullPointerException();
        }
        assert transport != null || id.equals(new ObjID(ObjID.DGC_ID));

        this.id = id;
        this.transport = transport;
    }

    /**
     * Compares the specified object with this object endpoint for
     * equality.
     *
     * This method returns true if and only if the specified object is an
     * ObjectEndpoint instance with the same object identifier and
     * transport as this object.
     **/
    public boolean equals(Object obj) {
        if (obj instanceof ObjectEndpoint oe) {
            return id.equals(oe.id) && transport == oe.transport;
        } else {
            return false;
        }
    }

    /**
     * Returns the hash code value for this object endpoint.
     */
    public int hashCode() {
        return id.hashCode() ^ (transport != null ? transport.hashCode() : 0);
    }

    /**
     * Returns a string representation for this object endpoint.
     */
    public String toString() {
        return id.toString();
    }
}
