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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation;


import java.util.HashMap;
import java.util.Map;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;















/**
 * <p>Abstract implementation of Schema for W3C XML Schemas.</p>
 *
 * @LastModified: Oct 2017
 */
abstract class AbstractXMLSchema extends Schema implements
        XSGrammarPoolContainer {

    /**
     * Map containing the initial values of features for
     * validators created using this grammar pool container.
     */
    private final Map<String, Boolean> fFeatures;

    /**
     * Map containing the initial values of properties for
     * validators created using this grammar pool container.
     */
    private final Map<String, Object> fProperties;

    public AbstractXMLSchema() {
        fFeatures = new HashMap<>();
        fProperties = new HashMap<>();
    }

    /*
     * Schema methods
     */

    /*
     * @see javax.xml.validation.Schema#newValidator()
     */
    public final Validator newValidator() {
        return new ValidatorImpl(this);
    }

    /*
     * @see javax.xml.validation.Schema#newValidatorHandler()
     */
    public final ValidatorHandler newValidatorHandler() {
        return new ValidatorHandlerImpl(this);
    }

    /*
     * XSGrammarPoolContainer methods
     */

    /**
     * Returns the initial value of a feature for validators created
     * using this grammar pool container or null if the validators
     * should use the default value.
     */
    public final Boolean getFeature(String featureId) {
        return fFeatures.get(featureId);
    }

    /*
     * Set a feature on the schema
     */
    public final void setFeature(String featureId, boolean state) {
        fFeatures.put(featureId, state ? Boolean.TRUE : Boolean.FALSE);
    }

    /**
     * Returns the initial value of a property for validators created
     * using this grammar pool container or null if the validators
     * should use the default value.
     */
    public final Object getProperty(String propertyId) {
        return fProperties.get(propertyId);
    }

    /*
     * Set a property on the schema
     */
    public final void setProperty(String propertyId, Object state) {
        fProperties.put(propertyId, state);
    }

} // AbstractXMLSchema
