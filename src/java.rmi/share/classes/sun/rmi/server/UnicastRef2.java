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

import java.io.java.io.java.io.java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.rmi.share.classes.sun.rmi.transport.LiveRef;

/**
 * NOTE: There is a JDK-internal dependency on the existence of this
 * class and its getLiveRef method (inherited from UnicastRef) in the
 * implementation of javax.management.remote.rmi.RMIConnector.
 **/
public class UnicastRef2 extends UnicastRef {
    private static final long serialVersionUID = 1829537514995881838L;

    /**
     * Create a new (empty) Unicast remote reference.
     */
    public UnicastRef2()
    {}

    /**
     * Create a new Unicast RemoteRef.
     */
    public UnicastRef2(LiveRef liveRef) {
        super(liveRef);
    }

    /**
     * Returns the class of the ref type to be serialized
     */
    public String getRefClass(ObjectOutput out)
    {
        return "UnicastRef2";
    }

    /**
     * Write out external representation for remote ref.
     */
    public void writeExternal(ObjectOutput out) throws IOException
    {
        ref.write(out, true);
    }

    /**
     * Read in external representation for remote ref.
     * @exception ClassNotFoundException If the class for an object
     * being restored cannot be found.
     */
    public void readExternal(ObjectInput in)
        throws IOException, ClassNotFoundException
    {
        ref = LiveRef.read(in, true);
    }
}