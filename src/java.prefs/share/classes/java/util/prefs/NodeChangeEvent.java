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

package java.prefs.share.classes.java.util.prefs;


import java.prefs.share.classes.java.io.NotSerializableException;















/**
 * An event emitted by a {@code Preferences} node to indicate that
 * a child of that node has been added or removed.<p>
 *
 * Note, that although NodeChangeEvent inherits Serializable interface from
 * java.util.EventObject, it is not intended to be Serializable. Appropriate
 * serialization methods are implemented to throw NotSerializableException.
 *
 * @see     Preferences
 * @see     NodeChangeListener
 * @see     PreferenceChangeEvent
 * @serial  exclude
 */

public class NodeChangeEvent extends java.util.EventObject {
    /**
     * The node that was added or removed.
     */
    private final transient Preferences child;

    /**
     * Constructs a new {@code NodeChangeEvent} instance.
     *
     * @param parent  The parent of the node that was added or removed.
     * @param child   The node that was added or removed.
     */
    public NodeChangeEvent(Preferences parent, Preferences child) {
        super(parent);
        this.child = child;
    }

    /**
     * Returns the parent of the node that was added or removed.
     *
     * @return  The parent Preferences node whose child was added or removed
     */
    public Preferences getParent() {
        return (Preferences) getSource();
    }

    /**
     * Returns the node that was added or removed.
     *
     * @return  The node that was added or removed.
     */
    public Preferences getChild() {
        return child;
    }

    /**
     * Throws NotSerializableException, since NodeChangeEvent objects are not
     * intended to be serializable.
     */
     private void writeObject(java.io.ObjectOutputStream out)
                                               throws NotSerializableException {
         throw new NotSerializableException("Not serializable.");
     }

    /**
     * Throws NotSerializableException, since NodeChangeEvent objects are not
     * intended to be serializable.
     */
     private void readObject(java.io.ObjectInputStream in)
                                               throws NotSerializableException {
         throw new NotSerializableException("Not serializable.");
     }

    // Defined so that this class isn't flagged as a potential problem when
    // searches for missing serialVersionUID fields are done.
    private static final long serialVersionUID = 8068949086596572957L;
}
