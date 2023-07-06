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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.parsers;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.Constants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * This is a concrete vanilla XML parser class. It uses the abstract parser
 * with either a BasicConfiguration object or the one specified by the
 * application.
 *
 *
 */
public class XMLDocumentParser
    extends AbstractXMLDocumentParser {

    //
    // Constructors
    //

    /**
     * Constructs a document parser using the default basic parser
     * configuration.
     */
    public XMLDocumentParser() {
        super(new XIncludeAwareParserConfiguration());
    } // <init>()

    /**
     * Constructs a document parser using the specified parser configuration.
     */
    public XMLDocumentParser(XMLParserConfiguration config) {
        super(config);
    } // <init>(ParserConfiguration)

    /**
     * Constructs a document parser using the specified symbol table.
     */
    public XMLDocumentParser(SymbolTable symbolTable) {
        super(new XIncludeAwareParserConfiguration());
        fConfiguration.setProperty(Constants.XERCES_PROPERTY_PREFIX+Constants.SYMBOL_TABLE_PROPERTY, symbolTable);
    } // <init>(SymbolTable)

    /**
     * Constructs a document parser using the specified symbol table and
     * grammar pool.
     */
    public XMLDocumentParser(SymbolTable symbolTable,
                             XMLGrammarPool grammarPool) {
        super(new XIncludeAwareParserConfiguration());
        fConfiguration.setProperty(Constants.XERCES_PROPERTY_PREFIX+Constants.SYMBOL_TABLE_PROPERTY, symbolTable);
        fConfiguration.setProperty(Constants.XERCES_PROPERTY_PREFIX+Constants.XMLGRAMMAR_POOL_PROPERTY, grammarPool);
    }

} // class XMLDocumentParser
