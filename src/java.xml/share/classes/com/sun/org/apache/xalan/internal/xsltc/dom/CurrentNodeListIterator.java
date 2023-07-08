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

import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Iterators of this kind use a CurrentNodeListFilter to filter a subset of
 * nodes from a source iterator. For each node from the source, the boolean
 * method CurrentNodeListFilter.test() is called.
 *
 * All nodes from the source are read into an array upon calling setStartNode()
 * (this is needed to determine the value of last, a parameter to
 * CurrentNodeListFilter.test()). The method getLast() returns the last element
 * after applying the filter.
 */

public final class CurrentNodeListIterator extends DTMAxisIteratorBase {
    /**
     * A flag indicating if nodes are returned in document order.
     */
    private boolean _docOrder;

    /**
     * The source for this iterator.
     */
    private DTMAxisIterator _source;

    /**
     * A reference to a filter object.
     */
    private final CurrentNodeListFilter _filter;

    /**
     * An integer array to store nodes from source iterator.
     */
    private IntegerArray _nodes = new IntegerArray();

    /**
     * Index in _nodes of the next node to filter.
     */
    private int _currentIndex;

    /**
     * The current node in the stylesheet at the time of evaluation.
     */
    private final int _currentNode;

    /**
     * A reference to the translet.
     */
    private final AbstractTranslet _translet;

    public CurrentNodeListIterator(DTMAxisIterator source,
                                   CurrentNodeListFilter filter,
                                   int currentNode,
                                   AbstractTranslet translet)
    {
        this(source, !source.isReverse(), filter, currentNode, translet);
    }

    public CurrentNodeListIterator(DTMAxisIterator source, boolean docOrder,
                                   CurrentNodeListFilter filter,
                                   int currentNode,
                                   AbstractTranslet translet)
    {
        _source = source;
        _filter = filter;
        _translet = translet;
        _docOrder = docOrder;
        _currentNode = currentNode;
    }

    public DTMAxisIterator forceNaturalOrder() {
        _docOrder = true;
        return this;
    }

    public void setRestartable(boolean isRestartable) {
        _isRestartable = isRestartable;
        _source.setRestartable(isRestartable);
    }

    public boolean isReverse() {
        return !_docOrder;
    }

    public DTMAxisIterator cloneIterator() {
        try {
            final CurrentNodeListIterator clone =
                (CurrentNodeListIterator) super.clone();
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

    public DTMAxisIterator reset() {
        _currentIndex = 0;
        return resetPosition();
    }

    public int next() {
        final int last = _nodes.cardinality();
        final int currentNode = _currentNode;
        final AbstractTranslet translet = _translet;

        for (int index = _currentIndex; index < last; ) {
            final int position = _docOrder ? index + 1 : last - index;
            final int node = _nodes.at(index++);        // note increment

            if (_filter.test(node, position, last, currentNode, translet,
                             this)) {
                _currentIndex = index;
                return returnNode(node);
            }
        }
        return END;
    }

    public DTMAxisIterator setStartNode(int node) {
        if (_isRestartable) {
            _source.setStartNode(_startNode = node);

            _nodes.clear();
            while ((node = _source.next()) != END) {
                _nodes.add(node);
            }
            _currentIndex = 0;
            resetPosition();
        }
        return this;
    }

    public int getLast() {
        if (_last == -1) {
            _last = computePositionOfLast();
        }
        return _last;
    }

    public void setMark() {
        _markedNode = _currentIndex;
    }

    public void gotoMark() {
        _currentIndex = _markedNode;
    }

    private int computePositionOfLast() {
        final int last = _nodes.cardinality();
        final int currNode = _currentNode;
        final AbstractTranslet translet = _translet;

        int lastPosition = _position;
        for (int index = _currentIndex; index < last; ) {
            final int position = _docOrder ? index + 1 : last - index;
            int nodeIndex = _nodes.at(index++);         // note increment

            if (_filter.test(nodeIndex, position, last, currNode, translet,
                             this)) {
                lastPosition++;
            }
        }
        return lastPosition;
    }
}
