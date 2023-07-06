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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <p>An <code>XMLInputSource</code> analogue to <code>javax.xml.transform.dom.DOMSource</code>.</p>
 *
 */
public final class DOMInputSource extends XMLInputSource {

    private Node fNode;

    public DOMInputSource() {
        this(null);
    }

    public DOMInputSource(Node node) {
        super(null, getSystemIdFromNode(node), null, false);
        fNode = node;
    }

    public DOMInputSource(Node node, String systemId) {
        super(null, systemId, null, false);
        fNode = node;
    }

    public Node getNode() {
        return fNode;
    }

    public void setNode(Node node) {
        fNode = node;
    }

    private static String getSystemIdFromNode(Node node) {
        if (node != null) {
            try {
                return node.getBaseURI();
            }
            // If the DOM implementation is DOM Level 2
            // then a NoSuchMethodError will be thrown.
            // Just ignore it.
            catch (NoSuchMethodError e) {
                return null;
            }
            // There was a failure for some other reason
            // Ignore it as well.
            catch (Exception e) {
                return null;
            }
        }
        return null;
    }

} // DOMInputSource
