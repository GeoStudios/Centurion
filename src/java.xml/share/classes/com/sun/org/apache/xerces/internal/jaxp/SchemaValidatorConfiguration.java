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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.Constants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.FeatureState;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.PropertyState;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <p>Parser configuration for Xerces' XMLSchemaValidator.</p>
 *
 */
final class SchemaValidatorConfiguration implements XMLComponentManager {

    // feature identifiers

    /** Feature identifier: schema validation. */
    private static final String SCHEMA_VALIDATION =
        Constants.XERCES_FEATURE_PREFIX + Constants.SCHEMA_VALIDATION_FEATURE;

    /** Feature identifier: validation. */
    private static final String VALIDATION =
        Constants.SAX_FEATURE_PREFIX + Constants.VALIDATION_FEATURE;

    /** Feature identifier: use grammar pool only. */
    private static final String USE_GRAMMAR_POOL_ONLY =
        Constants.XERCES_FEATURE_PREFIX + Constants.USE_GRAMMAR_POOL_ONLY_FEATURE;

    /** Feature identifier: parser settings. */
    private static final String PARSER_SETTINGS =
        Constants.XERCES_FEATURE_PREFIX + Constants.PARSER_SETTINGS;

    // property identifiers

    /** Property identifier: error reporter. */
    private static final String ERROR_REPORTER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_REPORTER_PROPERTY;

    /** Property identifier: validation manager. */
    private static final String VALIDATION_MANAGER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.VALIDATION_MANAGER_PROPERTY;

    /** Property identifier: grammar pool. */
    private static final String XMLGRAMMAR_POOL =
        Constants.XERCES_PROPERTY_PREFIX + Constants.XMLGRAMMAR_POOL_PROPERTY;

    //
    // Data
    //

    /** Parent component manager. **/
    private final XMLComponentManager fParentComponentManager;

    /** The Schema's grammar pool. **/
    private final XMLGrammarPool fGrammarPool;

    /**
     * Tracks whether the validator should use components from
     * the grammar pool to the exclusion of all others.
     */
    private final boolean fUseGrammarPoolOnly;

    /** Validation manager. */
    private final ValidationManager fValidationManager;

    public SchemaValidatorConfiguration(XMLComponentManager parentManager,
            XSGrammarPoolContainer grammarContainer, ValidationManager validationManager) {
        fParentComponentManager = parentManager;
        fGrammarPool = grammarContainer.getGrammarPool();
        fUseGrammarPoolOnly = grammarContainer.isFullyComposed();
        fValidationManager = validationManager;
        // add schema message formatter to error reporter
        try {
            XMLErrorReporter errorReporter = (XMLErrorReporter) fParentComponentManager.getProperty(ERROR_REPORTER);
            if (errorReporter != null) {
                errorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
            }
        }
        // Ignore exception.
        catch (XMLConfigurationException exc) {}
    }

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
    public boolean getFeature(String featureId)
            throws XMLConfigurationException {
        FeatureState state = getFeatureState(featureId);
        if (state.isExceptional()) {
            throw new XMLConfigurationException(state.status, featureId);
        }
        return state.state;
    }

    public FeatureState getFeatureState(String featureId) {
        if (PARSER_SETTINGS.equals(featureId)) {
            return fParentComponentManager.getFeatureState(featureId);
        }
        else if (VALIDATION.equals(featureId) || SCHEMA_VALIDATION.equals(featureId)) {
            return FeatureState.is(true);
        }
        else if (USE_GRAMMAR_POOL_ONLY.equals(featureId)) {
            return FeatureState.is(fUseGrammarPoolOnly);
        }
        return fParentComponentManager.getFeatureState(featureId);
    }

    public PropertyState getPropertyState(String propertyId) {
        if (XMLGRAMMAR_POOL.equals(propertyId)) {
            return PropertyState.is(fGrammarPool);
        }
        else if (VALIDATION_MANAGER.equals(propertyId)) {
            return PropertyState.is(fValidationManager);
        }
        return fParentComponentManager.getPropertyState(propertyId);
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
    public Object getProperty(String propertyId)
            throws XMLConfigurationException {
        PropertyState state = getPropertyState(propertyId);
        if (state.isExceptional()) {
            throw new XMLConfigurationException(state.status, propertyId);
        }
        return state.state;
    }

    public boolean getFeature(String featureId, boolean defaultValue) {
        FeatureState state = getFeatureState(featureId);
        if (state.isExceptional()) {
            return defaultValue;
        }
        return state.state;
    }

    public Object getProperty(String propertyId, Object defaultValue) {
        PropertyState state = getPropertyState(propertyId);
        if (state.isExceptional()) {
            return defaultValue;
        }
        return state.state;
    }

}