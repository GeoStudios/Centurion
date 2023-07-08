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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl;


import java.io.CharConversionException;
import java.io.EOFException;
import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLString;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.xml.share.classes.com.sun.xml.internal.stream.Entity.ScannedEntity;















/**
 * This class scans the version of the document to determine
 * which scanner to use: XML 1.1 or XML 1.0.
 * The version is scanned using XML 1.1. scanner.
 *
 * @xerces.internal
 *
 */
public class XMLVersionDetector {

    //
    // Constants
    //

    private final static char[] XML11_VERSION = new char[]{'1', '.', '1'};


    // property identifiers

    /** Property identifier: symbol table. */
    protected static final String SYMBOL_TABLE =
        Constants.XERCES_PROPERTY_PREFIX + Constants.SYMBOL_TABLE_PROPERTY;

    /** Property identifier: error reporter. */
    protected static final String ERROR_REPORTER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_REPORTER_PROPERTY;

    /** Property identifier: entity manager. */
    protected static final String ENTITY_MANAGER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.ENTITY_MANAGER_PROPERTY;

    //
    // Data
    //

    /** Symbol: "version". */
    protected final static String fVersionSymbol = "version";

    // symbol:  [xml]:
    protected static final String fXMLSymbol = "[xml]";

    /** Symbol table. */
    protected SymbolTable fSymbolTable;

    /** Error reporter. */
    protected XMLErrorReporter fErrorReporter;

    /** Entity manager. */
    protected XMLEntityManager fEntityManager;

    protected String fEncoding = null;

    private final XMLString fVersionNum = new XMLString();

    private final char [] fExpectedVersionString = {'<', '?', 'x', 'm', 'l', ' ', 'v', 'e', 'r', 's',
                    'i', 'o', 'n', '=', ' ', ' ', ' ', ' ', ' '};

    /**
     *
     *
     * @param componentManager The component manager.
     *
     * @throws SAXException Throws exception if required features and
     *                      properties cannot be found.
     */
    public void reset(XMLComponentManager componentManager)
        throws XMLConfigurationException {

        // Xerces properties
        fSymbolTable = (SymbolTable)componentManager.getProperty(SYMBOL_TABLE);
        fErrorReporter = (XMLErrorReporter)componentManager.getProperty(ERROR_REPORTER);
        fEntityManager = (XMLEntityManager)componentManager.getProperty(ENTITY_MANAGER);
        for(int i=14; i<fExpectedVersionString.length; i++ )
            fExpectedVersionString[i] = ' ';
    } // reset(XMLComponentManager)

    /**
     * Reset the reference to the appropriate scanner given the version of the
     * document and start document scanning.
     * @param scanner - the scanner to use
     * @param version - the version of the document (XML 1.1 or XML 1.0).
     */
    public void startDocumentParsing(XMLEntityHandler scanner, short version){

        if (version == Constants.XML_VERSION_1_0){
            fEntityManager.setScannerVersion(Constants.XML_VERSION_1_0);
        }
        else {
            fEntityManager.setScannerVersion(Constants.XML_VERSION_1_1);
        }
        // Make sure the locator used by the error reporter is the current entity scanner.
        fErrorReporter.setDocumentLocator(fEntityManager.getEntityScanner());

        // Note: above we reset fEntityScanner in the entity manager, thus in startEntity
        // in each scanner fEntityScanner field must be reset to reflect the change.
        //
        fEntityManager.setEntityHandler(scanner);

        scanner.startEntity(fXMLSymbol, fEntityManager.getCurrentResourceIdentifier(), fEncoding, null);
    }


