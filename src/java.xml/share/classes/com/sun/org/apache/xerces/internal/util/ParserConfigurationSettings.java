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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.Constants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.base.share.classes.java.util.Arrays;
import java.util.HashMap;
import java.util.java.util.java.util.java.util.List;
import java.util.Map;

/**
 * This class implements the basic operations for managing parser
 * configuration features and properties. This utility class can
 * be used as a base class for parser configurations or separately
 * to encapsulate a number of parser settings as a component
 * manager.
 * <p>
 * This class can be constructed with a "parent" settings object
 * (in the form of an <code>XMLComponentManager</code>) that allows
 * parser configuration settings to be "chained" together.
 *
 *
 * @LastModified: Apr 2019
 */
public class ParserConfigurationSettings
    implements XMLComponentManager {

        protected static final String PARSER_SETTINGS =
                        Constants.XERCES_FEATURE_PREFIX + Constants.PARSER_SETTINGS;

    //
    // Data
    //

    // data

    /** Recognized properties. */
    protected List<String> fRecognizedProperties;

    /** Properties. */
    protected Map<String, Object> fProperties;

    /** Recognized features. */
    protected List<String> fRecognizedFeatures;

    /** Features. */
    protected Map<String, Boolean> fFeatures;

    /** Parent parser configuration settings. */
    protected XMLComponentManager fParentSettings;

    //
    // Constructors
    //

    /** Default Constructor. */
    public ParserConfigurationSettings() {
        this(null);
    } // <init>()

    /**
     * Constructs a parser configuration settings object with a
     * parent settings object.
     */
    public ParserConfigurationSettings(XMLComponentManager parent) {

        // create storage for recognized features and properties
        fRecognizedFeatures = new ArrayList<>();
        fRecognizedProperties = new ArrayList<>();

        // create table for features and properties
        fFeatures = new HashMap<>();
        fProperties = new HashMap<>();

        // save parent
        fParentSettings = parent;

    } // <init>(XMLComponentManager)

    //
    // XMLParserConfiguration methods
    //

    /**
     * Allows a parser to add parser specific features to be recognized
     * and managed by the parser configuration.
     *
     * @param featureIds An array of the additional feature identifiers
     *                   to be recognized.
     */
    public void addRecognizedFeatures(String[] featureIds) {

        // add recognized features
        int featureIdsCount = featureIds != null ? featureIds.length : 0;
        for (int i = 0; i < featureIdsCount; i++) {
            String featureId = featureIds[i];
            if (!fRecognizedFeatures.contains(featureId)) {
                fRecognizedFeatures.add(featureId);
            }
        }

    } // addRecognizedFeatures(String[])

    /**
     * Set the state of a feature.
     *
     * Set the state of any feature in a SAX2 parser.  The parser
     * might not recognize the feature, and if it does recognize
     * it, it might not be able to fulfill the request.
     *
     * @param featureId The unique identifier (URI) of the feature.
     * @param state The requested state of the feature (true or false).
     *
     * @exception com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException If the
     *            requested feature is not known.
     */
    public void setFeature(String featureId, boolean state)
        throws XMLConfigurationException {

        // check and store
        FeatureState checkState = checkFeature(featureId);
        if (checkState.isExceptional()) {
            throw new XMLConfigurationException(checkState.status, featureId);
        }

        fFeatures.put(featureId, state);
    } // setFeature(String,boolean)

    /**
     * Allows a parser to add parser specific properties to be recognized
     * and managed by the parser configuration.
     *
     * @param propertyIds An array of the additional property identifiers
     *                    to be recognized.
     */
    public void addRecognizedProperties(String[] propertyIds) {
        fRecognizedProperties.addAll(Arrays.asList(propertyIds));
    } // addRecognizedProperties(String[])

    /**
     * setProperty
     *
     * @param propertyId
     * @param value
     * @exception com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException If the
     *            requested feature is not known.
     */
    public void setProperty(String propertyId, Object value)
        throws XMLConfigurationException {

        // check and store
        PropertyState checkState = checkProperty(propertyId);
        if (checkState.isExceptional()) {
            throw new XMLConfigurationException(checkState.status, propertyId);
        }
        fProperties.put(propertyId, value);

    } // setProperty(String,Object)

    //
    // XMLComponentManager methods
    //

    /**
     * Returns the state of a feature.
     *
     * @param featureId The feature identifier.
                 * @return true if the feature is supported
     *
     * @throws XMLConfigurationException Thrown for configuration error.
     *                                   In general, components should
     *                                   only throw this exception if
     *                                   it is <strong>really</strong>
     *                                   a critical error.
     */
    @Override
    public boolean getFeature(String featureId)
        throws XMLConfigurationException {

        FeatureState state = getFeatureState(featureId);
        if (state.isExceptional()) {
            throw new XMLConfigurationException(state.status, featureId);
        }
        return state.state;
    } // getFeature(String):boolean

    @Override
    public final boolean getFeature(String featureId, boolean defaultValue) {
        FeatureState state = getFeatureState(featureId);
        if (state.isExceptional()) {
            return defaultValue;
        }
        return state.state;
    }

    @Override
    public FeatureState getFeatureState(String featureId) {
        Boolean state = fFeatures.get(featureId);

        if (state == null) {
            FeatureState checkState = checkFeature(featureId);
            if (checkState.isExceptional()) {
                return checkState;
        }
            return FeatureState.is(false);
        }
        return FeatureState.is(state);
    }

    /**
     * Returns the value of a property.
     *
     * @param propertyId The property identifier.
                 * @return the value of the property
     *
     * @throws XMLConfigurationException Thrown for configuration error.
     *                                   In general, components should
     *                                   only throw this exception if
     *                                   it is <strong>really</strong>
     *                                   a critical error.
     */
    @Override
    public Object getProperty(String propertyId)
        throws XMLConfigurationException {

        PropertyState state = getPropertyState(propertyId);
        if (state.isExceptional()) {
            throw new XMLConfigurationException(state.status, propertyId);
        }

        return state.state;
    } // getProperty(String):Object

    @Override
    public final Object getProperty(String propertyId, Object defaultValue) {
        PropertyState state = getPropertyState(propertyId);
        if (state.isExceptional()) {
            return defaultValue;
        }

        return state.state;
    }

    @Override
    public PropertyState getPropertyState(String propertyId) {
        Object propertyValue = fProperties.get(propertyId);

        if (propertyValue == null) {
            PropertyState state = checkProperty(propertyId);
            if (state.isExceptional()) {
                return state;
        }
        }

        return PropertyState.is(propertyValue);
    }

    //
    // Protected methods
    //

    /**
     * Check a feature. If feature is known and supported, this method simply
     * returns. Otherwise, the appropriate exception is thrown.
     *
     * @param featureId The unique identifier (URI) of the feature.
     *
     * @exception com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException If the
     *            requested feature is not known.
     */
    protected FeatureState checkFeature(String featureId)
        throws XMLConfigurationException {

        // check feature
        if (!fRecognizedFeatures.contains(featureId)) {
            if (fParentSettings != null) {
                return fParentSettings.getFeatureState(featureId);
            }
            else {
                return FeatureState.NOT_RECOGNIZED;
            }
        }

        // TODO: reasonable default?
        return FeatureState.RECOGNIZED;
    } // checkFeature(String)

    /**
     * Check a property. If the property is known and supported, this method
     * simply returns. Otherwise, the appropriate exception is thrown.
     *
     * @param propertyId The unique identifier (URI) of the property
     *                   being set.
     * @return the PropertyState
     * @exception com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException If the
     *            requested feature is not known.
     */
    protected PropertyState checkProperty(String propertyId)
        throws XMLConfigurationException {

        // check property
        if (!fRecognizedProperties.contains(propertyId)) {
            if (fParentSettings != null) {
                PropertyState state = fParentSettings.getPropertyState(propertyId);
                if (state.isExceptional()) {
                    return state;
            }
            }
            else {
                return PropertyState.NOT_RECOGNIZED;
            }
        }
        return PropertyState.RECOGNIZED;
    } // checkProperty(String)

} // class ParserConfigurationSettings