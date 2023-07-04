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

package javax.xml.xpath;

import java.util.Iterator;
import org.w3c.dom.Node;

/**
 * XPathNodes represents a set of nodes selected by a location path as specified
 * in <a href="http://www.w3.org/TR/xpath/#node-sets">XML Path Language (XPath)
 * Version 1.0, 3.3 Node-sets</a>.
 *
 */
public interface XPathNodes extends Iterable<Node> {

    /**
     * Returns an iterator of the Nodes.
     *
     * @return an Iterator.
     */
    @Override
    Iterator<Node> iterator();

    /**
     * Returns the number of items in the result
     *
     * @return The number of items in the result
     */
    int size();

    /**
     * Returns a Node at the specified position
     *
     * @param index Index of the element to return.
     * @return The Node at the specified position.
     * @throws javax.xml.xpath.XPathException If the index is out of range
     * (index &lt; 0 || index &gt;= size())
     */
    Node get(int index)
            throws XPathException;
}
