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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.dom;

import java.xml.share.classes.com.sun.org.w3c.dom.CharacterData;
import java.xml.share.classes.com.sun.org.w3c.dom.Comment;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Represents an XML (or HTML) comment.
 *
 * @xerces.internal
 *
 */
public class CommentImpl
    extends CharacterDataImpl
    implements CharacterData, Comment {

    //
    // Constants
    //

    /** Serialization version. */
    static final long serialVersionUID = -2685736833408134044L;

    //
    // Constructors
    //

    /** Factory constructor. */
    public CommentImpl(CoreDocumentImpl ownerDoc, String data) {
        super(ownerDoc, data);
    }

    //
    // Node methods
    //

    /**
     * A short integer indicating what type of node this is. The named
     * constants for this value are defined in the org.w3c.dom.Node interface.
     */
    public short getNodeType() {
        return Node.COMMENT_NODE;
    }

    /** Returns the node name. */
    public String getNodeName() {
        return "#comment";
    }

} // class CommentImpl
