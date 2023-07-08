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

package java.rmi.share.classes.sun.rmi.server;


import java.io.*;
import java.rmi.Remote;
import java.rmi.server.RemoteStub;
import java.rmi.share.classes.sun.rmi.transport.ObjectTable;
import java.rmi.share.classes.sun.rmi.transport.Target;















/**
 * A MarshalOutputStream extends ObjectOutputStream to add functions
 * specific to marshaling of remote object references. If it is
 * necessary to serialize remote objects or objects that contain
 * references to remote objects a MarshalOutputStream must be used
 * instead of ObjectOutputStream. <p>
 *
 * A new MarshalOutputStream is constructed to serialize remote
 * objects or graphs containing remote objects. Objects are written to
 * the stream using the ObjectOutputStream.writeObject method. <p>
 *
 * MarshalOutputStream maps remote objects to the corresponding remote
 * stub and embeds the location from which to load the stub
 * classes. The location may be ignored by the client but is supplied.
 */
public class MarshalOutputStream extends ObjectOutputStream
{
    /**
     * Creates a marshal output stream with protocol version 1.
     */
    public MarshalOutputStream(OutputStream out) throws IOException {
        this(out, ObjectStreamConstants.PROTOCOL_VERSION_1);
    }

    /**
     * Creates a marshal output stream with the given protocol version.
     */
    @SuppressWarnings("removal")
    public MarshalOutputStream(OutputStream out, int protocolVersion)
        throws IOException
    {
        super(out);
        this.useProtocolVersion(protocolVersion);
        java.security.AccessController.doPrivileged(
            new java.security.PrivilegedAction<Void>() {
                public Void run() {
                enableReplaceObject(true);
                return null;
            }
        });
    }

    /**
     * Checks for objects that are instances of java.rmi.Remote
     * that need to be serialized as proxy objects.
     */
    @SuppressWarnings("deprecation")
    protected final Object replaceObject(Object obj) throws IOException {
        if ((obj instanceof Remote) && !(obj instanceof RemoteStub)) {
            Target target = ObjectTable.getTarget((Remote) obj);
            if (target != null) {
                return target.getStub();
            }
        }
        return obj;
    }

    /**
     * Serializes a location from which to load the specified class.
     */
    protected void annotateClass(Class<?> cl) throws IOException {
        writeLocation(java.rmi.server.RMIClassLoader.getClassAnnotation(cl));
    }

    /**
     * Serializes a location from which to load the specified class.
     */
    protected void annotateProxyClass(Class<?> cl) throws IOException {
        annotateClass(cl);
    }

    /**
     * Writes the location for the class into the stream.  This method can
     * be overridden by subclasses that store this annotation somewhere
     * else than as the next object in the stream, as is done by this class.
     */
    protected void writeLocation(String location) throws IOException {
        writeObject(location);
    }
}
