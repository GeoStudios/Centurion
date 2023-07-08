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

/**
 * EntityReference models the XML &entityname; syntax, when used for
 * entities defined by the DOM. Entities hardcoded into XML, such as
 * character entities, should instead have been translated into text
 * by the code which generated the DOM tree.
 * <P>
 * An XML processor has the alternative of fully expanding Entities
 * into the normal document tree. If it does so, no EntityReference nodes
 * will appear.
 * <P>
 * Similarly, non-validating XML processors are not required to read
 * or process entity declarations made in the external subset or
 * declared in external parameter entities. Hence, some applications
 * may not make the replacement value available for Parsed Entities
 * of these types.
 * <P>
 * EntityReference behaves as a read-only node, and the children of
 * the EntityReference (which reflect those of the Entity, and should
 * also be read-only) give its replacement value, if any. They are
 * supposed to automagically stay in synch if the DocumentType is
 * updated with new values for the Entity.
 * <P>
 * The defined behavior makes efficient storage difficult for the DOM
 * implementor. We can't just look aside to the Entity's definition
 * in the DocumentType since those nodes have the wrong parent (unless
 * we can come up with a clever "imaginary parent" mechanism). We
 * must at least appear to clone those children... which raises the
 * issue of keeping the reference synchronized with its parent.
 * This leads me back to the "cached image of centrally defined data"
 * solution, much as I dislike it.
 * <P>
 * For now I have decided, since REC-DOM-Level-1-19980818 doesn't
 * cover this in much detail, that synchronization doesn't have to be
 * considered while the user is deep in the tree. That is, if you're
 * looking within one of the EntityReferennce's children and the Entity
 * changes, you won't be informed; instead, you will continue to access
 * the same object -- which may or may not still be part of the tree.
 * This is the same behavior that obtains elsewhere in the DOM if the
 * subtree you're looking at is deleted from its parent, so it's
 * acceptable here. (If it really bothers folks, we could set things
 * up so deleted subtrees are walked and marked invalid, but that's
 * not part of the DOM's defined behavior.)
 * <P>
 * As a result, only the EntityReference itself has to be aware of
 * changes in the Entity. And it can take advantage of the same
 * structure-change-monitoring code I implemented to support
 * DeepNodeList.
 *
 * @xerces.internal
 *
 */
public class DeferredEntityReferenceImpl
    extends EntityReferenceImpl
    implements DeferredNode {

    //
    // Constants
    //

    /** Serialization version. */
    static final long serialVersionUID = 390319091370032223L;

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
    DeferredEntityReferenceImpl(DeferredDocumentImpl ownerDocument,
                                int nodeIndex) {
        super(ownerDocument, null);

        fNodeIndex = nodeIndex;
        needsSyncData(true);

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

    /**
     * Synchronize the entity data. This is special because of the way
     * that the "fast" version stores the information.
     */
    protected void synchronizeData() {

        // no need to sychronize again
        needsSyncData(false);

        // get the node data
        DeferredDocumentImpl ownerDocument =
            (DeferredDocumentImpl)this.ownerDocument;
        name = ownerDocument.getNodeName(fNodeIndex);
        baseURI = ownerDocument.getNodeValue(fNodeIndex);

    } // synchronizeData()

    /** Synchronize the children. */
    protected void synchronizeChildren() {

        // no need to synchronize again
        needsSyncChildren(false);

        // get children
        isReadOnly(false);
        DeferredDocumentImpl ownerDocument =
            (DeferredDocumentImpl) ownerDocument();
        ownerDocument.synchronizeChildren(this, fNodeIndex);
        setReadOnly(true, true);

    } // synchronizeChildren()

} // class DeferredEntityReferenceImpl
