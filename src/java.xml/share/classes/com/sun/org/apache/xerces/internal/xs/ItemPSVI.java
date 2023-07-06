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

/**
 *  Represents an abstract PSVI item for an element or an attribute
 * information item.
 */
public interface ItemPSVI {
    /**
     * Validity value indicating that validation has either not been performed
     * or that a strict assessment of validity could not be performed.
     */
    short VALIDITY_NOTKNOWN         = 0;
    /**
     * Validity value indicating that validation has been strictly assessed
     * and the item in question is invalid according to the rules of schema
     * validation.
     */
    short VALIDITY_INVALID          = 1;
    /**
     * Validation status indicating that schema validation has been performed
     * and the item in question is valid according to the rules of schema
     * validation.
     */
    short VALIDITY_VALID            = 2;
    /**
     * Validation status indicating that schema validation has been performed
     * and the item in question has specifically been skipped.
     */
    short VALIDATION_NONE           = 0;
    /**
     * Validation status indicating that schema validation has been performed
     * on the item in question under the rules of lax validation.
     */
    short VALIDATION_PARTIAL        = 1;
    /**
     * Validation status indicating that full schema validation has been
     * performed on the item.
     */
    short VALIDATION_FULL           = 2;

    /**
     * Returns a reference to an immutable instance with the same data
     * that this instance of <code>ItemPSVI</code> currently has.
     */
    ItemPSVI constant();

    /**
     * Returns <code>true</code> if this specific instance of
     * <code>ItemPSVI</code> is immutable, otherwise <code>false</code>.
     */
    boolean isConstant();

    /**
     * The nearest ancestor element information item with a
     * <code>[schema information]</code> property (or this element item
     * itself if it has such a property). For more information refer to
     * element validation context and attribute validation context .
     */
    String getValidationContext();

    /**
     * <code>[validity]</code>: determines the validity of the schema item
     * with respect to the validation being attempted. The value will be one
     * of the constants: <code>VALIDITY_NOTKNOWN</code>,
     * <code>VALIDITY_INVALID</code> or <code>VALIDITY_VALID</code>.
     */
    short getValidity();

    /**
     * <code>[validation attempted]</code>: determines the extent to which
     * the schema item has been validated. The value will be one of the
     * constants: <code>VALIDATION_NONE</code>,
     * <code>VALIDATION_PARTIAL</code> or <code>VALIDATION_FULL</code>.
     */
    short getValidationAttempted();

    /**
     * <code>[schema error code]</code>: a list of error codes generated from
     * the validation attempt or an empty <code>StringList</code> if no
     * errors occurred during the validation attempt.
     */
    StringList getErrorCodes();

    /**
     * A list of error messages generated from the validation attempt or
     * an empty <code>StringList</code> if no errors occurred during the
     * validation attempt. The indices of error messages in this list are
     * aligned with those in the <code>[schema error code]</code> list.
     */
    StringList getErrorMessages();

    /**
     * <code>[schema normalized value]</code>: the normalized value of this
     * item after validation.
     *
     * @deprecated Use getSchemaValue().getNormalizedValue() instead
     */
    @Deprecated
    String getSchemaNormalizedValue();

    /**
     * <code>[schema normalized value]</code>: Binding specific actual value
     * or <code>null</code> if the value is in error.
     * @exception XSException
     *   NOT_SUPPORTED_ERR: Raised if the implementation does not support this
     *   method.
     *
     * @deprecated Use getSchemaValue().getActualValue() instead
     */
    @Deprecated
    Object getActualNormalizedValue()
                                   throws XSException;

    /**
     * The actual value built-in datatype, e.g.
     * <code>STRING_DT, SHORT_DT</code>. If the type definition of this
     * value is a list type definition, this method returns
     * <code>LIST_DT</code>. If the type definition of this value is a list
     * type definition whose item type is a union type definition, this
     * method returns <code>LISTOFUNION_DT</code>. To query the actual value
     * of the list or list of union type definitions use
     * <code>itemValueTypes</code>. If the <code>actualNormalizedValue</code>
     *  is <code>null</code>, this method returns <code>UNAVAILABLE_DT</code>.
     * @exception XSException
     *   NOT_SUPPORTED_ERR: Raised if the implementation does not support this
     *   method.
     *
     *  @deprecated Use getSchemaValue().getActualValueType() instead
     */
    @Deprecated
    short getActualNormalizedValueType()
                                   throws XSException;

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
     * @exception XSException
     *   NOT_SUPPORTED_ERR: Raised if the implementation does not support this
     *   method.
     *
     *  @deprecated Use getSchemaValue().getListValueTypes() instead
     */
    @Deprecated
    ShortList getItemValueTypes()
                                   throws XSException;

    /**
     * If this item has a simple type definition or a complex type with simple
     * content, then return the value with respect to the simple type. If
     * this item doesn't have a simple-typed value, the behavior of this method
     * is not specified.
     */
    XSValue getSchemaValue();

    /**
     * <code>[type definition]</code>: an item isomorphic to the type
     * definition used to validate the schema item.
     */
    XSTypeDefinition getTypeDefinition();

    /**
     * <code>[member type definition]</code>: if and only if that type
     * definition is a simple type definition with {variety} union, or a
     * complex type definition whose {content type} is a simple type
     * definition with {variety} union, then an item isomorphic to that
     * member of the union's {member type definitions} which actually
     * validated the schema item's normalized value.
     */
    XSSimpleTypeDefinition getMemberTypeDefinition();

    /**
     * <code>[schema default]</code>: the canonical lexical representation of
     * the declaration's {value constraint} value. For more information
     * refer to element schema default and attribute schema default.
     */
    String getSchemaDefault();

    /**
     * <code>[schema specified]</code>: if true, the value was specified in
     * the schema. If false, the value comes from the infoset. For more
     * information refer to element specified and attribute specified.
     */
    boolean getIsSchemaSpecified();

}