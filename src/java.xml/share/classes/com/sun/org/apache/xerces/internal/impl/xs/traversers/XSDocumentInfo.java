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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.traversers;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.validation.ValidationState;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.util.Stack;
import java.xml.share.classes.com.sun.org.w3c.dom.Attr;
import java.xml.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.share.classes.com.sun.org.w3c.dom.NamedNodeMap;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;

/**
 * Objects of this class hold all information pecular to a
 * particular XML Schema document.  This is needed because
 * namespace bindings and other settings on the <schema/> element
 * affect the contents of that schema document alone.
 *
 * @xerces.internal
 *
 * @LastModified: Oct 2017
 */
class XSDocumentInfo {

    // Data
    protected SchemaNamespaceSupport fNamespaceSupport;
    protected SchemaNamespaceSupport fNamespaceSupportRoot;
    protected Stack<SchemaNamespaceSupport> SchemaNamespaceSupportStack = new Stack<>();

    // schema's attributeFormDefault
    protected boolean fAreLocalAttributesQualified;

    // elementFormDefault
    protected boolean fAreLocalElementsQualified;

    // [block | final]Default
    protected short fBlockDefault;
    protected short fFinalDefault;

    // targetNamespace
    String fTargetNamespace;

    // represents whether this is a chameleon schema (i.e., whether its TNS is natural or comes from without)
    protected boolean fIsChameleonSchema;

    // the root of the schema Document tree itself
    protected Element fSchemaElement;

    // all namespaces that this document can refer to
    List<String> fImportedNS = new ArrayList<>();

    protected ValidationState fValidationContext = new ValidationState();

    SymbolTable fSymbolTable = null;

    // attribute checker to which we'll return the attributes
    // once we've been told that we're done with them
    protected XSAttributeChecker fAttrChecker;

    // array of objects on the schema's root element.  This is null
    // once returnSchemaAttrs has been called.
    protected Object [] fSchemaAttrs;

    // list of annotations contained in the schema document. This is null
    // once removeAnnotations has been called.
    protected XSAnnotationInfo fAnnotations = null;

    // note that the caller must ensure to call returnSchemaAttrs()
    // to avoid memory leaks!
    XSDocumentInfo (Element schemaRoot, XSAttributeChecker attrChecker, SymbolTable symbolTable)
                    throws XMLSchemaException {
        fSchemaElement = schemaRoot;
        initNamespaceSupport(schemaRoot);
        fIsChameleonSchema = false;

        fSymbolTable = symbolTable;
        fAttrChecker = attrChecker;

        if (schemaRoot != null) {
            Element root = schemaRoot;
            fSchemaAttrs = attrChecker.checkAttributes(root, true, this);
            // schemaAttrs == null means it's not an <xsd:schema> element
            // throw an exception, but we don't know the document systemId,
            // so we leave that to the caller.
            if (fSchemaAttrs == null) {
                throw new XMLSchemaException(null, null);
            }
            fAreLocalAttributesQualified =
                ((XInt)fSchemaAttrs[XSAttributeChecker.ATTIDX_AFORMDEFAULT]).intValue() == SchemaSymbols.FORM_QUALIFIED;
            fAreLocalElementsQualified =
                ((XInt)fSchemaAttrs[XSAttributeChecker.ATTIDX_EFORMDEFAULT]).intValue() == SchemaSymbols.FORM_QUALIFIED;
            fBlockDefault =
                ((XInt)fSchemaAttrs[XSAttributeChecker.ATTIDX_BLOCKDEFAULT]).shortValue();
            fFinalDefault =
                ((XInt)fSchemaAttrs[XSAttributeChecker.ATTIDX_FINALDEFAULT]).shortValue();
            fTargetNamespace =
                (String)fSchemaAttrs[XSAttributeChecker.ATTIDX_TARGETNAMESPACE];
            if (fTargetNamespace != null)
                fTargetNamespace = symbolTable.addSymbol(fTargetNamespace);

            fNamespaceSupportRoot = new SchemaNamespaceSupport(fNamespaceSupport);

            //set namespace support
            fValidationContext.setNamespaceSupport(fNamespaceSupport);
            fValidationContext.setSymbolTable(symbolTable);
            // pass null as the schema document, so that the namespace
            // context is not popped.

            // don't return the attribute array yet!
            //attrChecker.returnAttrArray(schemaAttrs, null);
        }
    }

