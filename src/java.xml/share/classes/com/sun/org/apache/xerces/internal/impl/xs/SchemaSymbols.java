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
package com.sun.org.apache.xerces.internal.impl.xs;


/**
 * Collection of symbols used to parse a Schema Grammar.
 *
 * @xerces.internal
 *
 */
public final class SchemaSymbols {

    // strings that's not added to the schema symbol table, because they
    // are not symbols in the schema document.
    // the validator can choose to add them by itself.

    // the following strings (xsi:, xsd) will be added into the
    // symbol table that comes with the parser

    // xsi attributes: in validator
    public static final String URI_XSI                        = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String XSI_SCHEMALOCATION            = "schemaLocation";
    public static final String XSI_NONAMESPACESCHEMALOCATION = "noNamespaceSchemaLocation";
    public static final String XSI_TYPE                       = "type";
    public static final String XSI_NIL                        = "nil";

    // schema namespace
    public static final String URI_SCHEMAFORSCHEMA            = "http://www.w3.org/2001/XMLSchema";

    // all possible schema element names
    public static final String ELT_ALL                  = "all";
    public static final String ELT_ANNOTATION           = "annotation";
    public static final String ELT_ANY                  = "any";
    public static final String ELT_ANYATTRIBUTE         = "anyAttribute";
    public static final String ELT_APPINFO              = "appinfo";
    public static final String ELT_ATTRIBUTE            = "attribute";
    public static final String ELT_ATTRIBUTEGROUP       = "attributeGroup";
    public static final String ELT_CHOICE               = "choice";
    public static final String ELT_COMPLEXCONTENT       = "complexContent";
    public static final String ELT_COMPLEXTYPE          = "complexType";
    public static final String ELT_DOCUMENTATION        = "documentation";
    public static final String ELT_ELEMENT              = "element";
    public static final String ELT_ENUMERATION          = "enumeration";
    public static final String ELT_EXTENSION            = "extension";
    public static final String ELT_FIELD                = "field";
    public static final String ELT_FRACTIONDIGITS       = "fractionDigits";
    public static final String ELT_GROUP                = "group";
    public static final String ELT_IMPORT               = "import";
    public static final String ELT_INCLUDE              = "include";
    public static final String ELT_KEY                  = "key";
    public static final String ELT_KEYREF               = "keyref";
    public static final String ELT_LENGTH               = "length";
    public static final String ELT_LIST                 = "list";
    public static final String ELT_MAXEXCLUSIVE         = "maxExclusive";
    public static final String ELT_MAXINCLUSIVE         = "maxInclusive";
    public static final String ELT_MAXLENGTH            = "maxLength";
    public static final String ELT_MINEXCLUSIVE         = "minExclusive";
    public static final String ELT_MININCLUSIVE         = "minInclusive";
    public static final String ELT_MINLENGTH            = "minLength";
    public static final String ELT_NOTATION             = "notation";
    public static final String ELT_PATTERN              = "pattern";
    public static final String ELT_REDEFINE             = "redefine";
    public static final String ELT_RESTRICTION          = "restriction";
    public static final String ELT_SCHEMA               = "schema";
    public static final String ELT_SELECTOR             = "selector";
    public static final String ELT_SEQUENCE             = "sequence";
    public static final String ELT_SIMPLECONTENT        = "simpleContent";
    public static final String ELT_SIMPLETYPE           = "simpleType";
    public static final String ELT_TOTALDIGITS          = "totalDigits";
    public static final String ELT_UNION                = "union";
    public static final String ELT_UNIQUE               = "unique";
    public static final String ELT_WHITESPACE           = "whiteSpace";

    // all possible schema attribute names (and xml:lang defined on <schema> and <documentation>)
    public static final String ATT_ABSTRACT             = "abstract";
    public static final String ATT_ATTRIBUTEFORMDEFAULT = "attributeFormDefault";
    public static final String ATT_BASE                 = "base";
    public static final String ATT_BLOCK                = "block";
    public static final String ATT_BLOCKDEFAULT         = "blockDefault";
    public static final String ATT_DEFAULT              = "default";
    public static final String ATT_ELEMENTFORMDEFAULT   = "elementFormDefault";
    public static final String ATT_FINAL                = "final";
    public static final String ATT_FINALDEFAULT         = "finalDefault";
    public static final String ATT_FIXED                = "fixed";
    public static final String ATT_FORM                 = "form";
    public static final String ATT_ID                   = "id";
    public static final String ATT_ITEMTYPE             = "itemType";
    public static final String ATT_MAXOCCURS            = "maxOccurs";
    public static final String ATT_MEMBERTYPES          = "memberTypes";
    public static final String ATT_MINOCCURS            = "minOccurs";
    public static final String ATT_MIXED                = "mixed";
    public static final String ATT_NAME                 = "name";
    public static final String ATT_NAMESPACE            = "namespace";
    public static final String ATT_NILLABLE             = "nillable";
    public static final String ATT_PROCESSCONTENTS      = "processContents";
    public static final String ATT_REF                  = "ref";
    public static final String ATT_REFER                = "refer";
    public static final String ATT_SCHEMALOCATION       = "schemaLocation";
    public static final String ATT_SOURCE               = "source";
    public static final String ATT_SUBSTITUTIONGROUP    = "substitutionGroup";
    public static final String ATT_SYSTEM               = "system";
    public static final String ATT_PUBLIC               = "public";
    public static final String ATT_TARGETNAMESPACE      = "targetNamespace";
    public static final String ATT_TYPE                 = "type";
    public static final String ATT_USE                  = "use";
    public static final String ATT_VALUE                = "value";
    public static final String ATT_VERSION              = "version";
    public static final String ATT_XML_LANG             = "xml:lang";
    public static final String ATT_XPATH                = "xpath";

