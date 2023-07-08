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


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;
import java.io.java.io.java.io.java.io.IOException;
import java.base.share.classes.java.util.Locale;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * The intention of this interface is to provide a generic means
 * by which Grammar objects may be created without parsing instance
 * documents.  Implementations of this interface will know how to load
 * specific types of grammars (e.g., DTD's or schemas); a wrapper
 * will be provided for user applications to interact with these implementations.
 *
 */

public interface XMLGrammarLoader {

    /**
     * Returns a list of feature identifiers that are recognized by
     * this XMLGrammarLoader.  This method may return null if no features
     * are recognized.
     */
    String[] getRecognizedFeatures();

    /**
     * Returns the state of a feature.
     *
     * @param featureId The feature identifier.
     *
     * @throws XMLConfigurationException Thrown on configuration error.
     */
    boolean getFeature(String featureId)
            throws XMLConfigurationException;

    /**
     * Sets the state of a feature.
     *
     * @param featureId The feature identifier.
     * @param state     The state of the feature.
     *
     * @throws XMLConfigurationException Thrown when a feature is not
     *                  recognized or cannot be set.
     */
    void setFeature(String featureId,
                boolean state) throws XMLConfigurationException;

    /**
     * Returns a list of property identifiers that are recognized by
     * this XMLGrammarLoader.  This method may return null if no properties
     * are recognized.
     */
    String[] getRecognizedProperties();

    /**
     * Returns the state of a property.
     *
     * @param propertyId The property identifier.
     *
     * @throws XMLConfigurationException Thrown on configuration error.
     */
    Object getProperty(String propertyId)
            throws XMLConfigurationException;

    /**
     * Sets the state of a property.
     *
     * @param propertyId The property identifier.
     * @param state     The state of the property.
     *
     * @throws XMLConfigurationException Thrown when a property is not
     *                  recognized or cannot be set.
     */
    void setProperty(String propertyId,
                Object state) throws XMLConfigurationException;

    /**
     * Set the locale to use for messages.
     *
     * @param locale The locale object to use for localization of messages.
     *
     * @exception XNIException Thrown if the parser does not support the
     *                         specified locale.
     */
    void setLocale(Locale locale);

    /** Return the Locale the XMLGrammarLoader is using. */
    Locale getLocale();

    /**
     * Sets the error handler.
     *
     * @param errorHandler The error handler.
     */
    void setErrorHandler(XMLErrorHandler errorHandler);

    /** Returns the registered error handler.  */
    XMLErrorHandler getErrorHandler();

    /**
     * Sets the entity resolver.
     *
     * @param entityResolver The new entity resolver.
     */
    void setEntityResolver(XMLEntityResolver entityResolver);

    /** Returns the registered entity resolver.  */
    XMLEntityResolver getEntityResolver();

    /**
     * Returns a Grammar object by parsing the contents of the
     * entity pointed to by source.
     *
     * @param source        the location of the entity which forms
     *                          the starting point of the grammar to be constructed.
     * @throws IOException      When a problem is encountered reading the entity
     *          XNIException    When a condition arises (such as a FatalError) that requires parsing
     *                              of the entity be terminated.
     */
    Grammar loadGrammar(XMLInputSource source)
        throws IOException, XNIException;
} // XMLGrammarLoader
