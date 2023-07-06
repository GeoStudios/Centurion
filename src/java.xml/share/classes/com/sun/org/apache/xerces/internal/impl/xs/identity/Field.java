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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.identity;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xpath.XPathException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.util.Shortjava.util.ListImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.XMLChar;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.Shortjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSComplexTypeDefinition;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSConstants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Schema identity constraint field.
 *
 * @xerces.internal
 *
 */
public class Field {

    //
    // Data
    //

    /** Field XPath. */
    protected final Field.XPath fXPath;

    /** Identity constraint. */
    protected final IdentityConstraint fIdentityConstraint;

    //
    // Constructors
    //

    /** Constructs a field. */
    public Field(Field.XPath xpath,
                 IdentityConstraint identityConstraint) {
        fXPath = xpath;
        fIdentityConstraint = identityConstraint;
    } // <init>(Field.XPath,IdentityConstraint)

    //
    // Public methods
    //

    /** Returns the field XPath. */
    public com.sun.org.apache.xerces.internal.impl.xpath.XPath getXPath() {
        return fXPath;
    } // getXPath():org.apache.xerces.impl.v1.schema.identity.XPath

    /** Returns the identity constraint. */
    public IdentityConstraint getIdentityConstraint() {
        return fIdentityConstraint;
    } // getIdentityConstraint():IdentityConstraint

    // factory method

    /** Creates a field matcher. */
    public XPathMatcher createMatcher(ValueStore store) {
        return new Field.Matcher(fXPath, store);
    } // createMatcher(ValueStore):XPathMatcher

    //
    // Object methods
    //

    /** Returns a string representation of this object. */
    public String toString() {
        return fXPath.toString();
    } // toString():String

    //
    // Classes
    //

    /**
     * Field XPath.
     *
     */
    public static class XPath
        extends com.sun.org.apache.xerces.internal.impl.xpath.XPath {

        //
        // Constructors
        //

        /** Constructs a field XPath expression. */
        public XPath(String xpath,
                     SymbolTable symbolTable,
                     NamespaceContext context) throws XPathException {
            super(fixupXPath(xpath), symbolTable, context);

            // verify that only one attribute is selected per branch
            for (int i=0;i<fLocationPaths.length;i++) {
                for(int j=0; j<fLocationPaths[i].steps.length; j++) {
                    com.sun.org.apache.xerces.internal.impl.xpath.XPath.Axis axis =
                        fLocationPaths[i].steps[j].axis;
                    if (axis.type == XPath.Axis.ATTRIBUTE &&
                            (j < fLocationPaths[i].steps.length-1)) {
                        throw new XPathException("c-fields-xpaths");
                    }
                }
            }
        } // <init>(String,SymbolTable,NamespacesContext)

        /** Fixup XPath expression. Avoid creating a new String if possible. */
        private static String fixupXPath(String xpath) {

            final int end = xpath.length();
            int offset = 0;
            boolean whitespace = true;
            char c;

            // NOTE: We have to prefix the field XPath with "./" in
            //       order to handle selectors such as "@attr" that
            //       select the attribute because the fields could be
            //       relative to the selector element. -Ac
            //       Unless xpath starts with a descendant node -Achille Fokoue
            //      ... or a / or a . - NG
            for (; offset < end; ++offset) {
                c = xpath.charAt(offset);
                if (whitespace) {
                    if (!XMLChar.isSpace(c)) {
                        if (c == '.' || c == '/') {
                            whitespace = false;
                        }
                        else if (c != '|') {
                            return fixupXPath2(xpath, offset, end);
                        }
                    }
                }
                else if (c == '|') {
                    whitespace = true;
                }
            }
            return xpath;

        } // fixupXPath(String):String

        private static String fixupXPath2(String xpath, int offset, final int end) {

            StringBuffer buffer = new StringBuffer(end + 2);
            for (int i = 0; i < offset; ++i) {
                buffer.append(xpath.charAt(i));
            }
            buffer.append("./");

            boolean whitespace = false;
            char c;

            for (; offset < end; ++offset) {
                c = xpath.charAt(offset);
                if (whitespace) {
                    if (!XMLChar.isSpace(c)) {
                        if (c == '.' || c == '/') {
                            whitespace = false;
                        }
                        else if (c != '|') {
                            buffer.append("./");
                            whitespace = false;
                        }
                    }
                }
                else if (c == '|') {
                    whitespace = true;
                }
                buffer.append(c);
            }
            return buffer.toString();

        } // fixupXPath2(String, int, int):String

    } // class XPath

