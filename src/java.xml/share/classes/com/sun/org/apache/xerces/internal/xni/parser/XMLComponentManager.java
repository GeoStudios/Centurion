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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.FeatureState;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.PropertyState;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * The component manager manages a parser configuration and the components
 * that make up that configuration. The manager notifies each component
 * before parsing to allow the components to initialize their state; and
 * also any time that a parser feature or property changes.
 * <p>
 * The methods of the component manager allow components to query features
 * and properties that affect the operation of the component.
 *
 * @see XMLComponent
 *
 *
 */
public interface XMLComponentManager {

    //
    // XMLComponentManager methods
    //

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
     * Returns the state of a feature.
     * Does not throw exceptions.
     *
     * @param featureId The feature identifier.
     * @param defaultValue Default value if future is not available.
     */
    boolean getFeature(String featureId, boolean defaultValue);

    /**
     * Returns the value of a property.
     *
     * @param propertyId The property identifier.
     *
    * @throws XMLConfigurationException Thrown on configuration error.
     */
    Object getProperty(String propertyId)
        throws XMLConfigurationException;

    /**
     * Returns the value of a property.
     * Does not throw exceptions.
     *
     * @param propertyId The property identifier.
     * @param defaultObject Return value if property is not available.
     *
     */
    Object getProperty(String propertyId, Object defaultObject);

    FeatureState getFeatureState(String featureId);

    PropertyState getPropertyState(String propertyId);

} // interface XMLComponentManager
