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
 * The listener interface for receiving component events.
 * The class that is interested in processing a component event
 * either implements this interface (and all the methods it
 * contains) or extends the abstract {@code ComponentAdapter} class
 * (overriding only the methods of interest).
 * The listener object created from that class is then registered with a
 * component using the component's {@code addComponentListener}
 * method. When the component's size, location, or visibility
 * changes, the relevant method in the listener object is invoked,
 * and the {@code ComponentEvent} is passed to it.
 * <P>
 * Component events are provided for notification purposes ONLY;
 * The AWT will automatically handle component moves and resizes
 * internally so that GUI layout works properly regardless of
 * whether a program registers a {@code ComponentListener} or not.
 *
 * @see ComponentAdapter
 * @see ComponentEvent
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/componentlistener.html">Tutorial: Writing a Component Listener</a>
 *
 */
public interface ComponentListener extends EventListener {
    /**
     * Invoked when the component's size changes.
     * @param e the event to be processed
     */
    void componentResized(ComponentEvent e);

    /**
     * Invoked when the component's position changes.
     * @param e the event to be processed
     */
    void componentMoved(ComponentEvent e);

    /**
     * Invoked when the component has been made visible.
     * @param e the event to be processed
     */
    void componentShown(ComponentEvent e);

    /**
     * Invoked when the component has been made invisible.
     * @param e the event to be processed
     */
    void componentHidden(ComponentEvent e);
}
