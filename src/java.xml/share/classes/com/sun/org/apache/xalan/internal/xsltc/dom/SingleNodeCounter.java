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
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.Axis;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 */
public abstract class SingleNodeCounter extends NodeCounter {
    static private final int[] EmptyArray = new int[] { };
    DTMAxisIterator _countSiblings = null;

    public SingleNodeCounter(Translet translet,
                             DOM document,
                             DTMAxisIterator iterator) {
        super(translet, document, iterator);
    }

    public SingleNodeCounter(Translet translet,
                             DOM document,
                             DTMAxisIterator iterator,
                             boolean hasFrom) {
        super(translet, document, iterator, hasFrom);
    }

    public NodeCounter setStartNode(int node) {
        _node = node;
        _nodeType = _document.getExpandedTypeID(node);
    _countSiblings = _document.getAxisIterator(Axis.PRECEDINGSIBLING);
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
                else result = (int) _value;
        }
        else {
            int next = _node;
            result = 0;
            boolean matchesCount = matchesCount(next);

            if (!matchesCount) {
                while ((next = _document.getParent(next)) > END) {
                    if (matchesCount(next)) {
                        break;          // found target
                    }
                    if (matchesFrom(next)) {
                        next = END;
                        break;          // no target found
                    }
                }
            }

            if (next != END) {
                int from = next;

                if (!matchesCount && _hasFrom) {
                    // Target found, but need to check if ancestor matches from
                    while ((from = _document.getParent(from)) > END) {
                        if (matchesFrom(from)) {
                            break;          // found from
                        }
                    }
                }

                // Have we found ancestor matching from?
                if (from != END) {
                    _countSiblings.setStartNode(next);
                    do {
                        if (matchesCount(next)) result++;
                    } while ((next = _countSiblings.next()) != END);

                    return formatNumbers(result);
                }
            }

            // If no target found then pass the empty list
            return formatNumbers(EmptyArray);
        }
        return formatNumbers(result);
    }

    public static NodeCounter getDefaultNodeCounter(Translet translet,
                                                    DOM document,
                                                    DTMAxisIterator iterator) {
        return new DefaultSingleNodeCounter(translet, document, iterator);
    }

    static class DefaultSingleNodeCounter extends SingleNodeCounter {
        public DefaultSingleNodeCounter(Translet translet,
                                        DOM document, DTMAxisIterator iterator) {
            super(translet, document, iterator);
        }

        public NodeCounter setStartNode(int node) {
            _node = node;
            _nodeType = _document.getExpandedTypeID(node);
            _countSiblings =
        _document.getTypedAxisIterator(Axis.PRECEDINGSIBLING,
                                               _document.getExpandedTypeID(node));
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
                else result = (int) _value;
            }
            else {
                int next;
                result = 1;
                _countSiblings.setStartNode(_node);
                while ((next = _countSiblings.next()) != END) {
                    result++;
                }
            }
            return formatNumbers(result);
        }
    }
}