    /**
     * Initialize namespace support by collecting all of the namespace
     * declarations in the root's ancestors. This is necessary to
     * support schemas fragments, i.e. schemas embedded in other
     * documents. See,
     *
     * https://jaxp.dev.java.net/issues/show_bug.cgi?id=43
     *
     * Requires the DOM to be created with namespace support enabled.
     */
    private void initNamespaceSupport(Element schemaRoot) {
        fNamespaceSupport = new SchemaNamespaceSupport();
        fNamespaceSupport.reset();

        Node parent = schemaRoot.getParentNode();
        while (parent != null && parent.getNodeType() == Node.ELEMENT_NODE
                && !parent.getNodeName().equals("DOCUMENT_NODE"))
        {
            Element eparent = (Element) parent;
            NamedNodeMap map = eparent.getAttributes();
            int length = (map != null) ? map.getLength() : 0;
            for (int i = 0; i < length; i++) {
                Attr attr = (Attr) map.item(i);
                String uri = attr.getNamespaceURI();

                // Check if attribute is an ns decl -- requires ns support
                if (uri != null && uri.equals("http://www.w3.org/2000/xmlns/")) {
                    String prefix = attr.getLocalName().intern();
                    if (prefix == "xmlns") prefix = "";
                    // Declare prefix if not set -- moving upwards
                    if (fNamespaceSupport.getURI(prefix) == null) {
                        fNamespaceSupport.declarePrefix(prefix,
                                attr.getValue().intern());
                    }
                }
            }
            parent = parent.getParentNode();
        }
    }

    // backup the current ns support, and use the one passed-in.
    // if no ns support is passed-in, use the one for <schema> element
    void backupNSSupport(SchemaNamespaceSupport nsSupport) {
        SchemaNamespaceSupportStack.push(fNamespaceSupport);
        if (nsSupport == null)
            nsSupport = fNamespaceSupportRoot;
        fNamespaceSupport = new SchemaNamespaceSupport(nsSupport);

        fValidationContext.setNamespaceSupport(fNamespaceSupport);
    }

    void restoreNSSupport() {
        fNamespaceSupport = SchemaNamespaceSupportStack.pop();
        fValidationContext.setNamespaceSupport(fNamespaceSupport);
    }

    // some Object methods
    public String toString() {
        return fTargetNamespace == null?"no targetNamspace":"targetNamespace is " + fTargetNamespace;
    }

    public void addAllowedNS(String namespace) {
        fImportedNS.add(namespace == null ? "" : namespace);
    }

    public boolean isAllowedNS(String namespace) {
        return fImportedNS.contains(namespace == null ? "" : namespace);
    }

    // store whether we have reported an error about that this document
    // can't access components from the given namespace
    private List<String> fReportedTNS = null;
    // check whether we need to report an error against the given uri.
    // if we have reported an error, then we don't need to report again;
    // otherwise we reported the error, and remember this fact.
    final boolean needReportTNSError(String uri) {
        if (fReportedTNS == null)
            fReportedTNS = new ArrayList<>();
        else if (fReportedTNS.contains(uri))
            return false;
        fReportedTNS.add(uri);
        return true;
    }

    // return the attributes on the schema element itself:
    Object [] getSchemaAttrs () {
        return fSchemaAttrs;
    }

    // deallocate the storage set aside for the schema element's
    // attributes
    void returnSchemaAttrs () {
        fAttrChecker.returnAttrArray (fSchemaAttrs, null);
        fSchemaAttrs = null;
    }

    // adds an annotation to the list of annotations
    void addAnnotation(XSAnnotationInfo info) {
        info.next = fAnnotations;
        fAnnotations = info;
    }

    // returns the list of annotations conatined in the
    // schema document or null if the document contained no annotations.
    XSAnnotationInfo getAnnotations() {
        return fAnnotations;
    }

    // removes reference to annotation list
    void removeAnnotations() {
        fAnnotations = null;
    }

} // XSDocumentInfo
