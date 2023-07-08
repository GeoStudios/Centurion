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

import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * CachedNodeListIterator is used for select expressions in a
 * variable or parameter. This iterator caches all nodes in an
 * IntegerArray. Its cloneIterator() method is overridden to
 * return an object of ClonedNodeListIterator.
 */
public final class CachedNodeListIterator extends DTMAxisIteratorBase {

    /**
     * Source for this iterator.
     */
    private final DTMAxisIterator _source;
    private final IntegerArray _nodes = new IntegerArray();
    private int _numCachedNodes = 0;
    private int _index = 0;
    private boolean _isEnded = false;

    public CachedNodeListIterator(DTMAxisIterator source) {
        _source = source;
    }

    public void setRestartable(boolean isRestartable) {
        //_isRestartable = isRestartable;
        //_source.setRestartable(isRestartable);
    }

    public DTMAxisIterator setStartNode(int node) {
        if (_isRestartable) {
            _startNode = node;
            _source.setStartNode(node);
            resetPosition();

            _isRestartable = false;
        }
        return this;
    }

    public int next() {
        return getNode(_index++);
    }

    public int getPosition() {
        return _index == 0 ? 1 : _index;
    }

    public int getNodeByPosition(int pos) {
        return getNode(pos);
    }

    public int getNode(int index) {
        if (index < _numCachedNodes) {
            return _nodes.at(index);
        }
        else if (!_isEnded){
            int node = _source.next();
            if (node != END) {
                _nodes.add(node);
                _numCachedNodes++;
            }
            else {
                _isEnded = true;
            }
            return node;
        }
        else
            return END;
    }

    public DTMAxisIterator cloneIterator() {
        ClonedNodeListIterator clone = new ClonedNodeListIterator(this);
        return clone;
    }

    public DTMAxisIterator reset() {
        _index = 0;
        return this;
    }

    public void setMark() {
        _source.setMark();
    }

    public void gotoMark() {
        _source.gotoMark();
    }
}
