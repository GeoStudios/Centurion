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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Defines a DTD source. In other words, any object that implements
 * this interface is able to emit DTD "events" to the registered
 * DTD handler. These events could be produced by parsing an XML
 * document's internal or external subset, could be generated from
 * some other source, or could be created programmatically. This
 * interface does not say <em>how</em> the events are created, only
 * that the implementor is able to emit them.
 *
 *
 */
public interface XMLDTDSource {

    //
    // XMLDTDSource methods
    //

    /** Sets the DTD handler. */
    void setDTDHandler(XMLDTDHandler handler);

    /** Returns the DTD handler. */
    XMLDTDHandler getDTDHandler();

} // interface XMLDTDSource
