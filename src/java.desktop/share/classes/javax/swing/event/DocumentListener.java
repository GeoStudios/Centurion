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

package java.desktop.share.classes.javax.swing.event;


import java.util.Eventjava.util.Listener;















/**
 * Interface for an observer to register to receive notifications
 * of changes to a text document.
 * <p>
 * The default implementation of
 * the Document interface (AbstractDocument) supports asynchronous
 * mutations.  If this feature is used (i.e. mutations are made
 * from a thread other than the Swing event thread), the listeners
 * will be notified via the mutating thread.  <em>This means that
 * if asynchronous updates are made, the implementation of this
 * interface must be threadsafe</em>!
 * <p>
 * The DocumentEvent notification is based upon the JavaBeans
 * event model.  There is no guarantee about the order of delivery
 * to listeners, and all listeners must be notified prior to making
 * further mutations to the Document.  <em>This means implementations
 * of the DocumentListener may not mutate the source of the event
 * (i.e. the associated Document)</em>.
 *
 * @see javax.swing.text.Document
 * @see javax.swing.text.StyledDocument
 * @see DocumentEvent
 */
public interface DocumentListener extends EventListener {

    /**
     * Gives notification that there was an insert into the document.  The
     * range given by the DocumentEvent bounds the freshly inserted region.
     *
     * @param e the document event
     */
    void insertUpdate(DocumentEvent e);

    /**
     * Gives notification that a portion of the document has been
     * removed.  The range is given in terms of what the view last
     * saw (that is, before updating sticky positions).
     *
     * @param e the document event
     */
    void removeUpdate(DocumentEvent e);

    /**
     * Gives notification that an attribute or set of attributes changed.
     *
     * @param e the document event
     */
    void changedUpdate(DocumentEvent e);
}
