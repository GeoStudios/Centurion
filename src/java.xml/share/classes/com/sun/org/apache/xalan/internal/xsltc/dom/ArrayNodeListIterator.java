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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

public class ArrayNodeListIterator implements DTMAxisIterator  {

    private int _pos = 0;

    private int _mark = 0;

    private int[] _nodes;

    private static final int[] EMPTY = { };

    public ArrayNodeListIterator(int[] nodes) {
        _nodes = nodes;
    }

    public int next() {
        return _pos < _nodes.length ? _nodes[_pos++] : END;
    }

    public DTMAxisIterator reset() {
        _pos = 0;
        return this;
    }

    public int getLast() {
        return _nodes.length;
    }

    public int getPosition() {
        return _pos;
    }

    public void setMark() {
        _mark = _pos;
    }

    public void gotoMark() {
        _pos = _mark;
    }

    public DTMAxisIterator setStartNode(int node) {
        if (node == END) _nodes = EMPTY;
        return this;
    }

    public int getStartNode() {
        return END;
    }

    public boolean isReverse() {
        return false;
    }

    public DTMAxisIterator cloneIterator() {
        return new ArrayNodeListIterator(_nodes);
    }

    public void setRestartable(boolean isRestartable) {
    }

    public int getNodeByPosition(int position) {
        return _nodes[position - 1];
    }

}
