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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.utils;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * $Id: MsgKey.java,v 1.1.4.1 2005/09/08 11:03:11 suresh_emailid Exp $
 */


/**
 * This class is not a public API,
 * It is used internally by serializer and is public,
 * in the Java sense, only because its use crosses
 * package boundaries.
 * <p>
 * This class holds only the message keys used
 * when generating messages.
 */
public class MsgKey {

    /** An internal error with the messages,
     * this is the message to use if the message key can't be found
     */
    public static final String BAD_MSGKEY = "BAD_MSGKEY";

    /**
     * An internal error with the messages,
     * this is the message to use if the message format operation failed.
     */
    public static final String BAD_MSGFORMAT = "BAD_MSGFORMAT";

    public static final String ER_RESOURCE_COULD_NOT_FIND =
        "ER_RESOURCE_COULD_NOT_FIND";
    public static final String ER_RESOURCE_COULD_NOT_LOAD =
        "ER_RESOURCE_COULD_NOT_LOAD";
    public static final String ER_BUFFER_SIZE_LESSTHAN_ZERO =
        "ER_BUFFER_SIZE_LESSTHAN_ZERO";
    public static final String ER_INVALID_UTF16_SURROGATE =
        "ER_INVALID_UTF16_SURROGATE";
    public static final String ER_OIERROR = "ER_OIERROR";
    public static final String ER_NAMESPACE_PREFIX = "ER_NAMESPACE_PREFIX";
    public static final String ER_STRAY_ATTRIBUTE = "ER_STRAY_ATTRIBUTE";
    public static final String ER_STRAY_NAMESPACE = "ER_STRAY_NAMESPACE";
    public static final String ER_COULD_NOT_LOAD_RESOURCE =
        "ER_COULD_NOT_LOAD_RESOURCE";
    public static final String ER_COULD_NOT_LOAD_METHOD_PROPERTY =
        "ER_COULD_NOT_LOAD_METHOD_PROPERTY";
    public static final String ER_SERIALIZER_NOT_CONTENTHANDLER =
        "ER_SERIALIZER_NOT_CONTENTHANDLER";
    public static final String ER_ILLEGAL_ATTRIBUTE_POSITION =
        "ER_ILLEGAL_ATTRIBUTE_POSITION";
    public static final String ER_ILLEGAL_CHARACTER = "ER_ILLEGAL_CHARACTER";

    public static final String ER_INVALID_PORT = "ER_INVALID_PORT";
    public static final String ER_PORT_WHEN_HOST_NULL =
        "ER_PORT_WHEN_HOST_NULL";
    public static final String ER_HOST_ADDRESS_NOT_WELLFORMED =
        "ER_HOST_ADDRESS_NOT_WELLFORMED";
    public static final String ER_SCHEME_NOT_CONFORMANT =
        "ER_SCHEME_NOT_CONFORMANT";
    public static final String ER_SCHEME_FROM_NULL_STRING =
        "ER_SCHEME_FROM_NULL_STRING";
    public static final String ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE =
        "ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE";
    public static final String ER_PATH_INVALID_CHAR = "ER_PATH_INVALID_CHAR";
    public static final String ER_NO_SCHEME_INURI = "ER_NO_SCHEME_INURI";
    public static final String ER_FRAG_INVALID_CHAR = "ER_FRAG_INVALID_CHAR";
    public static final String ER_FRAG_WHEN_PATH_NULL =
        "ER_FRAG_WHEN_PATH_NULL";
    public static final String ER_FRAG_FOR_GENERIC_URI =
        "ER_FRAG_FOR_GENERIC_URI";
    public static final String ER_NO_SCHEME_IN_URI = "ER_NO_SCHEME_IN_URI";
    public static final String ER_CANNOT_INIT_URI_EMPTY_PARMS =
        "ER_CANNOT_INIT_URI_EMPTY_PARMS";
    public static final String ER_NO_FRAGMENT_STRING_IN_PATH =
        "ER_NO_FRAGMENT_STRING_IN_PATH";
    public static final String ER_NO_QUERY_STRING_IN_PATH =
        "ER_NO_QUERY_STRING_IN_PATH";
    public static final String ER_NO_PORT_IF_NO_HOST = "ER_NO_PORT_IF_NO_HOST";
    public static final String ER_NO_USERINFO_IF_NO_HOST =
        "ER_NO_USERINFO_IF_NO_HOST";
    public static final String ER_SCHEME_REQUIRED = "ER_SCHEME_REQUIRED";
    public static final String ER_XML_VERSION_NOT_SUPPORTED = "ER_XML_VERSION_NOT_SUPPORTED";
    public static final String ER_FACTORY_PROPERTY_MISSING = "ER_FACTORY_PROPERTY_MISSING";
    public static final String ER_ENCODING_NOT_SUPPORTED = "ER_ENCODING_NOT_SUPPORTED";

