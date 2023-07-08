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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dtd;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.Constants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.XML11DTDScannerImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.XMLDTDScannerImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.XML11Char;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class extends XMLDTDProcessor by giving it
 * the ability to parse XML 1.1 documents correctly.  It can also be used
 * as a DTD loader, so that XML 1.1 external subsets can
 * be processed correctly (hence it's rather anomalous-appearing
 * derivation from XMLDTDLoader).
 *
 * @xerces.internal
 *
 *
 */
public class XML11DTDProcessor extends XMLDTDLoader{

    // constructors

    public XML11DTDProcessor() {
        super();
    } // <init>()

    public XML11DTDProcessor(SymbolTable symbolTable) {
        super(symbolTable);
    } // init(SymbolTable)

    public XML11DTDProcessor(SymbolTable symbolTable,
                XMLGrammarPool grammarPool) {
        super(symbolTable, grammarPool);
    } // init(SymbolTable, XMLGrammarPool)

    XML11DTDProcessor(SymbolTable symbolTable,
                XMLGrammarPool grammarPool, XMLErrorReporter errorReporter,
                XMLEntityResolver entityResolver) {
        super(symbolTable, grammarPool, errorReporter, entityResolver);
    } // init(SymbolTable, XMLGrammarPool, XMLErrorReporter, XMLEntityResolver)

    // overridden methods

    protected boolean isValidNmtoken(String nmtoken) {
        return XML11Char.isXML11ValidNmtoken(nmtoken);
    } // isValidNmtoken(String):  boolean

    protected boolean isValidName(String name) {
        return XML11Char.isXML11ValidName(name);
    } // isValidNmtoken(String):  boolean

    protected XMLDTDScannerImpl createDTDScanner(SymbolTable symbolTable,
            XMLErrorReporter errorReporter, XMLEntityManager entityManager) {
        return new XML11DTDScannerImpl(symbolTable, errorReporter, entityManager);
    } // createDTDScanner(SymbolTable, XMLErrorReporter, XMLEntityManager) : XMLDTDScannerImpl

    protected short getScannerVersion() {
        return Constants.XML_VERSION_1_1;
    } // getScannerVersion() : short

} // class XML11DTDProcessor
