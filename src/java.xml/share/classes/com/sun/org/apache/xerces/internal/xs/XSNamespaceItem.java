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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xs;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * The interface represents the namespace schema information information item.
 * Each namespace schema information information item corresponds to an XML
 * Schema with a unique namespace name.
 */
public interface XSNamespaceItem {
    /**
     * [schema namespace]: A namespace name or <code>null</code> if absent.
     */
    String getSchemaNamespace();

    /**
     * [schema components]: a list of top-level components, i.e. element
     * declarations, attribute declarations, etc. Identity-constraint
     * definitions are also considered top-level.
     *
     * @param objectType The type of the declaration, i.e.
     *   <code>ELEMENT_DECLARATION</code>. Note that
     *   <code>XSTypeDefinition.SIMPLE_TYPE</code> and
     *   <code>XSTypeDefinition.COMPLEX_TYPE</code> can also be used as the
     *   <code>objectType</code> to retrieve only complex types or simple
     *   types, instead of all types.
     * @return  A list of top-level definition of the specified type in
     *   <code>objectType</code> or an empty <code>XSNamedMap</code> if no
     *   such definitions exist.
     */
    XSNamedMap getComponents(short objectType);

    /**
     *  [annotations]: a set of annotations if it exists, otherwise an empty
     * <code>XSObjectList</code>.
     */
    XSObjectList getAnnotations();

    /**
     * Convenience method. Returns a top-level element declaration.
     * @param name The name of the declaration.
     * @return A top-level element declaration or <code>null</code> if such a
     *   declaration does not exist.
     */
    XSElementDeclaration getElementDeclaration(String name);

    /**
     * Convenience method. Returns a top-level attribute declaration.
     * @param name The name of the declaration.
     * @return A top-level attribute declaration or <code>null</code> if such
     *   a declaration does not exist.
     */
    XSAttributeDeclaration getAttributeDeclaration(String name);

    /**
     * Convenience method. Returns a top-level simple or complex type
     * definition.
     * @param name The name of the definition.
     * @return An <code>XSTypeDefinition</code> or <code>null</code> if such
     *   a definition does not exist.
     */
    XSTypeDefinition getTypeDefinition(String name);

    /**
     * Convenience method. Returns a top-level attribute group definition.
     * @param name The name of the definition.
     * @return A top-level attribute group definition or <code>null</code> if
     *   such a definition does not exist.
     */
    XSAttributeGroupDefinition getAttributeGroup(String name);

    /**
     * Convenience method. Returns a top-level model group definition.
     * @param name The name of the definition.
     * @return A top-level model group definition definition or
     *   <code>null</code> if such a definition does not exist.
     */
    XSModelGroupDefinition getModelGroupDefinition(String name);

    /**
     * Convenience method. Returns a top-level notation declaration.
     * @param name The name of the declaration.
     * @return A top-level notation declaration or <code>null</code> if such
     *   a declaration does not exist.
     */
    XSNotationDeclaration getNotationDeclaration(String name);

    /**
     * Convenience method. Returns an identity-constraint definition.
     * @param name The name of the definition.
     * @return An identity-constraint definition or <code>null</code> if such
     *   a declaration does not exist.
     */
    XSIDCDefinition getIDCDefinition(String name);

    /**
     * [document location] - a list of location URIs for the documents that
     * contributed to the <code>XSModel</code>.
     */
    StringList getDocumentLocations();

}
