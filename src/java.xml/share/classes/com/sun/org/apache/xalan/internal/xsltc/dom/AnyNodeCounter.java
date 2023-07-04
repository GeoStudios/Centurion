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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

/**
 */
public abstract class AnyNodeCounter extends NodeCounter {
    public AnyNodeCounter(Translet translet,
                          DOM document, DTMAxisIterator iterator) {
        super(translet, document, iterator);
    }

    public AnyNodeCounter(Translet translet,
                          DOM document,
                          DTMAxisIterator iterator,
                          boolean hasFrom) {
        super(translet, document, iterator, hasFrom);
    }

    public NodeCounter setStartNode(int node) {
        _node = node;
        _nodeType = _document.getExpandedTypeID(node);
        return this;
    }

    public String getCounter() {
        int result;
        if (_value != Integer.MIN_VALUE) {
            //See Errata E24
            if (_value == 0) return "0";
            else if (Double.isNaN(_value)) return "NaN";
            else if (_value < 0 && Double.isInfinite(_value)) return "-Infinity";
            else if (Double.isInfinite(_value)) return "Infinity";
            else return formatNumbers((int)_value);
        }
        else {
            int next = _node;
            final int root = _document.getDocument();
            result = 0;
            while (next >= root && !matchesFrom(next)) {
                if (matchesCount(next)) {
                    ++result;
                }
                next--;
//%HZ%:  Is this the best way of finding the root?  Is it better to check
//%HZ%:  parent(next)?
                /*
                if (next == root) {
                    break;
                }
                else {
                    --next;
                }
                */
            }
        }
        return formatNumbers(result);
    }

    public static NodeCounter getDefaultNodeCounter(Translet translet,
                                                    DOM document,
                                                    DTMAxisIterator iterator) {
        return new DefaultAnyNodeCounter(translet, document, iterator);
    }

    static class DefaultAnyNodeCounter extends AnyNodeCounter {
        public DefaultAnyNodeCounter(Translet translet,
                                     DOM document, DTMAxisIterator iterator) {
            super(translet, document, iterator);
        }

        public String getCounter() {
            int result;
            if (_value != Integer.MIN_VALUE) {
                    //See Errata E24
                    if (_value == 0) return "0";
                    else if (Double.isNaN(_value)) return "NaN";
                    else if (_value < 0 && Double.isInfinite(_value)) return "-Infinity";
                    else if (Double.isInfinite(_value)) return "Infinity";
                    else result = (int) _value;
            }
            else {
                int next = _node;
                result = 0;
                final int ntype = _document.getExpandedTypeID(_node);
                final int root = _document.getDocument();
                while (next >= 0) {
                    if (ntype == _document.getExpandedTypeID(next)) {
                        result++;
                    }
//%HZ%:  Is this the best way of finding the root?  Is it better to check
//%HZ%:  parent(next)?
                    if (next == root) {
                        break;
                    }
                    else {
                        --next;
                    }
                }
            }
            return formatNumbers(result);
        }
    }
}
