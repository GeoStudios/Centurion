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

package java.xml.share.classes.javax.xml.stream.util;

import java.xml.share.classes.javax.xml.stream.events.XMLEvent;
import java.xml.share.classes.javax.xml.stream.XMLStreamException;

/**
 * This interface defines an event consumer interface.  The contract of the
 * of a consumer is to accept the event.  This interface can be used to
 * mark an object as able to receive events.  Add may be called several
 * times in immediate succession so a consumer must be able to cache
 * events it hasn't processed yet.
 *
 * @version 1.0
 */
public interface XMLEventConsumer {

  /**
   * This method adds an event to the consumer. Calling this method
   * invalidates the event parameter. The client application should
   * discard all references to this event upon calling add.
   * The behavior of an application that continues to use such references
   * is undefined.
   *
   * @param event the event to add, may not be null
   * @throws XMLStreamException if there is an error in adding the event
   */
  void add(XMLEvent event)
    throws XMLStreamException;
}
