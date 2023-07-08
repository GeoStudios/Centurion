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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serialize;

import java.util.Map;

/**
 * Holds the state of the currently serialized element.
 *
 * @see BaseMarkupSerializer
 *
 * @deprecated As of JDK 9, Xerces 2.9.0, Xerces DOM L3 Serializer implementation
 * is replaced by that of Xalan. Main class
 * {@link com.sun.org.apache.xml.internal.serialize.DOMSerializerImpl} is replaced
 * by {@link com.sun.org.apache.xml.internal.serializer.dom3.LSSerializerImpl}.
 */
@Deprecated
public class ElementState
{

    /**
     * The element's raw tag name (local or prefix:local).
     */
    public String rawName;

    /**
     * The element's local tag name.
     */
    public String localName;

    /**
     * The element's namespace URI.
     */
    public String namespaceURI;

    /**
     * True if element is space preserving.
     */
    public boolean preserveSpace;

    /**
     * True if element is empty. Turns false immediately
     * after serializing the first contents of the element.
     */
    public boolean empty;

    /**
     * True if the last serialized node was an element node.
     */
    public boolean afterElement;

    /**
     * True if the last serialized node was a comment node.
     */
    public boolean afterComment;

    /**
     * True if textual content of current element should be
     * serialized as CDATA section.
     */
    public boolean doCData;

    /**
     * True if textual content of current element should be
     * serialized as raw characters (unescaped).
     */
    public boolean unescaped;

    /**
     * True while inside CData and printing text as CData.
     */
    public boolean inCData;

    /**
     * Association between namespace URIs (keys) and prefixes (values).
     */
    public Map<String, String> prefixes;

}
