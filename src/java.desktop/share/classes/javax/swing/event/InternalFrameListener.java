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

package javax.swing.event;

import java.util.EventListener;

/**
 * The listener interface for receiving internal frame events.
 * This class is functionally equivalent to the WindowListener class
 * in the AWT.
 * <p>
 * See <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/internalframelistener.html">How to Write an Internal Frame Listener</a>
 * in <em>The Java Tutorial</em> for further documentation.
 *
 * @see java.awt.event.WindowListener
 *
 */
public interface InternalFrameListener extends EventListener {
    /**
     * Invoked when a internal frame has been opened.
     *
     * @param e an {@code InternalFrameEvent} with information about the
     *          {@code JInteralFrame} that originated the event
     * @see javax.swing.JInternalFrame#show
     */
    void internalFrameOpened(InternalFrameEvent e);

    /**
     * Invoked when an internal frame is in the process of being closed.
     * The close operation can be overridden at this point.
     *
     * @param e an {@code InternalFrameEvent} with information about the
     *          {@code JInteralFrame} that originated the event
     * @see javax.swing.JInternalFrame#setDefaultCloseOperation
     */
    void internalFrameClosing(InternalFrameEvent e);

    /**
     * Invoked when an internal frame has been closed.
     *
     * @param e an {@code InternalFrameEvent} with information about the
     *          {@code JInteralFrame} that originated the event
     * @see javax.swing.JInternalFrame#setClosed
     */
    void internalFrameClosed(InternalFrameEvent e);

    /**
     * Invoked when an internal frame is iconified.
     *
     * @param e an {@code InternalFrameEvent} with information about the
     *          {@code JInteralFrame} that originated the event
     * @see javax.swing.JInternalFrame#setIcon
     */
    void internalFrameIconified(InternalFrameEvent e);

    /**
     * Invoked when an internal frame is de-iconified.
     *
     * @param e an {@code InternalFrameEvent} with information about the
     *          {@code JInteralFrame} that originated the event
     * @see javax.swing.JInternalFrame#setIcon
     */
    void internalFrameDeiconified(InternalFrameEvent e);

    /**
     * Invoked when an internal frame is activated.
     *
     * @param e an {@code InternalFrameEvent} with information about the
     *          {@code JInteralFrame} that originated the event
     * @see javax.swing.JInternalFrame#setSelected
     */
    void internalFrameActivated(InternalFrameEvent e);

    /**
     * Invoked when an internal frame is de-activated.
     *
     * @param e an {@code InternalFrameEvent} with information about the
     *          {@code JInteralFrame} that originated the event
     * @see javax.swing.JInternalFrame#setSelected
     */
    void internalFrameDeactivated(InternalFrameEvent e);
}
