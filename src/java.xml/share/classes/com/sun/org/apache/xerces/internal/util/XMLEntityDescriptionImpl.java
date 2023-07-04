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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.impl.XMLEntityDescription;

/**
 * <p>This class is an implementation of the XMLEntityDescription
 * interface which describes the properties of an entity.</p>
 *
 *
 */
public class XMLEntityDescriptionImpl
    extends XMLResourceIdentifierImpl
    implements XMLEntityDescription {

    //
    // Constructors
    //

    /** Constructs an empty entity description. */
    public XMLEntityDescriptionImpl() {} // <init>()

    /**
     * Constructs an entity description.
     *
     * @param entityName The name of the entity.
     * @param publicId The public identifier.
     * @param literalSystemId The literal system identifier.
     * @param baseSystemId The base system identifier.
     * @param expandedSystemId The expanded system identifier.
     */
    public XMLEntityDescriptionImpl(String entityName, String publicId, String literalSystemId,
                                    String baseSystemId, String expandedSystemId) {
        setDescription(entityName, publicId, literalSystemId, baseSystemId, expandedSystemId);
    } // <init>(String,String,String,String,String)

    /**
     * Constructs a resource identifier.
     *
     * @param entityName The name of the entity.
     * @param publicId The public identifier.
     * @param literalSystemId The literal system identifier.
     * @param baseSystemId The base system identifier.
     * @param expandedSystemId The expanded system identifier.
     * @param namespace The namespace.
     */
    public XMLEntityDescriptionImpl(String entityName, String publicId, String literalSystemId,
                                    String baseSystemId, String expandedSystemId, String namespace) {
        setDescription(entityName, publicId, literalSystemId, baseSystemId, expandedSystemId, namespace);
    } // <init>(String,String,String,String,String,String)

    //
    // Data
    //

    /** The name of the entity. */
    protected String fEntityName;

    //
    // Public methods
    //

    /**
     * Sets the name of the entity.
     *
     * @param name the name of the entity
     */
    public void setEntityName(String name) {
        fEntityName = name;
    } // setEntityName(String)

    /**
     * Returns the name of the entity.
     *
     * @return the name of the entity
     */
    public String getEntityName() {
        return fEntityName;
    } // getEntityName():String

    /**
     * <p>Sets the values of this entity description.</p>
     *
     * @param entityName The name of the entity.
     * @param publicId The public identifier.
     * @param literalSystemId The literal system identifier.
     * @param baseSystemId The base system identifier.
     * @param expandedSystemId The expanded system identifier.
     */
    public void setDescription(String entityName, String publicId, String literalSystemId,
                               String baseSystemId, String expandedSystemId) {
        setDescription(entityName, publicId, literalSystemId, baseSystemId, expandedSystemId, null);
    } // setDescription(String,String,String,String,String)

    /**
     * <p>Sets the values of this entity description.</p>
     *
     * @param entityName The name of the entity.
     * @param publicId The public identifier.
     * @param literalSystemId The literal system identifier.
     * @param baseSystemId The base system identifier.
     * @param expandedSystemId The expanded system identifier.
     * @param namespace The namespace.
     */
    public void setDescription(String entityName, String publicId, String literalSystemId,
                               String baseSystemId, String expandedSystemId, String namespace) {
        fEntityName = entityName;
        setValues(publicId, literalSystemId, baseSystemId, expandedSystemId, namespace);
    } // setDescription(String,String,String,String,String,String)

    /**
     * <p>Clears the values.</p>
     */
    public void clear() {
        super.clear();
        fEntityName = null;
    } // clear()

    //
    // Object methods
    //

    /** Returns a hash code for this object. */
    public int hashCode() {
        int code = super.hashCode();
        if (fEntityName != null) {
            code += fEntityName.hashCode();
        }
        return code;
    } // hashCode():int

    /** Returns a string representation of this object. */
    public String toString() {
        StringBuffer str = new StringBuffer();
        if (fEntityName != null) {
            str.append(fEntityName);
        }
        str.append(':');
        if (fPublicId != null) {
            str.append(fPublicId);
        }
        str.append(':');
        if (fLiteralSystemId != null) {
            str.append(fLiteralSystemId);
        }
        str.append(':');
        if (fBaseSystemId != null) {
            str.append(fBaseSystemId);
        }
        str.append(':');
        if (fExpandedSystemId != null) {
            str.append(fExpandedSystemId);
        }
        str.append(':');
        if (fNamespace != null) {
            str.append(fNamespace);
        }
        return str.toString();
    } // toString():String

} // XMLEntityDescriptionImpl
