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
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.XMLChar;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.Shortjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Schema identity constraint selector.
 *
 * @xerces.internal
 *
 */
public class Selector {

    //
    // Data
    //

    /** XPath. */
    protected final Selector.XPath fXPath;

    /** Identity constraint. */
    protected final IdentityConstraint fIdentityConstraint;

    // the Identity constraint we're the matcher for.  Only
    // used for selectors!
    protected IdentityConstraint fIDConstraint;

    //
    // Constructors
    //

    /** Constructs a selector. */
    public Selector(Selector.XPath xpath,
                    IdentityConstraint identityConstraint) {
        fXPath = xpath;
        fIdentityConstraint = identityConstraint;
    } // <init>(Selector.XPath,IdentityConstraint)

    //
    // Public methods
    //

    /** Returns the selector XPath. */
    public com.sun.org.apache.xerces.internal.impl.xpath.XPath getXPath() {
        return fXPath;
    } // getXPath():com.sun.org.apache.xerces.internal.v1.schema.identity.XPath

    /** Returns the identity constraint. */
    public IdentityConstraint getIDConstraint() {
        return fIdentityConstraint;
    } // getIDConstraint():IdentityConstraint

    // factory method

    /** Creates a selector matcher.
     * @param activator     The activator for this selector's fields.
     * @param initialDepth  The depth in the document at which this matcher began its life;
     *                          used in correctly handling recursive elements.
     */
    public XPathMatcher createMatcher(FieldActivator activator, int initialDepth) {
        return new Selector.Matcher(fXPath, activator, initialDepth);
    } // createMatcher(FieldActivator):XPathMatcher

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
     * Schema identity constraint selector XPath expression.
     *
     */
    public static class XPath
    extends com.sun.org.apache.xerces.internal.impl.xpath.XPath {

        //
        // Constructors
        //

        /** Constructs a selector XPath expression. */
        public XPath(String xpath, SymbolTable symbolTable,
                     NamespaceContext context) throws XPathException {
            super(normalize(xpath), symbolTable, context);
            // verify that an attribute is not selected
            for (int i=0;i<fLocationPaths.length;i++) {
                com.sun.org.apache.xerces.internal.impl.xpath.XPath.Axis axis =
                fLocationPaths[i].steps[fLocationPaths[i].steps.length-1].axis;
                if (axis.type == XPath.Axis.ATTRIBUTE) {
                    throw new XPathException("c-selector-xpath");
                }
            }

        } // <init>(String,SymbolTable,NamespacesScope)

        private static String normalize(String xpath) {
            // NOTE: We have to prefix the selector XPath with "./" in
            //       order to handle selectors such as "." that select
            //       the element container because the fields could be
            //       relative to that element. -Ac
            //       Unless xpath starts with a descendant node -Achille Fokoue
            //      ... or a '.' or a '/' - NG
            //  And we also need to prefix exprs to the right of | with ./ - NG
            StringBuffer modifiedXPath = new StringBuffer(xpath.length()+5);
            int unionIndex = -1;
            do {
                if(!(XMLChar.trim(xpath).startsWith("/") || XMLChar.trim(xpath).startsWith("."))) {
                    modifiedXPath.append("./");
                }
                unionIndex = xpath.indexOf('|');
                if(unionIndex == -1) {
                    modifiedXPath.append(xpath);
                    break;
                }
                modifiedXPath.append(xpath, 0, unionIndex+1);
                xpath = xpath.substring(unionIndex+1);
            } while(true);
            return modifiedXPath.toString();
        }

    } // class Selector.XPath

    /**
     * Selector matcher.
     *
     */
    public class Matcher
    extends XPathMatcher {

        //
        // Data
        //

        /** Field activator. */
        protected final FieldActivator fFieldActivator;

        /** Initial depth in the document at which this matcher was created. */
        protected final int fInitialDepth;

        /** Element depth. */
        protected int fElementDepth;

        /** Depth at match. */
        protected int fMatchedDepth;

        //
        // Constructors
        //

        /** Constructs a selector matcher. */
        public Matcher(Selector.XPath xpath, FieldActivator activator,
                int initialDepth) {
            super(xpath);
            fFieldActivator = activator;
            fInitialDepth = initialDepth;
        } // <init>(Selector.XPath,FieldActivator)

        //
        // XMLDocumentFragmentHandler methods
        //

        public void startDocumentFragment(){
            super.startDocumentFragment();
            fElementDepth = 0;
            fMatchedDepth = -1;
        } // startDocumentFragment()

        /**
         * The start of an element. If the document specifies the start element
         * by using an empty tag, then the startElement method will immediately
         * be followed by the endElement method, with no intervening methods.
         *
         * @param element    The name of the element.
         * @param attributes The element attributes.
         *
         */
        public void startElement(QName element, XMLAttributes attributes) {
            super.startElement(element, attributes);
            fElementDepth++;
            // activate the fields, if selector is matched
            //int matched = isMatched();

            if (isMatched()) {
/*            (fMatchedDepth == -1 && ((matched & MATCHED) == MATCHED)) ||
                    ((matched & MATCHED_DESCENDANT) == MATCHED_DESCENDANT)) { */
                fMatchedDepth = fElementDepth;
                fFieldActivator.startValueScopeFor(fIdentityConstraint, fInitialDepth);
                int count = fIdentityConstraint.getFieldCount();
                for (int i = 0; i < count; i++) {
                    Field field = fIdentityConstraint.getFieldAt(i);
                    XPathMatcher matcher = fFieldActivator.activateField(field, fInitialDepth);
                    matcher.startElement(element, attributes);
                }
            }

        } // startElement(QName,XMLAttrList,int)

        public void endElement(QName element, XSTypeDefinition type, boolean nillable, Object actualValue, short valueType, ShortList itemValueType) {
            super.endElement(element, type, nillable, actualValue, valueType, itemValueType);
            if (fElementDepth-- == fMatchedDepth) {
                fMatchedDepth = -1;
                fFieldActivator.endValueScopeFor(fIdentityConstraint, fInitialDepth);
            }
        }

        /** Returns the identity constraint. */
        public IdentityConstraint getIdentityConstraint() {
            return fIdentityConstraint;
        } // getIdentityConstraint():IdentityConstraint

        /** get the initial depth at which this selector matched. */
        public int getInitialDepth() {
            return fInitialDepth;
        } // getInitialDepth():  int

    } // class Matcher

} // class Selector
