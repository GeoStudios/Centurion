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

import java.xml.share.classes.com.sun.org.w3c.dom.NamedNodeMap;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * WARNING: because java doesn't support multi-inheritance some code is
 * duplicated. If you're changing this file you probably want to change
 * DeferredElementNSImpl.java at the same time.
 */

/**
 * Elements represent most of the "markup" and structure of the
 * document.  They contain both the data for the element itself
 * (element name and attributes), and any contained nodes, including
 * document text (as children).
 * <P>
 * Elements may have Attributes associated with them; the API for this is
 * defined in Node, but the function is implemented here. In general, XML
 * applications should retrive Attributes as Nodes, since they may contain
 * entity references and hence be a fairly complex sub-tree. HTML users will
 * be dealing with simple string values, and convenience methods are provided
 * to work in terms of Strings.
 * <P>
 * DeferredElementImpl inherits from ElementImpl which does not support
 * Namespaces. DeferredElementNSImpl, which inherits from ElementNSImpl, does.
 * @see DeferredElementNSImpl
 *
 * @xerces.internal
 *
 */
public class DeferredElementImpl
    extends ElementImpl
    implements DeferredNode {

    //
    // Constants
    //

    /** Serialization version. */
    static final long serialVersionUID = -7670981133940934842L;

    //
    // Data
    //

    /** Node index. */
    protected transient int fNodeIndex;

    //
    // Constructors
    //

    /**
     * This is the deferred constructor. Only the fNodeIndex is given here. All
     * other data, can be requested from the ownerDocument via the index.
     */
    DeferredElementImpl(DeferredDocumentImpl ownerDoc, int nodeIndex) {
        super(ownerDoc, null);

        fNodeIndex = nodeIndex;
        needsSyncChildren(true);

    } // <init>(DocumentImpl,int)

    //
    // DeferredNode methods
    //

    /** Returns the node index. */
    public final int getNodeIndex() {
        return fNodeIndex;
    }

    //
    // Protected methods
    //

    /** Synchronizes the data (name and value) for fast nodes. */
    protected final void synchronizeData() {

        // no need to sync in the future
        needsSyncData(false);

        // fluff data
        DeferredDocumentImpl ownerDocument =
            (DeferredDocumentImpl)this.ownerDocument;

        // we don't want to generate any event for this so turn them off
        boolean orig = ownerDocument.mutationEvents;
        ownerDocument.mutationEvents = false;

        name = ownerDocument.getNodeName(fNodeIndex);

        // attributes
        setupDefaultAttributes();
        int index = ownerDocument.getNodeExtra(fNodeIndex);
        if (index != -1) {
            NamedNodeMap attrs = getAttributes();
            do {
                NodeImpl attr = (NodeImpl)ownerDocument.getNodeObject(index);
                attrs.setNamedItem(attr);
                index = ownerDocument.getPrevSibling(index);
            } while (index != -1);
        }

        // set mutation events flag back to its original value
        ownerDocument.mutationEvents = orig;

    } // synchronizeData()

    protected final void synchronizeChildren() {
        DeferredDocumentImpl ownerDocument =
            (DeferredDocumentImpl) ownerDocument();
        ownerDocument.synchronizeChildren(this, fNodeIndex);
    } // synchronizeChildren()

} // class DeferredElementImpl
