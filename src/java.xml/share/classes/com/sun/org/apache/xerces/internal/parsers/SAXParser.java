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
import java.xml.share.classes.com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import java.xml.share.classes.com.sun.org.xml.sax.SAXNotRecognizedException;
import java.xml.share.classes.com.sun.org.xml.sax.SAXNotSupportedException;

/**
 * This is the main Xerces SAX parser class. It uses the abstract SAX
 * parser with a document scanner, a dtd scanner, and a validator, as
 * well as a grammar pool.
 *
 *
 * @LastModified: May 2021
 */
public class SAXParser
    extends AbstractSAXParser {

    //
    // Constants
    //

    // features

    /** Feature identifier: notify built-in refereces. */
    protected static final String NOTIFY_BUILTIN_REFS =
        Constants.XERCES_FEATURE_PREFIX + Constants.NOTIFY_BUILTIN_REFS_FEATURE;

    protected static final String REPORT_WHITESPACE =
            Constants.SUN_SCHEMA_FEATURE_PREFIX + Constants.SUN_REPORT_IGNORED_ELEMENT_CONTENT_WHITESPACE;

    /** Recognized features. */
    private static final String[] RECOGNIZED_FEATURES = {
        NOTIFY_BUILTIN_REFS,
        REPORT_WHITESPACE
    };

    // properties

    /** Property identifier: symbol table. */
    protected static final String SYMBOL_TABLE =
        Constants.XERCES_PROPERTY_PREFIX + Constants.SYMBOL_TABLE_PROPERTY;

    /** Property identifier: XML grammar pool. */
    protected static final String XMLGRAMMAR_POOL =
        Constants.XERCES_PROPERTY_PREFIX+Constants.XMLGRAMMAR_POOL_PROPERTY;

    /** Recognized properties. */
    private static final String[] RECOGNIZED_PROPERTIES = {
        SYMBOL_TABLE,
        XMLGRAMMAR_POOL,
    };

    //
    // Constructors
    //

    /**
     * Constructs a SAX parser using the specified parser configuration.
     */
    public SAXParser(XMLParserConfiguration config) {
        super(config);
    } // <init>(XMLParserConfiguration)

    /**
     * Constructs a SAX parser using the dtd/xml schema parser configuration.
     */
    public SAXParser() {
        this(null, null);
    } // <init>()

    /**
     * Constructs a SAX parser using the specified symbol table.
     */
    public SAXParser(SymbolTable symbolTable) {
        this(symbolTable, null);
    } // <init>(SymbolTable)

    /**
     * Constructs a SAX parser using the specified symbol table and
     * grammar pool.
     */
    public SAXParser(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        super(new XIncludeAwareParserConfiguration());

        // set features
        fConfiguration.addRecognizedFeatures(RECOGNIZED_FEATURES);
        fConfiguration.setFeature(NOTIFY_BUILTIN_REFS, true);

        // set properties
        fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            fConfiguration.setProperty(SYMBOL_TABLE, symbolTable);
        }
        if (grammarPool != null) {
            fConfiguration.setProperty(XMLGRAMMAR_POOL, grammarPool);
        }

    } // <init>(SymbolTable,XMLGrammarPool)

    /**
     * Sets the particular property in the underlying implementation of
     * org.xml.sax.XMLReader.
     */
    public void setProperty(String name, Object value)
        throws SAXNotRecognizedException, SAXNotSupportedException {
        /**
         * It's possible for users to set a security manager through the interface.
         * If it's the old SecurityManager, convert it to the new XMLSecurityManager
         */
        if (name.equals(Constants.SECURITY_MANAGER)) {
            securityManager = XMLSecurityManager.convert(value, securityManager);
            super.setProperty(Constants.SECURITY_MANAGER, securityManager);
            return;
        }
        if (name.equals(JdkConstants.XML_SECURITY_PROPERTY_MANAGER)) {
            if (value == null) {
                securityPropertyManager = new XMLSecurityPropertyManager();
            } else {
                securityPropertyManager = (XMLSecurityPropertyManager)value;
            }
            super.setProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER, securityPropertyManager);
            return;
        }

        if (securityManager == null) {
            securityManager = new XMLSecurityManager(true);
            super.setProperty(Constants.SECURITY_MANAGER, securityManager);
        }

        if (securityPropertyManager == null) {
            securityPropertyManager = new XMLSecurityPropertyManager();
            super.setProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER, securityPropertyManager);
        }

        int index = securityPropertyManager.getIndex(name);
        if (index > -1) {
            /**
             * this is a direct call to this parser, not a subclass since
             * internally the support of this property is done through
             * XMLSecurityPropertyManager
             */
            securityPropertyManager.setValue(index, XMLSecurityPropertyManager.State.APIPROPERTY, (String)value);
        } else {
            //check if the property is managed by security manager
            if (!securityManager.setLimit(name, JdkProperty.State.APIPROPERTY, value)) {
                //fall back to the default configuration to handle the property
                super.setProperty(name, value);
            }
        }
    }
} // class SAXParser