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

package java.xml.share.classes.org.w3c.dom.traversal;


import java.xml.share.classes.org.w3c.dom.Node;















/**
 * Filters are objects that know how to "filter out" nodes. If a
 * <code>NodeIterator</code> or <code>TreeWalker</code> is given a
 * <code>NodeFilter</code>, it applies the filter before it returns the next
 * node. If the filter says to accept the node, the traversal logic returns
 * it; otherwise, traversal looks for the next node and pretends that the
 * node that was rejected was not there.
 * <p>The DOM does not provide any filters. <code>NodeFilter</code> is just an
 * interface that users can implement to provide their own filters.
 * <p><code>NodeFilters</code> do not need to know how to traverse from node
 * to node, nor do they need to know anything about the data structure that
 * is being traversed. This makes it very easy to write filters, since the
 * only thing they have to know how to do is evaluate a single node. One
 * filter may be used with a number of different kinds of traversals,
 * encouraging code reuse.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Traversal-Range-20001113'>Document Object Model (DOM) Level 2 Traversal and Range Specification</a>.
 */
public interface NodeFilter {
    // Constants returned by acceptNode
    /**
     * Accept the node. Navigation methods defined for
     * <code>NodeIterator</code> or <code>TreeWalker</code> will return this
     * node.
     */
    short FILTER_ACCEPT             = 1;
    /**
     * Reject the node. Navigation methods defined for
     * <code>NodeIterator</code> or <code>TreeWalker</code> will not return
     * this node. For <code>TreeWalker</code>, the children of this node
     * will also be rejected. <code>NodeIterators</code> treat this as a
     * synonym for <code>FILTER_SKIP</code>.
     */
    short FILTER_REJECT             = 2;
    /**
     * Skip this single node. Navigation methods defined for
     * <code>NodeIterator</code> or <code>TreeWalker</code> will not return
     * this node. For both <code>NodeIterator</code> and
     * <code>TreeWalker</code>, the children of this node will still be
     * considered.
     */
    short FILTER_SKIP               = 3;

    // Constants for whatToShow
    /**
     * Show all <code>Nodes</code>.
     */
    int SHOW_ALL                  = 0xFFFFFFFF;
    /**
     * Show <code>Element</code> nodes.
     */
    int SHOW_ELEMENT              = 0x00000001;
    /**
     * Show <code>Attr</code> nodes. This is meaningful only when creating an
     * <code>NodeIterator</code> or <code>TreeWalker</code> with an
     * attribute node as its <code>root</code>; in this case, it means that
     * the attribute node will appear in the first position of the iteration
     * or traversal. Since attributes are never children of other nodes,
     * they do not appear when traversing over the document tree.
     */
    int SHOW_ATTRIBUTE            = 0x00000002;
    /**
     * Show <code>Text</code> nodes.
     */
    int SHOW_TEXT                 = 0x00000004;
    /**
     * Show <code>CDATASection</code> nodes.
     */
    int SHOW_CDATA_SECTION        = 0x00000008;
    /**
     * Show <code>EntityReference</code> nodes.
     */
    int SHOW_ENTITY_REFERENCE     = 0x00000010;
    /**
     * Show <code>Entity</code> nodes. This is meaningful only when creating
     * an <code>NodeIterator</code> or <code>TreeWalker</code> with an
     * <code>Entity</code> node as its <code>root</code>; in this case, it
     * means that the <code>Entity</code> node will appear in the first
     * position of the traversal. Since entities are not part of the
     * document tree, they do not appear when traversing over the document
     * tree.
     */
    int SHOW_ENTITY               = 0x00000020;
    /**
     * Show <code>ProcessingInstruction</code> nodes.
     */
    int SHOW_PROCESSING_INSTRUCTION = 0x00000040;
    /**
     * Show <code>Comment</code> nodes.
     */
    int SHOW_COMMENT              = 0x00000080;
    /**
     * Show <code>Document</code> nodes.
     */
    int SHOW_DOCUMENT             = 0x00000100;
    /**
     * Show <code>DocumentType</code> nodes.
     */
    int SHOW_DOCUMENT_TYPE        = 0x00000200;
    /**
     * Show <code>DocumentFragment</code> nodes.
     */
    int SHOW_DOCUMENT_FRAGMENT    = 0x00000400;
    /**
     * Show <code>Notation</code> nodes. This is meaningful only when creating
     * an <code>NodeIterator</code> or <code>TreeWalker</code> with a
     * <code>Notation</code> node as its <code>root</code>; in this case, it
     * means that the <code>Notation</code> node will appear in the first
     * position of the traversal. Since notations are not part of the
     * document tree, they do not appear when traversing over the document
     * tree.
     */
    int SHOW_NOTATION             = 0x00000800;

    /**
     * Test whether a specified node is visible in the logical view of a
     * <code>TreeWalker</code> or <code>NodeIterator</code>. This function
     * will be called by the implementation of <code>TreeWalker</code> and
     * <code>NodeIterator</code>; it is not normally called directly from
     * user code. (Though you could do so if you wanted to use the same
     * filter to guide your own application logic.)
     * @param n The node to check to see if it passes the filter or not.
     * @return A constant to determine whether the node is accepted,
     *   rejected, or skipped, as defined above.
     */
    short acceptNode(Node n);

}
