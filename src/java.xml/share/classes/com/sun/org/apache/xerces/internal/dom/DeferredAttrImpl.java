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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * WARNING: because java doesn't support multi-inheritance some code is
 * duplicated. If you're changing this file you probably want to change
 * DeferredAttrNSImpl.java at the same time.
 */

/**
 * Attribute represents an XML-style attribute of an
 * Element. Typically, the allowable values are controlled by its
 * declaration in the Document Type Definition (DTD) governing this
 * kind of document.
 * <P>
 * If the attribute has not been explicitly assigned a value, but has
 * been declared in the DTD, it will exist and have that default. Only
 * if neither the document nor the DTD specifies a value will the
 * Attribute really be considered absent and have no value; in that
 * case, querying the attribute will return null.
 * <P>
 * Attributes may have multiple children that contain their data. (XML
 * allows attributes to contain entity references, and tokenized
 * attribute types such as NMTOKENS may have a child for each token.)
 * For convenience, the Attribute object's getValue() method returns
 * the string version of the attribute's value.
 * <P>
 * Attributes are not children of the Elements they belong to, in the
 * usual sense, and have no valid Parent reference. However, the spec
 * says they _do_ belong to a specific Element, and an INUSE exception
 * is to be thrown if the user attempts to explicitly share them
 * between elements.
 * <P>
 * Note that Elements do not permit attributes to appear to be shared
 * (see the INUSE exception), so this object's mutability is
 * officially not an issue.
 * <P>
 * DeferredAttrImpl inherits from AttrImpl which does not support
 * Namespaces. DeferredAttrNSImpl, which inherits from AttrNSImpl, does.
 * @see DeferredAttrNSImpl
 *
 * @xerces.internal
 *
 */
public final class DeferredAttrImpl
    extends AttrImpl
    implements DeferredNode {

    //
    // Constants
    //

    /** Serialization version. */
    static final long serialVersionUID = 6903232312469148636L;

    //
    // Data
    //

    /** Node index. */
    protected transient int fNodeIndex;

    //
    // Constructors
    //

    /**
     * This is the deferred constructor. Only the fNodeIndex is given here.
     * All other data, can be requested from the ownerDocument via the index.
     */
    DeferredAttrImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
        super(ownerDocument, null);

        fNodeIndex = nodeIndex;
        needsSyncData(true);
        needsSyncChildren(true);

    } // <init>(DeferredDocumentImpl,int)

    //
    // DeferredNode methods
    //

    /** Returns the node index. */
    public int getNodeIndex() {
        return fNodeIndex;
    }

    //
    // Protected methods
    //

    /** Synchronizes the data (name and value) for fast nodes. */
    protected void synchronizeData() {

        // no need to sync in the future
        needsSyncData(false);

        // fluff data
        DeferredDocumentImpl ownerDocument =
            (DeferredDocumentImpl) ownerDocument();
        name = ownerDocument.getNodeName(fNodeIndex);
        int extra = ownerDocument.getNodeExtra(fNodeIndex);
        isSpecified((extra & SPECIFIED) != 0);
        isIdAttribute((extra & ID) != 0);

        int extraNode = ownerDocument.getLastChild(fNodeIndex);
        type = ownerDocument.getTypeInfo(extraNode);
    } // synchronizeData()

    /**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     */
    protected void synchronizeChildren() {
        DeferredDocumentImpl ownerDocument =
            (DeferredDocumentImpl) ownerDocument();
        ownerDocument.synchronizeChildren(this, fNodeIndex);
    } // synchronizeChildren()

} // class DeferredAttrImpl
