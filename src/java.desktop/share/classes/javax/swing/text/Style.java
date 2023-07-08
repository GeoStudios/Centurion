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

package java.desktop.share.classes.javax.swing.text;

import java.awt.Component;
import java.desktop.share.classes.javax.swing.event.Changejava.util.Listener;
import java.desktop.share.classes.javax.swing.event.ChangeEvent;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * A collection of attributes to associate with an element in a document.
 * Since these are typically used to associate character and paragraph
 * styles with the element, operations for this are provided.  Other
 * customized attributes that get associated with the element will
 * effectively be name-value pairs that live in a hierarchy and if a name
 * (key) is not found locally, the request is forwarded to the parent.
 * Commonly used attributes are separated out to facilitate alternative
 * implementations that are more efficient.
 *
 */
public interface Style extends MutableAttributeSet {

    /**
     * Fetches the name of the style.   A style is not required to be named,
     * so <code>null</code> is returned if there is no name
     * associated with the style.
     *
     * @return the name
     */
    String getName();

    /**
     * Adds a listener to track whenever an attribute
     * has been changed.
     *
     * @param l the change listener
     */
    void addChangeListener(ChangeListener l);

    /**
     * Removes a listener that was tracking attribute changes.
     *
     * @param l the change listener
     */
    void removeChangeListener(ChangeListener l);

}
