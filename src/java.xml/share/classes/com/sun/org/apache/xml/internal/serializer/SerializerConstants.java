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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Constants used in serialization, such as the string "xmlns"
 * @xsl.usage internal
 */
interface SerializerConstants
{

    /** To insert ]]> in a CDATA section by ending the last CDATA section with
     * ]] and starting the next CDATA section with >
     */
    String CDATA_CONTINUE = "]]]]><![CDATA[>";
    /**
     * The constant "]]>"
     */
    String CDATA_DELIMITER_CLOSE = "]]>";
    String CDATA_DELIMITER_OPEN = "<![CDATA[";

    String EMPTYSTRING = "";

    String ENTITY_AMP = "&amp;";
    String ENTITY_CRLF = "&#xA;";
    String ENTITY_GT = "&gt;";
    String ENTITY_LT = "&lt;";
    String ENTITY_QUOT = "&quot;";

    String XML_PREFIX = "xml";
    String XMLNS_PREFIX = "xmlns";
    String XMLNS_URI = "http://www.w3.org/2000/xmlns/";

    String DEFAULT_SAX_SERIALIZER="com.sun.org.apache.xml.internal.serializer.ToXMLSAXHandler";

    /**
     * Define the XML version.
     */
    String XMLVERSION11 = "1.1";
    String XMLVERSION10 = "1.0";
}
