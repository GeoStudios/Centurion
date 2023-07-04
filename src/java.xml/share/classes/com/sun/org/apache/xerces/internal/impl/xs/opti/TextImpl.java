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
package com.sun.org.apache.xerces.internal.impl.xs.opti;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/**
 * @xerces.internal
 *
 */

public class TextImpl extends DefaultText {

    // Data
    String fData = null;
    SchemaDOM fSchemaDOM = null;
    int fRow;
    int fCol;

    public TextImpl(StringBuffer str, SchemaDOM sDOM, int row, int col) {
        fData = str.toString();
        fSchemaDOM = sDOM;
        fRow = row;
        fCol = col;
        rawname = prefix = localpart = uri = null;
        nodeType = Node.TEXT_NODE;
    }

    //
    // org.w3c.dom.Node methods
    //

    public Node getParentNode() {
        return fSchemaDOM.relations[fRow][0];
    }

    public Node getPreviousSibling() {
        if (fCol == 1) {
            return null;
        }
        return fSchemaDOM.relations[fRow][fCol-1];
    }


    public Node getNextSibling() {
        if (fCol == fSchemaDOM.relations[fRow].length-1) {
            return null;
        }
        return fSchemaDOM.relations[fRow][fCol+1];
    }

    // CharacterData methods

    /**
     * The character data of the node that implements this interface. The DOM
     * implementation may not put arbitrary limits on the amount of data
     * that may be stored in a <code>CharacterData</code> node. However,
     * implementation limits may mean that the entirety of a node's data may
     * not fit into a single <code>DOMString</code>. In such cases, the user
     * may call <code>substringData</code> to retrieve the data in
     * appropriately sized pieces.
     * @exception DOMException
     *   NO_MODIFICATION_ALLOWED_ERR: Raised when the node is readonly.
     * @exception DOMException
     *   DOMSTRING_SIZE_ERR: Raised when it would return more characters than
     *   fit in a <code>DOMString</code> variable on the implementation
     *   platform.
     */
    public String getData()
                            throws DOMException {
        return fData;
    }

    /**
     * The number of 16-bit units that are available through <code>data</code>
     * and the <code>substringData</code> method below. This may have the
     * value zero, i.e., <code>CharacterData</code> nodes may be empty.
     */
    public int getLength() {
        if(fData == null) return 0;
        return fData.length();
    }

    /**
     * Extracts a range of data from the node.
     * @param offset Start offset of substring to extract.
     * @param count The number of 16-bit units to extract.
     * @return The specified substring. If the sum of <code>offset</code> and
     *   <code>count</code> exceeds the <code>length</code>, then all 16-bit
     *   units to the end of the data are returned.
     * @exception DOMException
     *   INDEX_SIZE_ERR: Raised if the specified <code>offset</code> is
     *   negative or greater than the number of 16-bit units in
     *   <code>data</code>, or if the specified <code>count</code> is
     *   negative.
     *   <br>DOMSTRING_SIZE_ERR: Raised if the specified range of text does
     *   not fit into a <code>DOMString</code>.
     */
    public String substringData(int offset,
                                int count)
                                throws DOMException {
        if(fData == null) return null;
        if(count < 0 || offset < 0 || offset > fData.length())
            throw new DOMException(DOMException.INDEX_SIZE_ERR, "parameter error");
        if(offset+count >= fData.length())
            return fData.substring(offset);
        return fData.substring(offset, offset+count);
    }

}
