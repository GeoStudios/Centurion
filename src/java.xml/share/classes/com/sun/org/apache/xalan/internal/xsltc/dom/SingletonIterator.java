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















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
public class SingletonIterator extends DTMAxisIteratorBase {
    private int _node;
    private final boolean _isConstant;

    public SingletonIterator() {
        this(Integer.MIN_VALUE, false);
    }

    public SingletonIterator(int node) {
        this(node, false);
    }

    public SingletonIterator(int node, boolean constant) {
        _node = _startNode = node;
        _isConstant = constant;
    }

    /**
     * Override the value of <tt>_node</tt> only when this
     * object was constructed using the empty constructor.
     */
    public DTMAxisIterator setStartNode(int node) {
        if (_isConstant) {
            _node = _startNode;
            return resetPosition();
        }
        else if (_isRestartable) {
            if (_node <= 0)
                _node = _startNode = node;
            return resetPosition();
        }
        return this;
    }

    public DTMAxisIterator reset() {
        if (_isConstant) {
            _node = _startNode;
            return resetPosition();
        }
        else {
            final boolean temp = _isRestartable;
            _isRestartable = true;
            setStartNode(_startNode);
            _isRestartable = temp;
        }
        return this;
    }

    public int next() {
        final int result = _node;
        _node = DTMAxisIterator.END;
        return returnNode(result);
    }

    public void setMark() {
        _markedNode = _node;
    }

    public void gotoMark() {
        _node = _markedNode;
    }
}
