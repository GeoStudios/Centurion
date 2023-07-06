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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * <p> This interface describes basic attributes of XML grammars--their
 * physical location and their type. </p>
 *
 */
public interface XMLGrammarDescription extends XMLResourceIdentifier {

    // initial set of grammar constants that some configurations will recognize;user
    // components which create and/or recognize other types of grammars may
    // certainly use their own constants in place of these (so long as
    // their Grammar objects implement this interface).

    /**
     * The grammar type constant for XML Schema grammars. When getGrammarType()
     * method returns this constant, the object should be an instance of
     * the XMLSchemaDescription interface.
     */
    String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    /**
     * The grammar type constant for DTD grammars. When getGrammarType()
     * method returns this constant, the object should be an instance of
     * the XMLDTDDescription interface.
     */
    String XML_DTD = "http://www.w3.org/TR/REC-xml";

    /**
     * Return the type of this grammar.
     *
     * @return  the type of this grammar
     */
    String getGrammarType();

} // interface XMLGrammarDescription
