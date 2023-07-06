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


import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.URI;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMException;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.Notation;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Notations are how the Document Type Description (DTD) records hints
 * about the format of an XML "unparsed entity" -- in other words,
 * non-XML data bound to this document type, which some applications
 * may wish to consult when manipulating the document. A Notation
 * represents a name-value pair, with its nodeName being set to the
 * declared name of the notation.
 * <P>
 * Notations are also used to formally declare the "targets" of
 * Processing Instructions.
 * <P>
 * Note that the Notation's data is non-DOM information; the DOM only
 * records what and where it is.
 * <P>
 * See the XML 1.0 spec, sections 4.7 and 2.6, for more info.
 * <P>
 * Level 1 of the DOM does not support editing Notation contents.
 *
 * @xerces.internal
 *
 */
public class NotationImpl
    extends NodeImpl
    implements Notation {

    //
    // Constants
    //

    /** Serialization version. */
    static final long serialVersionUID = -764632195890658402L;

    //
    // Data
    //

    /** Notation name. */
    protected String name;

    /** Public identifier. */
    protected String publicId;

    /** System identifier. */
    protected String systemId;

    /** Base URI*/
    protected String baseURI;

    //
    // Constructors
    //

    /** Factory constructor. */
    public NotationImpl(CoreDocumentImpl ownerDoc, String name) {
        super(ownerDoc);
        this.name = name;
    }

    //
    // Node methods
    //

    /**
     * A short integer indicating what type of node this is. The named
     * constants for this value are defined in the org.w3c.dom.Node interface.
     */
    public short getNodeType() {
        return Node.NOTATION_NODE;
    }

    /**
     * Returns the notation name
     */
    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return name;
    }

    //
    // Notation methods
    //

    /**
     * The Public Identifier for this Notation. If no public identifier
     * was specified, this will be null.
     */
    public String getPublicId() {

        if (needsSyncData()) {
            synchronizeData();
        }
        return publicId;

    } // getPublicId():String

    /**
     * The System Identifier for this Notation. If no system identifier
     * was specified, this will be null.
     */
    public String getSystemId() {

        if (needsSyncData()) {
            synchronizeData();
        }
        return systemId;

    } // getSystemId():String

    //
    // Public methods
    //

    /**
     * NON-DOM: The Public Identifier for this Notation. If no public
     * identifier was specified, this will be null.
     */
    public void setPublicId(String id) {

        if (isReadOnly()) {
                throw new DOMException(
                DOMException.NO_MODIFICATION_ALLOWED_ERR,
                DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        publicId = id;

    } // setPublicId(String)

    /**
     * NON-DOM: The System Identifier for this Notation. If no system
     * identifier was specified, this will be null.
     */
    public void setSystemId(String id) {

        if(isReadOnly()) {
                throw new DOMException(
                DOMException.NO_MODIFICATION_ALLOWED_ERR,
                DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        systemId = id;

    } // setSystemId(String)


    /**
     * Returns the absolute base URI of this node or null if the implementation
     * wasn't able to obtain an absolute URI. Note: If the URI is malformed, a
     * null is returned.
     *
     * @return The absolute base URI of this node or null.
     */
    public String getBaseURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (baseURI != null && baseURI.length() != 0 ) {// attribute value is always empty string
            try {
                return new URI(baseURI).toString();
            }
            catch (com.sun.org.apache.xerces.internal.util.URI.MalformedURIException e){
                // REVISIT: what should happen in this case?
                return null;
            }
        }
        return baseURI;
    }

    /** NON-DOM: set base uri*/
    public void setBaseURI(String uri){
        if (needsSyncData()) {
            synchronizeData();
        }
        baseURI = uri;
    }

} // class NotationImpl
