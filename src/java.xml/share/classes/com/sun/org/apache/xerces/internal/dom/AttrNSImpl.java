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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.dom;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * AttrNSImpl inherits from AttrImpl and adds namespace support.
 * <P>
 * The qualified name is the node name, and we store localName which is also
 * used in all queries. On the other hand we recompute the prefix when
 * necessary.
 *
 * @xerces.internal
 *
 */
public class AttrNSImpl
    extends AttrImpl {

    //
    // Constants
    //

    /** Serialization version. */
    static final long serialVersionUID = -781906615369795414L;

    static final String xmlnsURI = "http://www.w3.org/2000/xmlns/";
    static final String xmlURI = "http://www.w3.org/XML/1998/namespace";

    //
    // Data
    //

    /** DOM2: Namespace URI. */
    protected String namespaceURI;

    /** DOM2: localName. */
    protected String localName;

    /*
     * Default constructor
     */
    public AttrNSImpl(){}

   /**
     * DOM2: Constructor for Namespace implementation.
     */
    protected AttrNSImpl(CoreDocumentImpl ownerDocument,
                         String namespaceURI,
                         String qualifiedName) {

        super(ownerDocument, qualifiedName);
        setName(namespaceURI, qualifiedName);
    }

    private void setName(String namespaceURI, String qname){
        CoreDocumentImpl ownerDocument = ownerDocument();
        String prefix;
        // DOM Level 3: namespace URI is never empty string.
        this.namespaceURI = namespaceURI;
        if (namespaceURI !=null) {
            this.namespaceURI = (namespaceURI.length() == 0)? null
                    : namespaceURI;

        }
        int colon1 = qname.indexOf(':');
        int colon2 = qname.lastIndexOf(':');
        ownerDocument.checkNamespaceWF(qname, colon1, colon2);
        if (colon1 < 0) {
            // there is no prefix
            localName = qname;
            if (ownerDocument.errorChecking) {
                ownerDocument.checkQName(null, localName);

                if (qname.equals("xmlns") && (namespaceURI == null
                    || !namespaceURI.equals(NamespaceContext.XMLNS_URI))
                    || (namespaceURI!=null && namespaceURI.equals(NamespaceContext.XMLNS_URI)
                    && !qname.equals("xmlns"))) {
                    String msg =
                        DOMMessageFormatter.formatMessage(
                                DOMMessageFormatter.DOM_DOMAIN,
                                "NAMESPACE_ERR",
                                null);
                    throw new DOMException(DOMException.NAMESPACE_ERR, msg);
                }
            }
        }
        else {
            prefix = qname.substring(0, colon1);
            localName = qname.substring(colon2+1);
            ownerDocument.checkQName(prefix, localName);
            ownerDocument.checkDOMNSErr(prefix, namespaceURI);
        }
    }

    // when local name is known
    public AttrNSImpl(CoreDocumentImpl ownerDocument,
                         String namespaceURI,
                         String qualifiedName,
                         String localName) {
        super(ownerDocument, qualifiedName);

        this.localName = localName;
        this.namespaceURI = namespaceURI;
    }

    // for DeferredAttrImpl
    protected AttrNSImpl(CoreDocumentImpl ownerDocument,
                         String value) {
        super(ownerDocument, value);
    }

    // Support for DOM Level 3 renameNode method.
    // Note: This only deals with part of the pb. It is expected to be
    // called after the Attr has been detached for one thing.
    // CoreDocumentImpl does all the work.
    void rename(String namespaceURI, String qualifiedName) {
        if (needsSyncData()) {
            synchronizeData();
        }
                this.name = qualifiedName;
        setName(namespaceURI, qualifiedName);
    }

    //
    // DOM2: Namespace methods
    //

    /**
     * Introduced in DOM Level 2. <p>
     *
     * The namespace URI of this node, or null if it is unspecified.<p>
     *
     * This is not a computed value that is the result of a namespace lookup
     * based on an examination of the namespace declarations in scope. It is
     * merely the namespace URI given at creation time.<p>
     *
     * For nodes created with a DOM Level 1 method, such as createElement
     * from the Document interface, this is null.
     */
    public String getNamespaceURI()
    {
        if (needsSyncData()) {
            synchronizeData();
        }
        // REVIST: This code could/should be done at a lower-level, such that
        // the namespaceURI is set properly upon creation. However, there still
        // seems to be some DOM spec interpretation grey-area.
        return namespaceURI;
    }

    /**
     * Introduced in DOM Level 2. <p>
     *
     * The namespace prefix of this node, or null if it is unspecified. <p>
     *
     * For nodes created with a DOM Level 1 method, such as createElement
     * from the Document interface, this is null. <p>
     *
     */
    public String getPrefix()
    {
        if (needsSyncData()) {
            synchronizeData();
        }
        int index = name.indexOf(':');
        return index < 0 ? null : name.substring(0, index);
    }

    /**
     * Introduced in DOM Level 2. <p>
     *
     * Note that setting this attribute changes the nodeName attribute, which
     * holds the qualified name, as well as the tagName and name attributes of
     * the Element and Attr interfaces, when applicable.<p>
     *
     * @param prefix The namespace prefix of this node, or null(empty string) if it is unspecified.
     *
     * @exception INVALID_CHARACTER_ERR
     *                   Raised if the specified
     *                   prefix contains an invalid character.
     * @exception DOMException
     */
    public void setPrefix(String prefix)
        throws DOMException
    {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (ownerDocument().errorChecking) {
            if (isReadOnly()) {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null);
                throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, msg);
            }
            if (prefix != null && prefix.length() != 0) {

                if (!CoreDocumentImpl.isXMLName(prefix,ownerDocument().isXML11Version())) {
                    String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
                    throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
                }
                if (namespaceURI == null || prefix.indexOf(':') >=0) {
                    String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null);
                    throw new DOMException(DOMException.NAMESPACE_ERR, msg);

                }
               if (prefix.equals("xmlns")) {
                    if (!namespaceURI.equals(xmlnsURI)){
                        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null);
                        throw new DOMException(DOMException.NAMESPACE_ERR, msg);
                    }
                } else if (prefix.equals("xml")) {
                    if (!namespaceURI.equals(xmlURI)) {
                        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null);
                        throw new DOMException(DOMException.NAMESPACE_ERR, msg);
                    }
                }else if (name.equals("xmlns")) {
                    String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null);
                    throw new DOMException(DOMException.NAMESPACE_ERR, msg);
                }
            }
        }

        // update node name with new qualifiedName
        if (prefix !=null && prefix.length() != 0) {
            name = prefix + ":" + localName;
        }
        else {
            name = localName;
        }
    }

    /**
     * Introduced in DOM Level 2. <p>
     *
     * Returns the local part of the qualified name of this node.
     */
    public String getLocalName()
    {
        if (needsSyncData()) {
            synchronizeData();
        }
        return localName;
    }

    /**
     * @see org.w3c.dom.TypeInfo#getTypeName()
     */
    public String getTypeName() {
        if (type !=null){
            if (type instanceof XSSimpleTypeDecl){
                return ((XSSimpleTypeDecl)type).getName();
            }
            return (String)type;
        }
        return null;
    }

    /**
     * Introduced in DOM Level 3. <p>
     * Checks if a type is derived from another by restriction. See:
     * http://www.w3.org/TR/DOM-Level-3-Core/core.html#TypeInfo-isDerivedFrom
     *
     * @param typeNamespaceArg
     *        The namspace of the ancestor type declaration
     * @param typeNameArg
     *        The name of the ancestor type declaration
     * @param derivationMethod
     *        The derivation method
     *
     * @return boolean True if the type is derived by restriction for the
     *         reference type
     */
    public boolean isDerivedFrom(String typeNamespaceArg,
                                 String typeNameArg,
                                 int derivationMethod) {
        if (type != null) {
            if (type instanceof XSSimpleTypeDecl) {
                return ((XSSimpleTypeDecl) type).isDOMDerivedFrom(
                        typeNamespaceArg, typeNameArg, derivationMethod);
            }
        }
        return false;
    }

    /**
     * @see org.w3c.dom.TypeInfo#getTypeNamespace()
     */
    public String getTypeNamespace() {
        if (type !=null) {
            if (type instanceof XSSimpleTypeDecl){
                return ((XSSimpleTypeDecl)type).getNamespace();
            }
            return DTD_URI;
        }
        return null;
    }

}