    /**
     * Field matcher.
     *
     */
    protected class Matcher
        extends XPathMatcher {

        //
        // Data
        //

        /** Value store for data values. */
        protected final ValueStore fStore;

        /** A flag indicating whether the field is allowed to match a value. */
        protected boolean fMayMatch = true;

        //
        // Constructors
        //

        /** Constructs a field matcher. */
        public Matcher(Field.XPath xpath, ValueStore store) {
            super(xpath);
            fStore = store;
        } // <init>(Field.XPath,ValueStore)

        //
        // XPathHandler methods
        //

        /**
         * This method is called when the XPath handler matches the
         * XPath expression.
         */
        protected void matched(Object actualValue, short valueType, ShortList itemValueType, boolean isNil) {
            super.matched(actualValue, valueType, itemValueType, isNil);
            if(isNil && (fIdentityConstraint.getCategory() == IdentityConstraint.IC_KEY)) {
                String code = "KeyMatchesNillable";
                fStore.reportError(code,
                    new Object[]{fIdentityConstraint.getElementName(), fIdentityConstraint.getIdentityConstraintName()});
            }
            fStore.addValue(Field.this, fMayMatch, actualValue, convertToPrimitiveKind(valueType), convertToPrimitiveKind(itemValueType));
            // once we've stored the value for this field, we set the mayMatch
            // member to false so that in the same scope, we don't match any more
            // values (and throw an error instead).
            fMayMatch = false;
        } // matched(String)

        private short convertToPrimitiveKind(short valueType) {
            /** Primitive datatypes. */
            if (valueType <= XSConstants.NOTATION_DT) {
                return valueType;
            }
            /** Types derived from string. */
            if (valueType <= XSConstants.ENTITY_DT) {
                return XSConstants.STRING_DT;
            }
            /** Types derived from decimal. */
            if (valueType <= XSConstants.POSITIVEINTEGER_DT) {
                return XSConstants.DECIMAL_DT;
            }
            /** Other types. */
            return valueType;
        }

        private ShortList convertToPrimitiveKind(ShortList itemValueType) {
            if (itemValueType != null) {
                int i;
                final int length = itemValueType.getLength();
                for (i = 0; i < length; ++i) {
                    short type = itemValueType.item(i);
                    if (type != convertToPrimitiveKind(type)) {
                        break;
                    }
                }
                if (i != length) {
                    final short [] arr = new short[length];
                    for (int j = 0; j < i; ++j) {
                        arr[j] = itemValueType.item(j);
                    }
                    for(; i < length; ++i) {
                        arr[i] = convertToPrimitiveKind(itemValueType.item(i));
                    }
                    return new ShortListImpl(arr, arr.length);
                }
            }
            return itemValueType;
        }

        protected void handleContent(XSTypeDefinition type, boolean nillable, Object actualValue, short valueType, ShortList itemValueType) {
            if (type == null ||
               type.getTypeCategory() == XSTypeDefinition.COMPLEX_TYPE &&
               ((XSComplexTypeDefinition) type).getContentType()
                != XSComplexTypeDefinition.CONTENTTYPE_SIMPLE) {

                    // the content must be simpleType content
                    fStore.reportError( "cvc-id.3", new Object[] {
                            fIdentityConstraint.getName(),
                            fIdentityConstraint.getElementName()});

            }
            fMatchedString = actualValue;
            matched(fMatchedString, valueType, itemValueType, nillable);
        } // handleContent(XSElementDecl, String)

    } // class Matcher

} // class Field