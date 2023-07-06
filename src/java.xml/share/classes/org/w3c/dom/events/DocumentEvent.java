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

package java.xml.share.classes.org.w3c.dom.events;

import java.xml.share.classes.org.w3c.dom.DOMException;

/**
 *  The <code>DocumentEvent</code> interface provides a mechanism by which the
 * user can create an Event of a type supported by the implementation. It is
 * expected that the <code>DocumentEvent</code> interface will be
 * implemented on the same object which implements the <code>Document</code>
 * interface in an implementation which supports the Event model.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Events-20001113'>Document Object Model (DOM) Level 2 Events Specification</a>.
 */
public interface DocumentEvent {
    /**
     *
     * @param eventType The <code>eventType</code> parameter specifies the
     *   type of <code>Event</code> interface to be created. If the
     *   <code>Event</code> interface specified is supported by the
     *   implementation this method will return a new <code>Event</code> of
     *   the interface type requested. If the <code>Event</code> is to be
     *   dispatched via the <code>dispatchEvent</code> method the
     *   appropriate event init method must be called after creation in
     *   order to initialize the <code>Event</code>'s values. As an example,
     *   a user wishing to synthesize some kind of <code>UIEvent</code>
     *   would call <code>createEvent</code> with the parameter "UIEvents".
     *   The <code>initUIEvent</code> method could then be called on the
     *   newly created <code>UIEvent</code> to set the specific type of
     *   UIEvent to be dispatched and set its context information.The
     *   <code>createEvent</code> method is used in creating
     *   <code>Event</code>s when it is either inconvenient or unnecessary
     *   for the user to create an <code>Event</code> themselves. In cases
     *   where the implementation provided <code>Event</code> is
     *   insufficient, users may supply their own <code>Event</code>
     *   implementations for use with the <code>dispatchEvent</code> method.
     * @return The newly created <code>Event</code>
     * @exception DOMException
     *   NOT_SUPPORTED_ERR: Raised if the implementation does not support the
     *   type of <code>Event</code> interface requested
     */
    Event createEvent(String eventType)
                             throws DOMException;

}