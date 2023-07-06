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


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * <p>A container for grammar pools which only contain schema grammars.</p>
 *
 */
public interface XSGrammarPoolContainer {

    /**
     * <p>Returns the grammar pool contained inside the container.</p>
     *
     * @return the grammar pool contained inside the container
     */
    XMLGrammarPool getGrammarPool();

    /**
     * <p>Returns whether the schema components contained in this object
     * can be considered to be a fully composed schema and should be
     * used to the exclusion of other schema components which may be
     * present elsewhere.</p>
     *
     * @return whether the schema components contained in this object
     * can be considered to be a fully composed schema
     */
    boolean isFullyComposed();

    /**
     * Returns the initial value of a feature for validators created
     * using this grammar pool container or null if the validators
     * should use the default value.
     */
    Boolean getFeature(String featureId);

    /*
     * Set a feature on the schema
     */
    void setFeature(String featureId, boolean state);

    /**
     * Returns the initial value of a property for validators created
     * using this grammar pool container or null if the validators
     * should use the default value.
     */
    Object getProperty(String propertyId);

    /*
     * Set a property on the schema
     */
    void setProperty(String propertyId, Object state);

}