    // all possible schema attribute values
    public static final String ATTVAL_TWOPOUNDANY       = "##any";
    public static final String ATTVAL_TWOPOUNDLOCAL     = "##local";
    public static final String ATTVAL_TWOPOUNDOTHER     = "##other";
    public static final String ATTVAL_TWOPOUNDTARGETNS  = "##targetNamespace";
    public static final String ATTVAL_POUNDALL          = "#all";
    public static final String ATTVAL_FALSE_0           = "0";
    public static final String ATTVAL_TRUE_1            = "1";
    public static final String ATTVAL_ANYSIMPLETYPE     = "anySimpleType";
    public static final String ATTVAL_ANYTYPE           = "anyType";
    public static final String ATTVAL_ANYURI            = "anyURI";
    public static final String ATTVAL_BASE64BINARY      = "base64Binary";
    public static final String ATTVAL_BOOLEAN           = "boolean";
    public static final String ATTVAL_BYTE              = "byte";
    public static final String ATTVAL_COLLAPSE          = "collapse";
    public static final String ATTVAL_DATE              = "date";
    public static final String ATTVAL_DATETIME          = "dateTime";
    public static final String ATTVAL_DAY               = "gDay";
    public static final String ATTVAL_DECIMAL           = "decimal";
    public static final String ATTVAL_DOUBLE            = "double";
    public static final String ATTVAL_DURATION          = "duration";
    public static final String ATTVAL_ENTITY            = "ENTITY";
    public static final String ATTVAL_ENTITIES          = "ENTITIES";
    public static final String ATTVAL_EXTENSION         = "extension";
    public static final String ATTVAL_FALSE             = "false";
    public static final String ATTVAL_FLOAT             = "float";
    public static final String ATTVAL_HEXBINARY         = "hexBinary";
    public static final String ATTVAL_ID                = "ID";
    public static final String ATTVAL_IDREF             = "IDREF";
    public static final String ATTVAL_IDREFS            = "IDREFS";
    public static final String ATTVAL_INT               = "int";
    public static final String ATTVAL_INTEGER           = "integer";
    public static final String ATTVAL_LANGUAGE          = "language";
    public static final String ATTVAL_LAX               = "lax";
    public static final String ATTVAL_LIST              = "list";
    public static final String ATTVAL_LONG              = "long";
    public static final String ATTVAL_NAME              = "Name";
    public static final String ATTVAL_NEGATIVEINTEGER   = "negativeInteger";
    public static final String ATTVAL_MONTH             = "gMonth";
    public static final String ATTVAL_MONTHDAY          = "gMonthDay";
    public static final String ATTVAL_NCNAME            = "NCName";
    public static final String ATTVAL_NMTOKEN           = "NMTOKEN";
    public static final String ATTVAL_NMTOKENS          = "NMTOKENS";
    public static final String ATTVAL_NONNEGATIVEINTEGER= "nonNegativeInteger";
    public static final String ATTVAL_NONPOSITIVEINTEGER= "nonPositiveInteger";
    public static final String ATTVAL_NORMALIZEDSTRING  = "normalizedString";
    public static final String ATTVAL_NOTATION          = "NOTATION";
    public static final String ATTVAL_OPTIONAL          = "optional";
    public static final String ATTVAL_POSITIVEINTEGER   = "positiveInteger";
    public static final String ATTVAL_PRESERVE          = "preserve";
    public static final String ATTVAL_PROHIBITED        = "prohibited";
    public static final String ATTVAL_QNAME             = "QName";
    public static final String ATTVAL_QUALIFIED         = "qualified";
    public static final String ATTVAL_REPLACE           = "replace";
    public static final String ATTVAL_REQUIRED          = "required";
    public static final String ATTVAL_RESTRICTION       = "restriction";
    public static final String ATTVAL_SHORT             = "short";
    public static final String ATTVAL_SKIP              = "skip";
    public static final String ATTVAL_STRICT            = "strict";
    public static final String ATTVAL_STRING            = "string";
    public static final String ATTVAL_SUBSTITUTION      = "substitution";
    public static final String ATTVAL_TIME              = "time";
    public static final String ATTVAL_TOKEN             = "token";
    public static final String ATTVAL_TRUE              = "true";
    public static final String ATTVAL_UNBOUNDED         = "unbounded";
    public static final String ATTVAL_UNION             = "union";
    public static final String ATTVAL_UNQUALIFIED       = "unqualified";
    public static final String ATTVAL_UNSIGNEDBYTE      = "unsignedByte";
    public static final String ATTVAL_UNSIGNEDINT       = "unsignedInt";
    public static final String ATTVAL_UNSIGNEDLONG      = "unsignedLong";
    public static final String ATTVAL_UNSIGNEDSHORT     = "unsignedShort";
    public static final String ATTVAL_YEAR              = "gYear";
    public static final String ATTVAL_YEARMONTH         = "gYearMonth";

    // form qualified/unqualified
    public static final short FORM_UNQUALIFIED = 0;
    public static final short FORM_QUALIFIED   = 1;

    // attribute use
    public static final short USE_OPTIONAL   = 0;
    public static final short USE_REQUIRED   = 1;
    public static final short USE_PROHIBITED = 2;

    // maxOccurs = "unbounded"
    public static final int OCCURRENCE_UNBOUNDED = -1;

}
