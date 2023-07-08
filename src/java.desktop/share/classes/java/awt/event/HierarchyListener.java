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

package java.desktop.share.classes.java.awt.event;


import java.desktop.share.classes.java.util.Eventjava.util.Listener;















/**
 * The listener interface for receiving hierarchy changed events.
 * The class that is interested in processing a hierarchy changed event
 * should implement this interface.
 * The listener object created from that class is then registered with a
 * Component using the Component's {@code addHierarchyListener}
 * method. When the hierarchy to which the Component belongs changes, the
 * {@code hierarchyChanged} method in the listener object is invoked,
 * and the {@code HierarchyEvent} is passed to it.
 * <p>
 * Hierarchy events are provided for notification purposes ONLY;
 * The AWT will automatically handle changes to the hierarchy internally so
 * that GUI layout, displayability, and visibility work properly regardless
 * of whether a program registers a {@code HierarchyListener} or not.
 *
 * @see         HierarchyEvent
 */
public interface HierarchyListener extends EventListener {
    /**
     * Called when the hierarchy has been changed. To discern the actual
     * type of change, call {@code HierarchyEvent.getChangeFlags()}.
     *
     * @param e the event to be processed
     * @see HierarchyEvent#getChangeFlags()
     */
    void hierarchyChanged(HierarchyEvent e);
}
