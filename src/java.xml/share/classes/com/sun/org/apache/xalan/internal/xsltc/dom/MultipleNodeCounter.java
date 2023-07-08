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


import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.DOM;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.Translet;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.Axis;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
public abstract class MultipleNodeCounter extends NodeCounter {
    private DTMAxisIterator _precSiblings = null;

    public MultipleNodeCounter(Translet translet,
                               DOM document, DTMAxisIterator iterator) {
        super(translet, document, iterator);
    }

    public MultipleNodeCounter(Translet translet,
                               DOM document,
                               DTMAxisIterator iterator,
                               boolean hasFrom) {
        super(translet, document, iterator, hasFrom);
    }

    public NodeCounter setStartNode(int node) {
        _node = node;
        _nodeType = _document.getExpandedTypeID(node);
    _precSiblings = _document.getAxisIterator(Axis.PRECEDINGSIBLING);
        return this;
    }

    public String getCounter() {
        if (_value != Integer.MIN_VALUE) {
            //See Errata E24
            if (_value == 0) return "0";
            else if (Double.isNaN(_value)) return "NaN";
            else if (_value < 0 && Double.isInfinite(_value)) return "-Infinity";
            else if (Double.isInfinite(_value)) return "Infinity";
            else return formatNumbers((int)_value);
        }

        IntegerArray ancestors = new IntegerArray();

        // Gather all ancestors that do not match from pattern
        int next = _node;
        ancestors.add(next);            // include self
        while ((next = _document.getParent(next)) > END &&
               !matchesFrom(next)) {
            ancestors.add(next);
        }

        // Create an array of counters
        final int nAncestors = ancestors.cardinality();
        final int[] counters = new int[nAncestors];
        for (int i = 0; i < nAncestors; i++) {
            counters[i] = Integer.MIN_VALUE;
        }

        // Increment array of counters according to semantics
        for (int j = 0, i = nAncestors - 1; i >= 0 ; i--, j++) {
            final int counter = counters[j];
            final int ancestor = ancestors.at(i);

            if (matchesCount(ancestor)) {
                _precSiblings.setStartNode(ancestor);
                while ((next = _precSiblings.next()) != END) {
                    if (matchesCount(next)) {
                        counters[j] = (counters[j] == Integer.MIN_VALUE) ? 1
                            : counters[j] + 1;
                    }
                }
                // Count the node itself
                counters[j] = counters[j] == Integer.MIN_VALUE
                    ? 1
                    : counters[j] + 1;
            }
        }
        return formatNumbers(counters);
    }

    public static NodeCounter getDefaultNodeCounter(Translet translet,
                                                    DOM document,
                                                    DTMAxisIterator iterator) {
        return new DefaultMultipleNodeCounter(translet, document, iterator);
    }

    static class DefaultMultipleNodeCounter extends MultipleNodeCounter {
        public DefaultMultipleNodeCounter(Translet translet,
                                          DOM document,
                                          DTMAxisIterator iterator) {
            super(translet, document, iterator);
        }
    }
}