    // DOM Exceptions
    public static final String ER_FEATURE_NOT_FOUND = "FEATURE_NOT_FOUND";
    public static final String ER_FEATURE_NOT_SUPPORTED = "FEATURE_NOT_SUPPORTED";
    public static final String ER_STRING_TOO_LONG = "DOMSTRING_SIZE_ERR";
    public static final String ER_TYPE_MISMATCH_ERR = "TYPE_MISMATCH_ERR";

    // DOM Level 3 load and save messages
    public static final String ER_NO_OUTPUT_SPECIFIED = "no-output-specified";
    public static final String ER_UNSUPPORTED_ENCODING = "unsupported-encoding";
    public static final String ER_ELEM_UNBOUND_PREFIX_IN_ENTREF = "unbound-prefix-in-entity-reference";
    public static final String ER_ATTR_UNBOUND_PREFIX_IN_ENTREF = "unbound-prefix-in-entity-reference";
    public static final String ER_CDATA_SECTIONS_SPLIT = "cdata-sections-splitted";
    public static final String ER_WF_INVALID_CHARACTER = "wf-invalid-character";
    public static final String ER_WF_INVALID_CHARACTER_IN_NODE_NAME = "wf-invalid-character-in-node-name";

    // DOM Level 3 Implementation specific Exceptions
    public static final String ER_UNABLE_TO_SERIALIZE_NODE = "ER_UNABLE_TO_SERIALIZE_NODE";
    public static final String ER_WARNING_WF_NOT_CHECKED = "ER_WARNING_WF_NOT_CHECKED";

    public static final String ER_WF_INVALID_CHARACTER_IN_COMMENT = "ER_WF_INVALID_CHARACTER_IN_COMMENT";
    public static final String ER_WF_INVALID_CHARACTER_IN_PI = "ER_WF_INVALID_CHARACTER_IN_PI";
    public static final String ER_WF_INVALID_CHARACTER_IN_CDATA = "ER_WF_INVALID_CHARACTER_IN_CDATA";
    public static final String ER_WF_INVALID_CHARACTER_IN_TEXT = "ER_WF_INVALID_CHARACTER_IN_TEXT";
    public static final String ER_WF_DASH_IN_COMMENT = "ER_WF_DASH_IN_COMMENT";
    public static final String ER_WF_LT_IN_ATTVAL = "ER_WF_LT_IN_ATTVAL";
    public static final String ER_WF_REF_TO_UNPARSED_ENT = "ER_WF_REF_TO_UNPARSED_ENT";
    public static final String ER_WF_REF_TO_EXTERNAL_ENT =  "ER_WF_REF_TO_EXTERNAL_ENT";
    public static final String ER_NS_PREFIX_CANNOT_BE_BOUND =  "ER_NS_PREFIX_CANNOT_BE_BOUND";
    public static final String ER_NULL_LOCAL_ELEMENT_NAME = "ER_NULL_LOCAL_ELEMENT_NAME";
    public static final String ER_NULL_LOCAL_ATTR_NAME = "ER_NULL_LOCAL_ATTR_NAME";
    public static final String ER_WRITING_INTERNAL_SUBSET = "ER_WRITING_INTERNAL_SUBSET";

}
