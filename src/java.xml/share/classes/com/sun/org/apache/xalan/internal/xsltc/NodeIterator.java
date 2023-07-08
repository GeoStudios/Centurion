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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc;


import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
public interface NodeIterator extends Cloneable {
    int END = DTM.NULL;

    /**
     * Callers should not call next() after it returns END.
     */
    int next();

    /**
     * Resets the iterator to the last start node.
     */
    NodeIterator reset();

    /**
     * Returns the number of elements in this iterator.
     */
    int getLast();

    /**
     * Returns the position of the current node in the set.
     */
    int getPosition();

    /**
     * Remembers the current node for the next call to gotoMark().
     */
    void setMark();

    /**
     * Restores the current node remembered by setMark().
     */
    void gotoMark();

    /**
     * Set start to END should 'close' the iterator,
     * i.e. subsequent call to next() should return END.
     */
    NodeIterator setStartNode(int node);

    /**
     * True if this iterator has a reversed axis.
     */
    boolean isReverse();

    /**
     * Returns a deep copy of this iterator.
     */
    NodeIterator cloneIterator();

    /**
     * Prevents or allows iterator restarts.
     */
    void setRestartable(boolean isRestartable);

}