    /**
     * This methods scans the XML declaration to find out the version
     * (and provisional encoding)  of the document.
     * The scanning is doing using XML 1.1 scanner.
     * @param inputSource
     * @return short - Constants.XML_VERSION_1_1 if document version 1.1,
     *                  otherwise Constants.XML_VERSION_1_0
     * @throws IOException
     */
    public short determineDocVersion(XMLInputSource inputSource) throws IOException {
        fEncoding = fEntityManager.setupCurrentEntity(false, fXMLSymbol, inputSource, false, true);

        // Must use XML 1.0 scanner to handle whitespace correctly
        // in the XML declaration.
        fEntityManager.setScannerVersion(Constants.XML_VERSION_1_0);
        XMLEntityScanner scanner = fEntityManager.getEntityScanner();
        scanner.detectingVersion = true;
        try {
            if (!scanner.skipString("<?xml")) {
                // definitely not a well-formed 1.1 doc!
                scanner.detectingVersion = false;
                return Constants.XML_VERSION_1_0;
            }
            if (!scanner.skipDeclSpaces()) {
                fixupCurrentEntity(fEntityManager, fExpectedVersionString, 5);
                scanner.detectingVersion = false;
                return Constants.XML_VERSION_1_0;
            }
            if (!scanner.skipString("version")) {
                fixupCurrentEntity(fEntityManager, fExpectedVersionString, 6);
                scanner.detectingVersion = false;
                return Constants.XML_VERSION_1_0;
            }
            scanner.skipDeclSpaces();
            // Check if the next character is '='. If it is then consume it.
            if (scanner.peekChar() != '=') {
                fixupCurrentEntity(fEntityManager, fExpectedVersionString, 13);
                scanner.detectingVersion = false;
                return Constants.XML_VERSION_1_0;
            }
            scanner.scanChar(null);
            scanner.skipDeclSpaces();
            int quoteChar = scanner.scanChar(null);
            fExpectedVersionString[14] = (char) quoteChar;
            for (int versionPos = 0; versionPos < XML11_VERSION.length; versionPos++) {
                fExpectedVersionString[15 + versionPos] = (char) scanner.scanChar(null);
            }
            // REVISIT:  should we check whether this equals quoteChar?
            fExpectedVersionString[18] = (char) scanner.scanChar(null);
            fixupCurrentEntity(fEntityManager, fExpectedVersionString, 19);
            int matched = 0;
            for (; matched < XML11_VERSION.length; matched++) {
                if (fExpectedVersionString[15 + matched] != XML11_VERSION[matched])
                    break;
            }
            scanner.detectingVersion = false;
            if (matched == XML11_VERSION.length)
                return Constants.XML_VERSION_1_1;
            return Constants.XML_VERSION_1_0;
            // premature end of file
        }
        // encoding errors
        catch (MalformedByteSequenceException e) {
            fErrorReporter.reportError(e.getDomain(), e.getKey(),
                    e.getArguments(), XMLErrorReporter.SEVERITY_FATAL_ERROR, e);
            scanner.detectingVersion = false;
            return Constants.XML_VERSION_1_0;
        } catch (CharConversionException e) {
            fErrorReporter.reportError(
                    XMLMessageFormatter.XML_DOMAIN,
                    "CharConversionFailure",
                     null,
                     XMLErrorReporter.SEVERITY_FATAL_ERROR, e);
            scanner.detectingVersion = false;
            return Constants.XML_VERSION_1_0;
        } catch (EOFException e) {
            fErrorReporter.reportError(
                XMLMessageFormatter.XML_DOMAIN,
                "PrematureEOF",
                null,
                XMLErrorReporter.SEVERITY_FATAL_ERROR);
            scanner.detectingVersion = false;
            return Constants.XML_VERSION_1_0;
        }
    }

    // This method prepends "length" chars from the char array,
    // from offset 0, to the manager's fCurrentEntity.ch.
    private void fixupCurrentEntity(XMLEntityManager manager,
                char [] scannedChars, int length) {
        ScannedEntity currentEntity = manager.getCurrentEntity();
        if(currentEntity.count-currentEntity.position+length > currentEntity.ch.length) {
            //resize array; this case is hard to imagine...
            char[] tempCh = currentEntity.ch;
            currentEntity.ch = new char[length+currentEntity.count-currentEntity.position+1];
            System.arraycopy(tempCh, 0, currentEntity.ch, 0, tempCh.length);
        }
        if(currentEntity.position < length) {
            // have to move sensitive stuff out of the way...
            System.arraycopy(currentEntity.ch, currentEntity.position, currentEntity.ch, length, currentEntity.count-currentEntity.position);
            currentEntity.count += length-currentEntity.position;
        } else {
            // have to reintroduce some whitespace so this parses:
            for(int i=length; i<currentEntity.position; i++)
                currentEntity.ch[i]=' ';
        }
        // prepend contents...
        System.arraycopy(scannedChars, 0, currentEntity.ch, 0, length);
        currentEntity.position = 0;
        currentEntity.baseCharOffset = 0;
        currentEntity.startPosition = 0;
        currentEntity.columnNumber = currentEntity.lineNumber = 1;
    }

} // class XMLVersionDetector
