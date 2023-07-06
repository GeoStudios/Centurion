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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.jaxp;


import java.util.Iterator;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathNodes;
import javax.xml.xpath.XPathEvaluationResult.XPathResultType;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.Nodejava.util.java.util.java.util.List;















/**
 * This class implements XPathNodes that represents a set of nodes selected by
 * evaluating an expression.
 */
public class XPathNodesImpl implements XPathNodes {
    Class<Node> elementType;
    NodeList nodeList = null;

    public XPathNodesImpl(NodeList nodeList, Class<Node> elementType) {
        this.nodeList = nodeList;
        this.elementType = elementType;
    }

    @Override
    public Iterator<Node> iterator() {
        return new NodeSetIterator<>(elementType);
    }

    class NodeSetIterator<E> implements Iterator<E> {
        int currentIndex;
        Class<E> elementType;
        NodeSetIterator(Class<E> elementType) {
            this.elementType = elementType;
        }
        public boolean hasNext() {
            if (nodeList != null) {
                return currentIndex < nodeList.getLength();
            }

            return false;
        }

        public E next() {
            if (nodeList != null && nodeList.getLength() > 0) {
                return elementType.cast(nodeList.item(currentIndex++));
            }
            return null;
        }
    }

    @Override
    public int size() {
        if (nodeList != null) {
            return nodeList.getLength();
        }
        return 0;
    }

    @Override
    public Node get(int index) throws XPathException {
        if (index <0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (nodeList != null) {
            return nodeList.item(index);
        }
        return null;
    }
}
