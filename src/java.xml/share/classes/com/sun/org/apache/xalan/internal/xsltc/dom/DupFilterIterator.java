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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.dom;

import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Removes duplicates and sorts a source iterator. The nodes from the
 * source are collected in an array upon calling setStartNode(). This
 * array is later sorted and duplicates are ignored in next().
 */
public final class DupFilterIterator extends DTMAxisIteratorBase {

    /**
     * Reference to source iterator.
     */
    private DTMAxisIterator _source;

    /**
     * Array to cache all nodes from source.
     */
    private IntegerArray _nodes = new IntegerArray();

    /**
     * Index in _nodes array to current node.
     */
    private int _current = 0;

    /**
     * Cardinality of _nodes array.
     */
    private int _nodesSize = 0;

    /**
     * Last value returned by next().
     */
    private int _lastNext = END;

    /**
     * Temporary variable to store _lastNext.
     */
    private int _markedLastNext = END;

    public DupFilterIterator(DTMAxisIterator source) {
        _source = source;
// System.out.println("DFI source = " + source + " this = " + this);

        // Cache contents of id() or key() index right away. Necessary for
        // union expressions containing multiple calls to the same index, and
        // correct as well since start-node is irrelevant for id()/key() exrp.
        if (source instanceof KeyIndex) {
            setStartNode(DTMDefaultBase.ROOTNODE);
        }
    }

    /**
     * Set the start node for this iterator
     * @param node The start node
     * @return A reference to this node iterator
     */
    public DTMAxisIterator setStartNode(int node) {
        if (_isRestartable) {
            // KeyIndex iterators are always relative to the root node, so there
            // is never any point in re-reading the iterator (and we SHOULD NOT).
            boolean sourceIsKeyIndex = _source instanceof KeyIndex;

            if (sourceIsKeyIndex
                    && _startNode == DTMDefaultBase.ROOTNODE) {
                return this;
            }

            if (node != _startNode) {
                _source.setStartNode(_startNode = node);

                _nodes.clear();
                while ((node = _source.next()) != END) {
                    _nodes.add(node);
                }

                // Nodes produced by KeyIndex are known to be in document order.
                // Take advantage of it.
                if (!sourceIsKeyIndex) {
                    _nodes.sort();
                }
                _nodesSize = _nodes.cardinality();
                _current = 0;
                _lastNext = END;
                resetPosition();
            }
        }
        return this;
    }

    public int next() {
        while (_current < _nodesSize) {
            final int next = _nodes.at(_current++);
            if (next != _lastNext) {
                return returnNode(_lastNext = next);
            }
        }
        return END;
    }

    public DTMAxisIterator cloneIterator() {
        try {
            final DupFilterIterator clone =
                (DupFilterIterator) super.clone();
            clone._nodes = (IntegerArray) _nodes.clone();
            clone._source = _source.cloneIterator();
            clone._isRestartable = false;
            return clone.reset();
        }
        catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR,
                                      e.toString());
            return null;
        }
    }

    public void setRestartable(boolean isRestartable) {
        _isRestartable = isRestartable;
        _source.setRestartable(isRestartable);
    }

    public void setMark() {
        _markedNode = _current;
        _markedLastNext = _lastNext;    // Bugzilla 25924
    }

    public void gotoMark() {
        _current = _markedNode;
        _lastNext = _markedLastNext;    // Bugzilla 25924
    }

    public DTMAxisIterator reset() {
        _current = 0;
        _lastNext = END;
        return resetPosition();
    }
}
