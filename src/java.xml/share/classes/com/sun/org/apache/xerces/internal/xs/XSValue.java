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
 * Represents an actual value of a simple type.
 */
public interface XSValue {

    /**
     * The schema normalized value.
     * @return The normalized value.
     */
    String getNormalizedValue();

    /**
     * The actual value. <code>null</code> if the value is in error.
     * @return The actual value.
     */
    Object getActualValue();

    /**
     * The declared simple type definition used to validate this value.
     * It can be a union type.
     * @return The declared simple type definition
     */
    XSSimpleTypeDefinition getTypeDefinition();

    /**
     * If the declared simple type definition is a union, return the member
     * type actually used to validate the value. Otherwise null.
     * @return The member type
     */
    XSSimpleTypeDefinition getMemberTypeDefinition();

    /**
     * If <code>getTypeDefinition()</code> returns a list type whose item type
     * is a union type, then this method returns a list with the same length
     * as the value list, for simple types that actually validated
     * the corresponding item in the value.
     * @return A list of type definitions
     */
    XSObjectList getMemberTypeDefinitions();

    /**
     * The actual value built-in datatype, e.g.
     * <code>STRING_DT, SHORT_DT</code>. If the type definition of this
     * value is a list type definition, this method returns
     * <code>LIST_DT</code>. If the type definition of this value is a list
     * type definition whose item type is a union type definition, this
     * method returns <code>LISTOFUNION_DT</code>. To query the actual value
     * of the list or list of union type definitions use
     * <code>itemValueTypes()</code>.
     * @return The actual value type
     */
    short getActualValueType();

    /**
     * In the case the actual value represents a list, i.e. the
     * <code>actualNormalizedValueType</code> is <code>LIST_DT</code>, the
     * returned array consists of one type kind which represents the itemType
     * . For example:
     * <pre> &lt;simpleType name="listtype"&gt; &lt;list
     * itemType="positiveInteger"/&gt; &lt;/simpleType&gt; &lt;element
     * name="list" type="listtype"/&gt; ... &lt;list&gt;1 2 3&lt;/list&gt; </pre>
     *
     * The <code>schemaNormalizedValue</code> value is "1 2 3", the
     * <code>actualNormalizedValueType</code> value is <code>LIST_DT</code>,
     * and the <code>itemValueTypes</code> is an array of size 1 with the
     * value <code>POSITIVEINTEGER_DT</code>.
     * <br> If the actual value represents a list type definition whose item
     * type is a union type definition, i.e. <code>LISTOFUNION_DT</code>,
     * for each actual value in the list the array contains the
     * corresponding memberType kind. For example:
     * <pre> &lt;simpleType
     * name='union_type' memberTypes="integer string"/&gt; &lt;simpleType
     * name='listOfUnion'&gt; &lt;list itemType='union_type'/&gt;
     * &lt;/simpleType&gt; &lt;element name="list" type="listOfUnion"/&gt;
     * ... &lt;list&gt;1 2 foo&lt;/list&gt; </pre>
     *  The
     * <code>schemaNormalizedValue</code> value is "1 2 foo", the
     * <code>actualNormalizedValueType</code> is <code>LISTOFUNION_DT</code>
     * , and the <code>itemValueTypes</code> is an array of size 3 with the
     * following values: <code>INTEGER_DT, INTEGER_DT, STRING_DT</code>.
     * @return The list value types
     */
    ShortList getListValueTypes();

}
