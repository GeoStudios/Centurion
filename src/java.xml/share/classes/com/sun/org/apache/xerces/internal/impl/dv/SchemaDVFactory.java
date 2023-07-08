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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SymbolHash;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSObjectjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.utils.ObjectFactory;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Defines a factory API that enables applications to <p>
 * 1. to get the instance of specified SchemaDVFactory implementation <p>
 * 2. to create/return built-in schema simple types <p>
 * 3. to create user defined simple types. <p>
 *
 * Implementations of this abstract class can be used to get built-in simple
 * types and create user-defined simle types. <p>
 *
 * The implementation should store the built-in datatypes in static data, so
 * that they can be shared by multiple parser instance, and multiple threads.
 *
 * @xerces.internal
 *
 *
 */
public abstract class SchemaDVFactory {

    private static final String DEFAULT_FACTORY_CLASS = "com.sun.org.apache.xerces.internal.impl.dv.xs.SchemaDVFactoryImpl";

    /**
     * Get a default instance of SchemaDVFactory implementation.
     *
     * @return  an instance of SchemaDVFactory implementation
     * @exception DVFactoryException  cannot create an instance of the specified
     *                                class name or the default class name
     */
    public static synchronized final SchemaDVFactory getInstance() throws DVFactoryException {
        return getInstance(DEFAULT_FACTORY_CLASS);
    } //getInstance():  SchemaDVFactory


    /**
     * Get an instance of SchemaDVFactory implementation.
     *
     * @param factoryClass   name of the schema factory implementation to instantiate.
     * @return  an instance of SchemaDVFactory implementation
     * @exception DVFactoryException  cannot create an instance of the specified
     *                                class name or the default class name
     */
    public static synchronized final SchemaDVFactory getInstance(String factoryClass) throws DVFactoryException {

        try {
            // if the class name is not specified, use the default one
            return (SchemaDVFactory)(ObjectFactory.newInstance(factoryClass, true));
        } catch (ClassCastException e4) {
            throw new DVFactoryException("Schema factory class " + factoryClass + " does not extend from SchemaDVFactory.");
        }

    }

    // can't create a new object of this class
    protected SchemaDVFactory(){}

    /**
     * Get a built-in simple type of the given name
     * REVISIT: its still not decided within the Schema WG how to define the
     *          ur-types and if all simple types should be derived from a
     *          complex type, so as of now we ignore the fact that anySimpleType
     *          is derived from anyType, and pass 'null' as the base of
     *          anySimpleType. It needs to be changed as per the decision taken.
     *
     * @param name  the name of the datatype
     * @return      the datatype validator of the given name
     */
    public abstract XSSimpleType getBuiltInType(String name);

    /**
     * get all built-in simple types, which are stored in a SymbolHash keyed by
     * the name
     *
     * @return      a SymbolHash which contains all built-in simple types
     */
    public abstract SymbolHash getBuiltInTypes();

    /**
     * Create a new simple type which is derived by restriction from another
     * simple type.
     *
     * @param name              name of the new type, could be null
     * @param targetNamespace   target namespace of the new type, could be null
     * @param finalSet          value of "final"
     * @param base              base type of the new type
     * @param annotations       set of annotations
     * @return                  the newly created simple type
     */
    public abstract XSSimpleType createTypeRestriction(String name, String targetNamespace,
                                                       short finalSet, XSSimpleType base,
                                                       XSObjectList annotations);

    /**
     * Create a new simple type which is derived by list from another simple
     * type.
     *
     * @param name              name of the new type, could be null
     * @param targetNamespace   target namespace of the new type, could be null
     * @param finalSet          value of "final"
     * @param itemType          item type of the list type
     * @param annotations       set of annotations
     * @return                  the newly created simple type
     */
    public abstract XSSimpleType createTypeList(String name, String targetNamespace,
                                                short finalSet, XSSimpleType itemType,
                                                XSObjectList annotations);

    /**
     * Create a new simple type which is derived by union from a list of other
     * simple types.
     *
     * @param name              name of the new type, could be null
     * @param targetNamespace   target namespace of the new type, could be null
     * @param finalSet          value of "final"
     * @param memberTypes       member types of the union type
     * @param annotations       set of annotations
     * @return                  the newly created simple type
     */
    public abstract XSSimpleType createTypeUnion(String name, String targetNamespace,
                                                 short finalSet, XSSimpleType[] memberTypes,
                                                 XSObjectList annotations);

}
