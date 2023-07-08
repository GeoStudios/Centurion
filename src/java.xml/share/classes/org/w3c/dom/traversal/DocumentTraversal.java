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
import java.xml.share.classes.org.w3c.dom.DOMException;















/**
 * <code>DocumentTraversal</code> contains methods that create
 * <code>NodeIterators</code> and <code>TreeWalkers</code> to traverse a
 * node and its children in document order (depth first, pre-order
 * traversal, which is equivalent to the order in which the start tags occur
 * in the text representation of the document). In DOMs which support the
 * Traversal feature, <code>DocumentTraversal</code> will be implemented by
 * the same objects that implement the Document interface.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Traversal-Range-20001113'>Document Object Model (DOM) Level 2 Traversal and Range Specification</a>.
 */
public interface DocumentTraversal {
    /**
     * Create a new <code>NodeIterator</code> over the subtree rooted at the
     * specified node.
     * @param root The node which will be iterated together with its
     *   children. The <code>NodeIterator</code> is initially positioned
     *   just before this node. The <code>whatToShow</code> flags and the
     *   filter, if any, are not considered when setting this position. The
     *   root must not be <code>null</code>.
     * @param whatToShow This flag specifies which node types may appear in
     *   the logical view of the tree presented by the
     *   <code>NodeIterator</code>. See the description of
     *   <code>NodeFilter</code> for the set of possible <code>SHOW_</code>
     *   values.These flags can be combined using <code>OR</code>.
     * @param filter The <code>NodeFilter</code> to be used with this
     *   <code>NodeIterator</code>, or <code>null</code> to indicate no
     *   filter.
     * @param entityReferenceExpansion The value of this flag determines
     *   whether entity reference nodes are expanded.
     * @return The newly created <code>NodeIterator</code>.
     * @exception DOMException
     *   NOT_SUPPORTED_ERR: Raised if the specified <code>root</code> is
     *   <code>null</code>.
     */
    NodeIterator createNodeIterator(Node root,
                                           int whatToShow,
                                           NodeFilter filter,
                                           boolean entityReferenceExpansion)
                                           throws DOMException;

    /**
     * Create a new <code>TreeWalker</code> over the subtree rooted at the
     * specified node.
     * @param root The node which will serve as the <code>root</code> for the
     *   <code>TreeWalker</code>. The <code>whatToShow</code> flags and the
     *   <code>NodeFilter</code> are not considered when setting this value;
     *   any node type will be accepted as the <code>root</code>. The
     *   <code>currentNode</code> of the <code>TreeWalker</code> is
     *   initialized to this node, whether or not it is visible. The
     *   <code>root</code> functions as a stopping point for traversal
     *   methods that look upward in the document structure, such as
     *   <code>parentNode</code> and nextNode. The <code>root</code> must
     *   not be <code>null</code>.
     * @param whatToShow This flag specifies which node types may appear in
     *   the logical view of the tree presented by the
     *   <code>TreeWalker</code>. See the description of
     *   <code>NodeFilter</code> for the set of possible <code>SHOW_</code>
     *   values.These flags can be combined using <code>OR</code>.
     * @param filter The <code>NodeFilter</code> to be used with this
     *   <code>TreeWalker</code>, or <code>null</code> to indicate no filter.
     * @param entityReferenceExpansion If this flag is false, the contents of
     *   <code>EntityReference</code> nodes are not presented in the logical
     *   view.
     * @return The newly created <code>TreeWalker</code>.
     * @exception DOMException
     *    NOT_SUPPORTED_ERR: Raised if the specified <code>root</code> is
     *   <code>null</code>.
     */
    TreeWalker createTreeWalker(Node root,
                                       int whatToShow,
                                       NodeFilter filter,
                                       boolean entityReferenceExpansion)
                                       throws DOMException;

}
