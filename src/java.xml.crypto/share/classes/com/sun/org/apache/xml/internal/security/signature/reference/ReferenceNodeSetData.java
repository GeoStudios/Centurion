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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.reference;

import java.util.Iterator;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * An abstract representation of a {@code ReferenceData} type containing a node-set.
 */
public interface ReferenceNodeSetData extends ReferenceData {

    /**
     * Returns a read-only iterator over the nodes contained in this
     * {@code NodeSetData} in
     * <a href="http://www.w3.org/TR/1999/REC-xpath-19991116#dt-document-order">
     * document order</a>. Attempts to modify the returned iterator
     * via the {@code remove} method throw
     * {@code UnsupportedOperationException}.
     *
     * @return an {@code Iterator} over the nodes in this
     *    {@code NodeSetData} in document order
     */
    Iterator<Node> iterator();

}
