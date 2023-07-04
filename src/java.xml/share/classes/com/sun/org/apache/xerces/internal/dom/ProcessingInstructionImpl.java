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
package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;

/**
 * Processing Instructions (PIs) permit documents to carry
 * processor-specific information alongside their actual content. PIs
 * are most common in XML, but they are supported in HTML as well.
 *
 * This class inherits from CharacterDataImpl to reuse its setNodeValue method.
 *
 * @xerces.internal
 *
 */
public class ProcessingInstructionImpl
    extends CharacterDataImpl
    implements ProcessingInstruction {

    //
    // Constants
    //

    /** Serialization version. */
    static final long serialVersionUID = 7554435174099981510L;

    //
    // Data
    //

    protected String target;

    //
    // Constructors
    //

    /** Factory constructor. */
    public ProcessingInstructionImpl(CoreDocumentImpl ownerDoc,
                                     String target, String data) {
        super(ownerDoc, data);
        this.target = target;
    }

    //
    // Node methods
    //

    /**
     * A short integer indicating what type of node this is. The named
     * constants for this value are defined in the org.w3c.dom.Node interface.
     */
    public short getNodeType() {
        return Node.PROCESSING_INSTRUCTION_NODE;
    }

    /**
     * Returns the target
     */
    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return target;
    }

    //
    // ProcessingInstruction methods
    //

    /**
     * A PI's "target" states what processor channel the PI's data
     * should be directed to. It is defined differently in HTML and XML.
     * <p>
     * In XML, a PI's "target" is the first (whitespace-delimited) token
     * following the "<?" token that begins the PI.
     * <p>
     * In HTML, target is always null.
     * <p>
     * Note that getNodeName is aliased to getTarget.
     */
    public String getTarget() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return target;

    } // getTarget():String

    /**
     * A PI's data content tells the processor what we actually want it
     * to do.  It is defined slightly differently in HTML and XML.
     * <p>
     * In XML, the data begins with the non-whitespace character
     * immediately after the target -- @see getTarget().
     * <p>
     * In HTML, the data begins with the character immediately after the
     * "&lt;?" token that begins the PI.
     * <p>
     * Note that getNodeValue is aliased to getData
     */
    public String getData() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return data;

    } // getData():String

    /**
     * Change the data content of this PI.
     * Note that setData is aliased to setNodeValue.
     * @see #getData().
     * @throws DOMException(NO_MODIFICATION_ALLOWED_ERR) if node is read-only.
     */
    public void setData(String data) {
        // Hand off to setNodeValue for code-reuse reasons (mutation
        // events, readonly protection, synchronizing, etc.)
        setNodeValue(data);
    } // setData(String)



   /**
     * Returns the absolute base URI of this node or null if the implementation
     * wasn't able to obtain an absolute URI. Note: If the URI is malformed, a
     * null is returned.
     *
     * @return The absolute base URI of this node or null.
     */
    public String getBaseURI() {

        if (needsSyncData()) {
            synchronizeData();
        }
        return ownerNode.getBaseURI();
    }


} // class ProcessingInstructionImpl
