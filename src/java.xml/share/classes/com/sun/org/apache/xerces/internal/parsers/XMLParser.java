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


import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.Constants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import jdk.xml.internal.JdkConstants;
import java.xml.share.classes.com.sun.org.xml.sax.SAXNotSupportedException;
import java.xml.share.classes.com.sun.org.xml.sax.SAXNotRecognizedException;















/**
 * Base class of all XML-related parsers.
 * <p>
 * In addition to the features and properties recognized by the parser
 * configuration, this parser recognizes these additional features and
 * properties:
 * <ul>
 * <li>Properties
 *  <ul>
 *   <li>http://apache.org/xml/properties/internal/error-handler</li>
 *   <li>http://apache.org/xml/properties/internal/entity-resolver</li>
 *  </ul>
 * </ul>
 *
 * @LastModified: May 2021
 */
public abstract class XMLParser {

    //
    // Constants
    //

    // properties

    /** Property identifier: entity resolver. */
    protected static final String ENTITY_RESOLVER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.ENTITY_RESOLVER_PROPERTY;

    /** Property identifier: error handler. */
    protected static final String ERROR_HANDLER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_HANDLER_PROPERTY;

    /** Recognized properties. */
    private static final String[] RECOGNIZED_PROPERTIES = {
        ENTITY_RESOLVER,
        ERROR_HANDLER,
    };

    //
    // Data
    //

    /** The parser configuration. */
    protected XMLParserConfiguration fConfiguration;

    /** The XML Security Manager. */
    XMLSecurityManager securityManager;

    /** The XML Security Property Manager. */
    XMLSecurityPropertyManager securityPropertyManager;


    //
    // Constructors
    //

    /**
     * Query the state of a feature.
     */
    public boolean getFeature(String featureId)
            throws SAXNotSupportedException, SAXNotRecognizedException {
        return fConfiguration.getFeature(featureId);

    }

    /**
     * Default Constructor.
     */
    protected XMLParser(XMLParserConfiguration config) {

        // save configuration
        fConfiguration = config;

        // add default recognized properties
        fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);

    } // <init>(XMLParserConfiguration)

    //
    // Public methods
    //

    /**
     * parse
     *
     * @param inputSource
     *
     * @exception XNIException
     * @exception java.io.IOException
     */
    public void parse(XMLInputSource inputSource)
        throws XNIException, IOException {
        // null indicates that the parser is called directly, initialize them
        if (securityManager == null) {
            securityManager = new XMLSecurityManager(true);
            fConfiguration.setProperty(Constants.SECURITY_MANAGER, securityManager);
        }
        if (securityPropertyManager == null) {
            securityPropertyManager = new XMLSecurityPropertyManager();
            fConfiguration.setProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER, securityPropertyManager);
        }

        reset();
        fConfiguration.parse(inputSource);

    } // parse(XMLInputSource)

    //
    // Protected methods
    //

    /**
     * reset all components before parsing
     */
    protected void reset() throws XNIException {
    } // reset()

} // class XMLParser
