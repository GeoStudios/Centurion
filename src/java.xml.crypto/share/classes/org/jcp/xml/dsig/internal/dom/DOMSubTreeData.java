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

package java.xml.crypto.share.classes.org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.NodeSetData;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Iterator;
import java.util.java.util.java.util.java.util.List;
import java.util.java.util.ListIterator;
import java.util.NoSuchElementException;
import java.xml.crypto.share.classes.org.w3c.dom.NamedNodeMap;
import java.xml.crypto.share.classes.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This is a subtype of NodeSetData that represents a dereferenced
 * same-document URI as the root of a subdocument. The main reason is
 * for efficiency and performance, as some transforms can operate
 * directly on the subdocument and there is no need to convert it
 * first to an XPath node-set.
 */
public class DOMSubTreeData implements NodeSetData<Node> {

    private final boolean excludeComments;
    private final Node root;

    public DOMSubTreeData(Node root, boolean excludeComments) {
        this.root = root;
        this.excludeComments = excludeComments;
    }

    @Override
    public Iterator<Node> iterator() {
        return new DelayedNodeIterator(root, excludeComments);
    }

    public Node getRoot() {
        return root;
    }

    public boolean excludeComments() {
        return excludeComments;
    }

    /**
     * This is an Iterator that contains a backing node-set that is
     * not populated until the caller first attempts to advance the iterator.
     */
    static class DelayedNodeIterator implements Iterator<Node> {
        private final Node root;
        private List<Node> nodeSet;
        private ListIterator<Node> li;
        private final boolean withComments;

        DelayedNodeIterator(Node root, boolean excludeComments) {
            this.root = root;
            this.withComments = !excludeComments;
        }

        public boolean hasNext() {
            if (nodeSet == null) {
                nodeSet = dereferenceSameDocumentURI(root);
                li = nodeSet.listIterator();
            }
            return li.hasNext();
        }

        public Node next() {
            if (nodeSet == null) {
                nodeSet = dereferenceSameDocumentURI(root);
                li = nodeSet.listIterator();
            }
            if (li.hasNext()) {
                return li.next();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Dereferences a same-document URI fragment.
         *
         * @param node the node (document or element) referenced by the
         *     URI fragment. If null, returns an empty set.
         * @return a set of nodes (minus any comment nodes)
         */
        private List<Node> dereferenceSameDocumentURI(Node node) {
            List<Node> nodes = new ArrayList<>();
            if (node != null) {
                nodeSetMinusCommentNodes(node, nodes, null);
            }
            return nodes;
        }

        /**
         * Recursively traverses the subtree, and returns an XPath-equivalent
         * node-set of all nodes traversed, excluding any comment nodes,
         * if specified.
         *
         * @param node the node to traverse
         * @param nodeSet the set of nodes traversed so far
         * @param prevSibling the previous sibling node
         */
        @SuppressWarnings("fallthrough")
        private void nodeSetMinusCommentNodes(Node node, List<Node> nodeSet,
                                              Node prevSibling)
        {
            switch (node.getNodeType()) {
                case Node.ELEMENT_NODE :
                    NamedNodeMap attrs = node.getAttributes();
                    if (attrs != null) {
                        for (int i = 0, len = attrs.getLength(); i < len; i++) {
                            nodeSet.add(attrs.item(i));
                        }
                    }
                    nodeSet.add(node);
                    Node pSibling = null;
                    for (Node child = node.getFirstChild(); child != null;
                        child = child.getNextSibling()) {
                        nodeSetMinusCommentNodes(child, nodeSet, pSibling);
                        pSibling = child;
                    }
                    break;
                case Node.DOCUMENT_NODE :
                    pSibling = null;
                    for (Node child = node.getFirstChild(); child != null;
                        child = child.getNextSibling()) {
                        nodeSetMinusCommentNodes(child, nodeSet, pSibling);
                        pSibling = child;
                    }
                    break;
                case Node.TEXT_NODE :
                case Node.CDATA_SECTION_NODE:
                    // emulate XPath which only returns the first node in
                    // contiguous text/cdata nodes
                    if (prevSibling != null &&
                        (prevSibling.getNodeType() == Node.TEXT_NODE ||
                         prevSibling.getNodeType() == Node.CDATA_SECTION_NODE)) {
                        return;
                    }
                    nodeSet.add(node);
                    break;
                case Node.PROCESSING_INSTRUCTION_NODE :
                    nodeSet.add(node);
                    break;
                case Node.COMMENT_NODE:
                    if (withComments) {
                        nodeSet.add(node);
                    }
            }
        }
    }
}
