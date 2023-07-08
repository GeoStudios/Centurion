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
 * This interface represents an XML Schema.
 */
public interface XSModel {
    /**
     * Convenience method. Returns a list of all namespaces that belong to
     * this schema. The value <code>null</code> is not a valid namespace
     * name, but if there are components that do not have a target namespace
     * , <code>null</code> is included in this list.
     */
    StringList getNamespaces();

    /**
     * A set of namespace schema information information items (of type
     * <code>XSNamespaceItem</code>), one for each namespace name which
     * appears as the target namespace of any schema component in the schema
     * used for that assessment, and one for absent if any schema component
     * in the schema had no target namespace. For more information see
     * schema information.
     */
    XSNamespaceItemList getNamespaceItems();

    /**
     * Returns a list of top-level components, i.e. element declarations,
     * attribute declarations, etc. Identity-constraint definitions are also
     * considered top-level.
     *
     * @param objectType The type of the declaration, i.e.
     *   <code>ELEMENT_DECLARATION</code>. Note that
     *   <code>XSTypeDefinition.SIMPLE_TYPE</code> and
     *   <code>XSTypeDefinition.COMPLEX_TYPE</code> can also be used as the
     *   <code>objectType</code> to retrieve only complex types or simple
     *   types, instead of all types.
     * @return  A list of top-level definitions of the specified type in
     *   <code>objectType</code> or an empty <code>XSNamedMap</code> if no
     *   such definitions exist.
     */
    XSNamedMap getComponents(short objectType);

    /**
     * Convenience method. Returns a list of top-level component declarations
     * that are defined within the specified namespace, i.e. element
     * declarations, attribute declarations, etc. Identity-constraint
     * definitions are also considered top-level.
     *
     * @param objectType The type of the declaration, i.e.
     *   <code>ELEMENT_DECLARATION</code>.
     * @param namespace The namespace to which the declaration belongs or
     *   <code>null</code> (for components with no target namespace).
     * @return  A list of top-level definitions of the specified type in
     *   <code>objectType</code> and defined in the specified
     *   <code>namespace</code> or an empty <code>XSNamedMap</code>.
     */
    XSNamedMap getComponentsByNamespace(short objectType,
                                               String namespace);

    /**
     *  [annotations]: a set of annotations if it exists, otherwise an empty
     * <code>XSObjectList</code>.
     */
    XSObjectList getAnnotations();

    /**
     * Convenience method. Returns a top-level element declaration.
     * @param name The name of the declaration.
     * @param namespace The namespace of the declaration, otherwise
     *   <code>null</code>.
     * @return A top-level element declaration or <code>null</code> if such a
     *   declaration does not exist.
     */
    XSElementDeclaration getElementDeclaration(String name,
                                                      String namespace);

    /**
     * Convenience method. Returns a top-level attribute declaration.
     * @param name The name of the declaration.
     * @param namespace The namespace of the declaration, otherwise
     *   <code>null</code>.
     * @return A top-level attribute declaration or <code>null</code> if such
     *   a declaration does not exist.
     */
    XSAttributeDeclaration getAttributeDeclaration(String name,
                                                          String namespace);

    /**
     * Convenience method. Returns a top-level simple or complex type
     * definition.
     * @param name The name of the definition.
     * @param namespace The namespace of the declaration, otherwise
     *   <code>null</code>.
     * @return An <code>XSTypeDefinition</code> or <code>null</code> if such
     *   a definition does not exist.
     */
    XSTypeDefinition getTypeDefinition(String name,
                                              String namespace);

    /**
     * Convenience method. Returns a top-level attribute group definition.
     * @param name The name of the definition.
     * @param namespace The namespace of the definition, otherwise
     *   <code>null</code>.
     * @return A top-level attribute group definition or <code>null</code> if
     *   such a definition does not exist.
     */
    XSAttributeGroupDefinition getAttributeGroup(String name,
                                                        String namespace);

    /**
     * Convenience method. Returns a top-level model group definition.
     * @param name The name of the definition.
     * @param namespace The namespace of the definition, otherwise
     *   <code>null</code>.
     * @return A top-level model group definition or <code>null</code> if
     *   such a definition does not exist.
     */
    XSModelGroupDefinition getModelGroupDefinition(String name,
                                                          String namespace);

    /**
     * Convenience method. Returns a top-level notation declaration.
     * @param name The name of the declaration.
     * @param namespace The namespace of the declaration, otherwise
     *   <code>null</code>.
     * @return A top-level notation declaration or <code>null</code> if such
     *   a declaration does not exist.
     */
    XSNotationDeclaration getNotationDeclaration(String name,
                                                        String namespace);

    /**
     * Convenience method. Returns an identity-constraint definition.
     * @param name The name of the definition.
     * @param namespace The namespace of the definition, otherwise
     *   <code>null</code>.
     * @return An identity-constraint definition or <code>null</code> if such
     *   a declaration does not exist.
     */
    XSIDCDefinition getIDCDefinition(String name,
                                            String namespace);

    /**
     * Convenience method. Returns a list containing the members of the
     * substitution group for the given <code>XSElementDeclaration</code>
     * or an empty <code>XSObjectList</code> if the substitution group
     * contains no members.
     * @param head The substitution group head.
     * @return A list containing the members of the substitution group
     *  for the given <code>XSElementDeclaration</code> or an empty
     *  <code>XSObjectList</code> if the substitution group contains
     *  no members.
     */
    XSObjectList getSubstitutionGroup(XSElementDeclaration head);

}
